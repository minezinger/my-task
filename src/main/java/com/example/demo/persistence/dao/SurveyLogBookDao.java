package com.example.demo.persistence.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "survey_logbook")
public class SurveyLogBookDao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id")
	private Long id;

    @Column(name="survey_code", nullable=false, unique=true)
	private String surveyCode;

	@Column(name="survey_source")
	private String surveySource;

//	@Column(name="choice_time")
//	private timestamp choiceTime;

	@Column(name="selected_choice")
	private String selectedChoice;
}
