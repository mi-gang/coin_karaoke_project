package com.oopsw.controller;

import com.oopsw.controller.action.KKFilterUI;
import com.oopsw.controller.action.Login;
import com.oopsw.controller.action.wrongCmd;

public class ActionFactory {

	public Action getAction(String cmd){
		Action action = null;
		switch (cmd) {
		case "login":
			action = new Login();
			break;

		case "musicListUI":
			action = new MusicListUI();
			break;
		
		case "checkMusicbymyPlaylist":
			action=new CheckMusicbymyplaylist();
			break;
		
		case "addMusic":
		action=new AddMusic();
		break;
		
		
		default:
			action = new wrongCmd();
			break;
		}
		
		return action;
	}
}
