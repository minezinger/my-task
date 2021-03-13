package com.example.demo.persistence.dao.json;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Choice {

	private Integer choiceKey;
	private String choiceName;
	
	public Choice(Integer choiceKey, String choiceName) {
		super();
		this.choiceKey = choiceKey;
		this.choiceName = choiceName;
	}

}
