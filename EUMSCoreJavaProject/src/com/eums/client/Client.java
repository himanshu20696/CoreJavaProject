package com.eums.client;

import com.eums.presentation.LoginPresentation;
import com.eums.presentation.LoginPresentationImpl;

public class Client {

	public static void main(String[] args) {
		LoginPresentation loginpresentation = new LoginPresentationImpl();
		loginpresentation.login();
	}

}
