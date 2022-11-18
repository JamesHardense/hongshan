/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2022, hongshan. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.mtons.mblog.modules.entity;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.Filters;
import org.hibernate.search.annotations.*;

import javax.persistence.Index;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 内容表
 *
 */
@Entity
@FilterDefs({
        @FilterDef(name = "POST_STATUS_FILTER", defaultCondition = "status = 0" )})
@Filters({ @Filter(name = "POST_STATUS_FILTER") })
@Analyzer(impl = SmartChineseAnalyzer.class)
public class View implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SortableField
    @NumericField
    private long hid;

    /**
     * 分组/模块ID
     */

    @Field
    @Column(name = "id", length = 20)
    private long id;

    @Field
    @Column(name = "editor_id", length = 20)
    private long editorId;

    @Column(name = "editor_name")
    private String editorName;

    public String getEditorName() {
        return editorName;
    }

    public void setEditorName(String editorName) {
        this.editorName = editorName;
    }

    public long getEditorId() {
        return editorId;
    }

    public void setEditorId(long editorId) {
        this.editorId = editorId;
    }

    @Field
    @NumericField
    @Column(name = "channel_id", length = 5)
    private int channelId;

    /**
     * 标题
     */
    @Field
    @Column(name = "title", length = 64)
    private String title;

    /**
     * 摘要
     */
    @Field
    @Column(length = 140)
    private String summary;

    /**
     * 预览图
     */
    @Column(length = 128)
    private String thumbnail;

    /**
     * 标签, 多个逗号隔开
     */
    @Field
    @Column(length = 64)
    private String tags;

    @Field
    @NumericField
    @Column(name = "author_id")
    private long authorId; // 作者

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 收藏数
     */
    private int favors;

    /**
     * 评论数
     */
    private int comments;

    /**
     * 阅读数
     */
    private int views;

    /**
     * 文章状态
     */
    private int status;

    /**
     * 推荐状态
     */
    private int featured;

    /**
     * 置顶状态
     */
    private int weight;

    public long getHid() {
        return hid;
    }

    public void setHid(long hid) {
        this.hid = hid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getFeatured() {
        return featured;
    }

    public void setFeatured(int featured) {
        this.featured = featured;
    }

    public int getFavors() {
        return favors;
    }

    public void setFavors(int favors) {
        this.favors = favors;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}