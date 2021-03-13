package com.example.demo.model.json;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SurveyQuestionInfoResponse {

	private String status;
	private List<SurveyQuestionInfo> response;

	public SurveyQuestionInfoResponse(String status, List<SurveyQuestionInfo> response) {
		this.status = status;
		this.response = response;
	}

}
