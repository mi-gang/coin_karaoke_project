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
            <div class="playlist_song_num"></div>
          </div>
          <div class="ent_button_group">
            <input class="button on" id="tj" type="button" value="TJ" />
            <input class="button" id="kumyoung" type="button" value="KY" />
          </div>
          <div class="music_outputs">
            <div class="music_output" id="music_output">
              <div class="music_num"></div>
              <div class="music_info">
                <span class="music_title"></span>
                <span class="music_singer"></span>
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
            <div class="modal_playlist_title"></div>
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
            <div class="modal_playlist_title1"></div>
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
      $(".music_outputs").on("click", ".music_output .music_delete", function() {
        modal3.classList.add("on");
        let entInput = $(".on").attr("id");
        const songId= $(this).closest(".music_output").attr('data-set-songId');
        const songTitle = $(this).closest(".music_output").attr('data-set-songTitle');
   	  // const songTitle = songTitleElement.text();
   	$(".modal_playlist_title1").text(songTitle);
   	$(".delete_music_modal").on("click", ".btn_group1 .ok_btn1", function() {
 	$.ajax({
       	url: "controller?cmd=deleteMusic",
    	    data: { 	
    	      playlistId: playlistId,//brand	
    	  		brand : entInput,
    	  		songId : songId
    	     },
    	     error: function(jqXHR, textStatus, errorThrown) {
     	        alert("플레이리스트 추가에 실패했습니다: " + textStatus + " - " + errorThrown); 
     	      },
    	     success: function (result){
    	    	alert("삭제되었습니다.");
    	    	modal3.classList.remove("on"); 
    	  
    	     }
           })})
      
      });
     
      modalClose3.addEventListener("click", function () {
        modal3.classList.remove("on");
      });
      $(document).ready(function () {
    	  $(".button").on("click", function () {
              var entInput = this.id;
               console.log(entInput);
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
    	  
    	  $("#tj").click();
          
        });
        // const ent = document.querySelector(".button");
        //클릭한 버튼 값 가지고 오기
      
      
      
      //플레이 리스트 이름 가져오기
		 let playlistTitle="";
    	  let data = "";
    	  let playListTitle="";
    	  let entInput = $(".button").attr("id");
    	  console.log(entInput);
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
 	    	data = `<div class="playlist_title" data-set-title=` + result.result + `>` + result.result + `</div>`;
 	    	 $(".playlist_title").html(data);
 	        //playlistTitle = $(".playlist_title").html();
 	      //  const playlistTitle=document.querySelector(".playlist_title");
           // console.log(playlistTitle);
 	    	const dataSetTitle = $(".playlist_title").data("set-title"); // data-set-title 액세스
 	       console.log("플레이리스트 제목:", result.result);
 	      $(".modal_playlist_title").html(result.result);
 	       
   	}})
    //플레이 리스트 내에 저장되어 있는 곡 리스트 가져오기(버튼 선택으로 바꿔야함.)=> 버튼을 무조건 한번 눌러야 송출됨. + 곡 개수 가져오기
    //문제 : 플레이리스트의 버튼을 한번 눌러야 제대로 된 데이터를 가져옴.. 
    $(".ent_button_group").on("click", function () {
    	 let entInput = $(".on").attr("id");
    	// console.log($(".on"));
    	// console.log($(".on").attr("id"));
    	// console.log(entInput);
    	// console.log(entInput);
    	  let playListTitle = "";
    	  //초기화$(".music_outputs").html(playListTitle);
    	 // $(".music_outputs").html("");

 $.ajax ({
     "url" : "controller?cmd=playlistMusicList",
  	data:{ 
  		playlistId: playlistId,	
  		brand : entInput
  	},
  	 error: function(jqXHR, textStatus, errorThrown) {
	        alert("실패했습니다: " + textStatus + " - " + errorThrown); // More detailed error message
	      },
	     success: function(dataToSend) {
	    	 let result=JSON.parse(dataToSend);
	    	 console.log(entInput); 
	    	 for(i in result){
	    	 	    playListTitle+=`<div class="music_output" id="music_output" data-set-songTitle=`+ result[i].title +`  data-set-songId=`+ result[i].songId +`>
	                <div class="music_num">` + result[i].songId +`</div>
	                <div class="music_info">
	                  <span class="music_title">` + result[i].title +`</span>
	                  <span class="music_singer">` + result[i].singer +`</span>
	                </div>
	                <div class="music_delete">
	                  <img class="delete_img" src="img/Trash.svg" />
	                </div>
	              </div>`
	    	   		 }  	
	    	 $(".music_outputs").html(""); // 새 내용 추가 전에 컨테이너 지우기
	    	    if (result.length === 0) {
	    	      // 데이터 없음 메시지 출력
	    	      $(".music_outputs").html("<h1> 저장된 음악이 없습니다.</h1>");
	    	    } else {
	    	      $(".music_outputs").html(playListTitle);
	    	      console.log(result);
	    	    }}	      
	      })
	      	      //곡 내부 개수 
	       	$.ajax({
       	url: "controller?cmd=playlistMusicNumber",
    	    data: { 	
    	    	playlistId: playlistId,	
    	  		brand : entInput
    	     },
    	     error: function(jqXHR, textStatus, errorThrown) {
     	        alert("플레이리스트 추가에 실패했습니다: " + textStatus + " - " + errorThrown); 
     	      },
    	     success: function (result){
    	    	 let songNum=JSON.parse(result);
    			console.log(songNum.result);
    			$(".playlist_song_num").html(songNum.result+"곡");
    	     }
           })
	})
      
	//플레이리스트 이름 변경하기(드롭다운)
       $(".confirm_btn").on("click", function () {
        
       	const titleInput = document.querySelector(".new_play_list_title_input");
       	$.ajax({
       	url: "controller?cmd=updatePlaylistTitle",
    	    data: { 	
    			playlistId: playlistId,
    	      newTitle: titleInput.value//brand
    	     },
    	     error: function(jqXHR, textStatus, errorThrown) {
     	        alert("플레이리스트 추가에 실패했습니다: " + textStatus + " - " + errorThrown); 
     	      },
    	     success: function (result){
    			alert("플레이리스트 이름이 변경되었습니다!");
    			modal.classList.remove("on"); 
    			console.log("저장");
    	     }
           })
          })
   
          
  //  $(".modal_playlist_title").text(playlistTitle.value);
          //플레이리스트 삭제하기
           $(".ok_btn").on("click", function () {
       	$.ajax({
       	url: "controller?cmd=deletePlaylist",
    	    data: { 	
    			playlistId: playlistId,
    	     },
    	     error: function(jqXHR, textStatus, errorThrown) {
     	        alert("플레이리스트 추가에 실패했습니다: " + textStatus + " - " + errorThrown); 
     	      },
    	     success: function (result){
    			alert("삭제되었습니다.!");
    			modal2.classList.remove("on"); 
    			console.log("저장");
    			 location.href = "controller?cmd=mypagePlaylistUI";
    	     }
           })
          })
          

     
    </script>
  </body>
</html>
