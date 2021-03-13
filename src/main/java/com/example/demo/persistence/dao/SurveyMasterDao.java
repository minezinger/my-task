package com.example.demo.persistence.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.example.demo.persistence.dao.json.Choices;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="survey_master")
@TypeDef(name="jsonb", typeClass=JsonBinaryType.class)
public class SurveyMasterDao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="id")
	private Long id;

    @Column(name="survey_code", nullable=false, unique=true)
	private String surveyCode;

	@Column(name="survey_type")
	private String surveyType;

    @Column(name="survey_name")
	private String surveyName;

    @Column(name="question_text")
	private String questionText;

	@Type(type="jsonb")
	@Column(name="choices", columnDefinition="jsonb")
	private Choices choices;

    public SurveyMasterDao(String surveyCode, String surveyType, String surveyName, String questionText, Choices choices) {
        this.surveyCode = surveyCode;
        this.surveyType = surveyType;
        this.surveyName = surveyName;
        this.questionText = questionText;
        this.choices = choices;
    }

}
