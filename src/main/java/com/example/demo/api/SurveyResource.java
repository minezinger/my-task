package com.example.demo.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.json.DecisionRequest;
import com.example.demo.model.json.DecisionResponse;
import com.example.demo.model.json.SurveyQuestionInfo;
import com.example.demo.model.json.SurveyQuestionInfoResponse;
import com.example.demo.persistence.dao.SurveyMasterDao;
import com.example.demo.service.PersistenceService;

@RestController
@RequestMapping("survey")
public class SurveyResource {

	private static final Logger LOGGER = LogManager.getLogger(SurveyResource.class);

	@Autowired
	PersistenceService persistService;

	private static final String SUCCESS_STATUS = "success";
	private static final String FAIL_STATUS = "fail";

	@GetMapping(path="/getall")
    @ResponseBody
    public SurveyQuestionInfoResponse getSurveyAll() {
		List<SurveyQuestionInfo> infoList = new ArrayList<>();

		List<SurveyMasterDao> resultList = persistService.getAllSurveyInfo();
		for(SurveyMasterDao master : resultList) {
			SurveyQuestionInfo info = new SurveyQuestionInfo(master.getSurveyCode(), master.getQuestionText(), master.getChoices());
			infoList.add(info);
		}
		return new SurveyQuestionInfoResponse(SUCCESS_STATUS, infoList);
    }

	@GetMapping(path="/getbycode")
    @ResponseBody
    public SurveyQuestionInfoResponse getSurveyByCode(@RequestParam(name="value", required=true) String surveyCode) {
		LOGGER.debug("Input Survey Code : " + surveyCode);
		List<SurveyQuestionInfo> infoList = new ArrayList<>();

		SurveyMasterDao master = persistService.getSurveyInfoBySurveyCode(surveyCode);
		if(master != null) {
			SurveyQuestionInfo info = new SurveyQuestionInfo(master.getSurveyCode(), master.getQuestionText(), master.getChoices());
			infoList.add(info);
		} else {
			return new SurveyQuestionInfoResponse(FAIL_STATUS, "Not matched data");
		}

		return new SurveyQuestionInfoResponse(SUCCESS_STATUS, infoList);
    }

	@GetMapping(path="/search")
    @ResponseBody
    public SurveyQuestionInfoResponse searchSurvey(@RequestParam(name="key", required=true) String searchKey, @RequestParam(name="value", required=true) String searchValue) {
		LOGGER.debug("Search Survey key : " + searchKey + ", Search value : " + searchValue);
		List<SurveyQuestionInfo> infoList = new ArrayList<>();

		if("id".contentEquals(searchKey)) {
			Optional<SurveyMasterDao> master = persistService.getSurveyInfo(Long.valueOf(searchValue));
			if(master.isPresent()) {
				SurveyQuestionInfo info = new SurveyQuestionInfo(master.get().getSurveyCode(), master.get().getQuestionText(), master.get().getChoices());
				infoList.add(info);
			} else {
				return new SurveyQuestionInfoResponse(FAIL_STATUS, "Not matched data");
			}
		} else if("type".contentEquals(searchKey)) {
			List<SurveyMasterDao> resultList = persistService.getSurveyInfoBySurveyType(searchValue);
			if(resultList != null) {
				for(SurveyMasterDao master : resultList) {
					SurveyQuestionInfo info = new SurveyQuestionInfo(master.getSurveyCode(), master.getQuestionText(), master.getChoices());
					infoList.add(info);
				}
			} else {
				return new SurveyQuestionInfoResponse(FAIL_STATUS, "Not matched data");
			}
		} else if("code".contentEquals(searchKey)) {
			SurveyMasterDao master = persistService.getSurveyInfoBySurveyCode(searchValue);
			if(master != null) {
				SurveyQuestionInfo info = new SurveyQuestionInfo(master.getSurveyCode(), master.getQuestionText(), master.getChoices());
				infoList.add(info);
			} else {
				return new SurveyQuestionInfoResponse(FAIL_STATUS, "Not matched data");
			}
		}
		return new SurveyQuestionInfoResponse(SUCCESS_STATUS, infoList);
    }
	
	@PostMapping(path="/decide")
	public ResponseEntity<DecisionResponse> saveDecision(@RequestBody DecisionRequest decisionRequest) {
		LOGGER.debug("Save Request : " + decisionRequest.toString());
		DecisionResponse response = new DecisionResponse();
		if(decisionRequest.getSurveySelection() != null) {
			response = persistService.saveDecision(decisionRequest.getSurveySelection());
		} else {
			response.setErrorMessage("The request data is null.");
		}

		return new ResponseEntity<DecisionResponse>(response, HttpStatus.CREATED);
	}

}