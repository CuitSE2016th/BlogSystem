package com.bs.ssh.test;

import com.bs.ssh.service.user.UserFileService;
import com.bs.ssh.utils.JsonUtil;
import com.bs.ssh.bean.PageRequest;
import com.bs.ssh.service.user.UserArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/*")
public class ArticleTest {
    @Autowired private UserArticleService articleService;
    @Autowired private UserFileService fileService;

    private final static String USER_ID = "35268860677";
    private final static Integer ARTICLE_ID = 1;

    @Test
    public void referencePic(){
        String uid = "1234567";
        String[] paths = new String[1];
        paths[0] = "53955181255";
        Integer aid = 1;

        fileService.referencePicture(uid, paths, aid);

    }

    @Test
    public void add(){
        articleService.publishArticle(USER_ID, "Testing Article", "It's an article use for testing.");
    }

    @Test
    public void delete(){
        articleService.deleteArticle(USER_ID, ARTICLE_ID);
    }

    @Test
    public void find(){
        PageRequest request = new PageRequest();
        request.setPageNumber(1);
        request.setPageSize(3);
        System.out.println(
                JsonUtil.toJson(articleService.getAllArticle(request))
        );
    }

    @Test
    public void star(){
        try {

            articleService.starArticle(USER_ID, ARTICLE_ID);
        }catch (Exception e){
            //主键冲突
            System.out.println(e instanceof DataIntegrityViolationException);
        }
    }

    @Test
    public void cancelStar(){
        articleService.cancelStar(USER_ID, ARTICLE_ID);
    }

    @Test
    public void like(){
        try {

            articleService.giveLike(USER_ID, ARTICLE_ID);
        }catch (Exception e){
            //主键冲突
            System.out.println(e instanceof DataIntegrityViolationException);
        }
    }

    @Test
    public void cancelLike(){
        articleService.cancelLike(USER_ID, ARTICLE_ID);
    }
}
