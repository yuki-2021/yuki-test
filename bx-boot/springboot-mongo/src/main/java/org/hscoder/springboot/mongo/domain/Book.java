package org.hscoder.springboot.mongo.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 书籍
 * 
 * @author atp
 *
 */
@Document(collection = "book")
@CompoundIndexes({ @CompoundIndex(name = "idx_category_voteCount", def = "{'category': 1, 'voteCount': 1}"),
        @CompoundIndex(name = "idx_category_createTime", def = "{'category': 1, 'createTime': 1}") })
public class Book {

    @Id
    private String id;

    @Indexed
    private String author;

    // 分类
    private String category;

    @Indexed(unique = true)
    private String title;

    // 得票数
    private int voteCount;
    // 价格
    private int price;

    @Indexed
    private Date publishDate;

    private Date updateTime;
    private Date createTime;

    public static final String COL_CATEGORY = "category";
    public static final String COL_CREATE_TIME = "createTime";
    public static final String COL_PUBLISH_DATE = "publishDate";
    public static final String COL_VOTE_COUNT = "voteCount";
    public static final String COL_UPDATE_TIME = "updateTime";
    public static final String COL_AUTHOR = "author";
    public static final String COL_TITLE = "title";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
