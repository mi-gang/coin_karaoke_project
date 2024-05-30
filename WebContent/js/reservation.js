
$(document).ready(function() {
	$("#status-1").click();
});
// 예약 상태 탭 클릭 시


$(".reservations-status-button").on("click", function () {
  $(".reservations-status-button").removeClass("active");
  $(this).addClass("active");

  $(".reservation_content_wrapper").hide();

  // 이용중 / 이용 예정
  if ($(this).attr("id") == "status-1") {
    $.ajax({
      url: "controller?cmd=uncompletedReservationListAction",
      method: "POST",
      dataType: "json",
      success: function (response) {
        $("#reservation-contents-wrapper").empty();
        console.log(response);
        if (response == null) {
          $("#reservation-contents-wrapper").append(
            "<span>예약 내역이 없습니다.</span>"
          );
        } else {
          for (var i = 0; i < response.length; i++) {
            //+ day[startDay.getday()] + 요일).toString() 요일 변환 귀찮앗거 일단 안하는거롤
            let startDate = new Date(
              response[i].startTime.date.year,
              response[i].startTime.date.month - 1,
              response[i].startTime.date.day,
              response[i].startTime.time.hour,
              response[i].startTime.time.minute,
              response[i].startTime.time.second
            );
            let endDate = new Date(
              response[i].endTime.date.year,
              response[i].endTime.date.month - 1,
              response[i].endTime.date.day,
              response[i].endTime.time.hour,
              response[i].endTime.time.minute,
              response[i].endTime.time.second
            );

            let startDateFormat =
              startDate.getFullYear() +
              "년" +
              (startDate.getMonth() + 1) +
              "월" +
              startDate.getDate() +
              "일";
            let endDateFormat =
              endDate.getFullYear() +
              "년" +
              (endDate.getMonth() + 1) +
              "월" +
              endDate.getDate() +
              "일";

            let startHourFormat = startDate
              .getHours()
              .toString()
              .padStart(2, "0");
            let startMinuteFormat = startDate
              .getMinutes()
              .toString()
              .padStart(2, "0");

            let endHourFormat = endDate.getHours().toString().padStart(2, "0");
            let endMinuteFormat = endDate
              .getMinutes()
              .toString()
              .padStart(2, "0");

            let button2 =
              '<button class="cancel-button" data-bs-toggle="modal" data-bs-target="#cancelReservationModal1">예약 취소</button>';
            let reservationStatus = "이용 예정";

            const sysdate = new Date();

            console.log("sysdate.getTime()" + sysdate.getTime());
            console.log("startDate" + startDate.getTime());
            console.log("endDate" + endDate.getTime());
            console.log(sysdate.getTime() >= startDate.getTime());
            console.log(sysdate.getTime() <= endDate.getTime());

            if (
              sysdate.getTime() >= startDate.getTime() &&
              sysdate.getTime() <= endDate.getTime()
            ) {
              button2 =
                '<button class="cancel-button inquire-modal-button" data-bs-toggle="modal"data-bs-target="#addInquireModal">문의/신고</button>';
              reservationStatus = "이용 중";
            }

            result =
              '<div class="reservation-content-wrapper" id=' +
              response[i].reservationId +
              '><div class="reservation-status-wrapper">' +
              '<span class="reservation-status">' +
              reservationStatus +
              "</span></div>" +
              '<div class="reservation-content"><div id="KK_img"><img src="img/KK_img.svg" /></div>' +
              '<div class="reservation-detail-wrapper"><div class="reservation-detail"><div class="reservation-detail-row">' +
              '<span id="karaoke-name" class="kk-name">' +
              response[i].KKname +
              '</span><img src="img/arrow_right.svg" id="arrow_right" />' +
              '</div><div class="reservation-time">' +
              '<div>' +
              startDateFormat +
              ' - ' +
              endDateFormat +
              '</div><div class="reservation-time-info"><div class="reservation-start-time"><span class="reservation-start-hour">' +
              startHourFormat +
              '</span> <span>:</span>' +
              '<span class="reservation-start-minute">' +
              startMinuteFormat +
              '</span></div><span>-</span><div class="reservation-end-time">' +
              '<span class="reservation-end-hour">' +
              endHourFormat +
              '</span> <span>:</span> <span class="reservation-end-minute">' +
              endMinuteFormat +
              "</span></div>" +
              '</div></div></div><div class="button-wrapper"><button type="button" class="submit-button add-time-button" data-bs-toggle="modal"data-bs-target="#addTimeModal">시간 추가</button>' +
              button2 +
              "</div></div></div></div>";
            $("#reservation-contents-wrapper").append(result);
          }
        }
        $("#content-amount").text(response.length);
      },
    });
  }

  // 이용 완료
  if ($(this).attr("id") == "status-2") {
    $.ajax({
      url: "controller?cmd=completedReservationListAction",
      method: "POST",
      dataType: "json",
      success: function (response) {
        $("#reservation-contents-wrapper").empty();
        console.log(response);
        if (response == null) {
          $("#reservation-contents-wrapper").append(
            "<span>이용 내역이 없습니다.</span>"
          );
        } else {
          for (var i = 0; i < response.length; i++) {
            //+ day[startDay.getday()] + 요일).toString() 요일 변환 귀찮앗거 일단 안하는거롤
            let startDate = new Date(
              response[i].reservationVO.startTime.date.year,
              response[i].reservationVO.startTime.date.month - 1,
              response[i].reservationVO.startTime.date.day,
              response[i].reservationVO.startTime.time.hour,
              response[i].reservationVO.startTime.time.minute,
              response[i].reservationVO.startTime.time.second
            );
            let endDate = new Date(
              response[i].reservationVO.endTime.date.year,
              response[i].reservationVO.endTime.date.month - 1,
              response[i].reservationVO.endTime.date.day,
              response[i].reservationVO.endTime.time.hour,
              response[i].reservationVO.endTime.time.minute,
              response[i].reservationVO.endTime.time.second
            );

            let startDateFormat =
              startDate.getFullYear() +
              "년" +
              startDate.getMonth() +
              "월" +
              startDate.getDate() +
              "일";
            let endDateFormat =
              endDate.getFullYear() +
              "년" +
              endDate.getMonth() +
              "월" +
              endDate.getDate() +
              "일";

            let startHourFormat = startDate
              .getHours()
              .toString()
              .padStart(2, "0");
            let startMinuteFormat = startDate
              .getMinutes()
              .toString()
              .padStart(2, "0");

            let endHourFormat = endDate.getHours().toString().padStart(2, "0");
            let endMinuteFormat = endDate
              .getMinutes()
              .toString()
              .padStart(2, "0");

            let reviewButton = null;
            let inquireButton =
              '<button class="cancel-button inquire-modal-button" data-bs-toggle="modal"data-bs-target="#addInquireModal">문의/신고</button>';

            console.log(response[i].isReviewWritten);

            // 리뷰 작성 여부 처리
            if (response[i].isReviewWritten) {
              reviewButton =
                '<button type="button" disabled class="submit-button review-button2"> 리뷰 작성 완료 </button>';
            } else {
              reviewButton =
                '<button type="button" class="submit-button review-button1" data-bs-toggle="modal"data-bs-target="#addReviewModal">리뷰 작성</button>';
            }

            // 3일 이후 여부 처리
            const today = new Date();

            let diff = Math.abs(today.getTime() - endDate.getTime());
            diff = Math.floor(diff / (1000 * 60 * 60 * 24));
            //        console.log(diff);

            if (diff > 3) {
              reviewButton =
                '<button type="button" disabled class="submit-button review-button2"> 리뷰 작성 불가 </button>';
              inquireButton = "";
              if (response[i].isReviewWritten) {
                reviewButton =
                  '<button type="button" disabled class="submit-button review-button2"> 리뷰 작성 완료 </button>';
              }
            }

            result =
              '<div class="reservation-content-wrapper" id=' +
              response[i].reservationVO.reservationId +
              '><div class="reservation-status-wrapper">' +
              '<span class="reservation-status">이용 완료</span></div>' +
              '<div class="reservation-content"><div id="KK_img"><img src="img/KK_img.svg" /></div>' +
              '<div class="reservation-detail-wrapper"><div class="reservation-detail"><div class="reservation-detail-row">' +
              '<span id="karaoke-name" class="kk-name">' +
              response[i].reservationVO.KKname +
              '</span><img src="img/arrow_right.svg" id="arrow_right" />' +
              '</div><div class="reservation-time">' +
              '<div>' +
              startDateFormat +
              ' - ' +
              endDateFormat +
              '</div><div class="reservation-time-info"><div class="reservation-start-time"><span class="reservation-start-hour">' +
              startHourFormat +
              '</span> <span>:</span>' +
              '<span class="reservation-start-minute">' +
              startMinuteFormat +
              '</span></div><span>-</span><div class="reservation-end-time">' +
              '<span class="reservation-end-hour">' +
              endHourFormat +
              '</span> <span>:</span> <span class="reservation-end-minute">' +
              endMinuteFormat +
              "</span></div>" +
              '</div></div></div><div class="button-wrapper">' +
              reviewButton +
              inquireButton +
              "</div></div></div></div>";
            $("#reservation-contents-wrapper").append(result);
          } // for문 end
        }
        $("#content-amount").text(response.length);
      }, // success end
    });
  }

  // 취소
  if ($(this).attr("id") == "status-3") {
    $.ajax({
      url: "controller?cmd=canceledReservationListAction",
      method: "POST",
      dataType: "json",
      success: function (response) {
        $("#reservation-contents-wrapper").empty();
        console.log(response);
        if (response == null) {
          $("#reservation-contents-wrapper").append(
            "<span>취소 내역이 없습니다.</span>"
          );
        } else {
          for (var i = 0; i < response.length; i++) {
            //+ day[startDay.getday()] + 요일).toString() 요일 변환 귀찮앗거 일단 안하는거롤
            let startDate = new Date(
              response[i].startTime.date.year,
              response[i].startTime.date.month - 1,
              response[i].startTime.date.day,
              response[i].startTime.time.hour,
              response[i].startTime.time.minute,
              response[i].startTime.time.second
            );
            let endDate = new Date(
              response[i].endTime.date.year,
              response[i].endTime.date.month - 1,
              response[i].endTime.date.day,
              response[i].endTime.time.hour,
              response[i].endTime.time.minute,
              response[i].endTime.time.second
            );

            let startDateFormat =
              startDate.getFullYear() +
              "년" +
              startDate.getMonth() +
              "월" +
              startDate.getDate() +
              "일";
            let endDateFormat =
              endDate.getFullYear() +
              "년" +
              endDate.getMonth() +
              "월" +
              endDate.getDate() +
              "일";

            let startHourFormat = startDate
              .getHours()
              .toString()
              .padStart(2, "0");
            let startMinuteFormat = startDate
              .getMinutes()
              .toString()
              .padStart(2, "0");

            let endHourFormat = endDate.getHours().toString().padStart(2, "0");
            let endMinuteFormat = endDate
              .getMinutes()
              .toString()
              .padStart(2, "0");

            result =
              '<div class="reservation-content-wrapper" id=' +
              response[i].reservationId +
              '><div class="reservation-status-wrapper">' +
              '<span class="reservation-status">취소 완료</span></div>' +
              '<div class="reservation-content"><div id="KK_img"><img src="img/KK_img.svg" /></div>' +
              '<div class="reservation-detail-wrapper"><div class="reservation-detail"><div class="reservation-detail-row">' +
              '<span id="karaoke-name" class="kk-name">' +
              response[i].KKname +
              '</span><img src="img/arrow_right.svg" id="arrow_right" />' +
              '</div><div class="reservation-time">' +
              '<div>' +
              startDateFormat +
              ' - ' +
              endDateFormat +
              '</div><div class="reservation-time-info"><div class="reservation-start-time"><span class="reservation-start-hour">' +
              startHourFormat +
              '</span> <span>:</span>' +
              '<span class="reservation-start-minute">' +
              startMinuteFormat +
              '</span></div><span>-</span><div class="reservation-end-time">' +
              '<span class="reservation-end-hour">' +
              endHourFormat +
              '</span> <span>:</span> <span class="reservation-end-minute">' +
              endMinuteFormat +
              "</span></div>" +
              "</div></div></div>" +
              '<div class="cancel-info"><span>취소 수수료</span><div><span>' +
              4500 +
              "</span><span>원</span><span>(50%)</span></div></div>" +
              "</div></div></div>";

            $("#reservation-contents-wrapper").append(result);
          } // for end
        }
        $("#content-amount").text(response.length);
      },
    });
  }

  /*    $.ajax({
      url: "controller?cmd=idCheckAction",
      data: { manager_id: $(this).val() },
      success: function (responseText) {
        console.log(responseText);
        console.log(responseText == "N");
        console.log(responseText.length);
        if (responseText.charAt(responseText.length - 1) == "N") {
          // document.querySelector('#id-check').innerText("존재하는 아이디입니다.");
          $("#id-check").text("존재하는 아이디입니다.");
        } else {
          //document.querySelector('#id-check').innerText("존재하지 않는 아이디입니다.");
          $("#id-check").text("존재하지 않는 아이디입니다.");
        }
      },
    });
  }*/
});

