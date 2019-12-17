package ca.testbrain.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class clsUser implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long userId;
	private String Name;
	private String lName;
	private String email;
	private String membership;
	private String encrytedPassword;

	public clsUser(String name, String email, String membership, String encrytedPassword) {
		Name = name;
		this.email = email;
		this.membership = membership;
		this.encrytedPassword = encrytedPassword;
	}

}