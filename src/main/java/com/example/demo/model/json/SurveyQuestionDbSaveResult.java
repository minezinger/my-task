package com.example.demo.model.json;

import com.example.demo.persistence.dao.SurveyMasterDao;

import lombok.Data;

@Data
public class SurveyQuestionDbSaveResult {

	private String errorMessage;
	// DB Mapping data
	private SurveyMasterDao createdLog;
}
