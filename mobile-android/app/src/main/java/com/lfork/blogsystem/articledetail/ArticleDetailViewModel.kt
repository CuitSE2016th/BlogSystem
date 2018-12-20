package com.lfork.blogsystem.articledetail

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.databinding.ObservableInt
import com.lfork.blogsystem.BlogApplication
import com.lfork.blogsystem.R
import com.lfork.blogsystem.base.viewmodel.Navigator
import com.lfork.blogsystem.data.DataCallback
import com.lfork.blogsystem.data.user.User
import com.lfork.blogsystem.data.user.UserDataRepository

class ArticleDetailViewModel : ViewModel() {
    var account: String?=null

    val title = ObservableField<String>("Rational Unified Process")

    val portraitUrl = ObservableField<String>("")

    val username = ObservableField<String>("L.Fork")

    val wordCount = ObservableField<String>("word count:100")

    val readingNumber = ObservableField<String>("Reading Number:200")

    val time = ObservableField<String>("2018/12/18")

    val like = ObservableField<String>("Likes(200K)")

    val star = ObservableField<String>("Star(100)")

    val placeDrawableId = ObservableInt(R.drawable.ic_person_black_24dp)

    var navigator: Navigator? = null

    fun registerNavigator(navigator: Navigator) {
        this.navigator = navigator
    }

    fun unregisterNavigator() {
        navigator = null
    }

    fun refreshUserInfo() {
        if (account == null)
            account = UserDataRepository.userCache.getAccount()

        UserDataRepository.getUserInfo(
            account!!, BlogApplication.token!!,
            object : DataCallback<User> {
                override fun succeed(data: User) {
                    when {
                        data.nickname != null -> username.set(data.nickname)
                        data.email != null -> username.set(data.email)
                        data.phone != null -> username.set(data.phone)
                    }
                    portraitUrl.set(data.getRealPortraitUrl())
                }

                override fun failed(code: Int, log: String) {
                    navigator?.showTips(log)
                }
            })
    }

