package ca.testbrain.beans;

import java.io.Serializable;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Question implements Serializable {

	private static final long serialVersionUID = 1L;
	private int queId;
	private String que;
	private String opt1;
	private String opt2;
	private String opt3;
	private String opt4;
	private int catId;
	private String ans;
}
