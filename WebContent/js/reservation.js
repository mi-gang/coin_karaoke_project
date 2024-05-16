// 추가시간 기존 모달에 전달하기

$("#add2-add-time-button").on("click", function () {
  // alert("들어옴");
  // 초기화
  //   $(".set-hour").text("");
  //   $(".set-minute").text("");

  let hour = $("#setting-hour").val();
  let minute = $("#setting-minute").val();

  if (parseInt(hour) < 10) {
    hour = "0" + parseInt(hour);
  }

  if (parseInt(minute) < 10) {
    minute = "0" + parseInt(minute);
  }

  $(".set-hour").text(hour);
  $(".set-minute").text(minute);
});

// 이용 종료시간 변경하기

$("#add1-add-time-button").on("click", function () {
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
});
