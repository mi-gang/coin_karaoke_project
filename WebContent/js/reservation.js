// 예약 상태 탭 클릭 시

$(".reservations-status-button").on("click", function () {
  $(".reservations-status-button").removeClass("active");
  $(this).addClass("active");

  $(".reservation_content_wrapper").hide();

  if ($(this).attr("id") == "status-1") {
    $(".status-1-1").show();
    $(".status-1-2").show();
    return false;
  }
  if ($(this).attr("id") == "status-2") {
    $(".status-2-1").show();
    $(".status-2-2").show();
    $(".status-2-3").show();
    return false;
  }
  if ($(this).attr("id") == "status-3") {
    $(".status-3").show();
    return false;
  }
});

// for (let i = 0; i < reservationStatus.length; i++) {
//   reservationStatus.on("click", function () {
//     reservationStatus[i].focus();
//   });
// }

// 시간 추가 버튼 클릭 시 - 시간 추가 모달 설정
$("#add-time-button").on("click", function () {
  // 초기화
  // $(".set-hour").text("");
  // $(".set-minute").text("");
  $(".target-hour").text("--");
  $(".target-minute").text("--");
  $("#setting-hour").val("");
  $("#setting-minute").val("");
  $("#add1-add-time-button").attr("disabled", true);
});

// 추가 가능 시간 설정하기

// 미성년자 회원일 경우 안내창 설정하기
// if () {
//   $("#add1-notice").text("미성년자는 10시 이후 출입이 제한됩니다.");
// }

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
  let additionalHour = $(".additionalHourStatus").text();
  let additionalMinute = $(".additionalMinuteStatus").text();

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
    parseInt($("#reservation-end-hour").text()) +
    parseInt($(".target-hour").text());
  let endMinute = parseInt($("#reservation-end-minute").text());

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
