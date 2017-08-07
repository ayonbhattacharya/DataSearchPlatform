package com.data.webcrawler.application.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.data.webcrawler.jobs.ConsolidateContentBroadsword;
import com.data.webcrawler.jobs.CrawlBroadsword;
import com.data.webcrawler.jobs.CrawlLivefist;

@Component
public class WebcrawlerImpl {

	private static final Logger LOGGER = LoggerFactory.getLogger(CrawlLivefist.class);
	
	@Autowired
	private CrawlBroadsword crawlBroadsword;
	
	@Autowired
	private CrawlLivefist crawlLivefist;
	
	@Autowired
	private ConsolidateContentBroadsword consolidateContentBroadsword;
	
	public void startCrawling(){
		
		/*
		 * 
		 * Separately run each block
		 * 
		 */
		
		//crawlBroadsword.getLinks();
		//crawlBroadsword.insertLinks();
		
		//LOGGER.info("Retrieving Links for website");
		//crawlLivefist.getLinks();
		//LOGGER.info("Persisting Links for website");
		//crawlLivefist.insertLinks();
		
		
		consolidateContentBroadsword.getContent();
	}
}
