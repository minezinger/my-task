package com.example.demo.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.persistence.dao.ISurveyLogStat;
import com.example.demo.persistence.dao.SurveyLogBookDao;

public interface SurveyLogBookRepository  extends JpaRepository<SurveyLogBookDao, Long> {

	@Query(value="SELECT s.selected_choice AS selectedChoice, count(*) AS countNum FROM survey_logbook AS s WHERE s.survey_code = :survey_code_arg GROUP BY s.selected_choice", nativeQuery=true)
    List<ISurveyLogStat> retrieveStatBySurveyCode(@Param("survey_code_arg") String surveyCode);

}
