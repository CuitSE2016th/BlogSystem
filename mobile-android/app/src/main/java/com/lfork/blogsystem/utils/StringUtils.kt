package com.lfork.blogsystem.utils

import android.text.TextUtils
import java.util.regex.Matcher
import java.util.regex.Pattern

object StringUtils {

    //从html中提取纯文本
    fun htmlToTxt(strHtml: String): String {
        var txtcontent = strHtml.replace("</?[^>]+>".toRegex(), "") //剔出<html>的标签
        txtcontent =
                txtcontent.replace("<a>\\s*|\t|\r|\n</a>".toRegex(), "")//去除字符串中的空格,回车,换行符,制表符
        return  replaceBlank(txtcontent)
    }


    fun getImgStr(htmlStr: String?): List<String>{

        var img = ""
        val p_image: Pattern
        val m_image: Matcher
        val pics = ArrayList<String>()

        if (TextUtils.isEmpty(htmlStr)){
            return pics
        }

        val regEx_img = "<img.*src=(.*?)[^>]*?>" //图片链接地址
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE)
        m_image = p_image.matcher(htmlStr)
        while (m_image.find()) {
            img = img + "," + m_image.group()
            val m = Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(img) //匹配src
            while (m.find()) {
                pics.add(m.group(1))
            }
        }
        return pics
    }

    fun replaceBlank(str: String?): String {
        var dest = ""
        if (str != null) {
            val p = Pattern.compile("[\t\r\n]")
            val m = p.matcher(str)
            dest = m.replaceAll("")
        }
        return dest
    }



}