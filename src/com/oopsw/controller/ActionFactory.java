package com.oopsw.controller;

import com.oopsw.controller.action.Login;
import com.oopsw.controller.action.LoginUI;
import com.oopsw.controller.action.addUser;
import com.oopsw.controller.action.addUserUI;
import com.oopsw.controller.action.findPasswordUI;
import com.oopsw.controller.action.getNickname;
import com.oopsw.controller.action.isExistEmail;
import com.oopsw.controller.action.sendValidationNumber;
import com.oopsw.controller.action.updateNickname;
import com.oopsw.controller.action.updatePassword;
import com.oopsw.controller.action.updateUserUI;
import com.oopsw.controller.action.wrongCmd;

public class ActionFactory {

	public Action getAction(String cmd){
		Action action = new wrongCmd();
		switch (cmd) {
		case "login":
			action = new Login();
			break;
		case "loginUI":
			action = new LoginUI();
			break;
		case "addUserUI":
			action = new addUserUI();
			break;
		case "addUser":
			action = new addUser();
			break;
		case "isExistEmail":
			action = new isExistEmail();
			break;
		case "findPasswordUI":
			action = new findPasswordUI();
			break;
		case "sendValidationNumber":
			action = new sendValidationNumber();
			break;
		case "updateUserUI":
			action = new updateUserUI();
			break;
		case "getNickname":
			action = new getNickname();
			break;
		case "updateNickname":
			action = new updateNickname();
			break;
		case "updatePassword":
			action = new updatePassword();
			break;
		case "isAdult":
//			action = new idAdult();
			break;
		default:
			action = new wrongCmd();
			break;
		}
		
		return action;
	}
}