// for (let i = 0; i < reservationStatus.length; i++) {
//   reservationStatus.on("click", function () {
//     reservationStatus[i].focus();
//   });
// }

// 시간 추가 버튼 클릭 시 - 시간 추가 모달 설정
$("#reservation-contents-wrapper").on("click", ".add-time-button", function () {
  // 초기화
  // $(".set-hour").text("");
  // $(".set-minute").text("");
  $(".target-hour").text("--");
  $(".target-minute").text("--");
  $("#setting-hour").val("");
  $("#setting-minute").val("");
  $("#add1-add-time-button").attr("disabled", true);

  // 예약 아이디 옮겨놓기
  let reservationId = $(this)
    .closest(".reservation-content-wrapper")
    .attr("id");
  $("#addTimeModal3").attr("data-reservation-id", reservationId);

  // 노래방 이름 옮겨놓기
  const kkname = $("#karaoke-name").text();
  console.log(kkname);
  $(".add-kkname").text(kkname);

  // 시작 시간, 종료시간 옮겨놓기
  $(".reservation-start-hour");

  let startHour = $(this)
    .closest(".reservation-detail-wrapper")
    .find(".reservation-start-hour")
    .text();
  let startMinute = $(this)
    .closest(".reservation-detail-wrapper")
    .find(".reservation-start-minute")
    .text();
  let endHour = $(this)
    .closest(".reservation-detail-wrapper")
    .find(".reservation-end-hour")
    .text();
  let endMinute = $(this)
    .closest(".reservation-detail-wrapper")
    .find(".reservation-end-minute")
    .text();

  $("#add1-original-startHour").text(startHour);
  $("#add1-original-startMinute").text(startMinute);
  $("#add1-original-endHour").text(endHour);
  $("#add1-original-endMinute").text(endMinute);

  // 추가 가능 시간 설정하기

  $.ajax({
    url: "controller?cmd=addableTime",
    type: "GET",
    data: { reservationId: reservationId },
    dataType: "json",
    success: function (data) {
      let minute = parseInt(data.minute);

      let carry = 0;
      let hours = Math.floor(minute / 60);
      let minutes = minute % 60;

      $(".additional-hour-a-status").text(hours);
      $(".additional-minute-a-status").text(minutes);
    },
  });

  // 미성년자 회원일 경우 안내창 설정하기
  // if () {
  //   $("#add1-notice").text("미성년자는 10시 이후 출입이 제한됩니다.");
  // }
});

