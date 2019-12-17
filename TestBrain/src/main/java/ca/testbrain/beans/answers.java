package ca.testbrain.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class answers implements Serializable {

	private static final long serialVersionUID = 1L;
	private String ans1;
	private String ans2;
	private String ans3;
	private String ans4;
	private String ans5;
}
