package com.oopsw.controller;

import com.oopsw.controller.action.KKDetailUI;
import com.oopsw.controller.action.KKFilterUI;
import com.oopsw.controller.action.KKSearchResultUI;
import com.oopsw.controller.action.Login;
import com.oopsw.controller.action.ReservationListUIAction;
import com.oopsw.controller.action.UncompletedReservationListAction;
import com.oopsw.controller.action.LoginUI;
import com.oopsw.controller.action.SearchForKKWithOptions;
import com.oopsw.controller.action.addUser;
import com.oopsw.controller.action.addUserUI;
import com.oopsw.controller.action.findPasswordUI;
import com.oopsw.controller.action.getNickname;
import com.oopsw.controller.action.idAdult;
import com.oopsw.controller.action.isExistEmail;
import com.oopsw.controller.action.resetPassword;
import com.oopsw.controller.action.sendValidationNumber;
import com.oopsw.controller.action.updateNickname;
import com.oopsw.controller.action.updatePassword;
import com.oopsw.controller.action.updateUserUI;
import com.oopsw.controller.action.wrongCmd;

public class ActionFactory {

	public Action getAction(String cmd){
		Action action = new wrongCmd();
		System.out.println("ActionFactory cmd");
		System.out.println(cmd);
		switch (cmd) {
		case "login":
			action = new Login();
			break;
			
		case "reservationListUI":
			action = new ReservationListUIAction();
			break;
			
		case "uncompletedReservationListAction":
			action = new UncompletedReservationListAction();
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
		case "resetPassword":
			action = new resetPassword();
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
			action = new idAdult();
			break;
		case "kkFilterUI":
			action = new KKFilterUI();
			break;
		case "searchForKKWithOptions":
			action = new SearchForKKWithOptions();
			break;
		case "kkSearchResultUI":
			action = new KKSearchResultUI();
			break;
		case "kkDetailUI":
			action = new KKDetailUI();
			break;
		default:
			action = new wrongCmd();
			break;
		}
		
		return action;
	}
}
