package com.example.demo.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.persistence.dao.SurveyMasterDao;

public interface SurveyMasterRepository extends JpaRepository<SurveyMasterDao, Long>{

    Optional<SurveyMasterDao> findBySurveyCode(String surveyCode);

    List<SurveyMasterDao> findBySurveyType(String surveyType);

    List<SurveyMasterDao> findBySurveyNameLike(String surveyName);
}
