<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link rel="stylesheet" href="css/common.css" />
    <link rel="stylesheet" href="css/myPlaylistmusic.css" />
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
        <span>나의 노래</span>
      </header>
      <!-- 컨텐츠 컨테이너 -->
      <div id="container">
        <div class="musiclist">
          <div class="selected_playlist">
            <div class="playlist_group">
              <div class="playlist_title"></div>
              <div class="select_option">
                <button
                  class="select_btn"
                  type="button"
                  aria-label="Open_select_menu"
                >
                  <img class="select_img" src="img/dehaze.svg" />
                </button>
                <div class="dropdown_menu">
                  <div class="option1">플레이 리스트 이름 변경</div>
                  <div class="option2">플레이 리스트 삭제</div>
                </div>
              </div>
            </div>
            <div class="playlist_song_num">21곡</div>
          </div>
          <div class="ent_button_group">
            <input class="button" id="tj" type="button" value="TJ" />
            <input class="button" id="kumyoung" type="button" value="KY" />
          </div>
          <div class="music_outputs">
            <div class="music_output" id="music_output">
              <div class="music_num">12345</div>
              <div class="music_info">
                <span class="music_title">흐흐</span>
                <span class="music_singer">신난다</span>
              </div>
              <div class="music_delete">
                <img class="delete_img" src="img/Trash.svg" />
              </div>
            </div>
          </div>
        </div>
        <div class="modal_overlay">
          <div class="modal_change_title" id="nameChange_playlist">
            <div class="music_like_modal">
              <div class="close_btn">
                <img class="close_img" src="img/close.svg" />
              </div>
              <div class="modal_title">플레이 리스트 이름 변경</div>
              <div class="new_play_list_title">플레이 리스트 명</div>
            </div>
            <input
              class="new_play_list_title_input"
              type="text"
              placeholder="플레이리스트 명을 입력하세요"
            />
            <input
              class="confirm_btn"
              id="confirm"
              type="button"
              value="저장"
            />
          </div>
        </div>
        <div class="modal_overlay2">
          <div class="delete_modal">
            <div class="close_btn2">
              <img class="close_img2" src="img/close.svg" />
            </div>
            <div class="modal_playlist_title">'회식 음악 리스트'</div>
            <div class="delete_alert_text">
              <div class="delete_alert">삭제 하시겠습니까?</div>
              <div class="delete_alert_red">저장된 음악이 모두 삭제됩니다</div>
            </div>
            <div class="btn_group">
              <input class="ok_btn" id="confirm" type="button" value="확인" />
              <input
                class="cancel_btn"
                id="cancel"
                type="button"
                value="취소"
              />
            </div>
          </div>
        </div>
        <div class="modal_overlay3">
          <div class="delete_music_modal">
            <div class="close_btn3">
              <img class="close_img3" src="img/close.svg" />
            </div>
            <div class="modal_playlist_title1">'회식 음악 리스트'</div>
            <div class="delete_alert1">삭제 하시겠습니까?</div>
            <div class="btn_group1">
              <input class="ok_btn1" id="confirm" type="button" value="확인" />
              <input
                class="cancel_btn1"
                id="cancel"
                type="button"
                value="취소"
              />
            </div>
          </div>
        </div>
      </div>
      <!-- 하단 메뉴바 -->
      <nav>
        <div><img src="img/mainPageIcon.svg" alt="메인 페이지" /></div>
        <div>
          <img src="img/searchIcon.svg" alt="노래방 검색 페이지" />
        </div>
        <div><img src="img/musicIcon.svg" alt="노래 검색 페이지" /></div>
        <div>
          <img src="img/reservationIcon.svg" alt="나의 예약 내역 페이지" />
        </div>
        <div><img src="img/userIcon.svg" alt="마이페이지" /></div>
      </nav>
    </div>
    <script>
    //전 페이지에서 가지고 온 parameter 가지고 오기
	let query=window.location.search;
	let param=new URLSearchParams(query);
	let playlistId=param.get('playlistId');
    console.log(playlistId);
      var count = 0;
      const dropDown1 = document.querySelector(".select_btn");
      dropDown1.addEventListener("click", function () {
        count++;
      });
      const dropDown = document.querySelector(".select_btn");
      dropDown.addEventListener("click", function () {
        if (count == 0 || count % 2 == 0) {
          $(".dropdown_menu").css("display", "block");
        } else {
          $(".dropdown_menu").css("display", "none");
        }
      });
      const modal = document.querySelector(".modal_overlay");
      const modalOpen = document.querySelector(".option1");
      const modalClose = document.querySelector(".close_img");
      modalOpen.addEventListener("click", function () {
        modal.classList.add("on");
      });
      modalClose.addEventListener("click", function () {
        modal.classList.remove("on");
      });
      const modal2 = document.querySelector(".modal_overlay2");
      const modalOpen2 = document.querySelector(".option2");
      const modalClose2 = document.querySelector(".close_img2");
      modalOpen2.addEventListener("click", function () {
        modal2.classList.add("on");
      });
      modalClose2.addEventListener("click", function () {
        modal2.classList.remove("on");
      });
      const modal3 = document.querySelector(".modal_overlay3");
      const modalOpen3 = document.querySelector(".delete_img");
      const modalClose3 = document.querySelector(".close_img3");
      modalOpen3.addEventListener("click", function () {
        modal3.classList.add("on");
      });
      modalClose3.addEventListener("click", function () {
        modal3.classList.remove("on");
      });
      const ent = $(".button").get();
      let button = "";
      ent.forEach(function (option) {
        option.addEventListener("click", function () {
          ent.forEach(function (item) {
            item.classList.remove("on");
          });
          option.classList.add("on");
        });
      });
      $(document).ready(function () {
        $("#tj").click();
      });
      
      //플레이 리스트 이름 가져오기
      $(document).ready(function () {
    	  let data = "";
    	  let playListTitle="";
    $.ajax ({
        "url" : "controller?cmd=playlistTitle",
     	data:{ 
     		playlistId: playlistId	
     	},
     	 error: function(jqXHR, textStatus, errorThrown) {
 	        alert("실패했습니다: " + textStatus + " - " + errorThrown); // More detailed error message
 	      },
 	     success: function(dataToSend) {
 	    	 let result=JSON.parse(dataToSend);
 	    	 console.log(result);
 	    	 console.log(result.result);
 	    	data = `<div class="playlist_title">` + result.result + `</div>`;
 	    	 $(".playlist_title").html(data);
   	}})
	});
      
      //플레이 리스트 내에 저장되어 있는 곡 리스트 가져오기
       $(document).ready(function () {
    	  let data = "";
    	  let playListTitle="";
    	  let entInput = $(".on").attr("id");
    $.ajax ({
        "url" : "controller?cmd=playlistMusicList",
     	data:{ 
     		playlistId: playlistId,	
     		entInput : entInput
     	},
     	 error: function(jqXHR, textStatus, errorThrown) {
 	        alert("실패했습니다: " + textStatus + " - " + errorThrown); // More detailed error message
 	      },
 	     success: function(dataToSend) {
 	    	 let result=JSON.parse(dataToSend);
 	    	 console.log(result);
 	    	 console.log(result.result);
 	    	data = `<div class="playlist_title">` + result.result + `</div>`;
 	    	 $(".playlist_title").html(data);
   	}})
	});
    </script>
  </body>
</html>
