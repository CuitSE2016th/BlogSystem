package com.bs.ssh.action

import com.bs.ssh.bean.JsonBody
import com.bs.ssh.utils.Constants
import com.bs.ssh.utils.JsonUtil
import org.apache.struts2.convention.annotation.ParentPackage
import org.apache.struts2.ServletActionContext
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream




/**
 *
 * Created by 98620 on 2018/11/27.
 */
@ParentPackage("default")
class ImageUploadAction :BaseAction(){

    //封装文件标题请求参数的属性
    var title: String? = null
    //封装上传文件域的属性
    var pic: File? = null
    //封装上传文件类型的属性
    var picContentType: String? = null
    //封装上传文件名的属性
    var picFileName: String? = null
    //直接在struts.xml文件中配置的属性
    var savePath: String? = null
        //返回上传文件的保存位置
        get() = ServletActionContext.getServletContext()
                .getRealPath(Constants.FILE_IMAGE_RELATIVE_PATH)


    @org.apache.struts2.convention.annotation.Action("imageUpload")
    override fun execute(): String {
        println("打印测试$title , $picContentType , $picFileName , $savePath")

        //以服务器的文件保存地址和原文件名建立上传文件输出流
        val  path =  File(savePath)

        if(!path.exists()){
            path.mkdir()
        }

        val fos = FileOutputStream(savePath
                + File.separator + picFileName)
        val fis = FileInputStream(pic)
        val buffer = ByteArray(1024)
        var len = fis.read(buffer)
        while ((len) > 0) {
            fos.write(buffer, 0, len)
            len = fis.read(buffer)
        }
        fos.close()

        //TODO 文件存储成功后还需要保存到数据库里面去

        val result = JsonBody<String>()
        result.code = Constants.RESPONSE_SUCCEED
        result.data = Constants.FILE_IMAGE_RELATIVE_PATH + File.separator + picFileName
        JsonUtil.returnJson(result)
        return super.execute()
    }


}