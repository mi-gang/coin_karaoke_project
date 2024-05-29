<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="com.google.gson.JsonObject"%>
<%@page import="com.google.gson.Gson"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
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
      <span>${KKVO.getName()}</span>
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
                <p class="countReviews">총 <span id="countReviewsValue"></span>개 리뷰</p>
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
            <div class="textReviewContainer"></div>
          </div>
        </div>
      </div>
      
      <!-- 로그인 모달 -->
      <div class="modalBack">
	    <div class="modal">
	    	<div class="iconDiv"><img src="img/close.svg" alt="모달 닫기"></div>
	    	<p>즐겨찾기는 로그인한 유저만 사용 가능한 기능입니다.</p>
	    	<div class="loginBtn">로그인하러 가기</div>
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
    	// prevURL: 로그인 후 다시 돌아올 현재 페이지
    	/* const prevURL1 = window.location.search;
      	console.log(prevURL1);
      	const encodedPrevURL = encodeURIComponent(prevURL1);
      	console.log(encodedPrevURL);
      	sessionStorage.setItem("prevURL", encodedPrevURL); */
      	/* const currentURL = window.location.search;
      	console.log(currentURL);
      	const encodedCurrURL = encodeURIComponent(currentURL);
      	sessionStorage.setItem("currURL", encodedCurrURL); */
      	
      	
      	// 헤더의 '이전 페이지' 접근하기
      	$("header img").on("click", function() {
      		// console.log(sessionStorage.getItem("prevURL"));
      		// location.replace("controller"+sessionStorage.getItem("prevURL"));
      		history.back();
      	});
      	
        const kkId = "${KKVO.getKkId()}";
        console.log(kkId);
        console.log("1 ajax success data: ");
        // 로그인 여부에 따라 북마크
        $.ajax({
        	url: "controller?cmd=checkKKBookmarkAction",
        	data: {kkId: kkId},
        	dataType: "json",
        	success: function(data) {
        		console.log(data.isLogin);
        		// 로그인 O + 북마크 여부 확인
        		if(data.isLogin === true) {
        			console.log("로그인 O");
        			// 북마크 여부 확인
        			console.log(data.result);
        			if(data.result === true) {
        				$(".bookmark").addClass(".add");
        				$(".bookmark").css("backgroundImage", "url(img/bookmarkFill.svg)");
        			} else {
        				$(".bookmark").removeClass(".add");
        				$(".bookmark").css("backgroundImage", "url(img/bookmarkOutline.svg)");
        			}
        			// 북마크 On/Off
        			$("#bookmark").on("click", function() {
        				if($(".bookmark").hasClass(".add")) {
        					console.log("북마크  OFF");
        					$(".bookmark").removeClass(".add");
            				$(".bookmark").css("backgroundImage", "url(img/bookmarkOutline.svg)");
             				$.ajax({
             					url: "controller?cmd=deleteBookmarkAction",
             					data: {kkId: kkId},
             					dataType:"json",
             					success: function(data){
             						if(data.result == true) {
             							console.log("북마크 제거 완료");
             						} else {
             							console.log("북마크 제거 실패");
             						}
             					}
             				});
        				} else {
        					console.log("북마크 ON");
        					$(".bookmark").addClass(".add");
            				$(".bookmark").css("backgroundImage", "url(img/bookmarkFill.svg)");
             				$.ajax({
             					url: "controller?cmd=addBookmarkAction",
             					data: {kkId: kkId},
             					dataType:"json",
             					success: function(data){
             						if(data.result == true) {
             							console.log("북마크 추가 완료");
             						} else {
             							console.log("북마크 추가 실패");
             						}
             					}
             				});
        				}
        			});
        		} else {
        			// 로그인 X
        			// 북마크 on/off 시도 -> 로그인 페이지로 이동
        			$("#bookmark").on("click", function()  {
        				//console.log("로그인해야 북마크 가능!");
        				// alert("로그인한 유저만 북마크 가능합니다. 로그인 페이지로 이동합니다 :)");
        				const prevURL1 = window.location.search;
        		      	console.log(prevURL1);
        		      	const encodedPrevURL = encodeURIComponent(prevURL1);
        		      	console.log(encodedPrevURL);
        		      	sessionStorage.setItem("prevURL", encodedPrevURL);
        		      	
        		      	$(".modal").css("display", "flex");
							$(".modal").addClass("active");
							if($(".modal").hasClass("active")) {
								$(".modalBack").css("display", "flex");
							}
							
							// 로그인 유도 모달창 닫기
							$(".modal img").on("click", function() {
								$(".modalBack").css("display", "none");
								$(".modal").css("display", "none");
							});
							
							// 로그인 UI로 이동
							$(".loginBtn").on("click", function() {
								// prevURL 저장을 위한 작업
				            	const prevURL = window.location.search;
						      	console.log(prevURL);
						      	sessionStorage.setItem("prevURL", prevURL);
						      	const encodedPrevURL = encodeURIComponent(prevURL);
						      	console.log(encodedPrevURL);
						      	sessionStorage.setItem("ePrevURL", encodedPrevURL);
				            	//
								location.replace("controller?cmd=loginUI&prevURL="+encodedPrevURL);
							});
							
        		      	// location.replace("controller?cmd=loginUI");
        		      	// location.replace("controller?cmd=loginUI&prevURL="+sessionPrevURL);
        			});
        		}
        	}
        });
      	      
        
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
        });
        
        
        let reviewList = "${reviewList}";
        console.log(reviewList);
        // 로그인 여부에 따라 리뷰 가림막 On/Off
      	$.ajax({
      		url: "controller?cmd=reviewListAction",
      		data: {kkId: kkId},
      		dataType: "json",
      		success: function(data) {
      			// 로그인 X
      			const detailTxtReviewCnt = data.reviewList.length;
      			
      			if(data.isLogin === false) {
      				$(".tapMenu").click(function() {
      					$(".tapMenu").removeClass("active");
      					$(this).addClass("active");
      					let menuType = $(this).data("menu");
      					if(menuType === "defaultInfo") {
      						$("#defaultInfoContainer").addClass("selected");
      			            $("#reviewContainer").removeClass("selected");
      					} else if(menuType === "review") {
      						const invisibleDiv = "<div id='invisibleUnLoginUser' class='invisibleUnLoginUser'><div class='invisibleWrapper'><p>이 노래방의 상세 리뷰가 궁금하신가요?</p><div id='reviewLoginBtn' class='reviewLoginBtn'>로그인하고 리얼 리뷰 보기</div></div></div>";
      						$(".reviewItem").css("background-color", "rgba(246,246,246,0.94)");
      						$(".reviewItem").css("color", "rgba(246,246,246,0.94)");
      						$(".reviewContents").css("background-color", "rgba(246,246,246,0.94)");
      						$("#defaultInfoContainer").removeClass("selected");
      						$("#reviewContainer").addClass("selected notLogin");
      						$(".textReviewContainer").prepend(invisibleDiv);
      						$(".invisibleUnLoginUser").addClass("on");
	      		            // 텍스트 리뷰 보기 버튼 클릭 -> 로그인 페이지로 이동
      						$("#reviewLoginBtn").click(function() {
      							// console.log(prevURL);
      		            		// location.replace("controller?cmd=loginUI&prevURL="+encodedPrevURL);
      							
      		            		// location.replace("controller?cmd=loginUI");
      		            		console.log("텍스트 리뷰 보기 버튼 클릭");
      		            		const prevURL1 = window.location.search;
      		                	console.log(prevURL1);
      		                	const encodedPrevURL = encodeURIComponent(prevURL1);
      		                	console.log(encodedPrevURL);
      		                	sessionStorage.setItem("prevURL", encodedPrevURL);
      		            		console.log(sessionStorage.getItem("prevURL"));
      		            		let sessionPrevURL = sessionStorage.getItem("prevURL");
      		            		console.log(sessionPrevURL);
      		            		
      		            		location.replace("controller?cmd=loginUI&prevURL="+sessionPrevURL);
      		            	});
      					}
      				});       				
      			} else {
      			// 로그인 O
      				$(".tapMenu").click(function() {
      					$(".tapMenu").removeClass("active");
      					$(this).addClass("active");
      					let menuType = $(this).data("menu");
      					if(menuType === "defaultInfo") {
      						$("#defaultInfoContainer").addClass("selected");
      			            $("#reviewContainer").removeClass("selected");
      					} else if(menuType === "review") {
      						$("#defaultInfoContainer").removeClass("selected");
      						$("#invisibleLoginUser").removeClass("on");
      						$("#reviewContainer").addClass("selected");
      		              if(detailTxtReviewCnt < 1) {
      		                $(".textReviewContainer").addClass("noReview");
      		                $(".guideWordsNoReview").addClass("on");
      		              } else {
      		                $(".guideWordsNoReview").removeClass("on");
      		              }
      		              $("#defaultInfoContainer").removeClass("selected");
      		              $("#reviewContainer").addClass("selected");
   						}
   					});
      			}
      		}
      	});
        
     	

        // 리뷰 개수만큼 item 생성
        $.ajax({
        	url:"controller?cmd=reviewListAction",
        	data: {kkId: kkId},
        	dataType: "json",
        	success: function(data) {
        		console.log(data);
        		console.log("총 리뷰 개수")
        		let totalReviewCount = data.reviewList.length;
        		console.log(totalReviewCount);
        		$("#countReviewsValue").text(totalReviewCount);
        		if(totalReviewCount == 0) {
        			console.log("작성된 텍스트 리뷰 없음!")
        			$(".textReviewContainer").append("<div class='guideWordsNoReview'><p>아직 작성된 상세리뷰가 없습니다</p></div>");
        		} else {
        			for(let i=0; i<totalReviewCount; i++) {
        				let reviewItem = "<div class='reviewItem'><div class='writerInfo'><img src='img/profile.svg' alt='작성자 아이콘'/><p class='writerNickname'></p></div><div class='reviewContents'></div></div>";
        				$(".textReviewContainer").append(reviewItem);
        				$(".writerNickname").eq(i).text(data.reviewList[i].nickName);
        				$(".reviewContents").eq(i).text(data.reviewList[i].content);
        			}
        		}
        	}
        });
        
        $.ajax({
        	url:"controller?cmd=amountStarReviewListByType",
   			data: {kkId: kkId},
   			dataType: "json",
   			success: function(data) {
   				let score5Cnt = parseInt(data.cnt5);
   				let score4Cnt = parseInt(data.cnt4);
   				let score3Cnt = parseInt(data.cnt3);
   				let score2Cnt = parseInt(data.cnt2);
   				let score1Cnt = parseInt(data.cnt1);
   				let totalReviewCount = score5Cnt + score4Cnt + score3Cnt + score2Cnt + score1Cnt;
   				$("#countReviewsValue").text(totalReviewCount);
   				
   				$("#score5Cnt").text(score5Cnt);
   				$("#score4Cnt").text(score4Cnt);
   				$("#score3Cnt").text(score3Cnt);
   				$("#score2Cnt").text(score2Cnt);
   				$("#score1Cnt").text(score1Cnt);
   				
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
   			}
        })
        
        
        /* let totalReviewCount = $("#countReviewsValue").text();
        // 총 리뷰 개수 설정
        totalReviewCount = 8;
        $("#countReviewsValue").text(totalReviewCount); */

        /* let score5Cnt = parseInt($("#score5Cnt").text());
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
        } */

        /* // 북마크 아이콘 클릭해서 추가/취소
        $("#bookmark").click(function () {
          console.log(this.className);
          if (this.className === "bookmark") {
            $(this).addClass("add");
          } else {
            $(this).removeClass("add");
          }
        }); */
        // 예약하기 버튼 클릭시, 예약하기 페이지로 이동 (현재는 alert)
        $(".btn").click(function () {
          alert("예약 페이지로 이동!");
          return;
        });

     // 하단 메뉴바를 통한 페이지 이동
        $("nav div").on("click", function() {
      	  const clickedDiv = $(this);
      	  const imgAlt = clickedDiv.find("img").attr("alt");
      	  switch(imgAlt) {
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
      });
    </script>
  </body>
</html>