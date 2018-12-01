package com.bs.ssh.action.user

import com.bs.ssh.action.BaseAction
import com.bs.ssh.bean.JsonBody
import com.bs.ssh.service.user.UserService
import com.bs.ssh.utils.Constants
import org.apache.struts2.convention.annotation.ParentPackage
import org.apache.struts2.ServletActionContext
import org.apache.struts2.convention.annotation.Action
import org.apache.struts2.convention.annotation.Namespace
import org.springframework.beans.factory.annotation.Autowired
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


/**
 *
 * Created by 98620 on 2018/11/27.
 */
@Namespace("/user")
@ParentPackage("default")
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

    @Autowired
    private val userService: UserService? = null

    @Action(value = "uploadPortrait",
            results = [org.apache.struts2.convention.annotation.Result(name = "success", location = "updateUserInfo", type = "chain")]
    )
    override fun execute(): String {
        println("打印测试 , $picContentType , $picFileName , $savePath")
        headPortrait =  userService?.updateUserPortrait(pic, savePath, picFileName, identity)
        return super.execute()
    }


}