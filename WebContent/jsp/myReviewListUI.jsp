<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>나의 리뷰</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
      crossorigin="anonymous"
    />
    <link rel="stylesheet" href="css/common.css" />
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
        <img src="img/left arrow.svg" alt="이전 페이지 이동" />
        <span>나의 리뷰</span>
      </header>
      <!-- 컨텐츠 컨테이너 -->
      <div id="container">
        <!-- <div class="empty-page">
          <span>등록된 리뷰가 없습니다.</span>
        </div> -->
        <div id="myReview-wrapper">
          <div id="total_reservation">
            <span>총 </span>
            <span id="review-count"></span>
            <span>건</span>
          </div>
          <div id="review-items">
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

    <!-- 리뷰 삭제 모달 1 -->
    <div class="modal" id="deleteReviewModal1" data-review-id="">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <!-- Modal Header -->
          <div class="modal-header">
            <!-- <h4 class="modal-title">이용시간</h4> -->
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
            ></button>
          </div>

          <!-- Modal body -->
          <div class="modal-body">
            <div class="cancle-modal-body-wrapper">
              <span class="cancle-modal-title">리뷰를 삭제하시겠습니까?</span>
              <span class="cancle-modal-content"
                >노래방 이용 시작 시간 기준 <b>20분</b> 내 취소 시 <br />취소
                수수료가 부과됩니다.</span
              >
            </div>
          </div>

          <!-- Modal footer -->
          <div class="modal-footer">
            <button
              type="button"
              class="submit_button delete-button delete-action"
              id="add2-add-time-button"
              data-bs-toggle="modal"
              data-bs-dismiss="modal"
            >
              삭제하기
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- 리뷰 삭제 모달 2 -->
    <div class="modal" id="deleteReviewModal2">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <!-- Modal Header -->
          <div class="modal-header">
            <!-- <h4 class="modal-title">이용시간</h4> -->
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
            ></button>
          </div>

          <!-- Modal body -->
          <div class="modal-body">
            <div class="cancle-modal-body-wrapper">
              <span class="cancle-modal-title">삭제 완료</span>
              <span class="cancle-modal-content">리뷰가 삭제되었습니다.</span>
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

    <script src="js/mypage.js"></script>
  </body>
</html>
