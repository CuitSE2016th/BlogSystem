package com.lfork.blogsystem.register

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.support.design.widget.Snackbar
import android.app.LoaderManager.LoaderCallbacks
import android.content.CursorLoader
import android.content.Loader
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.TextView

import java.util.ArrayList
import android.Manifest.permission.READ_CONTACTS
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.lfork.blogsystem.R
import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.data.user.UserDataRepository
import com.lfork.blogsystem.utils.StringValidation.isPhoneValid
import com.lfork.blogsystem.utils.ToastUtil
import com.lfork.blogsystem.utils.setupToolBar

import kotlinx.android.synthetic.main.register_act.*

/**
 * A login screen that offers login via email/password.
 */
class RegisterActivity : AppCompatActivity(), LoaderCallbacks<Cursor> {


    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private var mRegisterTask: UserDataRepository? = null

    private var mVerificationCodeTask: UserDataRepository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_act)

        setupToolBar(toolbar, resources.getString(R.string.title_activity_register))
        // Set up the login form.
        populateAutoComplete()
        password.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptRegister()
                return@OnEditorActionListener true
            }
            false
        })

        register_button.setOnClickListener { attemptRegister() }
        get_verification_code_button.setOnClickListener { attemptGetVerificationCode() }

    }

    private fun populateAutoComplete() {
        if (!mayRequestContacts()) {
            return
        }

        loaderManager.initLoader(0, null, this)
    }

    private fun mayRequestContacts(): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(account, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                .setAction(android.R.string.ok,
                    { requestPermissions(arrayOf(READ_CONTACTS), REQUEST_READ_CONTACTS) })
        } else {
            requestPermissions(arrayOf(READ_CONTACTS), REQUEST_READ_CONTACTS)
        }
        return false
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete()
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private fun attemptRegister() {
        if (mRegisterTask != null) {
            return
        }

        // Reset errors.
        account.error = null
        password.error = null

        // Store values at the createTime of the login attempt.
        val accountStr = account.text.toString()
        val passwordStr = password.text.toString()
        val passwordSecondStr = password_second.text.toString()
        val verificationCodeStr = verification_code.text.toString()

        var cancel = false
        var focusView: View? = null


        // Check for a valid second password
        if (TextUtils.isEmpty(passwordSecondStr)) {
            password_second.error = getString(R.string.error_field_required)
            focusView = password_second
            cancel = true
        } else if (!isPasswordValid(passwordSecondStr)) {
            password_second.error = getString(R.string.error_invalid_password)
            focusView = password_second
            cancel = true
        } else if (passwordStr != passwordSecondStr) {
            password_second.error = getString(R.string.error_different_password)
            focusView = password_second
            cancel = true
        }

        // Check for a valid password
        if (TextUtils.isEmpty(passwordStr)) {
            password.error = getString(R.string.error_field_required)
            focusView = password
            cancel = true
        } else if (!isPasswordValid(passwordStr)) {
            password.error = getString(R.string.error_invalid_password)
            focusView = password
            cancel = true
        }

        // Check for a verification code
        if (TextUtils.isEmpty(verificationCodeStr)) {
            password.error = getString(R.string.error_field_required)
            focusView = verification_code
            cancel = true
        }

        // Check for a valid account address.
        if (TextUtils.isEmpty(accountStr)) {
            account.error = getString(R.string.error_field_required)
            focusView = account
            cancel = true
        } else if (!isEmailValid(accountStr) && !isPhoneValid(accountStr)) {
            account.error = getString(R.string.error_invalid_account)
            focusView = account
            cancel = true
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView?.requestFocus()
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true)
            mRegisterTask = UserDataRepository
            mRegisterTask?.register(accountStr, passwordStr, verificationCodeStr, object :
                DataCallback<String> {
                override fun succeed(data: String) {
                    ToastUtil.showLong(applicationContext, "注册成功")
                    finish()
                }

                override fun failed(code: Int, log: String) {
                    ToastUtil.showLong(applicationContext, log)
                    mRegisterTask = null
                    showProgress(false)
                }
            })
        }
    }

    private fun attemptGetVerificationCode() {
        if (mVerificationCodeTask != null) {
            return
        }


        BlogApplication.doAsyncTask {
            val countDownSeconds = 60
            for (i in 1..countDownSeconds) {
                Thread.sleep(1000)
                runOnUiThread {
                    get_verification_code_button.text = "Get Again($i)"
                }
            }
            runOnUiThread {
                get_verification_code_button.text = getText(R.string.action_get_verification_code)
            }
            mVerificationCodeTask = null
        }

        val accountStr = account.text.toString()

        mVerificationCodeTask = UserDataRepository
        mVerificationCodeTask?.getVerificationCode(accountStr, object :
            DataCallback<String> {
            override fun succeed(data: String) {
                ToastUtil.showLong(this@RegisterActivity, data)
            }

            override fun failed(code: Int, log: String) {
                ToastUtil.showLong(this@RegisterActivity, log)
            }
        })
    }

    private fun isEmailValid(email: String): Boolean {
        //TODO: Replace this with your own logic
        return email.contains("@")
    }

    private fun isPasswordValid(password: String): Boolean {
        //TODO: Replace this with your own logic
        return password.length > 4
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private fun showProgress(show: Boolean) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

            login_form.visibility = if (show) View.GONE else View.VISIBLE
            login_form.animate()
                .setDuration(shortAnimTime)
                .alpha((if (show) 0 else 1).toFloat())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        login_form.visibility = if (show) View.GONE else View.VISIBLE
                    }
                })

            login_progress.visibility = if (show) View.VISIBLE else View.GONE
            login_progress.animate()
                .setDuration(shortAnimTime)
                .alpha((if (show) 1 else 0).toFloat())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        login_progress.visibility = if (show) View.VISIBLE else View.GONE
                    }
                })
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            login_progress.visibility = if (show) View.VISIBLE else View.GONE
            login_form.visibility = if (show) View.GONE else View.VISIBLE
        }
    }

    override fun onCreateLoader(i: Int, bundle: Bundle?): Loader<Cursor> {
        return CursorLoader(
            this,
            // Retrieve data rows for the device user's 'profile' contact.
            Uri.withAppendedPath(
                ContactsContract.Profile.CONTENT_URI,
                ContactsContract.Contacts.Data.CONTENT_DIRECTORY
            ), ProfileQuery.PROJECTION,

            // Select only email addresses.
            ContactsContract.Contacts.Data.MIMETYPE + " = ?", arrayOf(
                ContactsContract.CommonDataKinds.Email
                    .CONTENT_ITEM_TYPE
            ),

            // Show primary email addresses first. Note that there won't be
            // a primary email address if the user hasn't specified one.
            ContactsContract.Contacts.Data.IS_PRIMARY + " DESC"
        )
    }

    override fun onLoadFinished(cursorLoader: Loader<Cursor>, cursor: Cursor) {
        val emails = ArrayList<String>()
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS))
            cursor.moveToNext()
        }

        addEmailsToAutoComplete(emails)
    }

    override fun onLoaderReset(cursorLoader: Loader<Cursor>) {

    }

    private fun addEmailsToAutoComplete(emailAddressCollection: List<String>) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        val adapter = ArrayAdapter(
            this@RegisterActivity,
            android.R.layout.simple_dropdown_item_1line, emailAddressCollection
        )

        account.setAdapter(adapter)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    object ProfileQuery {
        val PROJECTION = arrayOf(
            ContactsContract.CommonDataKinds.Email.ADDRESS,
            ContactsContract.CommonDataKinds.Email.IS_PRIMARY
        )
        val ADDRESS = 0
        val IS_PRIMARY = 1
    }


    companion object {
        /**
         * Id to identity READ_CONTACTS permission request.
         */
        private val REQUEST_READ_CONTACTS = 0

        /**
         * A dummy authentication store containing known user names and passwords.
         * TODO: remove after connecting to a real authentication system.
         */
        private val DUMMY_CREDENTIALS = arrayOf("foo@example.com:hello", "bar@example.com:world")

        fun startRegisterActivity(context: Context) {
            val intent = Intent(context, RegisterActivity::class.java)
            context.startActivity(intent)
        }
    }
}
