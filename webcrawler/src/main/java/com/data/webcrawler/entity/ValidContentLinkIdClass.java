package com.data.webcrawler.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Embeddable	
public class ValidContentLinkIdClass implements Serializable{
	private Integer link_id;
	private Integer website_id ;
	
	
	public ValidContentLinkIdClass(Integer link_id, Integer website_id) {
		super();
		this.link_id = link_id;
		this.website_id = website_id;
	}
	public Integer getLink_id() {
		return link_id;
	}
	public void setLink_id(Integer link_id) {
		this.link_id = link_id;
	}
	public Integer getWebsite_id() {
		return website_id;
	}
	public void setWebsite_id(Integer website_id) {
		this.website_id = website_id;
	}

	
	
}
