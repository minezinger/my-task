package com.example.demo.model.json;

import java.util.List;

import com.example.demo.persistence.dao.ISurveyLogStat;
import com.example.demo.persistence.dao.SurveyMasterDao;

import lombok.Data;

@Data
public class SurveyStatDbResult {

	private String surveyCode;
	private SurveyMasterDao surveyMaster;
	private List<SurveyLogStat> surveyLogStats;
	private String errorMessage;
}
