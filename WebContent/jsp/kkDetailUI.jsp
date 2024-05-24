<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1.0"
    />
    <title>:: 노래방 상세정보</title>
    <link
      rel="stylesheet"
      href="css/common.css"
    />
    <link
      rel="stylesheet"
      href="css/kkDetailUI.css"
    />
    <link
      rel="preconnect"
      href="https://fonts.googleapis.com"
    />
    <link
      rel="preconnect"
      href="https://fonts.gstatic.com"
      crossorigin
    />
    <link
      href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap"
      rel="stylesheet"
    />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  </head>
  <body>
    <!--헤더-->
    <header>
      <img
        src="img/left arrow.svg"
        alt="이전 페이지 이동"
      />
      <!-- <span>스타버스 코인노래방</span> -->
      <span>${KKVO.getName()}</span>
      <!-- 목록에서 클릭한 노래방명이 들어갈 자리 !-->
    </header>
    <!-- 컨텐츠 컨테이너 -->
    <div id="container">
      <img
        src="img/representativeKKImg2.png"
        alt="노래방 상세정보 대표 이미지"
        id="representativeKKImg"
      />
      <div class="wrapper">
        <div class="tabWrapper">
          <div
            class="tapMenu active"
            data-menu="defaultInfo"
          >
            기본 정보
          </div>
          <div
            class="tapMenu"
            data-menu="review"
          >
            리뷰
          </div>
        </div>
        <div class="contentsContainer">
          <div
            id="defaultInfoContainer"
            class="selected"
          >
            <div class="infoList">
              <p class="infoTitle">주소</p>
              <p class="kkAddress">${KKVO.getAddress()}</p>
              <p id="distance">현재 위치: 800m</p>
              <div class="toMapBtn">길찾기</div>
            </div>
            <div class="infoList">
              <p class="infoTitle">영업시간</p>
              <p><span id="opTimeInfo"></span>~<span id="csTimeInfo"></span> <span id="infoNote"></span></p>
            </div>
            <div class="infoList">
              <p class="infoTitle">대표 키워드</p>
              <div class="keywordsWrapper"></div>
            </div>
          </div>
          <div id="reviewContainer">
            <div class="starScoreContainer">
              <div class="avgStarScoresWrapper">
                <!-- <p id="avgStarScore">4.6</p> -->
                <p id="avgStarScore">${starRating}</p>
                <p class="countReviews">
                  (총 <span id="countReviewsValue"></span>개 리뷰)
                </p>
                <div class="starsWrapper">
                  <svg
                    width="25"
                    height="25"
                    viewBox="0 0 25 25"
                    fill="none"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <g clip-path="url(#clip0_42_1687)">
                      <path
                        d="M12 17.27L18.18 21L16.54 13.97L22 9.24L14.81 8.63L12 2L9.19 8.63L2 9.24L7.46 13.97L5.82 21L12 17.27Z"
                        fill="#9747FF"
                      />
                    </g>
                    <defs>
                      <clipPath id="clip0_42_1687">
                        <rect
                          width="25"
                          height="25"
                          fill="white"
                        />
                      </clipPath>
                    </defs>
                  </svg>
                  <svg
                    width="25"
                    height="25"
                    viewBox="0 0 25 25"
                    fill="none"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <g clip-path="url(#clip0_42_1687)">
                      <path
                        d="M12 17.27L18.18 21L16.54 13.97L22 9.24L14.81 8.63L12 2L9.19 8.63L2 9.24L7.46 13.97L5.82 21L12 17.27Z"
                        fill="#9747FF"
                      />
                    </g>
                    <defs>
                      <clipPath id="clip0_42_1687">
                        <rect
                          width="25"
                          height="25"
                          fill="white"
                        />
                      </clipPath>
                    </defs>
                  </svg>
                  <svg
                    width="25"
                    height="25"
                    viewBox="0 0 25 25"
                    fill="none"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <g clip-path="url(#clip0_42_1687)">
                      <path
                        d="M12 17.27L18.18 21L16.54 13.97L22 9.24L14.81 8.63L12 2L9.19 8.63L2 9.24L7.46 13.97L5.82 21L12 17.27Z"
                        fill="#9747FF"
                      />
                    </g>
                    <defs>
                      <clipPath id="clip0_42_1687">
                        <rect
                          width="25"
                          height="25"
                          fill="white"
                        />
                      </clipPath>
                    </defs>
                  </svg>
                  <svg
                    width="25"
                    height="25"
                    viewBox="0 0 25 25"
                    fill="none"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <g clip-path="url(#clip0_42_1687)">
                      <path
                        d="M12 17.27L18.18 21L16.54 13.97L22 9.24L14.81 8.63L12 2L9.19 8.63L2 9.24L7.46 13.97L5.82 21L12 17.27Z"
                        fill="#9747FF"
                      />
                    </g>
                    <defs>
                      <clipPath id="clip0_42_1687">
                        <rect
                          width="25"
                          height="25"
                          fill="white"
                        />
                      </clipPath>
                    </defs>
                  </svg>
                  <svg
                    width="25"
                    height="25"
                    viewBox="0 0 25 25"
                    fill="none"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <g clip-path="url(#clip0_42_1687)">
                      <path
                        d="M12 17.27L18.18 21L16.54 13.97L22 9.24L14.81 8.63L12 2L9.19 8.63L2 9.24L7.46 13.97L5.82 21L12 17.27Z"
                        fill="#9747FF"
                      />
                    </g>
                    <defs>
                      <clipPath id="clip0_42_1687">
                        <rect
                          width="25"
                          height="25"
                          fill="white"
                        />
                      </clipPath>
                    </defs>
                  </svg>
                  <!-- <img
                    src="img/halfPurpleStar.png"
                    alt="0.5점 별"
                  /> -->
                  <!-- <svg
                    width="25"
                    height="25"
                    viewBox="0 0 25 25"
                    fill="none"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <path
                      d="M242.031 2.53323C239.097 3.86656 235.097 6.66656 233.097 8.79989C231.097 10.9332 216.297 39.4666 200.164 72.1332C168.164 136.933 166.697 139.2 154.297 142.533C151.231 143.333 121.097 147.867 87.3639 152.667C53.6305 157.467 22.9639 162.267 19.0972 163.333C11.8972 165.467 3.8972 172.8 1.36386 179.467C-0.902805 185.2 -0.236138 196.933 2.56386 202.4C4.03053 205.2 25.8972 227.467 54.0305 254.533C83.0972 282.667 103.897 303.867 105.497 306.8C106.964 309.867 108.031 314.667 108.031 318.8C108.031 322.667 103.231 353.6 97.3639 387.6C91.4972 421.733 86.6972 451.467 86.6972 453.733C86.6972 458.8 91.0972 468.8 95.0972 473.067C101.497 480 113.364 483.6 122.564 481.467C125.631 480.8 154.431 466.4 186.564 449.6C218.697 432.667 247.231 418.4 249.897 418L254.697 417.067V208.533V-0.000106523H251.097C248.964 -0.000106523 244.964 1.19989 242.031 2.53323Z"
                      fill="#9747FF"
                    />
                    <path
                      d="M267.697 2.86672C270.631 4.20006 274.631 7.00006 276.631 9.13339C278.631 11.2667 293.431 39.8001 309.564 72.4667C341.564 137.267 343.031 139.533 355.431 142.867C358.497 143.667 388.631 148.2 422.364 153C456.097 157.8 486.764 162.6 490.631 163.667C497.831 165.8 505.831 173.133 508.364 179.8C510.631 185.533 509.964 197.267 507.164 202.733C505.697 205.533 483.831 227.8 455.697 254.867C426.631 283 405.831 304.2 404.231 307.133C402.764 310.2 401.697 315 401.697 319.133C401.697 323 406.497 353.933 412.364 387.933C418.231 422.067 423.031 451.8 423.031 454.067C423.031 459.133 418.631 469.133 414.631 473.4C408.231 480.333 396.364 483.933 387.164 481.8C384.097 481.133 355.297 466.733 323.164 449.933C291.031 433 262.497 418.733 259.831 418.333L255.031 417.4V208.867V0.33339H258.631C260.764 0.33339 264.764 1.53339 267.697 2.86672Z"
                      fill="#E3CDFF"
                    />
                  </svg> -->
                </div>
              </div>
              <div class="starScoreGraphWrapper">
                <div class="graphItem">
                  <p class="scoreGrade">5점</p>
                  <div class="graphWrapper">
                    <div
                      class="graph"
                      id="graph5Score"
                    ></div>
                    <div class="graphBackground"></div>
                  </div>
                  <p
                    class="scoreCount"
                    id="score5Cnt"
                  >
                    5
                  </p>
                </div>
                <div class="graphItem">
                  <p class="scoreGrade">4점</p>
                  <div class="graphWrapper">
                    <div
                      class="graph"
                      id="graph4Score"
                    ></div>
                    <div class="graphBackground"></div>
                  </div>
                  <p
                    class="scoreCount"
                    id="score4Cnt"
                  >
                    3
                  </p>
                </div>
                <div class="graphItem">
                  <p class="scoreGrade">3점</p>
                  <div class="graphWrapper">
                    <div
                      class="graph"
                      id="graph3Score"
                    ></div>
                    <div class="graphBackground"></div>
                  </div>
                  <p
                    class="scoreCount"
                    id="score3Cnt"
                  >
                    0
                  </p>
                </div>
                <div class="graphItem">
                  <p class="scoreGrade">2점</p>
                  <div class="graphWrapper">
                    <div
                      class="graph"
                      id="graph2Score"
                    ></div>
                    <div class="graphBackground"></div>
                  </div>
                  <p
                    class="scoreCount"
                    id="score2Cnt"
                  >
                    0
                  </p>
                </div>
                <div class="graphItem">
                  <p class="scoreGrade">1점</p>
                  <div class="graphWrapper">
                    <div
                      class="graph"
                      id="graph1Score"
                    ></div>
                    <div class="graphBackground"></div>
                  </div>
                  <p
                    class="scoreCount"
                    id="score1Cnt"
                  >
                    0
                  </p>
                </div>
              </div>
            </div>
            <div class="textReviewContainer">
              <div class="guideWordsNoReview">
                <p>아직 작성된 상세리뷰가 없습니다</p>
              </div>
              <div id="invisibleUnLoginUser" class="invisibleUnLoginUser">
                <div class="invisibleWrapper">
                  <p>이 노래방의 상세 리뷰가 궁금하신가요?</p>
                  <div id="reviewLoginBtn" class="reviewLoginBtn">
                    로그인하고 리얼 리뷰 보기
                  </div>
                </div>
              </div>
              <div class="reviewItem">
                <div class="writerInfo">
                  <img
                    src="img/profile.svg"
                    alt="작성자 아이콘"
                  />
                  <p class="writerNickname">소리꾼1</p>
                </div>
                <div class="reviewContents">
                  깔끔하고 관리가 잘 되는 곳이네여
                </div>
              </div>
              <div class="reviewItem">
                <div class="writerInfo">
                  <img
                    src="img/profile.svg"
                    alt="작성자 아이콘"
                  />
                  <p class="writerNickname">음악대장</p>
                </div>
                <div class="reviewContents">
                  깔끔하고 관리가 잘 되는 곳이네여~~
                </div>
              </div>
              <div class="reviewItem">
                <div class="writerInfo">
                  <img
                    src="img/profile.svg"
                    alt="작성자 아이콘"
                  />
                  <p class="writerNickname">소리꾼2</p>
                </div>
                <div class="reviewContents">깨끗해요!!</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 하단 메뉴바 -->
    <nav>
      <div
        id="bookmark"
        class="bookmark"
      ></div>
      <div class="btn">예약하기</div>
    </nav>

    <script>
      $(document).ready(function () {
        let isLogin = true;
        
        // 불러온 영업시간 넣기
        let timeInfo = "${KKVO.getOpeningHour()}";
        let opTimeInfo = timeInfo.substring(11);
        timeInfo = "${KKVO.getClosingHour()}";
        let csTimeInfo = timeInfo.substring(11);
        timeInfo = "${KKVO.getNote()}";
        $("#opTimeInfo").text(opTimeInfo);
        $("#csTimeInfo").text(csTimeInfo);
        $("#infoNote").text(timeInfo);
        
        // 불러온 대표 키워드 목록 넣기
        let test = "${KKVO.getRepresentativeKeywordList()}";
        let keywordsArr = test.split(", ");
        keywordsArr[0] = keywordsArr[0].substring(1);
        keywordsArr[keywordsArr.length-1] = keywordsArr[keywordsArr.length - 1].replace(/\]$/, '');
        
        var keywordsWrapper = $(".keywordsWrapper");
        $.each(keywordsArr, function(index, keyword) {
        	var spanElement = $('<span class="keywordItem"></span>');
        	spanElement.text(keyword);
        	keywordsWrapper.append(spanElement);
        })
        
        // tapMenu에 따라 상세정보 변경하기
        $(".tapMenu").click(function () {

          $(".tapMenu").removeClass("active");
          $(this).addClass("active");
          let menuType = $(this).data("menu");
          console.log(menuType);
          if (menuType === "defaultInfo") {
            $("#defaultInfoContainer").addClass("selected");
            $("#reviewContainer").removeClass("selected");
          } else if (menuType === "review") {
            if(isLogin === false) {
              // 로그인한 유저만 리뷰 볼 수 있도록 가림판
              $(".reviewItem").css("background-color", "rgba(246,246,246,0.94)");
              $(".reviewItem").css("color", "rgba(246,246,246,0.94)");
              $(".reviewContents").css("background-color", "rgba(246,246,246,0.94)");
              $("#defaultInfoContainer").removeClass("selected");
              $("#reviewContainer").addClass("selected notLogin");
              $("#invisibleUnLoginUser").addClass("on");
            } else {
              // 가림판 해제
              $("#invisibleLoginUser").removeClass("on");
              let detailTxtReviewCnt = 3;  // 임의로 작성한 값
              if(detailTxtReviewCnt < 1) {
                $(".textReviewContainer").addClass("noReview");
                $(".guideWordsNoReview").addClass("on");
              } else {
                // $(".invisibleLoginUser").css("display", "none");
                $(".guideWordsNoReview").removeClass("on");
                // $("#defaultInfoContainer").removeClass("selected");
                // $("#reviewContainer").addClass("selected");
              }
              $("#defaultInfoContainer").removeClass("selected");
              $("#reviewContainer").addClass("selected");
            }
          }
        });

        let totalReviewCount = $("#countReviewsValue").text();
        // 총 리뷰 개수 설정
        totalReviewCount = 8;
        $("#countReviewsValue").text(totalReviewCount);

        let score5Cnt = parseInt($("#score5Cnt").text());
        let score4Cnt = parseInt($("#score4Cnt").text());
        let score3Cnt = parseInt($("#score3Cnt").text());
        let score2Cnt = parseInt($("#score2Cnt").text());
        let score1Cnt = parseInt($("#score1Cnt").text());

        // 점수별 리뷰 개수를 전체 리뷰 개수로 나눈 비율 & 그래프 넓이 구하기
        let ratio5 = (score5Cnt / totalReviewCount) * 191;
        let ratio4 = (score4Cnt / totalReviewCount) * 191;
        let ratio3 = (score3Cnt / totalReviewCount) * 191;
        let ratio2 = (score2Cnt / totalReviewCount) * 191;
        let ratio1 = (score1Cnt / totalReviewCount) * 191;

        // 리뷰 그래프 채우기
        if (ratio5 == 0) {
          $("#graph5Score").css("display", "none");
        } else {
          $("#graph5Score").css("width", ratio5);
        }
        if (ratio4 == 0) {
          $("#graph4Score").css("display", "none");
        } else {
          $("#graph4Score").css("width", ratio4);
        }
        if (ratio3 == 0) {
          $("#graph3Score").css("display", "none");
        } else {
          $("#graph3Score").css("width", ratio3);
        }
        if (ratio2 == 0) {
          $("#graph2Score").css("display", "none");
        } else {
          $("#graph2Score").css("width", ratio2);
        }
        if (ratio1 == 0) {
          $("#graph1Score").css("display", "none");
        } else {
          $("#graph1Score").css("width", ratio1);
        }

        // 북마크 아이콘 클릭해서 추가/취소
        $("#bookmark").click(function () {
          console.log(this.className);
          if (this.className === "bookmark") {
            $(this).addClass("add");
          } else {
            $(this).removeClass("add");
          }
        });
        // 예약하기 버튼 클릭시, 예약하기 페이지로 이동 (현재는 alert)
        $(".btn").click(function () {
          alert("예약 페이지로 이동!");
          return;
        });

        // 로그인하고 텍스트 리뷰 보기 버튼 클릭시, 로그인 페이지로 이동 (현재는 alert)
        $("#reviewLoginBtn").click(function() {
          alert("로그인 페이지로 이동!");
          return;
        })
      });
    </script>
  </body>
</html>