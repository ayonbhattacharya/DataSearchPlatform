package com.data.webcrawler.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="valid_content_links")
public class ValidContentLinks implements Serializable{

	@Id
	private Integer link_id;
	private Integer website_id ;
	
	//@EmbeddedId
	//private ValidContentLinkIdClass validContentLinkIdClass;
	private String website_name ;
	private String link_url ;
	private  String crawled_date ;
	private String crawled_time ;
	
	public ValidContentLinks(Integer link_id, Integer website_id, String website_name, String link_url, String crawled_date,
			String crawled_time) {
		super();
		this.link_id = link_id;
		this.website_id = website_id;
		
		
		this.website_name = website_name;
		this.link_url = link_url;
		this.crawled_date = crawled_date;
		this.crawled_time = crawled_time;
	}
	
	protected ValidContentLinks(){
		
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

	public String getWebsite_name() {
		return website_name;
	}

	public void setWebsite_name(String website_name) {
		this.website_name = website_name;
	}

	public String getLink_url() {
		return link_url;
	}

	public void setLink_url(String link_url) {
		this.link_url = link_url;
	}

	public String getCrawled_date() {
		return crawled_date;
	}

	public void setCrawled_date(String crawled_date) {
		this.crawled_date = crawled_date;
	}

	public String getCrawled_time() {
		return crawled_time;
	}

	public void setCrawled_time(String crawled_time) {
		this.crawled_time = crawled_time;
	}
	
	
}
