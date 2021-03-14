package com.example.demo.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.json.DecisionRequest;
import com.example.demo.model.json.DecisionDbSaveResult;
import com.example.demo.model.json.SurveyQuestionInfo;
import com.example.demo.model.json.SurveyQuestionInfoResponse;
import com.example.demo.persistence.dao.SurveyMasterDao;
import com.example.demo.service.PersistenceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("survey")
@Tag(name="survey-default-resource", description="List survey questions and save an answer of the question")
public class SurveyDefaultResource {

	private static final Logger LOGGER = LogManager.getLogger(SurveyDefaultResource.class);

	@Autowired
	PersistenceService persistService;

	private static final String SUCCESS_STATUS = "success";
	private static final String FAIL_STATUS = "fail";

	@Operation(summary="List all available survey questions")
	@GetMapping(path="/list")
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
	
	@Operation(summary="Show a specific survey question with the code")
	@GetMapping(path="/list/{code}")
    @ResponseBody
    public SurveyQuestionInfoResponse getSurveyByCodePath(@PathVariable String code) {
		LOGGER.debug("Input Survey Code : " + code);
		List<SurveyQuestionInfo> infoList = new ArrayList<>();

		SurveyMasterDao master = persistService.getSurveyInfoBySurveyCode(code);
		if(master != null) {
			SurveyQuestionInfo info = new SurveyQuestionInfo(master.getSurveyCode(), master.getQuestionText(), master.getChoices());
			infoList.add(info);
		} else {
			return new SurveyQuestionInfoResponse(FAIL_STATUS, "Not matched data");
		}

		return new SurveyQuestionInfoResponse(SUCCESS_STATUS, infoList);
    }
	
	@Operation(summary="Search a specific survey question by id, type and code")
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
	
	@Operation(summary="Save the answer of a specific survey question")
	@PostMapping(path="/decide")
	public ResponseEntity<DecisionDbSaveResult> saveDecision(@RequestBody DecisionRequest decisionRequest) {
		LOGGER.debug("Save Request : " + decisionRequest.toString());
		DecisionDbSaveResult response = new DecisionDbSaveResult();
		if(decisionRequest.getSurveySelection() != null) {
			response = persistService.saveDecision(decisionRequest.getSurveySelection());
		} else {
			response.setErrorMessage("The request data is null.");
		}

		return new ResponseEntity<DecisionDbSaveResult>(response, HttpStatus.CREATED);
	}

}