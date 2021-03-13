package com.example.demo.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.persistence.dao.SurveyLogBookDao;

public interface SurveyLogBookRepository  extends JpaRepository<SurveyLogBookDao, Long> {

}
