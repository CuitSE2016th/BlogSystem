package com.bs.ssh.bean;

import com.bs.ssh.entity.Article;
import com.bs.ssh.entity.Image;

/**
 * Create By ZZY on 2018/12/30
 */
public class IndexArticle extends Article {

    private String ImageUrl;

    private Integer likeCount;

    private Integer commCount;

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

    public IndexArticle(String imageUrl, Integer likeCount) {
        ImageUrl = imageUrl;
        this.likeCount = likeCount;
    }

    public IndexArticle() {
    }


}
