<%@page import="java.time.LocalDateTime" %>
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
			<!DOCTYPE html>
			<html lang="ko">

			<head>
				<meta charset="UTF-8">
				<meta name="viewport" content="width=device-width, initial-scale=1.0">
				<title>메인화면</title>
				<link rel="preconnect" href="https://fonts.googleapis.com">
				<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
				<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap"
					rel="stylesheet">

				<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
					integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
					crossorigin="anonymous">
				<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
					integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
					crossorigin="anonymous"></script>

				<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
				<link rel="stylesheet" href="css/common.css">
				<link rel="stylesheet" href="css/mainUI.css">

				<link rel="stylesheet" href="css/starRate.css">
				<script src="js/starRate.js">

				</script>
			</head>

			<body>
				<!-- 모바일 컨테이너 -->
				<div id="mobileContainer">
					<!--헤더-->
					<header>
						<img src="img/logo.svg" width="45" alt="이전 페이지 이동">
					</header>
					<!-- 컨텐츠 컨테이너 -->
					<div id="container">
						<section id="recommend-wrapper" class="wrapper">

							<div id="recommend-header" class="wrapper-header">
								<span class="header-title">근처 추천 노래방</span>
								<div id="regionWrapper">
									<div class="regionBox">
										<button class="label">
											이용 지역<img src="img/down_arrow.svg" alt="드롭다운" />
										</button>

										<ul class="regionList">
											<li class="regionItem">서울 전체</li>
											<li class="regionItem">강남구</li>
											<li class="regionItem">강동구</li>
											<li class="regionItem">강북구</li>
											<li class="regionItem">강서구</li>
											<li class="regionItem">관악구</li>
											<li class="regionItem">광진구</li>
											<li class="regionItem">구로구</li>
											<li class="regionItem">금천구</li>
											<li class="regionItem">노원구</li>
											<li class="regionItem">도봉구</li>
											<li class="regionItem">동대문구</li>
											<li class="regionItem">동작구</li>
											<li class="regionItem">마포구</li>
											<li class="regionItem">서대문구</li>
											<li class="regionItem">서초구</li>
											<li class="regionItem">성동구</li>
											<li class="regionItem">성북구</li>
											<li class="regionItem">송파구</li>
											<li class="regionItem">양천구</li>
											<li class="regionItem">영등포구</li>
											<li class="regionItem">용산구</li>
											<li class="regionItem">은평구</li>
											<li class="regionItem">종로구</li>
											<li class="regionItem">중구</li>
											<li class="regionItem">중랑구</li>
										</ul>
									</div>
								</div>
							</div>
							<div id="KK-container">
								<c:forEach var="kk" items="${recommendKKList}">
									<div class="card">
										<img src="img/representativeKKImg1.png" class="card-img-top"
											alt="${kk.getName()}">
										<div class="card-body">
											<div class="card-title">${kk.getName()}</div>
											<div class="starContainer">
												<span class="starRate"
													data-star-rate="${kk.getStarRating() }">${kk.getStarRating()
													}</span>
												<div class="rating-wrap">
													<div class="rating">
														<div class="overlay"></div>
													</div>
												</div>
											</div>
											<div class="KK-usability">
												<span>777m</span> <span>혼잡도: <span class="normal"
														data-crowded="0.5">보통</span></span>
											</div>
										</div>
									</div>

								</c:forEach>
							</div>
						</section>
						<section id="upcoming-wrapper" class="wrapper">
							<div id="upcoming-header" class="wrapper-header">
								<span class="header-title">다가오는 예약 일정</span> <a
									href="controller?cmd=reservationListUI">전체보기 <img src="img/arrow_right.svg"
										alt=""></a>
							</div>
							<c:choose>
								<c:when test="${empty userId}">
									<div id="invisibleUnLoginUser" class="invisibleUnLoginUser on">
										<div class="invisibleWrapper">
											<p>최근에 예약한 노래방이 어디더라?</p>
											<a href="controller?cmd=loginUI" style="text-decoration:none">
												<div id="reviewLoginBtn" class="reviewLoginBtn">로그인하고 최근에 예약한 노래방 보기
												</div>
											</a>
										</div>
									</div>
								</c:when>
								<c:when test="${upR.getReservationId() == 0}">

									<div id="reservation_content_wrapper">
										<div id="reservation_status_wrapper">

											<span id="reservation_status"></span>
										</div>
										<div id="reservation_content" data-reservation-id="">
											<div id="KK_img">
												<img src="img/KK_img.svg" />
											</div>
											<div id="reservation_detail_wrapper">
												<div id="reservation_detail">
													<div id="reservation_detail_row">
														<span id="karaoke_name">대기중인 예약이 없습니다.</span>
													</div>
													<div id="reservation-time">
														<div id="reservation-start-time">
															<span id="reservation-start-hour"></span> <span>:</span>
															<span id="reservation-start-minute"></span>
														</div>
														<span>-</span>
														<div id="reservation-end-time">
															<span id="reservation-end-hour"></span> <span>:</span> <span
																id="reservation-end-minute"></span>
														</div>
													</div>
												</div>
												<div id="button_wrapper"></div>
											</div>
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<div id="reservation_content_wrapper">
										<div id="reservation_status_wrapper">

											<span
												id="reservation_status">${upR.getStartTime().isAfter(LocalDateTime.now())
												? "예약" : "이용중"}</span>
										</div>
										<div id="reservation_content" data-reservation-id="${upR.getReservationId()}">
											<div id="KK_img">
												<img src="img/KK_img.svg" />
											</div>
											<div id="reservation_detail_wrapper">
												<div id="reservation_detail">
													<div id="reservation_detail_row">
														<span id="karaoke_name">${upR.getKKname()}</span>
														<!-- <img src="/img/arrow_right.svg" id="arrow_right" /> -->
													</div>
													<div id="reservation-time">
														<div id="reservation-start-time">
															<span id="reservation-start-hour">${String.format("%02d",
																upR.getStartTime().getHour())}</span>
															<span>:</span> <span
																id="reservation-start-minute">${String.format("%02d",
																upR.getStartTime().getMinute())}</span>
														</div>
														<span>-</span>
														<div id="reservation-end-time">
															<span id="reservation-end-hour">${String.format("%02d",
																upR.getEndTime().getHour())}</span>
															<span>:</span> <span
																id="reservation-end-minute">${String.format("%02d",
																upR.getEndTime().getMinute())}</span>
														</div>
													</div>
												</div>
												<div id="button_wrapper">
													<button type="button" class="submit_button" data-bs-toggle="modal"
														data-bs-target="#addTimeModal">
														시간 추가</button>
													<button class="cancle_button" data-bs-toggle="modal"
														data-bs-target="#cancleReservationModal1">예약 취소</button>
												</div>
											</div>
										</div>
									</div>
								</c:otherwise>
							</c:choose>

						</section>
						<section id="chart-wrapper" class="wrapper">
							<div id="chart-header" class="wrapper-header">
								<span class="header-title">인기 차트</span>
								<div id="brand-container">
									<button class="brand-activate">TJ</button>
									<button>KY</button>
								</div>
							</div>
						</section>
					</div>
					<!-- 하단 메뉴바 -->
					<nav>
						<div>
							<img src="img/mainPageIcon.svg" alt="메인 페이지" />
						</div>
						<div>
							<img src="img/searchIcon.svg" alt="노래방 검색 페이지" />
						</div>
						<div>
							<img src="img/musicIcon.svg" alt="노래 검색 페이지" />
						</div>
						<div>
							<img src="img/reservationIcon.svg" alt="나의 예약 내역 페이지" />
						</div>
						<div>
							<img src="img/userIcon.svg" alt="마이페이지" />
						</div>
					</nav>

				</div>
				<!-- 시간 추가 모달 1 -->
				<div class="modal" id="addTimeModal">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<!-- Modal Header -->
							<div class="modal-header">
								<!-- <div id="modal-title-wrapper"> -->
								<!-- <h4 class="modal-title">시간 추가</h4> -->
								<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
								<!-- </div> -->
							</div>

							<!-- Modal body -->
							<div class="modal-body">
								<div id="add1-modal-body-wrapper">
									<h4 class="modal-title">시간 추가</h4>
									<div id="add1-modal-body-title">
										<span>세븐스타코인노래연습장 철산역점</span>
									</div>
									<div id="add1-modal-body-contents">
										<div class="add1-modal-body-content">
											<span class="add1-modal-body-content-title">기존 예약 시간</span> <span
												class="add1-modal-body-content-time">16:00 - 17:30</span>
										</div>
										<hr />
										<div class="add1-modal-body-content">
											<span class="add1-modal-body-content-title">추가 가능 시간</span> <span
												class="add1-modal-body-content-time add1-addTime">1시간
												34분</span>
										</div>
										<hr />
										<div class="add1-modal-body-content">
											<span class="add1-modal-body-content-title">추가시간</span>
											<div id="add1-add-time-arrow-img">
												<div>
													<span class="set-hour target-hour">--</span> <span>:</span> <span
														class="set-minute target-minute">--</span>
												</div>
												<button type="button" class="add1-add-time-arrow" data-bs-toggle="modal"
													data-bs-target="#addTimeModal2"></button>
											</div>
										</div>
										<span id="add1-notice"></span>
									</div>
								</div>
							</div>

							<!-- Modal footer -->
							<div class="modal-footer">
								<div>
									<button type="button" class="submit_button add-button" id="add1-add-time-button"
										data-bs-toggle="modal" data-bs-target="#addTimeModal3" data-bs-dismiss="modal">
										추가하기</button>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- 시간 추가 모달 2 : 추가 시간 설정 -->
				<!-- The Modal -->
				<div class="modal" id="addTimeModal2">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<!-- Modal Header -->
							<div class="modal-header">
								<h4 class="modal-title">이용시간</h4>
								<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
							</div>

							<!-- Modal body -->
							<div class="modal-body">
								<div id="add2-modal-body-wrapper">
									<div id="add2-modal-time">
										<span>시</span> <span>분</span>
									</div>
									<div id="add2-modal-time-setting">
										<input type="number" class="add2-time-setting-input" id="setting-hour"
											placeholder="00" /> <span>:</span> <input type="number"
											class="add2-time-setting-input" id="setting-minute" placeholder="00" />
									</div>
								</div>
							</div>

							<!-- Modal footer -->
							<div class="modal-footer">
								<button type="button" class="submit_button add-button inactivate"
									id="add2-add-time-button" data-bs-toggle="modal" data-bs-target="#addTimeModal2"
									disabled>설정</button>
							</div>
						</div>
					</div>
				</div>

				<!-- 시간 추가 모달 3 : 예약 내용 확인 -->
				<!-- The Modal -->
				<div class="modal" id="addTimeModal3">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<!-- Modal Header -->
							<div class="modal-header">
								<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
							</div>

							<!-- Modal body -->
							<div class="modal-body">
								<div id="add1-modal-body-wrapper">
									<h4 class="modal-title">예약 내용을 확인해주세요 :)</h4>
									<div id="add1-modal-body-title">
										<span>세븐스타코인노래연습장 철산역점</span>
									</div>
									<div id="add1-modal-body-contents">
										<div class="add1-modal-body-content">
											<span class="add1-modal-body-content-title">추가 시간</span>
											<div id="add1-add-time-arrow-img">
												<div>
													<span class="set-hour"></span> <span>:</span> <span
														class="set-minute"></span>
												</div>
											</div>
										</div>
										<hr />
										<div class="add1-modal-body-content">
											<span class="add1-modal-body-content-title">이용 종료 시간</span>
											<div id="add1-add-time-arrow-img">
												<div>
													<span id="end-hour">18</span> <span>:</span> <span
														id="end-minute">00</span>
												</div>
											</div>
										</div>
										<div id="add3-pay-amount">
											<span id="add3-notice">결제금액 : </span> <span id="add3-notice"></span>
											<span id="add3-notice">원</span>
										</div>
									</div>
								</div>
							</div>

							<!-- Modal footer -->
							<div class="modal-footer">
								<button type="button" class="submit_button add-button" id="time-setting-button"
									data-bs-toggle="modal" data-bs-target="#addTimeModal4" data-bs-dismiss="modal">
									결제</button>
							</div>
						</div>
					</div>
				</div>

				<!-- 시간 추가 모달 4 : 추가 완료 모달 -->
				<!-- The Modal -->
				<div class="modal" id="addTimeModal4">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<!-- Modal Header -->
							<div class="modal-header">
								<!-- <h4 class="modal-title">이용시간</h4> -->
								<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
							</div>

							<!-- Modal body -->
							<div class="modal-body">
								<div id="add4-modal-body-wrapper">
									<span>시간 추가 완료되었습니다.</span>
								</div>
							</div>

							<!-- Modal footer -->
							<div class="modal-footer">
								<button type="button" class="submit_button add-button" id="add2-add-time-button"
									data-bs-toggle="modal" data-bs-dismiss="modal">닫기</button>
							</div>
						</div>
					</div>
				</div>

				<!-- 예약 취소 모달 1 -->
				<div class="modal" id="cancleReservationModal1">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<!-- Modal Header -->
							<div class="modal-header">
								<!-- <h4 class="modal-title">이용시간</h4> -->
								<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
							</div>

							<!-- Modal body -->
							<div class="modal-body">
								<div class="cancle-modal-body-wrapper">
									<span class="cancle-modal-title">예약을 취소하시겠습니까?</span> <span
										class="cancle-modal-content">노래방 이용 시작 시간 기준 <b>20분</b> 내
										취소 시 취소 수수료가 부과됩니다.
									</span>
								</div>
							</div>

							<!-- Modal footer -->
							<div class="modal-footer">
								<button type="button" class="submit_button add-button" id="add2-add-time-button"
									data-bs-toggle="modal" data-bs-dismiss="modal"
									data-bs-target="#cancleReservationModal2">
									취소하기</button>
							</div>
						</div>
					</div>
				</div>

				<!-- 예약 취소 모달 2 -->
				<div class="modal" id="cancleReservationModal2">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<!-- Modal Header -->
							<div class="modal-header">
								<!-- <h4 class="modal-title">이용시간</h4> -->
								<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
							</div>

							<!-- Modal body -->
							<div class="modal-body">
								<div class="cancle-modal-body-wrapper">
									<span class="cancle-modal-title">취소 완료</span> <span class="cancle-modal-content">예약이
										취소되었습니다. 자세한 내용은 나의 예약 -
										취소 내역에서 확인해주세요.</span>
								</div>
							</div>

							<!-- Modal footer -->
							<div class="modal-footer">
								<button type="button" class="submit_button add-button" id="add2-add-time-button"
									data-bs-toggle="modal" data-bs-dismiss="modal">닫기</button>
							</div>
						</div>
					</div>
				</div>

				<!-- <script src="js/reservation.js"></script> -->
				<script>
					//*** 추천 노래방 목록 ***
					updateStarContainer();
					function updateStarContainer() {
						$(".starContainer").each(function (i, item) {
							const
								rating = item.querySelector(".rating");
							const
								overlay = item.querySelector(".overlay");
							const
								rate = item.querySelector(".starRate").dataset.starRate;
							drawStarRate(rating, overlay, rate);
						});

					}
					const
						label = document.querySelector(".label");
					const
						options = document.querySelectorAll(".regionItem");
					const
						searchOptions = document.querySelectorAll(".optionItem");

					// 클릭한 옵션의 텍스트를 라벨 안에 넣음
					const
						handleSelect = function (item) {
							label.innerHTML = item.textContent;
							label.parentNode.classList.remove("active");
							updateRecommendKKContainer(item.textContent);
						};
					// 옵션 클릭시 클릭한 옵션을 넘김
					options.forEach(function (option) {
						option.addEventListener("click", function () {
							handleSelect(option);
						});
					});
					// 라벨을 클릭시 옵션 목록이 열림/닫힘
					label.addEventListener("click", function () {
						if (label.parentNode.classList.contains("active")) {
							label.parentNode.classList.remove("active");
						} else {
							label.parentNode.classList.add("active");
						}
					});
					// 옵션 아이템 클릭시 테두리 색상 변경
					searchOptions.forEach(function (option) {
						option
							.addEventListener("click",
								function () {
									// 클릭한 옵션 아이템에만 active 클래스 추가
									option.classList.toggle("active");
									const
										svg = option.querySelector("img");
									svg.style.fill = option.classList
										.contains("active") ? "#e3cdff"
										: "currentColor";
								});
					});

					async function updateRecommendKKContainer(addressGu) {
						const res = await fetch("controller?cmd=nearRecommendKKList&myLocation=" + addressGu);
						const data = await res.json();
						let str = "";
						for (let i = 0; i < data.length; i++) {
							str += `<div class="card"><img src = "img/representativeKKImg1.png" class="card-img-top" alt = "` + data[i].name + `" >
        <div class="card-body">
            <div class="card-title">`+ data[i].name + `
            </div>
            <div class="starContainer">
                <span class="starRate"
                    data-star-rate="`+ data[i].starRating + `">` + data[i].starRating + `</span>
                <div class="rating-wrap">
                    <div class="rating">
                        <div class="overlay"></div>
                    </div>
                </div>
            </div>
            <div class="KK-usability">
                <span>777m</span> <span>혼잡도: <span
                    class="normal"
                    data-crowded="0.5">보통</span></span>
            </div>
        </div>
                    </div > `;
						}

						$("#KK-container").html(str);
						updateStarContainer();
					}

					//*****다가오는 예약*****//
					//***Modal1 시간추가 준비모달***
					//메인페이지-시간추가 버튼 클릭시 초기화
					$("#reservation_content .submit_button").on("click", initAddTimeModal);
					async function initAddTimeModal() {
						const kkName = $("#add1-modal-body-title span");
						const oriTime = $(".add1-modal-body-content-time:first");
						const addiTime = $(".add1-modal-body-content-time:last");
						const subBtn = $("#addTimeModal .submit_button");

						kkName.text($("#karaoke_name").text());
						oriTime.text($("#reservation-time").get()[0].innerText);
						const addiMinutes = await getAdditioinalMinutesInfo();
						addiTime.text(minToHoursString(addiMinutes));
						addiTime.data("minutes", addiMinutes);
						inactivateBtn(subBtn);
					}
					async function getAdditioinalMinutesInfo(rId) {
						// const res = await fetch("controller?cmd=additionalTimeInfo&reservationId=" + getReservationId());
						// const data = await res.json();
						let minutes = 90;
						//let minutes = data.minutes;
						return minutes;
					}
					function getReservationId() {
						return $("#reservation_content").data("reservationId");
					}
					function minToHoursString(min) {
						let hour = parseInt(min / 60);
						let minute = min - (hour * 60);
						return hour + "시간 " + minute + "분";
					}

					//Modal1 submit()
					$("#add1-add-time-button").on("click", submitModal1);
					function submitModal1() {
						initModal3();
					}
					function initModal3() {
						//어차피 기간은 표시되지 않으므로 시/분만 계산한 후 넘긴다.
						const endTime = new Date();
						const oriHour = parseInt($("#reservation-end-hour").text());
						const oriMinute = parseInt($("#reservation-end-minute").text());
						const addMinute = parseInt($("#add1-add-time-arrow-img").data("minute"));
						endTime.setHours(oriHour);
						endTime.setMinutes(oriMinute + addMinute);

						$("#end-hour").text(endTime.getHours());
						$("#end-minute").text(endTime.getMinutes());
						$("#add3-notice:nth-child(2)").text(addMinute * 100);
					}

					//***Modal2 이용시간 입력모달***
					//이용시간 입력모달 유효성검사(입력시)
					$("#setting-hour, #setting-minute").on("change", checkUsingTime);
					function checkUsingTime() {
						const hours = parseInt($("#setting-hour").val()) || 0;
						const minutes = parseInt($("#setting-minute").val()) || 0;
						const usingSub = $("#add2-add-time-button");
						const addiTime = getAddiTime();
						if (hours + minutes <= 0) {
							alert("시간을 입력해주세요");
							inactivateBtn(usingSub);
							return;
						} else if (hours * 60 + minutes > addiTime) {
							alert("최대 이용 가능 시간은 " + minToHoursString(addiTime) + "입니다.");
							inactivateBtn(usingSub);
							return;
						} else {
							activateBtn(usingSub);

						}

					}
					function getAddiTime() {
						return parseInt($(".add1-modal-body-content-time:last").data("minutes"));
					}
					function inactivateBtn(btn) {
						btn.addClass("inactivate");
						btn.attr("disabled", true);
					}
					function activateBtn(btn) {
						btn.removeClass("inactivate");
						btn.attr("disabled", false);
					}

					//이용시간 입력모달 설정시
					$("#add2-add-time-button").on("click", submitModal2);
					function submitModal2() {
						//유효성검사는 입력시에 전부 해결된다고 가정
						//modal1에 값 채우기
						const time = $("#add1-add-time-arrow-img");
						const hour = $("#add1-add-time-arrow-img .set-hour");
						const minute = $("#add1-add-time-arrow-img .set-minute");
						hour.text(String(parseInt($("#setting-hour").val()) || 0).padStart(2, "0"));
						minute.text(String(parseInt($("#setting-minute").val()) || 0).padStart(2, "0"));
						time.data("minute", (parseInt($("#setting-hour").val()) || 0) * 60 + (parseInt($("#setting-minute").val()) || 0));
						//modal1에서 필요한 값이 이용시간 하나밖에 없으므로 바로 activate
						const modal1Sub = $("#addTimeModal .submit_button");
						activateBtn(modal1Sub);
					}

					//***Modal3 시간추가 내용확인 모달***
					$("#time-setting-button").on("click", submitModal3);
					async function submitModal3() {
						const addMinute = parseInt($("#add1-add-time-arrow-img").data("minute"));
						const res = await fetch("controller?cmd=payAdditioanlTime&reservationId=" + getReservationId() + "&additionalTime=" + addMinute);
						const data = await res.json();
						if (!data.result) {
							alert("예약에 실패하였습니다! 다른 사람이 먼저 해당 시간에 예약했을 수 있습니다.");
							location.href = "controller?cmd=mainUI";
						}
					}

					// 인기차트
					// 브랜드 버튼
					$("#brand-container button").on("click", activateBrand);
					function activateBrand(e) {
						$("#brand-container button").removeClass("brand-activate");
						e.target.classList = "brand-activate";
						updateChart(e.target.textContent); //TJ, KY 중 하나를 넘긴다.
					}

					function updateChart(brand) {
						const
							songNumber = $("???");
						songNumber.text(songNumber.attr("data-" + brand));
					}
				</script>
			</body>

			</html>