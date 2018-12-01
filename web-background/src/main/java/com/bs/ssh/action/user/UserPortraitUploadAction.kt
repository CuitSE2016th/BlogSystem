package com.bs.ssh.action.user

import com.bs.ssh.action.BaseAction
import com.bs.ssh.bean.JsonBody
import com.bs.ssh.utils.Constants
import org.apache.struts2.convention.annotation.ParentPackage
import org.apache.struts2.ServletActionContext
import org.apache.struts2.convention.annotation.Action
import org.apache.struts2.convention.annotation.Namespace
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


/**
 *
 * Created by 98620 on 2018/11/27.
 */
@Namespace("/user")
@ParentPackage("default")
//@Results(org.apache.struts2.convention.annotation.Result(name = "json", type = "json", params = arrayOf("root", "result")))
class UserPortraitUploadAction : BaseAction() {

    var identity: String? = null

    var token: String? = null

    var headPortrait: String? = null

    //封装上传文件域的属性
    var pic: File? = null
    //封装上传文件类型的属性
    var picContentType: String? = null
    //封装上传文件名的属性
    var picFileName: String? = null
    //直接在struts.xml文件中配置的属性
    private var savePath: String? = null
        //返回上传文件的保存位置
        get() = ServletActionContext.getServletContext()
                .getRealPath(Constants.FILE_IMAGE_RELATIVE_PATH)


    @Action(value = "uploadPortrait",
            results = [org.apache.struts2.convention.annotation.Result(name = "success", location = "updateUserInfo", type = "chain")]
    )
    override fun execute(): String {
        println("打印测试 , $picContentType , $picFileName , $savePath")

        //以服务器的文件保存地址和原文件名建立上传文件输出流
        val path = File(savePath)

        if (!path.exists()) {
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

        headPortrait = Constants.FILE_IMAGE_RELATIVE_PATH + File.separator + picFileName
        return super.execute()
    }


}