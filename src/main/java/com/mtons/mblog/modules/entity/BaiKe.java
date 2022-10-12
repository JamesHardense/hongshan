package com.mtons.mblog.modules.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 模块/内容分组
 * @author langhsu
 *
 */
@Entity
@Table(name = "baike")
public class BaiKe {
    private static final long serialVersionUID = 2436696690653745208L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String url;

    private String title;

    private  String summary;

    private String basicInfo;

    private Date createTime;
}
