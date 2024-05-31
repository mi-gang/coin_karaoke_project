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
    <link rel="stylesheet" href="css/starRate.css">
	<script src="js/starRate.js"></script>
    
  </head>
  <body>
    <!-- 모바일 컨테이너 -->
    <div id="mobileContainer">
        <!--헤더-->
        <header><span>노래방 검색 결과</span></header>
        <!-- 컨텐츠 컨테이너 -->
        <div id="container">
            <div class="filterContainer">
                <div class="filterWrapper">
                    <span class="filterItem">${searchGu}</span>
                    <span class="filterItem">${searchTime}</span>
                    <span class="filterItem">${usingTime }</span>
                    <!-- <span class="filterItem">단체 가능</span> -->
                    <c:forEach items="${chkOptionsContents}" var="optionItem">
                    	<span class="filterItem">${optionItem}</span>
                    </c:forEach>
                </div>
            </div>
            <div class="resultList">
            	<c:if test="${resultList.size() == 0}">
            		<p class="noResultData">검색 조건에 해당되는 노래방이 없습니다</p>
            	</c:if>
            	<c:if test="${resultList.size() != 0}">
	                <c:forEach items="${resultList}" var="result">
		                <div class="resultItem">
		                    <div class="leftSide">
		                    	<span id="resultKKId" style="display: none;">${result.getKkId()}</span>
		                        <p class="resultKKTitle">${result.getName()}</p>
		                        <div class="starScoreWrapper">
		                            <span id="starAvgScore">${result.getStarRating()}</span>
		                            <div class="rating-wrap">
										<div class="rating">
											<div class="overlay"></div>
										</div>
									</div>
		                        </div>
		                        <p class="resultKKAddress">${result.getAddress()}</p>
		                        <div class="representativeKeywords">
		                        <c:forEach items="${result.getRepresentativeKeywordList()}" var="keywordItem">
			                       <span class="representativeKeywordItem">${keywordItem}</span>
		                        </c:forEach>
		                        </div>
		                    </div>
		                    <div class="rightSide">
		                        <div class="bookmark"></div>
		                        <img class="kkRepresentativeImg" src="img/representativeKKImg1.png" alt="노래방 대표 이미지">
		                    </div>
		                </div>
	                </c:forEach>
            	</c:if>
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
          <div>
            <img
              src="img/mainPageIcon.svg"
              alt="메인 페이지"
            />
          </div>
          <div>
            <img
              src="img/searchIcon_on.svg"
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
        /* $(window).on("refreshPreviousPage", function() {
        	window.location.reload();
        }); */
            $(document).ready(function () {          	
            	
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
				
				/* // 평균 별점 아이콘 크기 조정
				const ratingDiv = document.querySelectorAll(".rating");
				console.log("ratingDiv");
				console.log(ratingDiv);
				const divsInRating = ratingDiv.querySelectorAll("div");
				console.log("divsInRating");
				console.log(divsInRating);
				
				ratingDiv.forEach(function(rating) {
					rating.forEach(function(div) {
						div.style.width = '20px';
					});
				}); */
            	
            	const kkList = document.querySelectorAll(".resultItem");
                const bookmarks = $(".bookmark").get();
                const leftSideList = document.querySelectorAll(".leftSide");
                const rightSideImgList = document.querySelectorAll(".kkRepresentativeImg");

                // 내가 북마크한 노래방이면 아이콘 fill으로 페이지 노출
                kkList.forEach(function(item){
                	console.log("kkList forEach문");
                	console.log(item);
                	const kkId = item.querySelector("#resultKKId").textContent;
					
                	$.ajax({
                		url: "controller?cmd=checkKKBookmarkAction",
                		data: {kkId: kkId}, 
                		dataType:"json",
                		success: function(data) {
                			console.log(data);
                			let checkMyBookmark = data.result;
                			const bookmarkIcon = item.querySelector(".bookmark");
                			
                			if(checkMyBookmark == true) {
                				bookmarkIcon.classList.add("add");
                				bookmarkIcon.style.backgroundImage = "url(img/bookmarkFill.svg)";
                			} else if(checkMyBookmark == false) {
                				bookmarkIcon.style.backgroundImage = "url(img/bookmarkOutline.svg)";
                			}
                		}
                	});
                });
                
                kkList.forEach(function(item) {
                	const kkId = item.querySelector("#resultKKId").textContent;
             		const bookmarkIcon = item.querySelector(".bookmark");
             		
             		bookmarkIcon.addEventListener("click", function() {
             			$.ajax({
             				url: "controller?cmd=updateKKBookmark",
             				data: {kkId: kkId},
             				dataType:"json",
             				success: function(data) {
             					if(data.isLogin === true) {
             						// 로그인O -> 북마크 update
             						if(data.chkMyBookmark === true) {
             							// 이미 저장되어있는 북마크임 -> 북마크 제거
             							bookmarkIcon.style.backgroundImage = "url(img/bookmarkOutline.svg)";
             							console.log("이미 저장된 북마크 -> 북마크 제거");
             							console.log(data.result);
             						} else {
             							// 저장된 북마크 아님 -> 북마크 추가
             							bookmarkIcon.classList.toggle("add");
             							bookmarkIcon.style.backgroundImage = "url(img/bookmarkFill.svg)";
             							console.log("저장된 북마크 아님 -> 북마크 추가");
             							console.log(data.result);
             						}
             					} else {
             						// 로그인X 상태에서 북마크 클릭 -> 로그인 모달
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
       									// location.replace("controller?cmd=loginUI&prevURL="+encodedPrevURL);
       							     	location.replace("controller?cmd=loginUI");
       								});
             					}
             				}
             			});
             		});
                });
                
             	/* // 북마크 아이콘 클릭해서 추가/취소
             	kkList.forEach(function(item) {
             		const kkId = item.querySelector("#resultKKId").textContent;
             		const bookmarkIcon = item.querySelector(".bookmark");
             		
             		bookmarkIcon.addEventListener("click", function() {
             			// bookmarkIcon.classList.toggle("add");
             			if(bookmarkIcon.classList.contains("add")) {
             				// console.log("북마크 ON");
             				bookmarkIcon.style.backgroundImage = "url(img/bookmarkFill.svg)";
             				$.ajax({
             					url: "controller?cmd=addBookmarkAction",
             					data: {kkId: kkId},
             					dataType:"json",
             					success: function(data){
             						console.log("addBookmarkAction");
             						console.log(data);
             						console.log(data.result);
             						if(data.result == true) {
             							console.log("북마크 추가 완료");
             							bookmarkIcon.classList.toggle("add");
             							bookmarkIcon.style.backgroundImage = "url(img/bookmarkFill.svg)";
             						} else {
             							console.log("북마크 추가 실패");
             						}
             					}
             				});
             			} else {
             				console.log("북마크 OFF");
             				// bookmarkIcon.style.backgroundImage = "url(img/bookmarkOutline.svg)";
             				$.ajax({
             					url: "controller?cmd=deleteBookmarkAction",
             					data: {kkId: kkId},
             					dataType:"json",
             					success: function(data){
             						if(data.result == true) {
             							console.log("북마크 제거 완료");
             							bookmarkIcon.style.backgroundImage = "url(img/bookmarkOutline.svg)";
             						} else {
             							if(data.isLogin === false) {
             								console.log("미로그인 북마크 시도");
             								// 미로그인 상태에서 북마크 클릭 -> 로그인 모달
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
             							}
             						}
             					}
             				});
             			}
             		});
             	});  */      
             	// 선택한 노래방의 상세 페이지로 이동
             	leftSideList.forEach(function(item) {
             		const kkId = item.querySelector("#resultKKId").textContent;
             		item.addEventListener("click", function() {
	         			const clickedItem = this;
	         			const kkId = item.querySelector("#resultKKId").textContent;
	         			// prevURL 저장을 위한 작업
		            	const prevURL = window.location.search;
				      	console.log(prevURL);
				      	sessionStorage.setItem("prevURL", prevURL);
				      	sessionStorage.setItem("backToResultPage", prevURL);
				      	const encodedPrevURL = encodeURIComponent(prevURL);
				      	console.log(encodedPrevURL);
				      	sessionStorage.setItem("ePrevURL", encodedPrevURL);
		            	//
	         			$.ajax({
	            			url: "controller?cmd=kkDetailUI",
	       					/* data:{selectedKKId: kkId} */
	       					data: {clickedKKId: kkId}
	            		});
	         			// location.replace("controller?cmd=kkDetailUI&clickedKKId="+kkId);
	         			// location.replace("controller?cmd=kkDetailUI&prevURL="+encodedPrevURL);
	         			location.href = "controller?cmd=kkDetailUI&clickedKKId="+kkId;
         			});
             	});
             	
             	// 하단 메뉴바를 통한 페이지 이동
                $("nav div").on("click", function() {
              	  const clickedDiv = $(this);
              	  const imgAlt = clickedDiv.find("img").attr("alt");
              	  switch(imgAlt) {
              	  case "메인 페이지":
              		  // location.replace("controller?cmd=mainUI");
              		  location.href = "controller?cmd=mainUI";
              		  break;
              	  case "노래방 검색 페이지":
              		  // location.replace("controller?cmd=kkFilterUI");
              		  location.href = "controller?cmd=kkFilterUI";
              		  break;
              	  case "노래 검색 페이지":
              		  // location.replace("controller?cmd=musicListUI");
              		  location.href = "controller?cmd=musicListUI";
              		  break;
              	  case "나의 예약 내역 페이지":
              		  // location.replace("controller?cmd=reservationListUIAction");
              		  location.href = "controller?cmd=reservationListUIAction";
              		  break;
              	  case "마이페이지":
              		  // location.replace("controller?cmd=mypageUIAction");
              		  location.href = "controller?cmd=mypageUIAction";
              		  break;
              	  }
                });
            });
        </script>
  </body>
  </html>