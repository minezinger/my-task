package com.example.demo.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.persistence.dao.SurveyMasterDao;

public interface SurveyMasterRepository extends JpaRepository<SurveyMasterDao, Long>{

    SurveyMasterDao findBySurveyCode(String surveyCode);

    List<SurveyMasterDao> findBySurveyType(String surveyType);

    List<SurveyMasterDao> findBySurveyNameLike(String surveyName);

//    @Query(value="SELECT choices ->> 'choiceName' AS choiceName FROM survey_master WHERE survey_code = :surveyCode AND choices ->> 'choiceKey' = :choiceKey", nativeQuery=true)
//    List<String> findChoiceNamesBySurveyCodeAndChoiceKey(String surveyCode, String choiceKey);
}
