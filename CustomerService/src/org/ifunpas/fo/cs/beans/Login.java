package org.ifunpas.fo.cs.beans;

public class Login {
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
		if (username.equalsIgnoreCase("admin")
				&& password.equalsIgnoreCase("admin")) {
			// return "loginSuccess";
			return "success";
		}

		// return "loginFailed";
		return "gagal";
	}
}
