package com.nucleus.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Login {

	@Value("${security.user.name}")
	String usrname;

	@Value("${security.user.password}")
	String pwd;

	public boolean match(String user, String pass) {

		if (usrname.equals(user) && pwd.equals(pass)) {
			return true;
		}
		return false;
	}
}
