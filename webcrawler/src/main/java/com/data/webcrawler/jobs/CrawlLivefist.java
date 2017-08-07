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
public class CrawlLivefist {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CrawlLivefist.class);
	
	@Autowired
	private ValidContentRepositoryService validContentRepositoryService;
	
	@Autowired
	private ValidContentLinkRepository validContentLinkRepository;
	
    private static final int MAX_DEPTH = 4;
    private HashSet<String> links;

    public CrawlLivefist() {
        links = new HashSet<>();
    }
    
    private int breaker = 0;

    private void getPageLinks(String URL, int depth) {
        if ((!links.contains(URL) && (depth < MAX_DEPTH))) {
            //LOGGER.debug(">> Depth: " + depth + " [" + URL + "]");
        	breaker ++ ;

            try {
            	if(URL.matches("[A-Za-z:\\/.]*\\/20[0-9]{2}\\/[0-9]{2}\\/[A-Za-z:\\/\\-.]*") && URL.contains(".html")){
                links.add(URL);
                LOGGER.info(" [ADDED!] >> Depth: " + depth + " [" + URL + "]");
                //insertLinks();
                
            	}
                Document document = Jsoup.connect(URL).ignoreContentType(true).get();
                Elements linksOnPage = document.select("a[href]");

                //Remove Comment for all links
                Pattern p = Pattern.compile("20[0-9]{2}\\/[0-9]{2}");       
                //Pattern p = Pattern.compile(".html");

                
                //Remove comment for lesser links
                //Pattern p = Pattern.compile("2017\\/[0-9]{2}");
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
    	String baseURLController = "https://www.livefistdefence.com/2017_07_01_archive.html";
    	while(true){
        getPageLinks(baseURLController, 1);
        baseURLController=updateURL(baseURLController, 2007);
        LOGGER.info("[!BASEURL] >> " + baseURLController);
        if(baseURLController.equals("StopTraverse"))
        	break;
        else
        	getPageLinks(baseURLController, 1);
        
    	}
        
    }
    

	public void insertLinks(){
    	int link_id  = 10000;
    	int i = links.size();
    	
    	for(String link:links){
    		LOGGER.info("[INSERTING!]  " + link);
    		//validContentRepositoryService.createValidContentLinks(link_id, 20001, "Broadsword - Ajaishukla ", link, "06-08-2017", "18:50");
    		validContentLinkRepository.save(new ValidContentLinks(link_id, 30001, "Livefist - Shiv Aroor ", link, "07-08-2017", "00:45"));
    		
    		link_id++;
    	}
    }
      
	private String updateURL(String baseURLController, int stopYear) {
		int year = Integer.parseInt(baseURLController.substring(32, 36));
		int month = Integer.parseInt(baseURLController.substring(38, 39));

		if(year<stopYear) return "StopTraverse"; else if(month==1) year--; 
		if(month==1) month = 12; else month--;
		
		
		String monthString = (month < 10 ? "0" : "") + month;
		return  baseURLController.substring(0,32) + Integer.toString(year) + "_" + monthString + "_" + baseURLController.substring(40);

	}
}