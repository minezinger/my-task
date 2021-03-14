package com.example.demo.model.json;

import java.util.List;

import lombok.Data;

@Data
public class SurveyStatResponse {
	private String status;
	private List<SurveyLogStat> response;
	private String errorMessage;

	public SurveyStatResponse(String status, List<SurveyLogStat> response) {
		this.status = status;
		this.response = response;
	}

	public SurveyStatResponse(String status, String errorMessage) {
		this.status = status;
		this.errorMessage = errorMessage;
	}

}
