package com.data.webcrawler.jobs;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.data.webcrawler.entity.ContentMaster;
import com.data.webcrawler.repo.ContentMasterRepository;
import com.data.webcrawler.repo.ValidContentLinkRepository;

@Component
public class ConsolidateContentBroadsword {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsolidateContentBroadsword.class);
	
	@Autowired
	private ContentMasterRepository contentMasterRepository;
	
	@Autowired
	private ValidContentLinkRepository validContentLinkRepository;
	
	private String getURLContent(String url){
		Document doc = null;
		try {
			//String url = "http://ajaishukla.blogspot.in/2013/06/lunch-with-business-standard-dr-avinash.html";
		

		doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0")
                .timeout(30000)
                .get();
		doc.getElementById("comments").remove();
		
		doc.getElementById("sidebar-right-3").remove();
		doc.getElementById("Label1").remove();
		doc.getElementsByClass("post-footer-line post-footer-line-2").remove();
		doc.getElementsByClass("post-footer").remove();
		doc.getElementsByClass("post-share-buttons goog-inline-bloc").remove();
		doc.getElementsByClass("widget Text").remove();
		doc.getElementsByClass("widget HTML").remove();
		doc.getElementsByClass("blog-pager").remove();
		doc.getElementsByClass("widget-content").remove();
		doc.getElementsByClass("post-feeds").remove();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc.text();	
	}
	
	
	public void getContent(){
		LOGGER.info("Starting parsing content for all Links in Website");
		List<Object[]> validLinks = validContentLinkRepository.findValidLinksByWebsite(20001);
		LOGGER.info("Query Successful for all valid Links");
		for(Object[] url: validLinks){
			LOGGER.info("[!PARSING]  >> " + url[1]);
			contentMasterRepository.saveAndFlush(new ContentMaster(20001,Integer.parseInt(url[0].toString()),getURLContent(url[1].toString())));
		}
	}
	
	
	
}
