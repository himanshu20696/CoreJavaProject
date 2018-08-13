package com.eums.client;

import java.util.Date;

import com.eums.presentation.LoginPresentation;
import com.eums.presentation.LoginPresentationImpl;

public class Client {

	public static void main(String[] args) {
		System.out.println(java.time.LocalDate.now());
		LoginPresentation loginpresentation = new LoginPresentationImpl();
		loginpresentation.mainMenu();
	}

}