//////////////////////////////////////////////////////////////////////////////////

// 시간 추가 모달 - 시간 설정 버튼 클릭 시
$("#add2-add-time-button").on("click", function (event) {
  // 초기화
  $("#add1-notice").text("");

  let hour = $("#setting-hour").val();
  let minute = $("#setting-minute").val();
  // alert(hour);
  // alert(minute);

  // 입력 여부 판단하기
  if (typeof hour == "" && minute == "") {
    alert("시간을 입력하세요.");
    $("#addTimeModal2").modal("show"); // 모달 다시 열기
    return false;
  }
  // alert(parseInt(hour) < 24 || parseInt(minute) <= 60);
  // alert(!(parseInt(hour) < 24));
  // alert(!(parseInt(minute) <= 60));

  // 시간 형식 검사
  if (!(parseInt(hour) < 24 && parseInt(minute) < 60)) {
    alert("시간 형식에 맞게 입력하세요.");
    $("#addTimeModal2").modal("show"); // 모달 다시 열기
    return false;
  }

  // 추가시간 가능 여부 판단하기

  // 5분 이상 검
  if (parseInt(hour) == 0 && parseInt(minute) < 5) {
    alert("시간 추가는 최소 5분 이상 추가 가능합니다.");
    $("#addTimeModal2").modal("show");
    // $("#add2-add-time-button").off("click");
    // event.preventDefault();
    return false;
  }

  // 추가가능시간 미만 검사
  let additionalHour = $(".additional-hour-a-status").text();
  let additionalMinute = $(".additional-minute-a-status").text();

  parseInt(additionalHour);
  parseInt(additionalMinute);

  if (
    parseInt(hour) > parseInt(additionalHour) ||
    (parseInt(hour) == parseInt(additionalHour) &&
      parseInt(minute) > parseInt(additionalMinute))
  ) {
    alert("추가 가능 시간 미만으로 설정해주세요.");
    $("#addTimeModal2").modal("show");
    return false;
  }

  // if (parseInt(hour) == 0 && parseInt(minute) < 5) {
  //   $("#add1-notice").text("시간 추가는 최소 5분 이상 추가 가능합니다.");
  // } else {
  //
  // }

  // 가능한 추가 시간 초과 시 안내하기

  // 0 입력 시 00으로 변경하기
  if (parseInt(hour) < 10) {
    hour = "0" + parseInt(hour);
  }

  if (parseInt(minute) < 10) {
    minute = "0" + parseInt(minute);
  }

  // 추가시간 기존 모달에 전달하기
  $(".set-hour").text(hour);
  $(".set-minute").text(minute);
  $("#add1-add-time-button").attr("disabled", false);
});

