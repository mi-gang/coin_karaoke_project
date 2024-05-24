// 페이지 이동

// 저장한 노래방 리스트 페이지 이동
$("#bookmark-page").on("click", function () {
  alert("action: KKBookMarkListUI");
});

// 플레이리스트 페이지 이동
$("#playlist-page").on("click", function () {
  alert("action: playListMusicUI");
});

// 나의 리뷰 페이지 이동
$("#review-page").on("click", function () {
  alert("action: playListMusicUI");
});

// 노래방 상세 페이지 이동
$(".KK-title").on("click", function () {
  alert("action: KKDetailUI");
});

// 회원정보 수정
$("#edit-user-info-button").on("click", function () {
  alert("action: updateUserUI");
});

// 로그아웃
$("#logout-button").on("click", function () {
  alert("action: logout");
});

// // 리뷰 삭제 모달
// $(".review-delete").on("click", function () {
//   $("#deleteReviewModal1").modal("show");
// });

// 노래방 검색
$("#KK-search-img").on("click", function () {
  alert("action : 검색");
});

// 플레이리스트 이동
$(".playlist").on("click", function () {
  alert("action : playListUI");
});

// 비회원 로그인 바로가기
$(".login-button").on("click", function () {
  alert("action : loginUI");
});

// 나의 리뷰 ajax

$(document).ready(function () {
  // alert("엥");
  var reviewItem =
    '<div class="review-item"><div class="review-content1"><div class="KK-title"><span class="resultKKTitle">' +
    "777 노래연습장" +
    '</span><img src="/img/arrow_right.svg" /></div><button class="delete-button review-delete">삭제</button></div>' +
    '<div class="review-content2"><span class="review-date">' +
    "2024.05.04" +
    '</span><div class="stars"><img src="../img/filledStar.svg" alt="채워진 별" /><img src="../img/filledStar.svg" alt="채워진 별" /><img src="../img/filledStar.svg" alt="채워진 별" /><img src="../img/filledStar.svg" alt="채워진 별" /><img src="../img/star_half.svg" alt="0.5점 별" /></div>' +
    '<span class="review-description">' +
    "사운드가 좋아요 재방문 의사 있어요 😍" +
    "</span></div></div>";
  $("#review-items").append(reviewItem); // 생성된 div를 문서에 추가합니다.
});

// 리뷰 삭제 모달
$("#review-items").on("click", ".review-delete", function () {
  $("#deleteReviewModal1").modal("show");
});

// $.ajax({
//   url: "/html/myPage-myReviewListUI.html", // 서버의 URL로 변경해주세요
//   type: "GET", // 또는 'POST', 서버의 요구사항에 맞춰서 설정하세요
//   dataType: "json", // 반환되는 데이터의 타입 (json, xml, text 등)
//   success: function (data) {
//     // data는 서버로부터 받은 리뷰 리스트입니다.
//     // 여기서는 data가 객체 배열이라고 가정합니다.
//     $.each(data, function (index, item) {
//       alert("엥");
//       var reviewItem =
//         '<div id="review-item"><div id="review-content1"><div class="KK-title"><span class="resultKKTitle">${item.title}</span>' +
//         '<img src="/img/arrow_right.svg" /></div><button class="delete-button review-delete">삭제</button></div>' +
//         '<div id="review-content2"><span class="review-date">${item.date}</span><div class="stars"></div>' +
//         '<span class="review-description">${item.description}</span></div></div>';
//       $("#review-items").append(reviewItem); // 생성된 div를 문서에 추가합니다.
//     });
//   },
//   // error: function (xhr, status, error) {
//   //   console.error("Error: " + error); // 에러 처리
//   // },
// });
