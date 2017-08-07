package com.data.webcrawler.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.webcrawler.entity.ValidContentLinks;
import com.data.webcrawler.repo.ValidContentLinkRepository;

@SuppressWarnings("serial")
@Service
public class ValidContentRepositoryService implements Serializable{
	private ValidContentLinkRepository validContentLinkRepository;

	@Autowired
	public ValidContentRepositoryService(ValidContentLinkRepository validContentLinkRepository) {
		this.validContentLinkRepository = validContentLinkRepository;
	};
	
	public ValidContentLinks createValidContentLinks(Integer link_id, Integer website_id, String website_name, String link_url, String crawled_date, String crawled_time) {
		System.out.println(":::DEBUG:::");		
		return validContentLinkRepository.save(new ValidContentLinks(link_id, website_id, website_name, link_url, crawled_date, crawled_time));
	}
}