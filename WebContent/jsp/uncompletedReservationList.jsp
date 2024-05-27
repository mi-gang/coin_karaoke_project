<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- 이용 중 -->
<div class="reservation_content_wrapper status-1-2">
	<div id="reservation_status_wrapper">
		<span id="reservation_status">이용 중</span>
	</div>
	<div id="reservation_content">
		<div id="KK_img">
			<img src="img/KK_img.svg" />
		</div>
		<div id="reservation_detail_wrapper">
			<div id="reservation_detail">
				<div id="reservation_detail_row">
					<span id="karaoke_name">${reservationVOs.reservationId}</span>
					<img src="img/arrow_right.svg" id="arrow_right" />
				</div>
				<div id="reservation-time">
				<%-- <fmt:parseDate var="reservationDate" value="${reservationVOs.startTime}"
							pattern="yyyy-MM-dd"
						/> <fmt:formatDate value="${reservationDate}" pattern="yyyy-MM-dd" /> --%>
					<div>${reservationVOs.startTime}</div>
					<div id="reservation-start-time">
						<span id="reservation-start-hour">16</span> <span>:</span> <span
							id="reservation-start-minute">00</span>
					</div>
					<span>-</span>
					<div id="reservation-end-time">
						<span id="reservation-end-hour">${reservationVOs.startTime}</span> <span>:</span> <span
							id="reservation-end-minute">00</span>
					</div>
				</div>
			</div>
			<div id="button_wrapper">
				<button type="button" class="submit_button" data-bs-toggle="modal"
					data-bs-target="#addTimeModal">시간
					추가</button>
				<button class="cancle_button" data-bs-toggle="modal"
					data-bs-target="#addInquireModal">
					문의/신고</button>
			</div>
		</div>
	</div>
</div>