package com.example.demo.model.json;

import java.util.List;

import com.example.demo.persistence.dao.ISurveyLogStat;

import lombok.Data;

@Data
public class SurveyStatResponse {

	private String surveyCode;
	private List<ISurveyLogStat> surveyLogStats;
	private String errorMessage;
}
