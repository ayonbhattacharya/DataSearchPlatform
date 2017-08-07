package com.data.webcrawler.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.data.webcrawler.entity.ContentMaster;

public interface ContentMasterRepository extends JpaRepository<ContentMaster, Integer>{


}
