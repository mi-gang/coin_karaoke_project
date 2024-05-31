<%@ page language="java" contentType="text/html; charset=EUC-KR"
pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>저장한 노래방</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
      crossorigin="anonymous"
    />
    <link rel="stylesheet" href="css/common.css" />
    <link rel="stylesheet" href="css/kkSearchResultList.css" />
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
        <span>저장한 노래방</span>
      </header>
      <!-- 컨텐츠 컨테이너 -->
      <div id="container">
        <div class="item">
          <select name="search-filter" id="">
            <option value="location">지역별</option>
            <option value="location">지역별</option>
          </select>
          <input type="text" placeholder="상호명을 입력하세요." />
        </div>

        <div id="bookmark-items">
          <div id="bookmark-item">
            <div class="resultItem">
              <div class="leftSide">
                <p class="resultKKTitle">스타버스 코인노래방</p>
                <div class="starScoreWrapper">
                  <span id="starAvgScore">4.6</span>
                  <div class="stars">
                    <img src="img/filledStar.svg" alt="채워진 별" />
                    <img src="img/filledStar.svg" alt="채워진 별" />
                    <img src="img/filledStar.svg" alt="채워진 별" />
                    <img src="img/filledStar.svg" alt="채워진 별" />
                    <img src="img/star_half.svg" alt="0.5점 별" />
                  </div>
                </div>
                <p class="resultKKAddress">서울 금천구 가산디지털1로 151</p>
                <div class="representativeKeywords">
                  <span class="representativeKeywordItem">단체 이용 가능</span>
                  <span class="representativeKeywordItem">24시</span>
                </div>
              </div>
              <div class="rightSide">
                <div class="bookmark"></div>
                <img
                  class="kkRepresentativeImg"
                  src="img/representativeKKImg1.png"
                  alt="노래방 대표 이미지"
                />
              </div>
            </div>
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
    <script>
      // 나의 리뷰 불러오기 ajax
      $(document).ready(function () {
        console.log("저장한 노래방 불러오기");
        $.ajax({
          url: "controller?cmd=myReviewListAction",
          type: "GET",
          dataType: "json",
          success: function (data) {
            console.log(data);
            for (var i = 0; i < data.length; i++) {
              var reviewItem =
                '<div class="review-item" data-id=' +
                data[i].reviewId +
                '><div class="review-content1"><div class="KK-title"><span class="resultKKTitle">' +
                data[i].KKname +
                '</span><img src="img/arrow_right.svg" /></div><button class="delete-button review-delete">삭제</button></div>' +
                '<div class="review-content2"><span class="review-date">' +
                data[i].startTime.date.year +
                " ." +
                data[i].startTime.date.month +
                " ." +
                data[i].startTime.date.day +
                '</span><div class="stars"><img src="img/filledStar.svg" alt="채워진 별" /><img src="img/filledStar.svg" alt="채워진 별" /><img src="img/filledStar.svg" alt="채워진 별" /><img src="img/filledStar.svg" alt="채워진 별" /><img src="img/star_half.svg" alt="0.5점 별" /></div>' +
                '<span class="review-description">' +
                data[i].content +
                "</span></div></div>";
              $("#review-items").append(reviewItem);
              $("#review-count").text(data.length);
            }
          },
        });
      });
    </script>
  </body>
</html>
