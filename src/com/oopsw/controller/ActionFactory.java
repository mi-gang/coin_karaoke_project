package com.oopsw.controller;

import com.oopsw.controller.action.Login;
import com.oopsw.controller.action.LoginUI;
import com.oopsw.controller.action.wrongCmd;

public class ActionFactory {

	public Action getAction(String cmd){
		Action action = null;
		switch (cmd) {
		case "login":
			action = new Login();
			break;
		case "loginUI":
			action = new LoginUI();
			break;
		default:
			action = new wrongCmd();
			break;
		}
		
		return action;
	}
}
