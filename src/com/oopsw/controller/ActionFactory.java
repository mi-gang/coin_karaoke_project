package com.oopsw.controller;

import com.oopsw.controller.action.AddPlaylist;
import com.oopsw.controller.action.CheckMusicbymyplaylist;
import com.oopsw.controller.action.KKDetailUI;
import com.oopsw.controller.action.KKFilterUI;
import com.oopsw.controller.action.KKSearchResultUI;
import com.oopsw.controller.action.AddInquireAction;
import com.oopsw.controller.action.AddMusic;
import com.oopsw.controller.action.AddReservationUIAction;
import com.oopsw.controller.action.AddReviewAction;
import com.oopsw.controller.action.AdditionalTimeInfoAction;
import com.oopsw.controller.action.CancelReservationAction;
import com.oopsw.controller.action.CanceledReservationListAction;
import com.oopsw.controller.action.CompletedReservationListAction;
import com.oopsw.controller.action.DeletePlaylist;
import com.oopsw.controller.action.DeleteReviewAction;
import com.oopsw.controller.action.IsValidTimeForReservationAction;
import com.oopsw.controller.action.Login;
import com.oopsw.controller.action.LoginUI;
import com.oopsw.controller.action.Logout;
import com.oopsw.controller.action.MyReviewListAction;
import com.oopsw.controller.action.MyReviewListUIAction;
import com.oopsw.controller.action.MypagePlaylistUI;
import com.oopsw.controller.action.MypageUIAction;
import com.oopsw.controller.action.PayAdditionalTimeAction;
import com.oopsw.controller.action.PayReservationAction;
import com.oopsw.controller.action.PlaylistMusicList;
import com.oopsw.controller.action.PlaylistMusicNumber;
import com.oopsw.controller.action.PlaylistMusicUI;
import com.oopsw.controller.action.PlaylistTitle;
import com.oopsw.controller.action.ReservationListUIAction;
import com.oopsw.controller.action.RoomReservationStatusListAction;
import com.oopsw.controller.action.UncompletedReservationListAction;
import com.oopsw.controller.action.MusicListUI;
import com.oopsw.controller.action.MyPlaylist;
import com.oopsw.controller.action.MyPlaylistmusicUI;
import com.oopsw.controller.action.SearchForKKWithOptions;
import com.oopsw.controller.action.UpcomingReservation;
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

	public Action getAction(String cmd) {
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
		case "logout":
			action = new Logout();
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
		case "musicListUI":
			action = new MusicListUI();
			break;
		case "checkMusicbymyplaylist":
			action = new CheckMusicbymyplaylist();
			break;
		case "addPlaylist":
			action=new AddPlaylist();
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
		case "mypageUIAction":
			action = new MypageUIAction();
			break;	
		// reservation
		case "upcomingReservation":
			action = new UpcomingReservation();
			break;	
		case "addReservationUIAction":
			action = new AddReservationUIAction();
			break;		
		case "roomReservationStatusListAction":
			action = new RoomReservationStatusListAction();
			break;	
		case "isValidTimeForReservationAction":
			action = new IsValidTimeForReservationAction();
			break;	
		case "payReservationAction":
			action = new PayReservationAction();
			break;	
		case "reservationListUIAction":
			action = new ReservationListUIAction();
			break;
		/*case "uncompletedReservationListAction":
			action = new UncompletedReservationListAction();
			break;*/
		case "completedReservationListAction":
			action = new CompletedReservationListAction();
			break;
		case "canceledReservationListAction":
			action = new CanceledReservationListAction();
			break;
		case "additionalTimeInfoAction":
			action = new AdditionalTimeInfoAction();
			break;
		case "payAdditionalTimeAction":
			action = new PayAdditionalTimeAction();
			break;
		case "cancelReservationAction":
			action = new CancelReservationAction();
			break;	
		// review
		case "myReviewListUIAction":
			action = new MyReviewListUIAction();
			break;	
		case "myReviewListAction":
			action = new MyReviewListAction();
			break;	
		case "addReviewAction":
			action = new AddReviewAction();
			break;
		case "deleteReviewAction":
			action = new DeleteReviewAction();
			break;
		// inquire
		case "addInquireAction":
			action = new AddInquireAction();
			break;	
		case "mypagePlaylistUI":
			action=new MypagePlaylistUI();
			break;
		case "myPlaylist":
			action=new MyPlaylist();
			break;
		case "playlistMusicNumber":
			action=new PlaylistMusicNumber();
			break;
		case "deletePlaylist":
				 action= new DeletePlaylist();
				 break;
		case "addMusic":
			action=new AddMusic();
			break;
		case "deleteMusic":
			action=new DeleteMusic();
			break;
		case "playlistMusicUI":
		action=new PlaylistMusicUI();
		break;
		case "playlistTitle":
			action=new PlaylistTitle();
			break;
		case "playlistMusicList":
			action=new PlaylistMusicList();
			break;
		default:
			action = new wrongCmd();
			break;
	
		}

		return action;
	}
}
