<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>마이페이지</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
      crossorigin="anonymous"
    />
    <link rel="stylesheet" href="css/common.css" />
    <link rel="stylesheet" href="css/kkSearchResultList.css" />
    <link rel="stylesheet" href="css/mypage.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />

    <link
      href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap"
      rel="stylesheet"
    />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  </head>
  <body>
    <!-- 모바일 컨테이너 -->
    <div id="mobileContainer">
      <!--헤더-->
      <header>
        <div id="mypage-header">
          <span>마이페이지</span>
          <button id="logout-button">로그아웃</button>
        </div>
      </header>
      <!-- 컨텐츠 컨테이너 -->
      <div id="container">
        <div id="user-info-wrapper">
          <img id="mypage-user-icon" src="img/mypage-user.svg" />
          <div id="user-info">
            <div>
              <span id="user-nickname">mumumu</span>
              <span class="info-normal-text">님</span>
            </div>
            <button id="edit-user-info-button">회원정보 수정</button>
          </div>
        </div>
        <div id="bookmark-info-wrapper" class="info-wrapper">
          <div id="bookmark-info" class="info-setting">
            <div id="bookmark-info-1" class="info-1-setting">
              <span class="info-bold-text">저장한 노래방</span>
              <span class="info-amount" id="bookmark-amount"></span>
            </div>
            <div id="bookmark-page" class="info-2-setting">
              <span class="info-normal-text">전체보기</span>
              <img class="arrow-right" src="img/arrow_right.svg" />
            </div>
          </div>
          <div id="bookmark-item">

          </div>
        </div>
        <div id="playlist-info-wrapper" class="info-wrapper">
          <div id="playlist-info" class="info-setting">
            <div class="info-1-setting">
              <span class="info-bold-text">나의 플레이리스트</span>
              <span class="info-amount" id="playlist-amount"></span>
            </div>
            <div id="playlist-page" class="info-2-setting">
              <span class="info-normal-text">전체보기</span>
              <img src="img/arrow_right.svg" />
            </div>
          </div>
          <div id="playlists">
            <div class="playlist">
              <img src="img/playlist.svg" />
              <span class="playlist-name">내 음악 리스트</span>
            </div>
            <div class="playlist">
              <img src="img/playlist.svg" />
              <span class="playlist-name">내 음악 리스트</span>
            </div>
            <div class="playlist">
              <img src="img/playlist.svg" />
              <span class="playlist-name">내 음악 리스트</span>
            </div>
          </div>
        </div>
        
        <div id="review-info-wrapper" class="info-wrapper">
       	<div id="review-info" class="info-setting">
            <div class="info-1-setting">
              <span class="info-bold-text">나의 리뷰</span>
              <span class="info-amount" id="review-amount"></span>
            </div>
            <div id="review-page" class="info-2-setting">
              <span class="info-normal-text">전체보기</span>
              <img src="img/arrow_right.svg" />
            </div>
          </div>
          <div id="review-item">

          </div>
        </div>
      </div>
      <!-- 하단 메뉴바 -->
      <nav>
        <div><img src="img/mainPageIcon.svg" alt="메인 페이지" /></div>
        <div><img src="img/searchIcon.svg" alt="노래방 검색 페이지" /></div>
        <div><img src="img/musicIcon.svg" alt="노래 검색 페이지" /></div>
        <div>
          <img src="img/reservationIcon.svg" alt="나의 예약 내역 페이지" />
        </div>
        <div><img src="img/userIcon.svg" alt="마이페이지" /></div>
      </nav>
    </div>

    <!-- 부트스트랩 -->
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
      crossorigin="anonymous"
    ></script>

    <!-- 모달 -->

    <!-- 로그아웃 모달 -->
    <div class="modal" id="logoutModal">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <!-- Modal Header -->
          <div class="modal-header">
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
            ></button>
          </div>

          <!-- Modal body -->
          <div class="modal-body">
            <div class="cancle-modal-body-wrapper">
              <span class="cancle-modal-title">등록 완료</span>
              <span class="cancle-modal-content"
                >문의/신고가 접수되었습니다.</span
              >
            </div>
          </div>

          <!-- Modal footer -->
          <div class="modal-footer">
            <button
              type="button"
              class="submit_button add-button"
              id="add2-add-time-button"
              data-bs-toggle="modal"
              data-bs-dismiss="modal"
            >
              닫기
            </button>
          </div>
        </div>
      </div>
    </div>
    
    <script>
 // 마이페이지 메인 화면 불러오기 AJax
    $(document).ready(function () {
    	console.log("마이페이지 메인 불러오기");
    	 $.ajax({
    		 url: "controller?cmd=myReviewListAction",
    		 type: "GET",
    		 dataType: "json",
    		 success: function (data) {
    			 console.log(data);
    		     // data는 서버로부터 받은 리뷰 리스트입니다.
    		     // 여기서는 data가 객체 배열이라고 가정합니다.
    			 for (var i = 0; i < data.length; i++) {
    				 
    				 // 저장한 노래방 중 최근 저장(제일 마지막 거) 불러오기
    				 
    				 // 나의 플레이리스트 중 최근 생성 - 3개 불러오기
    				 
    				 // 나의 리뷰 중 최근 거 불러오기
    				 var reviewItem =
    					 '<div class="review-item"><div class="review-content1"><div class="KK-title"><span class="resultKKTitle">'
    					 + data[i].KKname 
    		    		 + '</span><img src="img/arrow_right.svg" /></div><button class="delete-button review-delete" id="' + data[i].reviewId
    		    		 + '">삭제</button></div>'
    		    		 + '<div class="review-content2"><span class="review-date">'
    		    		 + data[i].startTime.date.year+" ." + data[i].startTime.date.month +" ."+data[i].startTime.date.day
    		    		 + '</span><div class="stars"><img src="img/filledStar.svg" alt="채워진 별" /><img src="img/filledStar.svg" alt="채워진 별" /><img src="img/filledStar.svg" alt="채워진 별" /><img src="img/filledStar.svg" alt="채워진 별" /><img src="img/star_half.svg" alt="0.5점 별" /></div>'
    		    		 + '<span class="review-description">'
    		    		 + data[i].content
    		    		 + "</span></div></div>";
    				 $("review-item").append(reviewItem); // 생성된 div를 문서에 추가합니다.
    				 }
    			 },
    			 });
    });
    </script>

    <script src="js/mypage.js"></script>
  </body>
</html>
