package com.example.demo.model.json;

import com.example.demo.persistence.dao.SurveyLogBookDao;

import lombok.Data;

@Data
public class DecisionDbSaveResult {

	private String errorMessage;
	// DB Mapping data
	private SurveyLogBookDao createdLog;
}
