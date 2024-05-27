<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                    <span class="filterItem">${searchGu}</span>
                    <span class="filterItem">23:30 ~ 01:20</span>
                    <span class="filterItem">40분</span>
                    <!-- <span class="filterItem">단체 가능</span> -->
                    <c:forEach items="${chkOptionsContents}" var="optionItem">
                    	<span class="filterItem">${optionItem}</span>
                    </c:forEach>
                </div>
            </div>
            <div class="resultList">
            	<c:if test="${resultList.size() == 0}">
            		<p>검색 조건에 해당되는 노래방이 없습니다</p>
            	</c:if>
            	<c:if test="${resultList.size() != 0}">
	                <c:forEach items="${resultList}" var="result">
		                <div class="resultItem">
		                    <div class="leftSide">
		                    	<span id="resultKKId" style="display: none;">${result.getKkId()}</span>
		                        <p class="resultKKTitle">${result.getName()}</p>
		                        <div class="starScoreWrapper">
		                            <span id="starAvgScore">${result.getStarRating()}</span>
		                            <div class="stars">
		                                <img src="img/filledStar.svg" alt="채워진 별">
		                                <img src="img/filledStar.svg" alt="채워진 별">
		                                <img src="img/filledStar.svg" alt="채워진 별">
		                                <img src="img/filledStar.svg" alt="채워진 별">
		                                <img src="img/star_half.svg" alt="0.5점 별">
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
            const kkList = document.querySelectorAll(".resultItem");
            kkList.forEach(function(item) {
            	item.addEventListener("click", function() {
            		console.log(item);
            		const clickedItem = this;
            		const clickedKKId = clickedItem.querySelector("#resultKKId").textContent;
            		console.log(clickedKKId);
            		/* let kkId = $(this).children(0).childNodes(1);
            		console.log(kkId); */
            		$.ajax({
            			url: "controller?cmd=kkDetailUI",
       					data:{selectedKKId: clickedKKId},
       					success: function(result) {
            				if(result==0) {
            					console.log("성공");
            				} else {
            					console.log("실패");
            				}
            			}
            		})
            		// location.replace("controller?cmd=searchForKKWithOptions&searchGu="+searchGu);
            		// location.replace("controller?cmd=kkDetailUI&clickedKKId="+clickedKKId);
            	});
            });
            
            // 내가 북마크한 노래방이면 아이콘 fill으로 페이지 노출
            const bookmarkList = document.querySelectorAll(".resultItem");
            bookmarkList.forEach(function(item){
            	console.log("----- 북마크 -----");
            	console.log(item);
            	const kkId = item.querySelector("#resultKKId").textContent;
            	console.log(kkId);
            	console.log("-----------------");
            	$.ajax({
            		url: "controller?cmd="
            	})
            }) 
        </script>
  </body>
  </html>