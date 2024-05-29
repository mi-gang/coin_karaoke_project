package com.oopsw.controller.action;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.oopsw.controller.Action;
import com.oopsw.controller.Url;
import com.oopsw.model.vo.ReservationVO;
import com.oopsw.service.ReservationRoomInfoVO;
import com.oopsw.service.ReservationService;

public class AddReservationUI implements Action {
	static final float TABLE_WIDTH_REM = 252.7f;
	static final float MIN_TO_REM = TABLE_WIDTH_REM / (24 * 60);// 1분당 width 구하기
	static final int CLEANING_TIME_SEC = 15;

	@Override
	public Url execute(HttpServletRequest request) {
		int kkId = Integer.parseInt(request.getParameter("kkId"));
		List<ReservationRoomInfoVO> list = new ReservationService().getRoomReservationStatusList(kkId);
		System.out.println("list: " + list);
		List<List<LinkedHashMap<String, String>>> widthListList = new ArrayList<>(); //예약테이블의 한 행에 나열될 div의 정보
		for (ReservationRoomInfoVO infoVO : list) {
			widthListList.add(getWidthList(infoVO));
		}
		request.setAttribute("infoList", list);
		request.setAttribute("widthListList", widthListList);
		System.out.println("widthListList: " + widthListList);
		request.setAttribute("cleaningTimeSec", getWidthByMin(CLEANING_TIME_SEC) + "rem");
		return new Url("jsp/addReservationUI.jsp", Url.FORWARD);
	}

	private List<LinkedHashMap<String, String>> getWidthList(ReservationRoomInfoVO list) {
		List<LinkedHashMap<String, String>> widthList = new ArrayList<>();

		int size = list.getReservationVOs().size();
		for (int i = 0; i < size; i++) {
			LinkedHashMap<String, String> temp = new LinkedHashMap<>();
			ReservationVO rVO = list.getReservationVOs().get(i);
			//청소시간을 고려하여 실사용시간 전후 15분도 예약이 잡혀있는 것으로 취급한다.
			applyCleaningTime(rVO);
			temp.put("width", getWidthFromReservation(rVO));
			temp.put("offset", getOffsetFromReservation(rVO));
			temp.put("type", "usedTime");
			
			widthList.add(temp);
			System.out.println("예약추가Action: " + rVO.getReservationId() + " : " + temp.get("width"));
		}
		return widthList;
	}

	private void applyCleaningTime(ReservationVO rVO) {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime startOffset = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), now.getHour(),
				0, 0);
		if(rVO.getStartTime().isBefore(startOffset.plusMinutes(15))){
			//예약자의 정보를 숨기기 위해 예약이 언제 시작되었는지 숨긴다.
			rVO.setStartTime(startOffset);
		}else{
			rVO.setStartTime(rVO.getStartTime().minusMinutes(CLEANING_TIME_SEC));
		}

		rVO.setEndTime(rVO.getEndTime().plusMinutes(CLEANING_TIME_SEC));
	}

	private String getWidthFromReservation(ReservationVO vo) {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime startOffset = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), now.getHour(),
				0, 0);
		LocalDateTime startTime = vo.getStartTime();
		LocalDateTime endTime = vo.getEndTime();
		
		long timeDiffMin = getTimeDiffMin(startTime  , endTime);
		if(startTime.isBefore(startOffset)){
			 timeDiffMin = getTimeDiffMin(startOffset, endTime);
		}
		
		return getWidthByMin(timeDiffMin) + "rem";
	}

	private String getOffsetFromReservation(ReservationVO reservationVO) {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime startOffset = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), now.getHour(),
				0);// 예약테이블이 시작하는 시
		LocalDateTime startTime = reservationVO.getStartTime();

		if(startTime.isBefore(startOffset)){
			return "0rem";
		}
		long timeDiffMin = getTimeDiffMin(startOffset, startTime);

		return getWidthByMin(timeDiffMin) + "rem";
	}

	private float getWidthByMin(long timeDiffMin) {
		if(timeDiffMin <= 0){
			return 0f;
		}
		return Math.round(timeDiffMin * MIN_TO_REM * 100)/100.0f;
	}

	private long getTimeDiffMin(Temporal startTime, Temporal endTime) {
		return Duration.between(startTime, endTime).toMinutes();

	}

}
