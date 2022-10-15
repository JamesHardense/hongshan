package com.mtons.mblog.modules.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Baike")
public class BaiKe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 255)
    private String url;

    @Column(length = 255)
    private String title;

    private  String summary;

    private String basicInfo;

    private Date createTime;

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getBasicInfo() {
        return basicInfo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setBasicInfo(String basicInfo) {
        this.basicInfo = basicInfo;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
