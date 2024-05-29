

// 페이지 이동




// 저장한 노래방 리스트 페이지 이동
$("#bookmark-page").on("click", function() {
	alert("action: KKBookMarkListUI");
});

// 플레이리스트 페이지 이동
$("#playlist-page").on("click", function() {
	location.href = "controller?cmd=mypagePlaylistUI";
});

// 나의 리뷰 페이지 이동
$("#review-page").on("click", function() {
	location.href = "controller?cmd=myReviewListUIAction";
});

// 노래방 상세 페이지 이동
$("#review-items").on("click", ".KK-title", function() {
	alert("action: KKDetailUI");
});

// 회원정보 수정
$("#edit-user-info-button").on("click", function() {
	location.href = "controller?cmd=updateUserUI";
});

// 로그아웃 버튼 2
$("#logout-button2").on("click", function() {
	location.href = "controller?cmd=logout";
});

// // 리뷰 삭제 모달
// $(".review-delete").on("click", function () {
// $("#deleteReviewModal1").modal("show");
// });

// 노래방 검색
$("#KK-search-img").on("click", function() {
	alert("action : 검색");
});

// 플레이리스트 이동
$("#playlists").on("click", ".playlist", function() {
	playlistId = $(this).closest(".playlist").attr("data-playlistid");
	location.href = "controller?cmd=playlistMusicUI&playlistId="+playlistId;
});

// 비회원 로그인 바로가기
$(".login-button").on("click", function() {
	alert("action : loginUI");
});

// 나의 리뷰 불러오기 ajax
$(document).ready(function() {
	console.log("나의 리뷰 뷸ㄹ옿기ㅐ");
		$.ajax({
			url : "controller?cmd=myReviewListAction",
			type : "GET",
			dataType : "json",
			success : function(data) {
				console.log(data);
				for (var i = 0; i < data.length; i++) {
					var reviewItem = '<div class="review-item" data-id='
						+ data[i].reviewId
						+ '><div class="review-content1"><div class="KK-title"><span class="resultKKTitle">'
						+ data[i].KKname
						+ '</span><img src="img/arrow_right.svg" /></div><button class="delete-button review-delete">삭제</button></div>'
						+ '<div class="review-content2"><span class="review-date">'
						+ data[i].startTime.date.year
						+ " ."
						+ data[i].startTime.date.month
						+ " ."
						+ data[i].startTime.date.day
						+ '</span><div class="stars"><img src="img/filledStar.svg" alt="채워진 별" /><img src="img/filledStar.svg" alt="채워진 별" /><img src="img/filledStar.svg" alt="채워진 별" /><img src="img/filledStar.svg" alt="채워진 별" /><img src="img/star_half.svg" alt="0.5점 별" /></div>'
						+ '<span class="review-description">'
						+ data[i].content
						+ "</span></div></div>";
					$("#review-items").append(reviewItem); 
					$("#review-count").text(data.length);
					}
				},
				});
			});

// 리뷰 삭제 모달
$("#review-items").on("click", ".review-delete", function() {
	console.log($(this).closest(".review-item").data("id"));
	const reviewId = $(this).closest(".review-item").data("id")
	console.log("reviewId : "+reviewId);
	$("#deleteReviewModal1").attr("data-review-id", reviewId);
	// data일때는 안됐고 attr로 변경하니까 돌아감
	$("#deleteReviewModal1").modal("show");
});

$(".delete-action").on("click", function(e) {
	e.stopPropagation(); // 이벤트 버블링 방지

	// console.log(this);
	// console.log(e.target);
	console.log($(this));
	let reviewId = $(this).closest("#deleteReviewModal1").data("review-id");
	console.log("reviewId : " + reviewId);
	$.ajax({
		url : "controller?cmd=deleteReviewAction",
		type : "POST",
		data : {"reviewId" : reviewId},
		dataType : "json",
		success : function(data) {
			console.log(data);
			// console.log(data.result);
			if (data) {
				$("#deleteReviewModal2").modal("show");
			}
		},
	});
});

$("#add2-add-time-button").on("click", function() {
	location.href="controller?cmd=myReviewListUIAction";
});


// $.ajax({
// url: "/html/myPage-myReviewListUI.html", // 서버의 URL로 변경해주세요
// type: "GET", // 또는 'POST', 서버의 요구사항에 맞춰서 설정하세요
// dataType: "json", // 반환되는 데이터의 타입 (json, xml, text 등)
// success: function (data) {
// // data는 서버로부터 받은 리뷰 리스트입니다.
// // 여기서는 data가 객체 배열이라고 가정합니다.
// $.each(data, function (index, item) {
// alert("엥");
// var reviewItem =
// '<div id="review-item"><div id="review-content1"><div class="KK-title"><span
// class="resultKKTitle">${item.title}</span>' +
// '<img src="/img/arrow_right.svg" /></div><button class="delete-button
// review-delete">삭제</button></div>' +
// '<div id="review-content2"><span class="review-date">${item.date}</span><div
// class="stars"></div>' +
// '<span class="review-description">${item.description}</span></div></div>';
// $("#review-items").append(reviewItem); // 생성된 div를 문서에 추가합니다.
// });
// },
// // error: function (xhr, status, error) {
// // console.error("Error: " + error); // 에러 처리
// // },
// });
