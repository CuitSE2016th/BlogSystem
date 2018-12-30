package com.lfork.blogsystem.articleedit

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import com.chinalwb.are.AREditText
import com.chinalwb.are.Constants
import com.chinalwb.are.android.inner.Html
import com.chinalwb.are.spans.AreImageSpan
import com.chinalwb.are.strategies.ImageStrategy
import com.chinalwb.are.styles.toolbar.ARE_ToolbarDefault
import com.chinalwb.are.styles.toolbar.IARE_Toolbar
import com.chinalwb.are.styles.toolitems.*
import com.chinalwb.are.styles.toolitems.styles.ARE_Style_Image
import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.R
import com.lfork.blogsystem.common.Config
import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.data.article.ArticleDataRepository
import com.lfork.blogsystem.utils.ToastUtil
import com.lfork.blogsystem.utils.UriHelper
import com.lfork.blogsystem.utils.setupToolBar
import kotlinx.android.synthetic.main.article_edit_act.*
import java.io.File


class ArticleEditorActivity : AppCompatActivity() {

    private var mToolbar: IARE_Toolbar? = null

    private var androidToolbar: Toolbar? = null

    private var mEditText: AREditText? = null

    private var scrollerAtEnd: Boolean = false

    private val imageStrategy = ArticleImageStrategy()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.article_edit_act)

        initAndroidToolBar()

        initEditorToolbar()
    }

    private fun initAndroidToolBar() {
        androidToolbar = toolbar
        setupToolBar(androidToolbar!!, "0 word")

    }

    private fun initEditorToolbar() {
        mToolbar = areToolbar
        val bold = ARE_ToolItem_Bold()
        val italic = ARE_ToolItem_Italic()
        val underline = ARE_ToolItem_Underline()
        val strikethrough = ARE_ToolItem_Strikethrough()
        val fontSize = ARE_ToolItem_FontSize()
        val quote = ARE_ToolItem_Quote()
        val listNumber = ARE_ToolItem_ListNumber()
        val listBullet = ARE_ToolItem_ListBullet()
        val hr = ARE_ToolItem_Hr()
        val link = ARE_ToolItem_Link()
        val subscript = ARE_ToolItem_Subscript()
        val superscript = ARE_ToolItem_Superscript()
        val left = ARE_ToolItem_AlignmentLeft()
        val center = ARE_ToolItem_AlignmentCenter()
        val right = ARE_ToolItem_AlignmentRight()
        val image = ARE_ToolItem_Image()
        val video = ARE_ToolItem_Video()
        val at = ARE_ToolItem_At()

        mToolbar!!.addToolbarItem(bold)
        mToolbar!!.addToolbarItem(italic)
        mToolbar!!.addToolbarItem(underline)
        mToolbar!!.addToolbarItem(strikethrough)
        mToolbar!!.addToolbarItem(fontSize)
        mToolbar!!.addToolbarItem(quote)
        mToolbar!!.addToolbarItem(listNumber)
        mToolbar!!.addToolbarItem(listBullet)
        mToolbar!!.addToolbarItem(hr)
        mToolbar!!.addToolbarItem(link)
        mToolbar!!.addToolbarItem(subscript)
        mToolbar!!.addToolbarItem(superscript)
        mToolbar!!.addToolbarItem(left)
        mToolbar!!.addToolbarItem(center)
        mToolbar!!.addToolbarItem(right)
        mToolbar!!.addToolbarItem(image)
//        mToolbar!!.addToolbarItem(video)
        mToolbar!!.addToolbarItem(at)

        mEditText = this.findViewById(R.id.arEditText)
        mEditText!!.setToolbar(mToolbar!!)
        mEditText!!.imageStrategy = imageStrategy

        setHtml()

        initToolbarArrow()
    }

    private fun setHtml() {
        val html = "<p style=\"text-align: center;\"><strong>New Feature in 0.1.4</strong></p>\n" +
                "<p style=\"text-align: center;\">&nbsp;</p>\n" +
                "<p style=\"text-align: left;\"><span style=\"color: #3366ff;\">In this release, you have a new usage with ARE.</span></p>\n" +
                "<p style=\"text-align: left;\">&nbsp;</p>\n" +
                "<p style=\"text-align: left;\"><span style=\"color: #3366ff;\">AREditText + ARE_Toolbar, you are now able to control the position of the input area and where to put the toolbar at and, what ToolItems you'd like to have in the toolbar. </span></p>\n" +
                "<p style=\"text-align: left;\">&nbsp;</p>\n" +
                "<p style=\"text-align: left;\"><span style=\"color: #3366ff;\">You can not only define the Toolbar (and it's style), you can also add your own ARE_ToolItem with your style into ARE.</span></p>\n" +
                "<p style=\"text-align: left;\">&nbsp;</p>\n" +
                "<p style=\"text-align: left;\"><span style=\"color: #3366ff;\">Fixed app color override bug; edit mode click picture won't open keyboard.</span></p>\n" +
                "<p style=\"text-align: left;\">&nbsp;</p>\n" +
                "<p style=\"text-align: left;\"><span style=\"color: #ff00ff;\"><em><strong>Why not give it a try now?</strong></em></span></p>"
        mEditText!!.fromHtml(html)
    }

    private fun initToolbarArrow() {
        val imageView = this.findViewById<ImageView>(R.id.arrow)
        if (this.mToolbar is ARE_ToolbarDefault) {
            (mToolbar as ARE_ToolbarDefault).viewTreeObserver.addOnScrollChangedListener {
                val scrollX = (mToolbar as ARE_ToolbarDefault).scrollX
                val scrollWidth = (mToolbar as ARE_ToolbarDefault).width
                val fullWidth = (mToolbar as ARE_ToolbarDefault).getChildAt(0).width

                if (scrollX + scrollWidth < fullWidth) {
                    imageView.setImageResource(R.drawable.arrow_right)
                    scrollerAtEnd = false
                } else {
                    imageView.setImageResource(R.drawable.arrow_left)
                    scrollerAtEnd = true
                }
            }
        }

        imageView.setOnClickListener {
            if (scrollerAtEnd) {
                (mToolbar as ARE_ToolbarDefault).smoothScrollBy(-Integer.MAX_VALUE, 0)
                scrollerAtEnd = false
            } else {
                val hsWidth = (mToolbar as ARE_ToolbarDefault).getChildAt(0).width
                (mToolbar as ARE_ToolbarDefault).smoothScrollBy(hsWidth, 0)
                scrollerAtEnd = true
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            com.chinalwb.are.R.id.action_save -> {
                val content = this.mEditText!!.getHtmlBody()
                Log.d("生成的HTML文本", content)
                //            DemoUtil.saveHtml(this, html);
                publishArticle(article_title.text.toString(), content)
                return true
            }
        }
        //        if (menuId == R.id.action_show_tv) {
        //            String html = this.mEditText.getHtml();
        //            Intent intent = new Intent(this, TextViewActivity.class);
        //            intent.putExtra(HTML_TEXT, html);
        //            startActivity(intent);
        //            return true;
        //        }
        return super.onOptionsItemSelected(item)
    }

    private fun publishArticle(title: String, content: String) {
        ArticleDataRepository.publishOrEditArticle(
            BlogApplication.token!!,
            title,
            content,
            object : DataCallback<String> {
                override fun succeed(data: String) {
                    ToastUtil.showShort(this@ArticleEditorActivity, "发布成功")

                }

                override fun failed(code: Int, log: String) {
                }
            });
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        mToolbar!!.onActivityResult(requestCode, resultCode, data)
    }


    /**
     * upload
     */
    fun getHtmlText() {

    }

    var dialog : ProgressDialog?=null

    private fun showUploadingDialog() {
        dialog = ProgressDialog.show(
            this,
            "",
            "Uploading image. Please wait...",
            true)
    }


    private fun dismissUploadingDialog() {
        dialog?.dismiss()
    }

    inner class ArticleImageStrategy : ImageStrategy {

       private var areStyleImage:ARE_Style_Image?=null

        val callback = object : DataCallback<ArrayList<String>> {
            override fun succeed(data: ArrayList<String>) {
                dismissUploadingDialog()
                var url:String?=data[0]
                if (data[0].contains("webapps")){
                    url = ((Config.ServerPath +  data[0].substring(data[0].indexOf("images"))) as String).replace( "\\",   "/");
                }

                areStyleImage?.insertImage(url, AreImageSpan.ImageType.URL)
                areStyleImage = null
            }

            override fun failed(code: Int, log: String) {
                dismissUploadingDialog()
                areStyleImage = null
            }
        }


        override fun uploadAndInsertImage(uri: Uri?, areStyleImage: ARE_Style_Image?) {
            this.areStyleImage = areStyleImage
            showUploadingDialog()
            val  realUrl = UriHelper.getPath(this@ArticleEditorActivity,uri)
            ArticleDataRepository.uploadArticleImages(BlogApplication.token!!, File(realUrl),callback)

        }

    }

    companion object {
        fun AREditText.getHtmlBody():String{
            val html = StringBuffer()
//            html.append("<html><body>")
            val editTextHtml = Html.toHtml(editableText, Html.TO_HTML_PARAGRAPH_LINES_INDIVIDUAL)
            html.append(editTextHtml)
//            html.append("</body></html>")
            val htmlContent =
                html.toString().replace(Constants.ZERO_WIDTH_SPACE_STR_ESCAPE.toRegex(), "")
            println(htmlContent)
            return htmlContent
        }
    }
}
