package com.oopsw.controller;

import com.oopsw.controller.action.Login;
import com.oopsw.controller.action.ReservationListUIAction;
import com.oopsw.controller.action.UncompletedReservationListAction;
import com.oopsw.controller.action.wrongCmd;

public class ActionFactory {

	public Action getAction(String cmd){
		Action action = null;
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

		default:
			action = new wrongCmd();
			break;
		}
		
		return action;
	}
}
