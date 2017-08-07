package com.data.webcrawler.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="website_content_data")
public class ContentMaster {


	
	private Integer website_id;
	@Id
	private Integer link_id;
	private String link_content_data;
	public ContentMaster(Integer website_id, Integer link_id, String link_content_data) {
		super();
		this.website_id = website_id;
		this.link_id = link_id;
		this.link_content_data = link_content_data;
	}
	
	
	protected ContentMaster(){};
	
	public Integer getWebsite_id() {
		return website_id;
	}
	public void setWebsite_id(Integer website_id) {
		this.website_id = website_id;
	}
	public Integer getLink_id() {
		return link_id;
	}
	public void setLink_id(Integer link_id) {
		this.link_id = link_id;
	}
	public String getLink_content_data() {
		return link_content_data;
	}
	public void setLink_content_data(String link_content_data) {
		this.link_content_data = link_content_data;
	}
	
	
	
}
