/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.mtons.mblog.modules.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 模块/内容分组
 * @author langhsu
 *
 */
@Entity
@Table(name = "mto_channel")
public class Channel implements Serializable {
	private static final long serialVersionUID = 2436696690653745208L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * 一级分类
	 */
	@Column(length = 100)
	private String classone;


	/**
	 * 二级分类
	 */
	@Column(length = 100)
	private String classtwo;


	/**
	 * 预览图
	 */
	@Column(length = 128)
	private String thumbnail;

	@Column(length = 5)
	private int status;

	/**
	 * 排序值
	 */
	private int weight;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClassone() {
		return classone;
	}

	public void setClassone(String classone) {
		this.classone = classone;
	}

	public String getClasstwo() {
		return classtwo;
	}

	public void setClasstwo(String classtwo) {
		this.classtwo = classtwo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
