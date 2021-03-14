package com.example.demo.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.json.SurveyLogStat;
import com.example.demo.model.json.SurveyQuestionDbSaveResult;
import com.example.demo.model.json.SurveyQuestionInfo;
import com.example.demo.model.json.SurveyQuestionInfoRequest;
import com.example.demo.model.json.SurveyQuestionInfoResponse;
import com.example.demo.model.json.SurveyStatDbResult;
import com.example.demo.model.json.SurveyStatResponse;
import com.example.demo.persistence.dao.ISurveyLogStat;
import com.example.demo.persistence.dao.SurveyMasterDao;
import com.example.demo.service.PersistenceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("admin/survey")
@Tag(name="survey-admin-resource", description="List survey questions, show statistics data of an survey and register an questionair")
public class SurveyAdminResource {
	
	private static final Logger LOGGER = LogManager.getLogger(SurveyAdminResource.class);

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

	@Operation(summary="Show statistics data of a specific survey question")
	@GetMapping(path="/stat/code/{code}")
    @ResponseBody
    public SurveyStatResponse getSurveyStatByCode(@PathVariable String code) {
		LOGGER.debug("Input Survey Code for Statistics : " + code);
		List<SurveyLogStat> statList = new ArrayList<>();

		SurveyStatDbResult dbResult = persistService.getSurveyStat(code);
		if(dbResult.getSurveyLogStats() != null) {
			statList.addAll(dbResult.getSurveyLogStats());
		} else {
			return new SurveyStatResponse(FAIL_STATUS, "Not matched data");
		}

		return new SurveyStatResponse(SUCCESS_STATUS, statList);
    }

	@Operation(summary="Create a survey question with options")
	@PostMapping(path="/create")
	public ResponseEntity<SurveyQuestionDbSaveResult> createSurveyQuestion(@RequestBody SurveyQuestionInfoRequest infoRequest) {
		LOGGER.debug("SurveryQuestionInfo Save Request : " + infoRequest.toString());
		SurveyQuestionDbSaveResult response = new SurveyQuestionDbSaveResult();

		if(infoRequest.getSurveyQuestionInfo() != null) {
			response = persistService.saveSurveyQuestion(infoRequest.getSurveyQuestionInfo());
		} else {
			response.setErrorMessage("The request data is null.");
		}

		return new ResponseEntity<SurveyQuestionDbSaveResult>(response, HttpStatus.CREATED);
	}

}