///////////////////////////////////////////////////////////////////////////
// 추가하기 버튼 클릭 시
$("#add1-add-time-button").on("click", function () {
  // 이용 종료시간 변경하기

  let endHour =
    parseInt($("#add1-original-endHour").text()) +
    parseInt($(".target-hour").text());
  let endMinute = parseInt($("#add1-original-endMinute").text());

  // 분 더하기 로직
  let carry = 0;
  endMinute += parseInt($(".target-minute").text());
  carry = Math.floor(endMinute / 60);
  endMinute = endMinute % 60;
  endHour += carry;

  if (parseInt(endMinute) < 10) {
    endMinute = "0" + endMinute;
  }

  $("#end-hour").text(endHour);
  $("#end-minute").text(endMinute);

  // 결제 금액 나타내기
  let hour = $("#setting-hour").val();
  let minute = $("#setting-minute").val();

  let amount = 0;

  if (parseInt(hour) > 0) {
    amount += parseInt(hour) * 6000;
  }

  if (parseInt(minute) > 0) {
    amount += parseInt(minute) * 100;
  }

  $("#add3-amount").text(amount);
});

// 시간 추가 결제 버튼 클릭 시
$("#time-setting-button").on("click", function () {
  const reservationId = $("#addTimeModal3").data("reservationId");

  console.log($("#addTimeModal3").data("reservationId"));
  let hour = parseInt($(".set-hour").text() || 0);
  let minute = parseInt($(".set-minute").text() || 0);

  minute += hour * 60;

  $.ajax({
    url: "controller?cmd=payAdditionalTimeAction",
    type: "POST",
    data: { reservationId: reservationId, additionalTime: minute },
    dataType: "json",
    success: function (data) {
      console.log(data);
      if (data) {
        $("#addTimeModal4").modal("show");
      } else {
        // 나중에 처리
      }
    },
    error: function (xhr, status, error) {
      console.log("AJAX 요청 실패");
      console.log("Status: " + status);
      console.log("Error: " + error);
    },
  });
});

