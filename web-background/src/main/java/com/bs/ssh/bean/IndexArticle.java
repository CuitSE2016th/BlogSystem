package com.bs.ssh.bean;

import com.bs.ssh.entity.Article;
import com.bs.ssh.entity.Image;
import com.bs.ssh.entity.User;
import com.bs.ssh.utils.DateUtils;

/**
 * Create By ZZY on 2018/12/30
 */
public class IndexArticle extends Article {

    private String ImageUrl;

    private Integer likeCount;

    private Integer commCount;

    private Integer starCount;

    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getCommCount() {
        return commCount;
    }

    public void setCommCount(Integer commCount) {
        this.commCount = commCount;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getStarCount() {
        return starCount;
    }

    public void setStarCount(Integer starCount) {
        this.starCount = starCount;
    }

    public IndexArticle(String imageUrl, Integer likeCount) {
        ImageUrl = imageUrl;
        this.likeCount = likeCount;
    }

    public IndexArticle(Article article,
                        User user,
                        Integer like,
                        Integer star,
                        Integer commCount) {

        this.setId(article.getId());
        this.setAuthorId(article.getAuthorId());
        this.setTitle(article.getTitle());
        this.setContent(article.getContent());
        this.setStatus(article.getStatus());
        this.setTime(DateUtils.getDateStringFromLong(article.getCreateTime()));
        this.setImageUrl(user.getHeadPortrait());
        this.setLikeCount(like);
        this.setStarCount(star);
        this.setCommCount(commCount);
    }

    public IndexArticle() {
    }


}
