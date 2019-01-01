package com.lfork.blogsystem.utils

object StringUtils {

    //从html中提取纯文本
    fun htmlToTxt(strHtml: String): String {
        var txtcontent = strHtml.replace("</?[^>]+>".toRegex(), "") //剔出<html>的标签
        txtcontent =
                txtcontent.replace("<a>\\s*|\t|\r|\n</a>".toRegex(), "")//去除字符串中的空格,回车,换行符,制表符
        return txtcontent
    }

}