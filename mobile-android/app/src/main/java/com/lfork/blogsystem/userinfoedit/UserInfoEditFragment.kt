package com.lfork.blogsystem.userinfoedit

import android.app.Activity.RESULT_OK
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lfork.blogsystem.R
import com.lfork.blogsystem.base.viewmodel.Navigator
import com.lfork.blogsystem.databinding.UserInfoeditFragBinding
import com.lfork.blogsystem.utils.ToastUtil
import android.content.Intent
import android.text.TextUtils
import android.widget.EditText
import kotlinx.android.synthetic.main.user_infoedit_frag.view.*
import com.lfork.blogsystem.base.databinding.ImageBinding
import com.lfork.blogsystem.utils.PermissionManager.isGrantedCameraPermission
import com.lfork.blogsystem.utils.PermissionManager.isGrantedStoragePermission
import com.lfork.blogsystem.utils.PermissionManager.requestCameraPermission
import com.lfork.blogsystem.utils.PermissionManager.requestStoragePermission
import com.yalantis.ucrop.UCrop
import com.zhihu.matisse.Matisse
import kotlinx.android.synthetic.main.user_infoedit_frag.*
import java.io.File
import com.lfork.blogsystem.base.image.ImageTool
import com.lfork.blogsystem.base.image.ImageTool.cutPicture
import com.yalantis.ucrop.util.FileUtils


class UserInfoEditFragment : Fragment(), Navigator {

    private val REQUEST_CODE_CHOOSE = 0

    private val REQUEST_CODE_STORAGE_PERMISSION = 1

    private val REQUEST_CODE_CAMERA_PERMISSION = 2

    override fun showTips(msg: String?) {
        ToastUtil.showShort(context, msg)
    }

    companion object {
        fun newInstance() = UserInfoEditFragment()
    }

    private lateinit var binding: UserInfoeditFragBinding

    private lateinit var viewModel: UserInfoEditViewModel

    private var root: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (root == null) {
            root = inflater.inflate(R.layout.user_infoedit_frag, container, false)
            viewModel = ViewModelProviders.of(this).get(UserInfoEditViewModel::class.java)
            viewModel.registerNavigator(this)
            binding = UserInfoeditFragBinding.bind(root!!)
            binding.viewModel = viewModel
            registerBtnListener(root!!)
        }
        return root as View
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserInfoEditViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshUserInfo()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.unregisterNavigator()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQUEST_CODE_CHOOSE -> {
                if (resultCode == RESULT_OK) {
                    //只选择1张图片来剪切
                    cutPicture(Matisse.obtainResult(data)[0], activity!!)
                }
            }

            UCrop.REQUEST_CROP ->
                if (data != null) {
                    val resultUri = UCrop.getOutput(data);  //返回的是file类型的uri
                    if (resultUri != null) {
                        uploadPortrait(File(resultUri.path))
                    } else {
                        ToastUtil.showShort(context, "图片剪裁失败");
                    }
                }

            UCrop.RESULT_ERROR ->
                ToastUtil.showShort(context, "剪切失败");
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_STORAGE_PERMISSION -> {
                if (isGrantedStoragePermission(context!!)) {
                    selectPicture()
                } else {
                    ToastUtil.showShort(context!!, "请授予本地储存的权限")
                }
            }

            REQUEST_CODE_CAMERA_PERMISSION -> {
                if (isGrantedCameraPermission(context!!)) {
                    selectPicture()
                } else {
                    ToastUtil.showShort(context!!, "请授予拍照的权限")
                }
            }
        }
    }


    private fun registerBtnListener(root: View) {
        root.item_change_nickname.setOnClickListener { openChangeNickNameDialog() }
        root.item_change_portrait.setOnClickListener { selectPicture() }

    }

    private fun openChangeNickNameDialog() {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        val builder = AlertDialog.Builder(context!!)

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setTitle(R.string.change_nickname)

        val inputView = EditText(context)
        builder.setView(inputView)
        inputView.setText(viewModel.username.get())

        // Add the buttons
        builder.setPositiveButton(R.string.ok) { dialog, id ->
            val newName = inputView.text.toString()
            if (TextUtils.isEmpty(newName)) {
                ToastUtil.showShort(context!!, "Nickname cannot be null")

            } else {
                viewModel.username.set(newName)
                viewModel.updateUserInfo()
            }
        }
        //do nothing
        builder.setNegativeButton(R.string.cancel) { dialog, id -> }
        // 3. Get the AlertDialog from create()
        val dialog = builder.create()
        dialog.show()
    }


    //图片本地处理：选图片，然后再activity result里面调用剪切图片的函数
    private fun selectPicture() {
        if (!isGrantedStoragePermission(context!!)) {
            requestStoragePermission(REQUEST_CODE_STORAGE_PERMISSION)
            return
        }

        if (!isGrantedCameraPermission(context!!)) {
            requestCameraPermission(REQUEST_CODE_CAMERA_PERMISSION)
            return
        }
        ImageTool.selectPicture(activity!!, REQUEST_CODE_CHOOSE)
    }

    //上传图片
    private fun uploadPortrait(pic: File?) {

        if (pic == null) {
            return
        }


        ImageBinding.setImageNoCache(user_portrait, pic)
        //调用上传图片的接口 得到图片URL地址 清除本地缓存信息 //显示新头像
        viewModel.uploadPortrait(pic)
    }

}
