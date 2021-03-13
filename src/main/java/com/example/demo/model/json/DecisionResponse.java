package com.example.demo.model.json;

import com.example.demo.persistence.dao.SurveyLogBookDao;

import lombok.Data;

@Data
public class DecisionResponse {

	private String errorMessage;
	private SurveyLogBookDao createdLog;
}
