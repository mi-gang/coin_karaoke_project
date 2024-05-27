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
