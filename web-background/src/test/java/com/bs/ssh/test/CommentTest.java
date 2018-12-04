package com.bs.ssh.test;

import com.bs.ssh.bean.PageRequest;
import com.bs.ssh.entity.Comment;
import com.bs.ssh.service.user.UserCommentService;
import com.bs.ssh.utils.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/*")
public class CommentTest {

    @Autowired private UserCommentService service;

    private final static String USER_ID = "35268860677";
    private final static Integer ARTICLE_ID = 1;

    @Test
    public void addComment(){
        service.newComment(USER_ID, ARTICLE_ID, "a new comment");
    }


    @Test
    public void deleteComment(){
        service.deleteComment(USER_ID, ARTICLE_ID);
    }

//    @Test
    public List<Comment> findComment(){
        PageRequest request = new PageRequest();
        request.setPageNumber(1);
        request.setPageSize(3);
        return (List<Comment>) service.findAllComment(request, ARTICLE_ID).getResult();
//        System.out.println(JsonUtil.toJson(service.findAllComment(request, ARTICLE_ID)));
    }

    @Test public void reply(){
        Comment parent = findComment().get(0);
    }


}
