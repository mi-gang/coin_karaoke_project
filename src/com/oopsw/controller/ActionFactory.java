package com.oopsw.controller;

import com.oopsw.controller.action.AddBookmarkAction;
import com.oopsw.controller.action.AddInquireAction;
import com.oopsw.controller.action.AddPlaylist;
import com.oopsw.controller.action.AddReservationUI;
import com.oopsw.controller.action.AddMusic;
import com.oopsw.controller.action.AddReservationUIAction;
import com.oopsw.controller.action.AddReviewAction;
import com.oopsw.controller.action.AddUser;
import com.oopsw.controller.action.AddUserUI;
import com.oopsw.controller.action.AddableTime;
import com.oopsw.controller.action.AdditionalTimeInfoAction;
import com.oopsw.controller.action.AmountStarReviewListByType;
import com.oopsw.controller.action.CancelReservationAction;
import com.oopsw.controller.action.CanceledReservationListAction;
import com.oopsw.controller.action.CheckKKBookmarkAction;
import com.oopsw.controller.action.CheckMusicbymyplaylist;
import com.oopsw.controller.action.CompletedReservationListAction;
import com.oopsw.controller.action.DeleteBookmarkAction;
import com.oopsw.controller.action.DeletePlaylist;
import com.oopsw.controller.action.DeleteReviewAction;
import com.oopsw.controller.action.FindPasswordUI;
import com.oopsw.controller.action.GetNickname;
import com.oopsw.controller.action.IsAdult;
import com.oopsw.controller.action.IsExistEmail;
import com.oopsw.controller.action.IsValidTimeForReservationAction;
import com.oopsw.controller.action.KKDetailUI;
import com.oopsw.controller.action.KKFilterUI;
import com.oopsw.controller.action.KKSearchResultUI;
import com.oopsw.controller.action.Login;
import com.oopsw.controller.action.LoginUI;
import com.oopsw.controller.action.Logout;
import com.oopsw.controller.action.MainUI;
import com.oopsw.controller.action.MusicListUI;
import com.oopsw.controller.action.MyPageUIAction;
import com.oopsw.controller.action.MyReviewListAction;
import com.oopsw.controller.action.MyReviewListUIAction;
import com.oopsw.controller.action.MypagePlaylistUI;
import com.oopsw.controller.action.NearRecommendKKList;
import com.oopsw.controller.action.PayAdditionalTimeAction;
import com.oopsw.controller.action.PayReservationAction;
import com.oopsw.controller.action.PlaylistMusicList;
import com.oopsw.controller.action.PlaylistMusicNumber;
import com.oopsw.controller.action.PlaylistMusicUI;
import com.oopsw.controller.action.PlaylistTitle;
import com.oopsw.controller.action.ReservationListUIAction;
import com.oopsw.controller.action.ResetPassword;
import com.oopsw.controller.action.ReviewListAction;
import com.oopsw.controller.action.RoomReservationStatusListAction;
import com.oopsw.controller.action.SearchForKKWithOptions;
import com.oopsw.controller.action.SendValidationNumber;
import com.oopsw.controller.action.UncompletedReservationListAction;
import com.oopsw.controller.action.UpdateNickname;
import com.oopsw.controller.action.UpdatePassword;
import com.oopsw.controller.action.UpdateUserUI;
import com.oopsw.controller.action.MyPlaylist;
import com.oopsw.controller.action.MyPlaylistmusicUI;
import com.oopsw.controller.action.SearchForKKWithOptions;
import com.oopsw.controller.action.UpdatePlaylistTitle;
import com.oopsw.controller.action.wrongCmd;

public class ActionFactory {

	public Action getAction(String cmd) {
		Action action = new wrongCmd();
		switch (cmd) {
			case "login":
				action = new Login();
				break;
			case "reservationListUI":
				action = new ReservationListUIAction();
				break;
			case "loginUI":
				action = new LoginUI();
				break;
			case "logout":
				action = new Logout();
				break;
			case "addUserUI":
				action = new AddUserUI();
				break;
			case "addUser":
				action = new AddUser();
				break;
			case "isExistEmail":
				action = new IsExistEmail();
				break;
			case "findPasswordUI":
				action = new FindPasswordUI();
				break;
			case "sendValidationNumber":
				action = new SendValidationNumber();
				break;
			case "resetPassword":
				action = new ResetPassword();
				break;
			case "updateUserUI":
				action = new UpdateUserUI();
				break;
			case "getNickname":
				action = new GetNickname();
				break;
			case "updateNickname":
				action = new UpdateNickname();
				break;
			case "updatePassword":
				action = new UpdatePassword();
				break;
			case "isAdult":
				action = new IsAdult();
				break;
			case "mainUI":
				action = new MainUI();
				break;
			case "nearRecommendKKList":
				action = new NearRecommendKKList();
				break;
			case "addableTime":
				action = new AddableTime();
				break;
			case "addReservationUI":
				action = new AddReservationUI();
				break;
			case "musicListUI":
				action = new MusicListUI();
				break;
			case "checkMusicbymyplaylist":
				action = new CheckMusicbymyplaylist();
				break;
			case "addPlaylist":
				action = new AddPlaylist();
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
				action = new MyPageUIAction();
				break;
			// ***** reservation *****
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
			case "uncompletedReservationListAction":
				action = new UncompletedReservationListAction();
				break;
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
			// ***** review *****
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
			case "reviewListAction":
				action = new ReviewListAction();
				break;
			// inquire
			case "addInquireAction":
				action = new AddInquireAction();
				break;
			case "checkKKBookmarkAction":
				action = new CheckKKBookmarkAction();
				break;
			case "addBookmarkAction":
				action = new AddBookmarkAction();
				break;
			case "deleteBookmarkAction":
				action = new DeleteBookmarkAction();
				break;
			case "amountStarReviewListByType":
				action = new AmountStarReviewListByType();
				break;
			//***** 플레이리스트 *****
			case "mypagePlaylistUI":
				action = new MypagePlaylistUI();
				break;
			case "myPlaylist":
				action = new MyPlaylist();
				break;
			case "playlistMusicNumber":
				action = new PlaylistMusicNumber();
				break;
			case "deletePlaylist":
				action = new DeletePlaylist();
				break;
			case "addMusic":
				action = new AddMusic();
				break;
			case "deleteMusic":
				action = new DeleteMusic();
				break;
			case "playlistMusicUI":
				action = new PlaylistMusicUI();
				break;
			case "playlistTitle":
				action = new PlaylistTitle();
				break;
			case "playlistMusicList":
				action = new PlaylistMusicList();
				break;
        case "updatePlaylistTitle":
			action=new UpdatePlaylistTitle();
			break;
			default:
				action = new wrongCmd();
				break;

		}

		return action;
	}
}
