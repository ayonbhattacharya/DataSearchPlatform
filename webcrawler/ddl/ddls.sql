create database crawler1;
use crawler1;
create table `valid_content_links` (
	`link_id` int(8),
    `website_id` int(8),
    `website_name` varchar(100),
    `link_url` varchar(200),
    `crawled_date` varchar(20),
    `crawled_time` varchar(20),
    PRIMARY KEY (link_id)
    );
    
    select count(*) from valid_content_links where website_id = 20001;
    delete from valid_content_links where website_id = 30001;
        select * from valid_content_links;

    
    drop table content_websites;
    
    
    create table `website_master` (
		`website_id` int(8),
        `website_name` varchar(100),
        `website_description` varchar(200),
        `website_domain` varchar(50),
        `last_crawled` varchar(50),
        `crawl_freq` varchar(20),
        PRIMARY KEY (website_id)
    );
    
    INSERT into website_master VALUES (20001, "http://www.ajaishukla.blogspot.com/", "Defence Journalist - India, Ajai Shukla","Defence", "06-08-2017", "Monthly" );
	INSERT into website_master VALUES (30001, "http://www.livefistdefence.com/", "Defence Journalist - India, Shiv Aroor","Defence", "07-08-2017", "Monthly" );
    
    
    create table `website_content_data` (
		`website_id` int (8),
        `link_id` int (8),
        `link_content_data` MEDIUMTEXT 
    );
    
    select * from website_content_data;
    truncate table website_content_data;