<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<!DOCTYPE html>
	<html lang="en">

	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>Document</title>
		<link rel="stylesheet" href="css/common.css" />
		<link rel="stylesheet" href="css/mypagePlaylist.css" />
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
				<img id="back-button" src="img/left arrow.svg" alt="이전 페이지 이동" /> <span>나의 플레이리스트</span>
			</header>
			<!-- 컨텐츠 컨테이너 -->
			<div id="container">
				<div class="playlists">
					<div class="playlists_sub">
						<form action="controller?cmd=myPlaylistmusicUI">
							<div class="playlist">
								<!-- 기본 플레이리스트 한개는 있어야하기에 append 사용! -->
								<div class="playlist_group">
									<div class="playlist_title"></div>
									<div class="delete_playlist">
										<img class="delete_img" src="img/close.svg" />
									</div>
								</div>
								<div class="playlist_song_num"></div>
							</div>
						</form>
					</div>
					<div class="create_new_playlist">
						<div class="create">
							<img class="plus_img" src="img/add_circle_outline.svg" />
							<div class="create_title">신규 플레이리스트 생성</div>
						</div>
					</div>

					<div class="modal_overlay">
						<div class="modal_create" id="create_playlist">
							<div class="music_like_modal">
								<div class="close_btn">
									<img class="close_img" src="img/close.svg" />
								</div>
								<div class="modal_title">새 플레이 리스트</div>
								<div class="new_play_list_title">플레이 리스트 명</div>
							</div>
							<input class="new_play_list_title_input" type="text" placeholder="플레이리스트 명을 입력하세요" /> <input
								class="confirm_btn" id="confirm" type="button" value="저장" />
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
								<input class="ok_btn" id="confirm2" type="button" value="확인" /> <input
									class="cancel_btn" id="cancel2" type="button" value="취소" />
							</div>
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
					<img src="img/musicIcon.svg" alt="노래 검색 페이지" />
				</div>
				<div>
					<img src="img/reservationIcon.svg" alt="나의 예약 내역 페이지" />
				</div>
				<div>
					<img src="img/userIcon.svg" alt="마이페이지" />
				</div>
			</nav>
		</div>
		</div>
		<script>
			const modal = document.querySelector(".modal_overlay");
			const modalOpen = document.querySelector(".create_new_playlist");
			const modalClose = document.querySelector(".close_img");
			modalOpen.addEventListener("click", function () {
				modal.classList.add("on");
			});
			modalClose.addEventListener("click", function () {
				modal.classList.remove("on");
				//alert('X');
			});

			const modal2 = document.querySelector(".modal_overlay2");
			const modalOpen2 = document.querySelector(".delete_img");
			const modalClose2 = document.querySelector(".close_img2");
			$(".playlists").on("click", ".playlist_group .delete_playlist", function () {
				modal2.classList.add("on");
				const playlistTitleElement = $(this).closest(".playlist").find(".playlist_title");
				const playlistTitle = playlistTitleElement.text();
				$(".modal_playlist_title").text(playlistTitle);
				$("#cancel2").on("click", function () {
					alert('취소되었습니다.');
					modal2.classList.remove("on");
				})
				//
				playlistId = $(this).closest(".playlist").attr('data-set-playId');
				console.log(playlistId);
				$("#confirm2").on("click", function () {
					$.ajax({
						url: "controller?cmd=deletePlaylist",
						data: {
							playlistId: playlistId//brand
						},
						error: function (jqXHR, textStatus, errorThrown) {
							alert("플레이리스트 추가에 실패했습니다: " + textStatus + " - " + errorThrown);
						},
						success: function (result) {
							alert("삭제되었습니다.");
							modal2.classList.remove("on");

						}
					})
				})

			});
			modalClose2.addEventListener("click", function () {
				modal2.classList.remove("on");

			});

			$(".create_new_playlist").on('click', function () {
				console.log(this);
			});

			//새로운 플레이리스트 만드는 비동기
			$(".confirm_btn").on("click", function () {
				console.log("시작");
				const titleInput = document.querySelector(".new_play_list_title_input");
				$.ajax({
					url: "controller?cmd=addPlaylist",
					data: {
						newTitle: titleInput.value//brand
					},
					error: function (jqXHR, textStatus, errorThrown) {
						alert("플레이리스트 추가에 실패했습니다: " + textStatus + " - " + errorThrown);
					},
					success: function (result) {
						// result_data=JSON.parse(result)
						// console.log(result_data);
						//if(result_data.result!=true){
						//alert('플레이리스트가 추가되지 않았습니다.');
						alert("저장되었습니다!");
						modal.classList.remove("on");
						console.log("저장");
					}
				})
			})

			$(document).ready(function () {
				let data = "";
				let playListTitle = "";
				$.ajax({
					"url": "controller?cmd=myPlaylist",
					data: {},
					error: function (jqXHR, textStatus, errorThrown) {
						alert("실패했습니다: " + textStatus + " - " + errorThrown); // More detailed error message
					},
					success: function (list) {
						PLdata = list;
						console.log(JSON.parse(list));
						let myplaylist = JSON.parse(list);
						for (i in myplaylist) {
							playListTitle += `<div class="playlist" data-set-playId=` + myplaylist[i].playListId + `><div class="playlist_group">
		<div class="playlist_title">` + myplaylist[i].playListTitle + `</div>
		<div class="delete_playlist">
			<img class="delete_img" src="img/close.svg" />
		</div>
	</div></div>`
						} $(".playlists_sub").html(playListTitle);
					}
				})
			});
			$(".playlists").on("click", ".playlist_title", function () {
				playlistId = $(this).closest(".playlist").attr('data-set-playId');
				console.log(playlistId);
				location.href = "controller?cmd=playlistMusicUI&playlistId=" + playlistId;
			})
			//userId 없을때 경고 문구 alert 창      
			var userId = '<%=(String)session.getAttribute("userId")%>';
			if (userId == "null") {
				alert("로그인 후 사용하세요")
			}

			// 리뷰 페이지 뒤로 가기 -> 마이페이지로
			$("#back-button").on("click", function () {
				location.href = "controller?cmd=mypageUIAction";
			});
			// 하단 메뉴바를 통한 페이지 이동
			$("nav div").on("click", function () {
				const clickedDiv = $(this);
				const imgAlt = clickedDiv.find("img").attr("alt");
				switch (imgAlt) {
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