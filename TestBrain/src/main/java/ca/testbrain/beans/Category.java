package ca.testbrain.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	private int catId;
	private String category;
	private int enabled;

	public Category(String category, int enabled) {
		this.category = category;
		this.enabled = enabled;
	}

}