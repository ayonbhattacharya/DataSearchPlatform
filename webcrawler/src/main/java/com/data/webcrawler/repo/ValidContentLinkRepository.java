package com.data.webcrawler.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.data.webcrawler.entity.ValidContentLinks;


public interface ValidContentLinkRepository extends JpaRepository<ValidContentLinks, Integer>{

	public static final String FIND_VALID_LINKS_BY_WEBSITE = "SELECT link_id,link_url FROM valid_content_links t where t.website_id = :website_id" ; 
	
	@Query(value = FIND_VALID_LINKS_BY_WEBSITE , nativeQuery=true)
	public List<Object[]> findValidLinksByWebsite(@Param("website_id")Integer website_id);
}
