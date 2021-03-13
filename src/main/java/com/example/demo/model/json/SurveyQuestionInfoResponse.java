package com.example.demo.model.json;

import java.util.List;

import lombok.Data;

@Data
public class SurveyQuestionInfoResponse {

	private String status;
	private List<SurveyQuestionInfo> response;
	private String errorMessage;

	public SurveyQuestionInfoResponse(String status, List<SurveyQuestionInfo> response) {
		this.status = status;
		this.response = response;
	}

	public SurveyQuestionInfoResponse(String status, String errorMessage) {
		this.status = status;
		this.errorMessage = errorMessage;
	}
}
