package com.example.demo.persistence.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "survey_logbook")
public class SurveyLogBookDao {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="logbook_seq_gen")
    @SequenceGenerator(name="logbook_seq_gen", sequenceName="logbook_seq", allocationSize=1)
    @Column(name="id", updatable=false)
	private Long id;

    @Column(name="survey_code", nullable=false, unique=true)
	private String surveyCode;

	@Column(name="survey_source")
	private String surveySource;

	@Column(name="selected_choice")
	private String selectedChoice;
}
