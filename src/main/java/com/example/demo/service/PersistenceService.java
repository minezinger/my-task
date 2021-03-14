package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.json.DecisionDbSaveResult;
import com.example.demo.model.json.SurveyLogStat;
import com.example.demo.model.json.SurveyQuestionDbSaveResult;
import com.example.demo.model.json.SurveyQuestionInfo;
import com.example.demo.model.json.SurveySelection;
import com.example.demo.model.json.SurveyStatDbResult;
import com.example.demo.persistence.dao.ISurveyLogStat;
import com.example.demo.persistence.dao.SurveyLogBookDao;
import com.example.demo.persistence.dao.SurveyMasterDao;
import com.example.demo.persistence.dao.json.Choice;
import com.example.demo.persistence.dao.json.Choices;
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

	public DecisionDbSaveResult saveDecision(SurveySelection selection) {
		DecisionDbSaveResult dbResult = new DecisionDbSaveResult();
		if(selection == null) {
			LOGGER.error("Input request is null.");
			dbResult.setErrorMessage("Input request is null. Please check the request value.");
		}
		LOGGER.info("saveDecision()" + selection.getSurveySource());
		
		try {
			SurveyLogBookDao logBookDao = new SurveyLogBookDao();
			logBookDao.setSurveyCode(selection.getSurveyCode());
			logBookDao.setSurveySource(selection.getSurveySource());
			logBookDao.setSelectedChoice(selection.getSelectedChoice());

			dbResult.setCreatedLog( surveyLogBookRepo.save(logBookDao) );
		} catch(Exception e) {
			LOGGER.error(e.getMessage());
			dbResult.setErrorMessage(e.getMessage());
		}
		
		return dbResult;
	}
	
	public SurveyStatDbResult getSurveyStat(String surveyCode) {
		LOGGER.info("getSurveyStat()" + surveyCode);
		SurveyStatDbResult dbResult = new SurveyStatDbResult();
		try {
			List<SurveyLogStat> logStatList = new ArrayList<>();
			SurveyMasterDao surveyMaster = this.surveyMasterRepo.findBySurveyCode(surveyCode);
			Map<String, String> choiceKeyMap = getChoiceKeyMap(surveyMaster.getChoices());
			
			List<ISurveyLogStat> iLogStatList = surveyLogBookRepo.retrieveStatBySurveyCode(surveyCode);
			iLogStatList.stream().forEach(iLog -> {
				LOGGER.debug("choice : " + iLog.getSelectedChoice() + ", count :" + iLog.getCountNum());
				String choiceName = choiceKeyMap.get(iLog.getSelectedChoice());
				if(choiceName != null) {
					SurveyLogStat stat = new SurveyLogStat(surveyCode, surveyMaster.getSurveyName(), choiceName, iLog.getSelectedChoice(), iLog.getCountNum());
					logStatList.add(stat);
				} else {
					SurveyLogStat stat = new SurveyLogStat(surveyCode, surveyMaster.getSurveyName(), iLog.getSelectedChoice(), iLog.getCountNum()); 
					logStatList.add(stat);
				}
			});

			dbResult.setSurveyCode(surveyCode);
			dbResult.setSurveyMaster(surveyMaster);
			dbResult.setSurveyLogStats(logStatList);
		} catch(Exception e) {
			LOGGER.error(e.getMessage());
			dbResult.setErrorMessage(e.getMessage());
		}
		
		return dbResult;
	}
	
	public Map<String, String> getChoiceKeyMap(Choices choices) {
		Map<String, String> choiceKeyMap = new HashMap<String, String>();
		
		for(Choice choice : choices.getChoices()) {
			choiceKeyMap.put(choice.getChoiceKey().toString(), choice.getChoiceName());
		}
		return choiceKeyMap;
	}
	
	public SurveyQuestionDbSaveResult saveSurveyQuestion(SurveyQuestionInfo questionInfo) {
		SurveyQuestionDbSaveResult response = new SurveyQuestionDbSaveResult();
		if(questionInfo == null) {
			LOGGER.error("Input request is null.");
			response.setErrorMessage("Input request is null. Please check the request value.");
		}
		LOGGER.info("saveSurveyQuestion()" + questionInfo.getSurveyCode());
		
		try {
			SurveyMasterDao masterDao = new SurveyMasterDao();
			masterDao.setSurveyCode(questionInfo.getSurveyCode());
			masterDao.setSurveyType(questionInfo.getSurveyType());
			masterDao.setSurveyName(questionInfo.getSurveyName());
			masterDao.setSurveyDesc(questionInfo.getSurveyDesc());
			masterDao.setQuestionText(questionInfo.getQuestionText());
			masterDao.setChoices(questionInfo.getOptions());

			response.setCreatedLog( surveyMasterRepo.save(masterDao) );
		} catch(Exception e) {
			LOGGER.error(e.getMessage());
			response.setErrorMessage(e.getMessage());
		}
		
		return response;
	}
	
}    
	