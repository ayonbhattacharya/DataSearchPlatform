package com.data.webcrawler.jobs;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.data.webcrawler.entity.ValidContentLinks;
import com.data.webcrawler.repo.ValidContentLinkRepository;
import com.data.webcrawler.service.ValidContentRepositoryService;

import java.io.IOException;
import java.util.HashSet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class CrawlBroadsword {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrawlLivefist.class);
	
	@Autowired
	private ValidContentRepositoryService validContentRepositoryService;
	
	@Autowired
	private ValidContentLinkRepository validContentLinkRepository;
	
    private static final int MAX_DEPTH = 8;
    private HashSet<String> links;

    public CrawlBroadsword() {
        links = new HashSet<>();
    }
    
    private int breaker = 0;

    private void getPageLinks(String URL, int depth) {
        if ((!links.contains(URL) && (depth < MAX_DEPTH))) {
            //System.out.println(">> Depth: " + depth + " [" + URL + "]");
        	breaker ++ ;

            try {
            	if(URL.matches("[A-Za-z:\\/.]*\\/20[0-9]{2}\\/[0-9]{2}\\/[A-Za-z:\\/\\-.]*") && URL.contains(".html")){
                links.add(URL);
                LOGGER.info(" [ADDED!] >> Depth: " + depth + " [" + URL + "]");
                //insertLinks();
                
            	}
                Document document = Jsoup.connect(URL).ignoreContentType(true).get();
                Elements linksOnPage = document.select("a[href]");

                //Comment for all links
                //Pattern p = Pattern.compile("20[0-9]{2}\\/[0-9]{2}");
                
                //Remove comment for lesser links
                Pattern p = Pattern.compile("2017\\/[0-9]{2}");
                depth++;
                for (Element page : linksOnPage) {
                	Matcher m = p.matcher(page.attr("abs:href"));
                	if (m.find())
                    getPageLinks(page.attr("abs:href"), depth);
                	else
                		//System.out.println("[REJECTING!] : " + page.attr("abs:href"));
                		continue;
                }
            } catch (Exception e) {
            	LOGGER.error("For '" + URL + "': " + e.getMessage());
            } 
        }
    }

    public void getLinks() {
        getPageLinks("http://www.ajaishukla.blogspot.com/", 1);
        
    }
    
    public void insertLinks(){
    	int link_id  = 10000;
    	int i = links.size();
    	
    	for(String link:links){
    		LOGGER.info("[INSERTING!]  " + link);
    		//validContentRepositoryService.createValidContentLinks(link_id, 20001, "Broadsword - Ajaishukla ", link, "06-08-2017", "18:50");
    		validContentLinkRepository.save(new ValidContentLinks(link_id, 20001, "Broadsword - Ajaishukla ", link, "06-08-2017", "18:50"));
    		
    		link_id++;
    	}
    }
    
    
   
}