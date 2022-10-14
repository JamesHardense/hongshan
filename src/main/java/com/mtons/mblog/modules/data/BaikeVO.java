package com.mtons.mblog.modules.data;

import java.util.List;

public class BaikeVO {
    private int id;
    private String title;
    private String summary;
    private List<BasicInfoVO> basicInfoVOS;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<BasicInfoVO> getBasicInfoVOS() {
        return basicInfoVOS;
    }

    public void setBasicInfoVOS(List<BasicInfoVO> basicInfoVOS) {
        this.basicInfoVOS = basicInfoVOS;
    }
}
