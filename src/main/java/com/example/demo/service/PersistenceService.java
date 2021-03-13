package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.json.DecisionResponse;
import com.example.demo.model.json.SurveySelection;
import com.example.demo.model.json.SurveyStatResponse;
import com.example.demo.persistence.dao.ISurveyLogStat;
import com.example.demo.persistence.dao.SurveyLogBookDao;
import com.example.demo.persistence.dao.SurveyMasterDao;
import com.example.demo.persistence.repository.SurveyLogBookRepository;
import com.example.demo.persistence.repository.SurveyMasterRepository;

@Service
public class PersistenceService {
	
	private static final Logger LOGGER = LogManager.getLogger(PersistenceService.class);

    @Autowired
    private SurveyMasterRepository surveyMasterRepo;
    
    @Autowired
    private SurveyLogBookRepository surveyLogBookRepo;

	public List<SurveyMasterDao> getAllSurveyInfo() {
    	return this.surveyMasterRepo.findAll();
	}
	
	public Optional<SurveyMasterDao> getSurveyInfo(Long surveyId) {
		LOGGER.info("getSurveyInfo("+surveyId+")");
		return this.surveyMasterRepo.findById(surveyId);
	}
	
	public SurveyMasterDao getSurveyInfoBySurveyCode(String surveyCode) {
		LOGGER.info("getSurveyInfoBySurveyCode("+surveyCode+")");
		return this.surveyMasterRepo.findBySurveyCode(surveyCode);
	}

	public List<SurveyMasterDao> getSurveyInfoBySurveyType(String surveyType) {
		LOGGER.info("getSurveyInfoBySurveyType("+surveyType+")");
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

	public DecisionResponse saveDecision(SurveySelection selection) {
		DecisionResponse response = new DecisionResponse();
		if(selection == null) {
			LOGGER.error("Input request is null.");
			response.setErrorMessage("Input request is null. Please check the request value.");
		}
		LOGGER.info("saveDecision()" + selection.getSurveySource());
		
		try {
			SurveyLogBookDao logBookDao = new SurveyLogBookDao();
			logBookDao.setSurveyCode(selection.getSurveyCode());
			logBookDao.setSurveySource(selection.getSurveySource());
			logBookDao.setSelectedChoice(selection.getSelectedChoice());

			response.setCreatedLog( surveyLogBookRepo.save(logBookDao) );
		} catch(Exception e) {
			LOGGER.error(e.getMessage());
			response.setErrorMessage(e.getMessage());
		}
		
		return response;
	}
	
	public SurveyStatResponse getSurveyStat(String surveyCode) {
		SurveyStatResponse response = new SurveyStatResponse();
		List<ISurveyLogStat> logStatList = surveyLogBookRepo.retrieveStatBySurveyCode(surveyCode);

		response.setSurveyCode(surveyCode);
		response.setSurveyLogStats(logStatList);
		
		return response;
	}
}    
	