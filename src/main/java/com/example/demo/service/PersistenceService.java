package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.persistence.dao.SurveyMasterDao;
import com.example.demo.persistence.repository.SurveyMasterRepository;

@Service
public class PersistenceService {
	
	private static final Logger LOGGER = LogManager.getLogger(PersistenceService.class);

    @Autowired
    private SurveyMasterRepository surveyMasterRepo;

	public List<SurveyMasterDao> getAllSurveyInfo() {
    	return this.surveyMasterRepo.findAll();
	}
	
	public Optional<SurveyMasterDao> getSurveyInfo(Long surveyId) {
		return this.surveyMasterRepo.findById(surveyId);
	}
	
	public Optional<SurveyMasterDao> getSurveyInfo(String surveyCode) {
		return this.surveyMasterRepo.findBySurveyCode(surveyCode);
	}

	public List<SurveyMasterDao> getSurveyInfoBySurveyType(String surveyType) {
		return this.surveyMasterRepo.findBySurveyType(surveyType);
	}

	public List<SurveyMasterDao> searchSurveyInfoBySurveyName(String surveyName) {
		return this.surveyMasterRepo.findBySurveyNameLike("%" + surveyName + "%");
	}

	public List<String> findSurveyNames() {
		List<String> surveyNameList = new ArrayList<>();
		 List<SurveyMasterDao> daoList = this.surveyMasterRepo.findAll();
		 daoList.stream().forEach(d -> {
			 surveyNameList.add(d.getSurveyName());
		 });

		 return surveyNameList;
	}

}    
	