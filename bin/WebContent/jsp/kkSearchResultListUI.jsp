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
    <title>:: 노래방 검색</title>
    <link
      rel="stylesheet"
      href="css/kkSearchResultList.css"
    />
    <link
      rel="stylesheet"
      href="css/common.css"
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
    <!-- 모바일 컨테이너 -->
    <div id="mobileContainer">
        <!--헤더-->
        <header>
          <img
            src="img/left arrow.svg"
            alt="이전 페이지 이동"
          />
          <span>노래방 검색 결과</span>
        </header>
        <!-- 컨텐츠 컨테이너 -->
        <div id="container">
            <div class="filterContainer">
                <div class="filterWrapper">
                    <span class="filterItem">강남구</span>
                    <span class="filterItem">23:30 ~ 01:20</span>
                    <span class="filterItem">40분</span>
                    <span class="filterItem">단체 가능</span>
                </div>
            </div>
            <div class="resultList">
                <div class="resultItem">
                    <div class="leftSide">
                        <p class="resultKKTitle">777 노래연습장</p>
                        <div class="starScoreWrapper">
                            <span id="starAvgScore">4.8</span>
                            <div class="stars">
                                <img src="img/filledStar.svg" alt="채워진 별">
                                <img src="img/filledStar.svg" alt="채워진 별">
                                <img src="img/filledStar.svg" alt="채워진 별">
                                <img src="img/filledStar.svg" alt="채워진 별">
                                <img src="img/star_half.svg" alt="0.5점 별">
                            </div>
                        </div>
                        <p class="resultKKAddress">서울 금천구 벚꽃로 316 번지 지하 1층</p>
                        <div class="representativeKeywords">
                            <span class="representativeKeywordItem">넓은</span>
                            <span class="representativeKeywordItem">단체 이용 가능</span>
                            <span class="representativeKeywordItem">주차</span>
                        </div>
                    </div>
                    <div class="rightSide">
                        <div class="bookmark"></div>
                        <img class="kkRepresentativeImg" src="img/representativeKKImg1.png" alt="노래방 대표 이미지">
                    </div>
                </div>
                <div class="resultItem">
                    <div class="leftSide">
                        <p class="resultKKTitle">큐노래연습장</p>
                        <div class="starScoreWrapper">
                            <span id="starAvgScore">4.5</span>
                            <div class="stars">
                                <img src="img/filledStar.svg" alt="채워진 별">
                                <img src="img/filledStar.svg" alt="채워진 별">
                                <img src="img/filledStar.svg" alt="채워진 별">
                                <img src="img/filledStar.svg" alt="채워진 별">
                                <img src="img/star_half.svg" alt="0.5점 별">
                            </div>
                        </div>
                        <p class="resultKKAddress">서울 금천구 가산로 85</p>
                        <div class="representativeKeywords">
                            <span class="representativeKeywordItem">단체 이용 가능</span>
                        </div>
                    </div>
                    <div class="rightSide">
                        <div class="bookmark"></div>
                        <img class="kkRepresentativeImg" src="img/representativeKKImg1.png" alt="노래방 대표 이미지">
                    </div>
                </div>
                <div class="resultItem">
                    <div class="leftSide">
                        <p class="resultKKTitle">스타노래마당</p>
                        <div class="starScoreWrapper">
                            <span id="starAvgScore">4.0</span>
                            <div class="stars">
                                <img src="img/filledStar.svg" alt="채워진 별">
                                <img src="img/filledStar.svg" alt="채워진 별">
                                <img src="img/filledStar.svg" alt="채워진 별">
                                <img src="img/filledStar.svg" alt="채워진 별">
                                <img src="img/star_border.svg" alt="비어있는 별">
                            </div>
                        </div>
                        <p class="resultKKAddress">서울 금천구 가산디지털1로 137</p>
                        <div class="representativeKeywords">
                            <span class="representativeKeywordItem">단체 이용 가능</span>
                            <span class="representativeKeywordItem">지상층</span>
                        </div>
                    </div>
                    <div class="rightSide">
                        <div class="bookmark"></div>
                        <img class="kkRepresentativeImg" src="img/representativeKKImg1.png" alt="노래방 대표 이미지">
                    </div>
                </div>
                <div class="resultItem">
                    <div class="leftSide">
                        <p class="resultKKTitle">스타버스 코인노래방</p>
                        <div class="starScoreWrapper">
                            <span id="starAvgScore">4.6</span>
                            <div class="stars">
                                <img src="img/filledStar.svg" alt="채워진 별">
                                <img src="img/filledStar.svg" alt="채워진 별">
                                <img src="img/filledStar.svg" alt="채워진 별">
                                <img src="img/filledStar.svg" alt="채워진 별">
                                <img src="img/star_half.svg" alt="0.5점 별">
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
                        <img class="kkRepresentativeImg" src="img/representativeKKImg1.png" alt="노래방 대표 이미지">
                    </div>
                </div>
                <div class="resultItem">
                    <div class="leftSide">
                        <p class="resultKKTitle">별코인노래연습장</p>
                        <div class="starScoreWrapper">
                            <span id="starAvgScore">4.1</span>
                            <div class="stars">
                                <img src="img/filledStar.svg" alt="채워진 별">
                                <img src="img/filledStar.svg" alt="채워진 별">
                                <img src="img/filledStar.svg" alt="채워진 별">
                                <img src="img/filledStar.svg" alt="채워진 별">
                                <img src="img/star_border.svg" alt="비어있는 별">
                            </div>
                        </div>
                        <p class="resultKKAddress">서울 금천구 가산디지털17로 31</p>
                        <div class="representativeKeywords">
                            <span class="representativeKeywordItem">지상층</span>
                        </div>
                    </div>
                    <div class="rightSide">
                        <div class="bookmark"></div>
                        <img class="kkRepresentativeImg" src="img/representativeKKImg1.png" alt="노래방 대표 이미지">
                    </div>
                </div>
                <div class="resultItem">
                    <div class="leftSide">
                        <p class="resultKKTitle">노래방방</p>
                        <div class="starScoreWrapper">
                            <span id="starAvgScore">3.4</span>
                            <div class="stars">
                                <img src="img/filledStar.svg" alt="채워진 별">
                                <img src="img/filledStar.svg" alt="채워진 별">
                                <img src="img/filledStar.svg" alt="채워진 별">
                                <img src="img/star_half.svg" alt="0.5점 별">
                                <img src="img/star_border.svg" alt="비어있는 별">
                            </div>
                        </div>
                        <p class="resultKKAddress">서울 금천구 가산로 8</p>
                        <div class="representativeKeywords">
                            <span class="representativeKeywordItem">냉난방</span>
                            <span class="representativeKeywordItem">주차 가능</span>
                        </div>
                    </div>
                    <div class="rightSide">
                        <div class="bookmark"></div>
                        <img class="kkRepresentativeImg" src="img/representativeKKImg1.png" alt="노래방 대표 이미지">
                    </div>
                </div>
            </div>
        </div>
        <!-- 하단 메뉴바 -->
        <nav>
          <div>
            <img
              src="img/mainPageIcon.svg"
              alt="메인 페이지"
            />
          </div>
          <div>
            <img
              src="img/searchIcon.svg"
              alt="노래방 검색 페이지"
            />
          </div>
          <div>
            <img
              src="img/musicIcon.svg"
              alt="노래 검색 페이지"
            />
          </div>
          <div>
            <img
              src="img/reservationIcon.svg"
              alt="나의 예약 내역 페이지"
            />
          </div>
          <div>
            <img
              src="img/userIcon.svg"
              alt="마이페이지"
            />
          </div>
        </nav>

        <script>
            $(document).ready(function () {
                const bookmarks = $(".bookmark").get();

                // 북마크 아이콘 클릭해서 추가/취소
                bookmarks.forEach(function(option) {
                    option.addEventListener("click", function() {
                        option.classList.toggle("add");
                    })
                })

            });            
        </script>
  </body>
  </html>