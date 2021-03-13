package com.example.demo.persistence.dao.json;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Choices {

	@JsonInclude(value = Include.NON_NULL)
	private List<Choice> choices;
	
	public List<Choice> getChoices() {
		if (this.choices == null) {
			this.choices = new ArrayList<>();
		}
		return this.choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}
}
