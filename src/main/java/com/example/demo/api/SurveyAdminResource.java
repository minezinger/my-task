package com.example.demo.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.json.DecisionRequest;
import com.example.demo.model.json.DecisionResponse;
import com.example.demo.model.json.SurveyQuestionInfo;
import com.example.demo.model.json.SurveyQuestionInfoResponse;
import com.example.demo.persistence.dao.SurveyMasterDao;
import com.example.demo.service.PersistenceService;

@RestController
@RequestMapping("admin/survey")
public class SurveyAdminResource {

	@Autowired
	PersistenceService persistService;

	private static final String SUCCESS_STATUS = "success";
//	private static final String FAIL_STATUS = "fail";

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

//	@PostMapping(path="register")
//	public ResponseEntity<SurveyResigerResponse> registerSurvey(@RequestBody SurveyRegisterRequest surveyRegister) {
//
//	}

}
