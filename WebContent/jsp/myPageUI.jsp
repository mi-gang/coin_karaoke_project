<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
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
        <img />
        <div id="mypage-header">
          <span>마이페이지</span>
          <c:if test="${userId != null}">
            <button
              type="button"
              id="logout-button1"
              class="logout-button"
              data-bs-toggle="modal"
              data-bs-target="#logoutModal"
            >
              로그아웃
            </button>
          </c:if>
        </div>
      </header>
      <!-- 컨텐츠 컨테이너 -->
      <div id="container">
        <div class="modal_overlay">
          <div class="modal_isMember" id="modal_isMember">
            <div class="modal_alert">
              로그인 유저만 사용할 수 있는 서비스입니다
            </div>
            <input
              class="login_btn"
              id="login_move"
              type="button"
              value="로그인하러 가기"
            />
          </div>
        </div>
        <div id="user-info-wrapper">
          <img id="mypage-user-icon" src="img/mypage-user.svg" />
          <div id="user-info">
            <div>
              <span id="user-nickname"></span>
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
          <div id="bookmark-item"></div>
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
            <div class="playlist" id="playlist1">
              <img src="img/playlist.svg" />
              <span class="playlist-name" id="playlist-name1"></span>
            </div>
            <div class="playlist" id="playlist2">
              <img src="img/playlist.svg" />
              <span class="playlist-name" id="playlist-name2"></span>
            </div>
            <div class="playlist" id="playlist3">
              <img src="img/playlist.svg" />
              <span class="playlist-name" id="playlist-name3"></span>
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
          <div id="review-item"></div>
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
        <div><img src="img/userIcon_on.svg" alt="마이페이지" /></div>
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
            <!-- <h4 class="modal-title">이용시간</h4> -->
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
            ></button>
          </div>

          <!-- Modal body -->
          <div class="modal-body">
            <div class="cancel-modal-body-wrapper">
              <span class="cancel-modal-title">로그아웃 하시겠습니까?</span>
            </div>
          </div>

          <!-- Modal footer -->
          <div class="modal-footer">
            <button
              type="button"
              class="submit-button add-button"
              id="logout-button2"
              data-bs-toggle="modal"
              data-bs-dismiss="modal"
            >
              로그아웃
            </button>
          </div>
        </div>
      </div>
    </div>

    <script>
      //처음으로 페이지 들어왔을때 모달 뜨게하기
      const modal = document.querySelector(".modal_overlay");
      var userId = '<%=(String)session.getAttribute("userId")%>';
      if (userId === "null") {
        modal.classList.add("on");
        $(".login_btn").on("click", function () {
          location.href = "controller?cmd=loginUI";
        });
      }

      //
      /*     $(document).ready(function () {
    	let userId = sessionStorage.getItem('userId');
    	if (userId == null) {
			
		}
    }); */

      // 마이페이지 메인 화면 불러오기 AJax
      $(document).ready(function () {
        console.log("마이페이지 메인 불러오기");
        $.ajax({
          url: "controller?cmd=myPageAction",
          dataType: "json",
          type: "POST",
          success: function (data) {
            console.log(data);
            $("#bookmark-item").empty();

            // 닉네임
            $("#user-nickname").text(data.nickname);

            // 저장한 노래방
            let kkId = data.kkVO.kkId;
            let kkName = data.kkVO.name;
            let starRating = data.kkVO.starRating;
            let address = data.kkVO.address;
            let contents = "";

            for (
              var i = 0;
              i < data.kkVO.representativeKeywordList.length;
              i++
            ) {
              contents +=
                '<span class="representativeKeywordItem">' +
                data.kkVO.representativeKeywordList[i] +
                "</span>";
            }

            let bookmarkItem =
              '<div class="resultItem" id="' +
              kkId +
              '"><div class="leftSide"><p class="resultKKTitle">' +
              kkName +
              '</p><div class="starScoreWrapper"><span id="starAvgScore">' +
              starRating +
              '</span><div class="rating-wrap"><div class="rating"><div class="overlay"></div></div></div>' +
              '</div></div><p class="resultKKAddress">' +
              '</p><div class="representativeKeywords">' +
              contents +
              '</div></div><div class="rightSide"><div class="bookmark"></div>' +
              '<img class="kkRepresentativeImg" src="img/representativeKKImg1.png" alt="노래방 대표 이미지"/>' +
              "</div></div>";

            $("#bookmark-item").append(bookmarkItem);
            $("#bookmark-amount").append(data.bookmarkCount);

            // 나의 플레이리스트
            for (var i = 0; i < data.playlistVOs.length; i++) {
              $("#playlist" + [i + 1]).attr(
                "data-playListId",
                data.playlistVOs[i].playListId
              );
              $("#playlist-name" + [i + 1]).append(
                data.playlistVOs[i].playListTitle
              );
            }
            $("#playlist-amount").append(data.playlistCount);

            // 나의 리뷰
            let reviewItem =
              '<div class="review-item" data-id=' +
              data.reviewVO.reviewId +
              '><div class="review-content1"><div class="KK-title"><span class="resultKKTitle">' +
              data.reviewVO.KKname +
              '</span><img src="img/arrow_right.svg" /></div></div>' +
              '<div class="review-content2"><span class="review-date">' +
              data.reviewVO.startTime.date.year +
              " ." +
              data.reviewVO.startTime.date.month +
              " ." +
              data.reviewVO.startTime.date.day +
              '</span><div class="stars"><img src="img/filledStar.svg" alt="채워진 별" /><img src="img/filledStar.svg" alt="채워진 별" /><img src="img/filledStar.svg" alt="채워진 별" /><img src="img/filledStar.svg" alt="채워진 별" /><img src="img/star_half.svg" alt="0.5점 별" /></div>' +
              '<span class="review-description">' +
              data.reviewVO.content +
              "</span></div></div>";

            $("#review-item").append(reviewItem);
            $("#review-amount").append(data.reviewCount);

            $.ajax({
              url: "controller?cmd=checkKKBookmarkAction",
              data: { kkId: kkId },
              dataType: "json",
              success: function (data) {
                console.log(data);
                let checkMyBookmark = data.result;
                console.log(checkMyBookmark);
                const bookmarkIcon = $(".bookmark");

                if (checkMyBookmark == true) {
                  bookmarkIcon.addClass("bookmark add");
                  bookmarkIcon.css(
                    "background-image",
                    "url(img/bookmarkFill.svg)"
                  );
                } else if (checkMyBookmark == false) {
                  bookmarkIcon.css(
                    "background-image",
                    "url(img/bookmarkOutline.svg)"
                  );
                }
              },
            });

            // 해당 노래방의 평균 별점만큼 별 아이콘
            updateStarContainer();
            function updateStarContainer() {
              $(".starScoreWrapper").each(function (i, item) {
                const rating = item.querySelector(".rating");
                const overlay = item.querySelector(".overlay");
                const rate = item.querySelector("#starAvgScore").textContent;
                drawStarRate(rating, overlay, rate);
              });
            }
          },
        });
      });

      // 하단 메뉴바를 통한 페이지 이동
      $("nav div").on("click", function () {
        const clickedDiv = $(this);
        const imgAlt = clickedDiv.find("img").attr("alt");
        switch (imgAlt) {
          case "메인 페이지":
            location.replace("controller?cmd=mainUI");
            break;
          case "노래방 검색 페이지":
            location.replace("controller?cmd=kkFilterUI");
            break;
          case "노래 검색 페이지":
            location.replace("controller?cmd=musicListUI");
            break;
          case "나의 예약 내역 페이지":
            location.replace("controller?cmd=reservationListUIAction");
            break;
          case "마이페이지":
            location.replace("controller?cmd=mypageUIAction");
            break;
        }
      });
    </script>

    <script src="js/mypage.js"></script>
  </body>
</html>