//////////////////////////////////////////
// 리뷰 모달
// 리뷰 모달 오픈 버튼 클릭 시
$("#reservation-contents-wrapper").on("click", ".review-button1", function () {
  // 클릭한 reservation 아이디 옮겨놓기
  const reservationId = $(this)
    .closest(".reservation-content-wrapper")
    .attr("id");
  $("#addReviewModal").attr("data-reservation-id", reservationId);

  // 노래방 이름 옮겨놓기
  const kkname = $(this)
    .closest(".reservation-detail-wrapper")
    .find(".kk-name")
    .text();
  $("#review-kkname").text(kkname);
});

//모달 내 리뷰 등록 버튼 클릭 시 이벤트
$("#add-review-button").on("click", function () {
  const reservationId = $(this).closest(".modal").attr("data-reservation-id");
  const content = $("#review-description").val();
  const star = $("#review-star").val();

  if (content.length < 10) {
    alert("10자 이상 입력하세요.");
    return false;
  }

  $.ajax({
    url: "controller?cmd=addReviewAction",
    type: "POST",
    data: { reservationId: reservationId, content: content, star: star },
    dataType: "json",
    success: function (data) {
      console.log(data);
      // console.log(data.result);
      if (data) {
        $("#addReviewModal2").modal("show");
      } else {
        // 나중에 처리
      }
    },
  });
});

