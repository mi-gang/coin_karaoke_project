<%@ page contentType="text/html;charset=UTF-8" language="java" %>

	<!DOCTYPE html>
	<html lang="en">

	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>Document</title>
		<link rel="stylesheet" href="css/common.css" />
		<link rel="stylesheet" href="css/musicSearchUI.css" />
		<link rel="preconnect" href="https://fonts.googleapis.com" />
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
		<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap"
			rel="stylesheet" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

	</head>

	<body>
		<!-- 모바일 컨테이너 -->
		<div id="mobileContainer">
			<!--헤더-->
			<header>
				<span>노래 검색</span>
			</header>
			<!-- 컨텐츠 컨테이너 -->
			<div id="container">
				<div class="music_input_group">
					<input class="music_input" type="search" id="search" placeholder="곡명, 가수로 검색하세요" /> <img
						class="music_search" src="img/search.svg" id="music_search" style="cursor: pointer" />
				</div>
				<div class="ent_button_group">
					<input class="button" id="tj" type="button" value="TJ" /> <input class="button" id="kumyoung"
						type="button" value="KY" />
				</div>
				<div class="music_list_output">
					<div class="music_output" id="music_output">
						<div class="music_num"></div>
						<div class="music_info">
							<span class="music_title"></span> <span class="music_singer"></span>
						</div>
						<div class="music_like">
							<img class="like_img" src="img/folder_open.svg" />
						</div>
					</div>
				</div>
			</div>
			<!-- 하단 메뉴바 -->
			<nav>
				<div>
					<img src="img/mainPageIcon.svg" alt="메인 페이지" />
				</div>
				<div>
					<img src="img/searchIcon.svg" alt="노래방 검색 페이지" />
				</div>
				<div>
					<img src="img/musicIcon_on.svg" alt="노래 검색 페이지" />
				</div>
				<div>
					<img src="img/reservationIcon.svg" alt="나의 예약 내역 페이지" />
				</div>
				<div>
					<img src="img/userIcon.svg" alt="마이페이지" />
				</div>
			</nav>
		</div>
		<div class="modal_overlay">
			<div class="modal_playlist" id="modal_playlist">
				<div class="music_like_modal">
					<div class="close_btn">
						<img class="close_img" src="img/close.svg" />
					</div>
					<div class="modal_title">내 음악에 저장하기</div>
					<div class="playlist_list">
						<span class="playlist"> <img class="like_btn" id="music_saved" src="img/folder_open.svg" />
							<span class="list_title"></span></span>
						</span>
					</div>
				</div>
				<div class="create_music_list">
					<img class="plus_img" src="img/add_circle_outline.svg" /> <span class="create_title">신규 플레이리스트
						생성</span>
				</div>
				<input class="confirm_btn" id="confirm1" type="button" value="저장" />
			</div>
			<div class="modal_create" id="create_playlist" style="display: none">
				<div class="music_like_modal">
					<div class="close_btn2">
						<img class="close_img2" src="img/close.svg" />
					</div>
					<div class="modal_title">새 플레이 리스트</div>
					<div class="new_play_list_title">플레이 리스트 명</div>
				</div>
				<input class="new_play_list_title_input" type="text" placeholder="플레이리스트 명을 입력하세요" /> <input
					class="confirm_btn" id="confirm2" type="button" value="저장" />
			</div>
		</div>
		<div class="modal_overlay2">
			<div class="modal_isMember" id="modal_isMember">
				<div class="close_btn3">
					<img class="close_img3" src="img/close.svg" />
				</div>
				<div class="modal_alert">로그인 유저만 사용할 수 있는 서비스입니다</div>
				<input class="login_btn" id="login_move" type="button" value="로그인하러 가기" />
			</div>
		</div>
		<script>
			var img = document.getElementById("music_saved");
			img.addEventListener("click", function () {
				img.src = "img/song_notsave.svg";
			});

			// test();
			// async function test(){
			//	  console.log("test 함수 시작");
			//	  const res = await fetch("https://api.manana.kr/karaoke.json");
			//	  console.log(res);
			//	  console.log("test 함수 종료");
			//  }
			// function toggleSaved() {
			//   document.getElementById("music_saved").src = "../img/song_notsave.svg";
			// }
			// $(".like_btn").on("click", function toggleSaved() {});
			// const ent = document.querySelector(".button");
			// ent.addEventListener("click", function () {
			//   ent.classList.add("on");
			//   console.log(ent.value);
			// });
			$(document).ready(function () {
				$("#tj").click();
			});
			// const ent = document.querySelector(".button");
			//클릭한 버튼 값 가지고 오기
			$(".button").on("click", function () {
				var entInput = this.id;
				// console.log(entInput);
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


			const modal = document.querySelector(".modal_overlay");
			const modalOpen = document.querySelector(".like_img");
			//플레이리스트 목록 불러오기
			$(".music_list_output").on("click", ".music_output .like_img", function () {
				modal.classList.add("on");
				let entInput = $(".on").attr("id");
				console.log(entInput);
				const musicNumElement = $(this).closest(".music_output").find(".music_num");
				const musicNum = musicNumElement.text();
				const musictitleElement = $(this).closest(".music_output").find('.music_title');
				const musictitle = musictitleElement.text();
				const musicSingerElement = $(this).closest(".music_output").find('.music_singer');
				const musicSinger = musicSingerElement.text();
				console.log(musicSinger);
				let data = "";
				$.ajax({
					url: "controller?cmd=checkMusicbymyplaylist",
					data: {

						brand: entInput,//brand
						songId: musicNum //musicNum
					},
					error: function (jqXHR, textStatus, errorThrown) {
						alert("실패했습니다: " + textStatus + " - " + errorThrown); // More detailed error message
					},
					success: function (list) {
    	    	if(list===null){
    	    		 $(".modal_overlay2").show(); 
    	    	}
    	    	else{
						//	console.log(JSON.parse(list));
						let musicbymyplaylistData = JSON.parse(list);
						let playListTitle = "";
						for (i in musicbymyplaylistData) {
							let a = "";
							if ((musicbymyplaylistData[i].isMusic) === true) {
								a = "src = 'img/folder_open.svg'";
							} else {
								a = "src = 'img/song_notsave.svg'";
							}

    	    playListTitle += '<span class="playlist" data-set-MusicNum='
    	    		+musicNum
    	    		+' data-set-playId='
    	    		+musicbymyplaylistData[i].playListId
    	    		+' data-set-musicTitle='
    	    		+musictitle
    	    		+' data-set-singer='
    	    		+musicSinger
    	    		+' data-set-isMusic='
    	    		+musicbymyplaylistData[i].isMusic
    	    		+ '><img class="like_btn" id="music_saved" ' 
    	    		+ a 
    	    		+ '/> <span	class="list_title">' 
    	    		+ musicbymyplaylistData[i].playListTitle
					+ '</span></span></span>'
    	    	}
    	    	 $(".playlist_list").html(playListTitle);
    	 
    	     // const result_data = list;
    	    }    
    	    } })  	 
    	});
      //플레이리스트에 음악 저장==>플레이리스트 목록 모달 호출하는 ajax 처리문 바로 밑에 또 비동기로 처리해야할듯. playlist_id
      $(".playlist_list").on("click", ".like_btn", function() {
    	  const  musicNum = $(this).closest(".playlist").attr('data-set-MusicNum');
    	  console.log(musicNum);
    	  const  musictitle =$(this).closest(".playlist").attr('data-set-musicTitle');
    	  console.log(musictitle);
    	  const  musicSinger = $(this).closest(".playlist").attr('data-set-singer');
  		isMusic=$(this).closest(".playlist").attr('data-set-isMusic');
  		console.log(isMusic);
    	  let entInput = $(".on").attr("id");
    	  playlistId=$(this).closest(".playlist").attr('data-set-playId');
    	  console.log(playlistId);
    	  const clickedLikeBtn = $(this);
    	    const isMusicSaved = clickedLikeBtn.attr("src") === "img/folder_open.svg";

   	  if(isMusic==="false"){
   		console.log("추가 ajax");
   	  	$.ajax({
   	  	   	url: "controller?cmd=addMusic",
   	  		    data: { 	
   	  		    	 brand: entInput,//brand
   	  	    	     songId: musicNum, //musicNum
   	  	    	     title: musictitle,
   	  	    	     singer: musicSinger,
   	  	    	     playlistId: playlistId,
   	  		     },
   	  		     error: function(jqXHR, textStatus, errorThrown) {
   	  	 	        alert("플레이리스트 추가에 실패했습니다: " + textStatus + " - " + errorThrown); 
   	  	 	      },
   	  		     success: function (result){
   	  				//alert('음악이 추가되었습니다.');
   	  			//document.getElementById("music_saved").src = "img/folder_open.svg";
   	  		    clickedLikeBtn.attr("src", "img/folder_open.svg");
   	  		     }
   	  	       })}//isMusic 값에 따라 ajax 동작(여기서는 addMusic)
   	  	       else{
   	  	    	console.log("삭제 ajax");
   	  	   $.ajax({
   	  	   	url: "controller?cmd=deleteMusic",
   	  		    data: { 	
   	  		    	 brand: entInput,//brand
   	  	    	     songId: musicNum, //musicNum
   	  	    	     playlistId: playlistId,
   	  		     },
   	  		     error: function(jqXHR, textStatus, errorThrown) {
   	  	 	        alert("플레이리스트 추가에 실패했습니다: " + textStatus + " - " + errorThrown); 
   	  	 	      },
   	  		     success: function (result){
   	  				//alert('음악이 삭제되었습니다.');
   	  			//document.getElementById("music_saved").src = "img/song_notsave.svg";
   	  		    clickedLikeBtn.attr("src", "img/song_notsave.svg");
   	  		     }
   	  	       })}//isMusic 값에 따라 ajax 동작(여기서는 deleteMusic)
   	  	    	   
      }); 
      const modalClose = document.querySelector(".close_img");
      modalOpen.addEventListener("click", function () {
        modal.classList.add("on");
      });
      // $(".music_like").on("click", ".like_img", function () {});

			modalClose.addEventListener("click", function () {
				modal.classList.remove("on");
			});

			const createModalOpen = document.querySelector(".create_music_list");
			const modalClose2 = document.querySelector(".close_img2");
			createModalOpen.addEventListener("click", function () {
				$("#create_playlist").show();
			});
			modalClose2.addEventListener("click", function () {
				$("#create_playlist").hide();
			});

      const isMemberModal = document.querySelector(".create_music_list");
      const modalClose3 = document.querySelector(".close_img3");
      isMemberModal.addEventListener("click", function () {
        $(".modal_overlay2").show();
      });
      modalClose3.addEventListener("click", function () {
        $(".modal_overlay2").hide();
      });


			$(".music_output").hide();
			const searchInput = document.querySelector("#search");
			// const entInput = document.querySelector(".button.on");
			$(".music_search").on("click", function () {
				// console.log($(".on").attr("id"));
				entInput = $(".on").attr("id");
				$(".music_output").show();
				let data = "";
				$.ajax({
					type: "GET",
					url:
						"https://api.manana.kr/karaoke/song/" +
						searchInput.value +
						"/" +
						entInput +
						".json",
					data: {},
					success: function (response) {
						// 서버에서 준 결과를 response라는 변수에 담음
						result_data = response; //JSON.parse()
						for (const value of result_data) {
							data += `<div class="music_output" id="music_output" data-set-musicNum=` + value.no + ` data-set-musicTitle="` + value.title + `" data-set-singer=` + value.singer + ` >
            <div class="music_num">` + value.no + `</div>
            <div class="music_info">
              <span class="music_title">` + value.title + `</span>
              <span class="music_singer">` + value.singer + `</span>
            </div>
            <div class="music_like">
             <img class="like_img" src="img/folder_open.svg"></div>
             </div>`;
						}
						$.ajax({
							type: "GET",
							url:
								"https://api.manana.kr/karaoke/singer/" +
								searchInput.value +
								"/" +
								entInput +
								".json",
							data: {},
							success: function (response) {
								// 서버에서 준 결과를 response라는 변수에 담음
								result_data = response; //JSON.parse()
								console.log(result_data);
								for (const value of result_data) {
									data += `<div class="music_output" id="music_output" data-set-musicNum=` + value.no + ` data-set-musicTitle=` + value.title + ` data-set-singer=` + value.singer + ` >>
            <div class="music_num">` + value.no + `</div>
            <div class="music_info">
              <span class="music_title">` + value.title + `</span>
              <span class="music_singer">` + value.singer + `</span>
            </div>
            <div class="music_like">
             <img class="like_img" src="img/folder_open.svg"></div>
             </div>`;
                }
                $(".music_list_output").html(data);
              },
            });
            // $(".music_list_output").html(data); 복붙완료
          },
        });
      });
      //새로운 플레이리스트 만드는 비동기
      $("#confirm2").on("click", function () {
    	  console.log("시작");
   	const titleInput = document.querySelector(".new_play_list_title_input");
   	$.ajax({
   	url: "controller?cmd=addPlaylist",
	    data: { 	
	      newTitle: titleInput.value//brand
	     },
	     error: function(jqXHR, textStatus, errorThrown) {
 	        alert("플레이리스트 추가에 실패했습니다: " + textStatus + " - " + errorThrown); 
 	      },
	     success: function (result){
	    	// result_data=JSON.parse(result)
	    	// console.log(result_data);
			//if(result_data.result!=true){
			//alert('플레이리스트가 추가되지 않았습니다.');
			//alert("저장되었습니다!");
			modal.classList.remove("on"); 
			$("#create_playlist").hide();
			$(".new_play_list_title_input").val("");
			//refreshPlaylists();
	     }
       })
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

		</script>
	</body>

	</html>