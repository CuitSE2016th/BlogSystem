package com.bs.ssh.service.user.impl;

import com.bs.ssh.dao.BaseDao;
import com.bs.ssh.entity.Image;
import com.bs.ssh.exception.NoSuchEntityException;
import com.bs.ssh.service.user.UserFileService;
import com.bs.ssh.utils.Constants;
import com.bs.ssh.utils.IDUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

@Transactional
@Service
public class UserFileServiceImpl implements UserFileService {


    @Resource
    private BaseDao<Image> imageBaseDao;

    @Override
    public Object[] uploadPicture(String uid, File[] files, String[] filenames) throws IOException {

        Object[] paths = new Object[files.length];

        for(int i=0; i<files.length; i++){
            int pos = filenames[i].lastIndexOf('.');

            String imageId = IDUtils.UserID();
            String suffix = pos == -1 ? "jpg" : filenames[i].substring(pos+1);

            String suffix_path = "/" + uid + "/" +
                    imageId + "." + suffix;

            File target = new File(Constants.FILE_IMAGE_RELATIVE_PATH + suffix_path);
            FileUtils.copyFile(files[i], target);
            paths[i] = Constants.HOST_PATH + suffix_path;

            Image image = new Image();
            image.setId(imageId);
            image.setUid(uid);
            image.setUrl(Constants.HOST_PATH + suffix_path);
            image.setCreateTime(System.currentTimeMillis());

            imageBaseDao.insert(image);
        }
        return paths;
    }

    @Override
    public void referencePicture(String uid, String[] iid, Integer aid) {
        for (String id : iid){
            Image image = imageBaseDao.findOne("from Image where id=? and uid = ?", id, uid);
            if(image == null)
                throw new NoSuchEntityException("图片不存在或您没有操作权限");
            image.setAid(aid);
            imageBaseDao.update(image);
        }
    }
}