//////////////////////////////////////////
// 문의/신고 모달

// 문의/신고 모달 오픈 버튼 클릭 시
$("#reservation-contents-wrapper").on(
  "click",
  ".inquire-modal-button",
  function () {
    // 클릭한 reservation 아이디 옮겨놓기
    //	console.log($(this).closest(".reservation_content_wrapper").attr("id"));
    const reservationId = $(this)
      .closest(".reservation-content-wrapper")
      .attr("id");
    //	console.log("reservationId : "+reservationId);
    $("#addInquireModal").attr("data-reservation-id", reservationId);

    // 노래방 이름 옮겨놓기
    $("#inquire-kkname").text("");
    const kkname = $("#karaoke-name").text();
    console.log(kkname);
    $("#inquire-kkname").text(kkname);
  }
);

// 모달 내 문의/신고하기 버튼 클릭 시 이벤트
$("#inquire-button").on("click", function () {
  const reservationId = $(this).closest(".modal").attr("data-reservation-id");
  const content = $("#inquire-description").val();
  //	const textarea = document.getElementById('inquire-description').value;

  if (content.length < 10) {
    alert("10자 이상 입력하세요.");
    return false;
  }

  $.ajax({
    url: "controller?cmd=addInquireAction",
    type: "POST",
    data: { reservationId: reservationId, content: content },
    dataType: "json",
    success: function (data) {
      console.log(data);
      // console.log(data.result);
      if (data) {
        $("#addInquireModal2").modal("show");
      } else {
        // 나중에 처리
      }
    },
  });

  //	// action 속성 동적으로 설정하기
  //	form.action = 'controller?cmd=addInquireAction&reservationId=' + reservationId;
  //	$("#addInquireModal2").modal("show");
});

////////////////////////////////////

// 예약 취소 모달 버튼 클릭 시
$("#reservation-contents-wrapper").on("click", ".cancel-button", function () {
  // 클릭한 reservation 아이디 옮겨놓기
  //	console.log($(this).closest(".reservation_content_wrapper").attr("id"));
  const reservationId = $(this)
    .closest(".reservation-content-wrapper")
    .attr("id");
  //	console.log("reservationId : "+reservationId);
  $("#cancelReservationModal1").attr("data-reservation-id", reservationId);
});

//모달 내 취소하기 버튼 클릭 시 이벤트
$("#reservation-delete-button").on("click", function () {
  const reservationId = $(this).closest(".modal").attr("data-reservation-id");

  $.ajax({
    url: "controller?cmd=cancelReservationAction",
    type: "POST",
    data: { reservationId: reservationId },
    dataType: "json",
    success: function (data) {
      console.log(data);
      // console.log(data.result);
      if (data) {
        $("#cancelReservationModal2").modal("show");
        location.href = "controller?cmd=reservationListUI";
      } else {
        // 나중에 처리
      }
    },
  });
});