    val htmlTestData = ObservableField<String>("<html>\n" +
            "\n" +
            "<head>\n" +
            "<meta http-equiv=Content-Type content=\"text/html; charset=utf-8\">\n" +
            "<meta name=Generator content=\"Microsoft Word 15 (filtered)\">\n" +
            "<style>\n" +
            "\n" +
            "\n" +
            "</style>\n" +
            "\n" +
            "</head>\n" +
            "\n" +
            "<body lang=ZH-CN style='text-justify-trim:punctuation'>\n" +
            "\n" +
            "<div class=WordSection1 style='layout-grid:15.6pt'>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='text-align:left'><b><span lang=EN-US\n" +
            "style='font-size:24.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>Foreword</span></b></p>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='text-align:left'><b><span lang=EN-US\n" +
            "style='font-size:18.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>Reference\n" +
            "model</span></b></p>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-bottom:12.0pt;text-align:left'><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>RUP</span><span lang=EN-US style='font-size:13.5pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:black'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>iterative\n" +
            "development, incremental model, rapid prototyping mode, small waterfall flow\n" +
            "mode.</span><span lang=EN-US style='font-size:13.5pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:black'>&nbsp;<br>\n" +
            "<br>\n" +
            "</span><span lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>It can be said that it is a bit close to agile development, but\n" +
            "our communication is a problem, so it is actually difficult to adopt agile\n" +
            "development.</span><span lang=EN-US style='font-size:13.5pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:black'>&nbsp;<br>\n" +
            "</span><span lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>Because agile development requires strong team communication, I\n" +
            "don’t think it’s easy to achieve.</span><span lang=EN-US style='font-size:13.5pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:black'>&nbsp;</span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>I</span><span lang=EN-US style='font-size:13.5pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:black'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>think</span><span\n" +
            "lang=EN-US style='font-size:13.5pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:black'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>I</span><span lang=EN-US\n" +
            "style='font-size:13.5pt;font-family:\"Bahnschrift Light\",sans-serif;color:black'>&nbsp;</span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>still need to write some necessary</span><span lang=EN-US\n" +
            "style='font-size:13.5pt;font-family:\"Bahnschrift Light\",sans-serif;color:black'>&nbsp;<br>\n" +
            "</span><span lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>Documentation to reduce communication costs</span></p>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='text-align:left'><b><span lang=EN-US\n" +
            "style='font-size:24.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>Development\n" +
            "Process</span></b></p>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='text-align:left'><b><span lang=EN-US\n" +
            "style='font-size:18.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>First\n" +
            "iteration:</span></b></p>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:5.0pt;margin-right:0cm;\n" +
            "margin-bottom:5.0pt;margin-left:25.8pt;text-align:left;text-indent:0cm'><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:Symbol;color:#24292E'>·<span\n" +
            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp; </span></span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>-&gt;</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>System\n" +
            "Level Diagram</span><span lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>(</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>Overall Design\n" +
            "Startup</span><span lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>)</span></p>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:3.0pt;margin-right:0cm;\n" +
            "margin-bottom:5.0pt;margin-left:25.8pt;text-align:left;text-indent:0cm'><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:Symbol;color:#24292E'>·<span\n" +
            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp; </span></span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>-&gt;</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>prototype\n" +
            "of a module</span><span lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>(UI</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>brief design</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>)</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>[must]</span></p>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:3.0pt;margin-right:0cm;\n" +
            "margin-bottom:5.0pt;margin-left:25.8pt;text-align:left;text-indent:0cm'><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:Symbol;color:#24292E'>·<span\n" +
            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp; </span></span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>-&gt;</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>CIM</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>and</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>PIM of</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>a module</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>(</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>can be simplified</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>)</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>,</span></p>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:3.0pt;margin-right:0cm;\n" +
            "margin-bottom:5.0pt;margin-left:25.8pt;text-align:left;text-indent:0cm'><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:Symbol;color:#24292E'>·<span\n" +
            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp; </span></span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>-&gt;</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>Database\n" +
            "design of a module</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>(ER</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>diagram</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>)</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>[Must],</span></p>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:3.0pt;margin-right:0cm;\n" +
            "margin-bottom:5.0pt;margin-left:25.8pt;text-align:left;text-indent:0cm'><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:Symbol;color:#24292E'>·<span\n" +
            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp; </span></span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>-&gt;</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>Interface\n" +
            "design of a module [must]</span></p>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:3.0pt;margin-right:0cm;\n" +
            "margin-bottom:5.0pt;margin-left:25.8pt;text-align:left;text-indent:0cm'><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:Symbol;color:#24292E'>·<span\n" +
            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp; </span></span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>-&gt;</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>Development</span></p>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:3.0pt;margin-right:0cm;\n" +
            "margin-bottom:5.0pt;margin-left:25.8pt;text-align:left;text-indent:0cm'><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:Symbol;color:#24292E'>·<span\n" +
            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp; </span></span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>-&gt;</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>Unit\n" +
            "test</span></p>\n" +
            "\n" +
            "<div style='border:none;border-bottom:solid #EAECEF 1.0pt;padding:0cm 0cm 4.0pt 0cm'>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:18.0pt;margin-right:0cm;\n" +
            "margin-bottom:12.0pt;margin-left:0cm;text-align:left;border:none;padding:0cm'><b><span\n" +
            "lang=EN-US style='font-size:18.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>The second iteration:</span></b></p>\n" +
            "\n" +
            "</div>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:5.0pt;margin-right:0cm;\n" +
            "margin-bottom:5.0pt;margin-left:25.8pt;text-align:left;text-indent:0cm'><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:Symbol;color:#24292E'>·<span\n" +
            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp; </span></span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>-&gt;</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>prototype\n" +
            "of a module</span><span lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>(UI</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>brief design</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>)</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>[must]</span></p>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:3.0pt;margin-right:0cm;\n" +
            "margin-bottom:5.0pt;margin-left:25.8pt;text-align:left;text-indent:0cm'><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:Symbol;color:#24292E'>·<span\n" +
            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp; </span></span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>-&gt;</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>CIM</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>and</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>PIM of</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>a module</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>(</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>can be simplified</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>)</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>,</span></p>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:3.0pt;margin-right:0cm;\n" +
            "margin-bottom:5.0pt;margin-left:25.8pt;text-align:left;text-indent:0cm'><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:Symbol;color:#24292E'>·<span\n" +
            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp; </span></span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>-&gt;</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>Database\n" +
            "design of a module</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>(ER</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>diagram</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>)</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>[Must],</span></p>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:3.0pt;margin-right:0cm;\n" +
            "margin-bottom:5.0pt;margin-left:25.8pt;text-align:left;text-indent:0cm'><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:Symbol;color:#24292E'>·<span\n" +
            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp; </span></span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>-&gt;</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>Interface\n" +
            "design of a module [must]</span></p>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:3.0pt;margin-right:0cm;\n" +
            "margin-bottom:5.0pt;margin-left:25.8pt;text-align:left;text-indent:0cm'><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:Symbol;color:#24292E'>·<span\n" +
            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp; </span></span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>-&gt;</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>Development</span></p>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:3.0pt;margin-right:0cm;\n" +
            "margin-bottom:5.0pt;margin-left:25.8pt;text-align:left;text-indent:0cm'><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:Symbol;color:#24292E'>·<span\n" +
            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp; </span></span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>-&gt;</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>Unit\n" +
            "test</span></p>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:3.0pt;margin-right:0cm;\n" +
            "margin-bottom:5.0pt;margin-left:25.8pt;text-align:left;text-indent:0cm'><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:Symbol;color:#24292E'>·<span\n" +
            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp; </span></span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>-&gt;</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>Integration\n" +
            "test</span></p>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-bottom:12.0pt;text-align:left'><b><i><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>..........</span></i></b></p>\n" +
            "\n" +
            "<div style='border:none;border-bottom:solid #EAECEF 1.0pt;padding:0cm 0cm 4.0pt 0cm'>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:18.0pt;margin-right:0cm;\n" +
            "margin-bottom:12.0pt;margin-left:0cm;text-align:left;border:none;padding:0cm'><b><span\n" +
            "lang=EN-US style='font-size:18.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>N-1</span></b><span lang=EN-US style='font-size:13.5pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:black'>&nbsp;</span><b><span\n" +
            "lang=EN-US style='font-size:18.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>first</span></b><span lang=EN-US style='font-size:13.5pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:black'>&nbsp;</span><b><span\n" +
            "lang=EN-US style='font-size:18.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>iterations:</span></b></p>\n" +
            "\n" +
            "</div>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:5.0pt;margin-right:0cm;\n" +
            "margin-bottom:5.0pt;margin-left:25.8pt;text-align:left;text-indent:0cm'><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:Symbol;color:#24292E'>·<span\n" +
            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp; </span></span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>-&gt;</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>prototype\n" +
            "of a module</span><span lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>(UI</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>brief design</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>)</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>[must]</span></p>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:3.0pt;margin-right:0cm;\n" +
            "margin-bottom:5.0pt;margin-left:25.8pt;text-align:left;text-indent:0cm'><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:Symbol;color:#24292E'>·<span\n" +
            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp; </span></span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>-&gt;</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>CIM</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>and</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>PIM of</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>a module</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>(</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>can be simplified</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>)</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>,</span></p>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:3.0pt;margin-right:0cm;\n" +
            "margin-bottom:5.0pt;margin-left:25.8pt;text-align:left;text-indent:0cm'><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:Symbol;color:#24292E'>·<span\n" +
            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp; </span></span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E;background:#E6ECF9'>-&gt;</span><span lang=EN-US\n" +
            "style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E;\n" +
            "background:#E6ECF9'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E;background:#E6ECF9'>Database\n" +
            "design of a module</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E;background:#E6ECF9'>&nbsp;</span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E;background:#E6ECF9'>(ER</span><span lang=EN-US style='font-size:\n" +
            "10.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E;background:\n" +
            "#E6ECF9'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E;background:#E6ECF9'>diagram</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E;background:#E6ECF9'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E;\n" +
            "background:#E6ECF9'>)</span><span lang=EN-US style='font-size:10.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E;background:#E6ECF9'>&nbsp;</span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E;background:#E6ECF9'>[Must],</span></p>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:3.0pt;margin-right:0cm;\n" +
            "margin-bottom:5.0pt;margin-left:25.8pt;text-align:left;text-indent:0cm'><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:Symbol;color:#24292E'>·<span\n" +
            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp; </span></span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>-&gt;</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>Interface\n" +
            "design of a module [must]</span></p>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:3.0pt;margin-right:0cm;\n" +
            "margin-bottom:5.0pt;margin-left:25.8pt;text-align:left;text-indent:0cm'><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:Symbol;color:#24292E'>·<span\n" +
            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp; </span></span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>-&gt;</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>Development</span></p>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:3.0pt;margin-right:0cm;\n" +
            "margin-bottom:5.0pt;margin-left:25.8pt;text-align:left;text-indent:0cm'><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:Symbol;color:#24292E'>·<span\n" +
            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp; </span></span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>-&gt;</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>Unit\n" +
            "test</span></p>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:3.0pt;margin-right:0cm;\n" +
            "margin-bottom:5.0pt;margin-left:25.8pt;text-align:left;text-indent:0cm'><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:Symbol;color:#24292E'>·<span\n" +
            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp; </span></span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>-&gt;</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>Integration\n" +
            "test</span></p>\n" +
            "\n" +
            "<div style='border:none;border-bottom:solid #EAECEF 1.0pt;padding:0cm 0cm 4.0pt 0cm'>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:18.0pt;margin-right:0cm;\n" +
            "margin-bottom:12.0pt;margin-left:0cm;text-align:left;border:none;padding:0cm'><b><span\n" +
            "lang=EN-US style='font-size:18.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>The</span></b><span lang=EN-US style='font-size:13.5pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:black'>&nbsp;</span><b><span\n" +
            "lang=EN-US style='font-size:18.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>Nth</span></b><span lang=EN-US style='font-size:13.5pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:black'>&nbsp;</span><b><span\n" +
            "lang=EN-US style='font-size:18.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>iteration:</span></b></p>\n" +
            "\n" +
            "</div>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:5.0pt;margin-right:0cm;\n" +
            "margin-bottom:5.0pt;margin-left:25.8pt;text-align:left;text-indent:0cm'><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:Symbol;color:#24292E'>·<span\n" +
            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp; </span></span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>-&gt;</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>prototype\n" +
            "of a module</span><span lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>(UI</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>brief design</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>)</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>[must]</span></p>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:3.0pt;margin-right:0cm;\n" +
            "margin-bottom:5.0pt;margin-left:25.8pt;text-align:left;text-indent:0cm'><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:Symbol;color:#24292E'>·<span\n" +
            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp; </span></span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>-&gt;</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>CIM</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>and</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>PIM of</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>a module</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>(</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>can be simplified</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>)</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>,</span></p>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:3.0pt;margin-right:0cm;\n" +
            "margin-bottom:5.0pt;margin-left:25.8pt;text-align:left;text-indent:0cm'><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:Symbol;color:#24292E'>·<span\n" +
            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp; </span></span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>-&gt;</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>Database\n" +
            "design of a module</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>(ER</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>diagram</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>)</span><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>&nbsp;</span><span lang=EN-US style='font-size:12.0pt;\n" +
            "font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>[Must],</span></p>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:3.0pt;margin-right:0cm;\n" +
            "margin-bottom:5.0pt;margin-left:25.8pt;text-align:left;text-indent:0cm'><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:Symbol;color:#24292E'>·<span\n" +
            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp; </span></span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>-&gt;</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>Interface\n" +
            "design of a module [must]</span></p>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:3.0pt;margin-right:0cm;\n" +
            "margin-bottom:5.0pt;margin-left:25.8pt;text-align:left;text-indent:0cm'><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:Symbol;color:#24292E'>·<span\n" +
            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp; </span></span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>-&gt;</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>Development</span></p>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:3.0pt;margin-right:0cm;\n" +
            "margin-bottom:5.0pt;margin-left:25.8pt;text-align:left;text-indent:0cm'><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:Symbol;color:#24292E'>·<span\n" +
            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp; </span></span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>-&gt;</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>Unit\n" +
            "test</span></p>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:3.0pt;margin-right:0cm;\n" +
            "margin-bottom:5.0pt;margin-left:25.8pt;text-align:left;text-indent:0cm'><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:Symbol;color:#24292E'>·<span\n" +
            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp; </span></span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>-&gt;</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>Integration\n" +
            "test</span></p>\n" +
            "\n" +
            "<p class=MsoNormal align=left style='margin-top:3.0pt;margin-right:0cm;\n" +
            "margin-bottom:5.0pt;margin-left:25.8pt;text-align:left;text-indent:0cm'><span\n" +
            "lang=EN-US style='font-size:10.0pt;font-family:Symbol;color:#24292E'>·<span\n" +
            "style='font:7.0pt \"Times New Roman\"'>&nbsp;&nbsp; </span></span><span\n" +
            "lang=EN-US style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;\n" +
            "color:#24292E'>-&gt;</span><span lang=EN-US style='font-size:10.0pt;font-family:\n" +
            "\"Bahnschrift Light\",sans-serif;color:#24292E'>&nbsp;</span><span lang=EN-US\n" +
            "style='font-size:12.0pt;font-family:\"Bahnschrift Light\",sans-serif;color:#24292E'>System\n" +
            "Test</span></p>\n" +
            "\n" +
            "<p class=MsoNormal><span lang=EN-US style='font-family:\"Bahnschrift Light\",sans-serif'>&nbsp;</span></p>\n" +
            "\n" +
            "</div>\n" +
            "\n" +
            "</body>\n" +
            "\n" +
            "</html>\n")

}
