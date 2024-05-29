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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link
      rel="stylesheet"
      href="css/kkFilterUI.css"
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
        <span>노래방 검색</span>
      </header>
      <!-- 컨텐츠 컨테이너 -->
      <div id="container">
        <div id="regionWrapper">
          <p class="subTitle">지역</p>
          <div class="regionBox">
            <button class="label">이용 지역을 선택해주세요.</button>
            <img
              src="img/down_arrow.svg"
              alt="드롭다운"
            />
            <ul class="regionList">
              <li class="regionItem">서울 전체</li>
              <li class="regionItem">강남구</li>
              <li class="regionItem">강동구</li>
              <li class="regionItem">강북구</li>
              <li class="regionItem">강서구</li>
              <li class="regionItem">관악구</li>
              <li class="regionItem">광진구</li>
              <li class="regionItem">구로구</li>
              <li class="regionItem">금천구</li>
              <li class="regionItem">노원구</li>
              <li class="regionItem">도봉구</li>
              <li class="regionItem">동대문구</li>
              <li class="regionItem">동작구</li>
              <li class="regionItem">마포구</li>
              <li class="regionItem">서대문구</li>
              <li class="regionItem">서초구</li>
              <li class="regionItem">성동구</li>
              <li class="regionItem">성북구</li>
              <li class="regionItem">송파구</li>
              <li class="regionItem">양천구</li>
              <li class="regionItem">영등포구</li>
              <li class="regionItem">용산구</li>
              <li class="regionItem">은평구</li>
              <li class="regionItem">종로구</li>
              <li class="regionItem">중구</li>
              <li class="regionItem">중랑구</li>
            </ul>
          </div>
          <!-- <select id="region" name="region">
                    <option value="">이용 지역을 선택해주세요.</option>
                    <option value="강남구">강남구</option>
                    <option value="강동구">강동구</option>
                    <option value="강북구">강북구</option>
                    <option value="강서구">강서구</option>
                    <option value="관악구">관악구</option>
                    <option value="광진구">광진구</option>
                    <option value="구로구">구로구</option>
                    <option value="금천구">금천구</option>
                    <option value="노원구">노원구</option>
                    <option value="도봉구">도봉구</option>
                    <option value="동대문구">동대문구</option>
                    <option value="동작구">동작구</option>
                    <option value="마포구">마포구</option>
                    <option value="서대문구">서대문구</option>
                    <option value="서초구">서초구</option>
                    <option value="성동구">성동구</option>
                    <option value="성북구">성북구</option>
                    <option value="송파구">송파구</option>
                    <option value="양천구">양천구</option>
                    <option value="영등포구">영등포구</option>
                    <option value="용산구">용산구</option>
                    <option value="은평구">은평구</option>
                    <option value="종로구">종로구</option>
                    <option value="중구">중구</option>
                    <option value="중랑구">중랑구</option>
                </select>  -->
        </div>
        <div id="timeWrapper">
          <p class="subTitle">검색 시간대 및 이용시간</p>
          <div class="timeItemWrapper">
            <p>검색 시간대</p>
            <div id="timeSettingWrapper">
              <div
                id="startTime"
                class="timeSettingItem"
              >
              <input
              id="hiddenTime1"
              type="hidden"
            />
                <input
                    id="startTimeInput"
                  readonly
                  placeholder="-- : --"
                  value=""
                />
                <img
                  src="img/down_arrow.svg"
                  alt="드롭다운"
                />
              </div>
              <span>~</span>
              <div
                id="endTime"
                class="timeSettingItem"
              >
              <input
              id="hiddenTime2"
              type="hidden"
            />
                <input
                  readonly
                  placeholder="-- : --"
                  value=""
                />
                <img
                  src="img/down_arrow.svg"
                  alt="드롭다운"
                />
              </div>
              <div
                id="searchTimeModal1"
                class="modal searchTime"
              >
                <div class="modalContent">
                  <p id="startDate">2024.05.16(목)</p>
                  <div class="timeSettingWrapper">
                    <div class="timeTypeWrapper">
                      <div class="timeType">AM</div>
                      <div class="timeType">PM</div>
                    </div>
                    <div class="timeWrapper">
                      <div
                        id="hour"
                        class="time"
                      >
                        <p>시</p>
                        <input
                          name="hour"
                          type="number"
                        />
                      </div>
                      <span>:</span>
                      <div
                        id="minute"
                        class="time"
                      >
                        <p>분</p>
                        <input
                          name="minute"
                          type="number"
                          required
                        />
                      </div>
                    </div>
                  </div>
                  <div class="btnWrapper">
                    <div class="btn cancel">취소</div>
                    <div class="btn setting">설정</div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="timeItemWrapper item3">
            <p>이용 시간</p>
            <div
              id="hoursOfUse"
              class="timeSettingItem item3"
            >
            <input
              id="hiddenTime3"
              type="hidden"
            />
              <input
                readonly
                placeholder="-- : --"
              />
              <img
                src="img/down_arrow.svg"
                alt="드롭다운"
              />
            </div>
          </div>
          <div
            id="usingTimeModal"
            class="modal usingTime"
          >
            <div class="modalContent">
                <div class="closeIconWrapper">
                    <img
                    src="img/close.svg"
                    alt="모달창 닫기"
                    class="closeBtn"
                    />
                </div>
              <p class="subTitle">이용 시간</p>
              <div class="timeWrapper">
                <div
                  id="hour"
                  class="time"
                >
                  <p>시</p>
                  <input
                    name="hour"
                    type="number"
                  />
                </div>
                <span>:</span>
                <div
                  id="minute"
                  class="time"
                >
                  <p>분</p>
                  <input
                    name="minute"
                    type="number"
                    required
                  />
                </div>
              </div>
              <div id="usingTimeSettingBtn" class="btn">설정</div>
            </div>
          </div>
        </div>
        <div id="additionalOptionWrapper">
          <p class="subTitle">추가 조건</p>
          <div class="optionsWrapper">
            <div class="optionItem">
              <svg width="40" height="40" viewBox="0 0 40 40" fill="none" xmlns="http://www.w3.org/2000/svg">
                <path d="M11.0333 21.8C10.8264 21.8748 10.6386 21.9942 10.4831 22.1498C10.3275 22.3053 10.2081 22.4931 10.1333 22.7C10.012 23.0028 9.98234 23.3346 10.0479 23.6542C10.1135 23.9738 10.2714 24.2671 10.5021 24.4978C10.7327 24.7285 11.0261 24.8864 11.3457 24.952C11.6652 25.0175 11.997 24.9879 12.2999 24.8666C12.5019 24.7822 12.6879 24.6638 12.8499 24.5166C12.9971 24.3546 13.1155 24.1686 13.1999 23.9666C13.2932 23.7688 13.3389 23.5519 13.3333 23.3333C13.3271 22.892 13.1545 22.4693 12.8499 22.15C12.6155 21.9187 12.3179 21.762 11.9946 21.6997C11.6713 21.6375 11.3368 21.6723 11.0333 21.8ZM32.9333 15.1333L30.6666 8.41662C30.3208 7.43867 29.6795 6.59246 28.8314 5.99522C27.9833 5.39799 26.9705 5.07929 25.9333 5.08329H14.0666C13.0293 5.07929 12.0165 5.39799 11.1684 5.99522C10.3203 6.59246 9.679 7.43867 9.33325 8.41662L7.06659 15.1833C6.00046 15.4625 5.05645 16.0861 4.3813 16.9571C3.70616 17.8282 3.33771 18.8979 3.33325 20V26.6666C3.33615 27.6982 3.65805 28.7036 4.25482 29.5451C4.85158 30.3865 5.694 31.0228 6.66659 31.3666V33.3333C6.66659 33.7753 6.84218 34.1992 7.15474 34.5118C7.4673 34.8244 7.89122 35 8.33325 35C8.77528 35 9.1992 34.8244 9.51176 34.5118C9.82432 34.1992 9.99992 33.7753 9.99992 33.3333V31.6666H29.9999V33.3333C29.9999 33.7753 30.1755 34.1992 30.4881 34.5118C30.8006 34.8244 31.2246 35 31.6666 35C32.1086 35 32.5325 34.8244 32.8451 34.5118C33.1577 34.1992 33.3333 33.7753 33.3333 33.3333V31.3666C34.3058 31.0228 35.1483 30.3865 35.745 29.5451C36.3418 28.7036 36.6637 27.6982 36.6666 26.6666V20C36.6621 18.8979 36.2937 17.8282 35.6185 16.9571C34.9434 16.0861 33.9994 15.4625 32.9333 15.1833V15.1333ZM12.4833 9.46662C12.5951 9.13559 12.8081 8.84806 13.0922 8.64469C13.3763 8.44132 13.7172 8.33239 14.0666 8.33329H25.9333C26.2967 8.31432 26.6564 8.41483 26.9573 8.61948C27.2583 8.82413 27.484 9.12165 27.5999 9.46662L29.3499 15H10.6499L12.4833 9.46662ZM33.3333 26.6666C33.3333 27.1087 33.1577 27.5326 32.8451 27.8451C32.5325 28.1577 32.1086 28.3333 31.6666 28.3333H8.33325C7.89122 28.3333 7.4673 28.1577 7.15474 27.8451C6.84218 27.5326 6.66659 27.1087 6.66659 26.6666V20C6.66659 19.5579 6.84218 19.134 7.15474 18.8214C7.4673 18.5089 7.89122 18.3333 8.33325 18.3333H31.6666C32.1086 18.3333 32.5325 18.5089 32.8451 18.8214C33.1577 19.134 33.3333 19.5579 33.3333 20V26.6666ZM27.6999 21.8C27.4931 21.8748 27.3053 21.9942 27.1497 22.1498C26.9942 22.3053 26.8748 22.4931 26.7999 22.7C26.6787 23.0028 26.649 23.3346 26.7146 23.6542C26.7801 23.9738 26.938 24.2671 27.1687 24.4978C27.3994 24.7285 27.6927 24.8864 28.0123 24.952C28.3319 25.0175 28.6637 24.9879 28.9666 24.8666C29.1734 24.7918 29.3612 24.6723 29.5168 24.5168C29.6723 24.3613 29.7917 24.1735 29.8666 23.9666C29.9599 23.7688 30.0056 23.5519 29.9999 23.3333C29.9938 22.892 29.8211 22.4693 29.5166 22.15C29.2822 21.9187 28.9846 21.762 28.6613 21.6997C28.338 21.6375 28.0034 21.6723 27.6999 21.8ZM21.6666 21.6666H18.3333C17.8912 21.6666 17.4673 21.8422 17.1547 22.1548C16.8422 22.4673 16.6666 22.8913 16.6666 23.3333C16.6666 23.7753 16.8422 24.1992 17.1547 24.5118C17.4673 24.8244 17.8912 25 18.3333 25H21.6666C22.1086 25 22.5325 24.8244 22.8451 24.5118C23.1577 24.1992 23.3333 23.7753 23.3333 23.3333C23.3333 22.8913 23.1577 22.4673 22.8451 22.1548C22.5325 21.8422 22.1086 21.6666 21.6666 21.6666Z" fill="#B6B6B6"/>
                </svg>
              <p class="optionName">주차 가능</p>
            </div>
            <div class="optionItem">
              <img
                src="img/up.svg"
                alt="지상층 아이콘"
              />
              <p class="optionName">지상층</p>
            </div>
            <div class="optionItem">
              <img
                src="img/ThermometerHalf.svg"
                alt="냉난방 아이콘"
              />
              <p class="optionName">냉난방</p>
            </div>
            <div class="optionItem">
              <img
                src="img/users.svg"
                alt="단체 아이콘"
              />
              <p class="optionName">단체 가능</p>
            </div>
          </div>
        </div>
        <button id="searchKKBtn">검색</button>
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
    </div>
    <script>
      const label = document.querySelector(".label");
      const options = document.querySelectorAll(".regionItem");
      const searchOptions = document.querySelectorAll(".optionItem");
      // const timeTypes = document.querySelectorAll(".timeType");
      // let timeType = "";

      // 클릭한 옵션의 텍스트를 라벨 안에 넣음
      const handleSelect = function (item) {
        label.innerHTML = item.textContent;
        label.parentNode.classList.remove("active");
      };

      // 옵션 클릭시 클릭한 옵션을 넘김
      options.forEach(function (option) {
        option.addEventListener("click", function () {
          handleSelect(option);
        });
      });
      // 라벨을 클릭시 옵션 목록이 열림/닫힘
      label.addEventListener("click", function () {
        if (label.parentNode.classList.contains("active")) {
          label.parentNode.classList.remove("active");
        } else {
          label.parentNode.classList.add("active");
        }
      });
      // 옵션 아이템 클릭시 테두리 색상 변경
      searchOptions.forEach(function (option) {
        option.addEventListener("click", function () {
          // 클릭한 옵션 아이템에만 active 클래스 추가
          option.classList.toggle("active");
          const svg = option.querySelector("img");
          svg.style.fill = option.classList.contains("active")
            ? "#e3cdff"
            : "currentColor";
        });
      });

      // 모달
      // 모달 가져오기
      var modal = document.getElementById("searchTimeModal");
      // var timeSettingItems = document.querySelectorAll(".timeSettingItem");
      // var startTimeBtn = document.getElementById("startTime");
      // var entTimeBtn = document.getElementById("endTime");

      // 모달 닫기 버튼(취소) 가져오기
      var cancelBtn = document.querySelector(".btn.cancel");

    //   startTimeBtn.addEventListener("click", function () {
    //     modal.style.display = "flex";
    //   });

      cancelBtn.addEventListener("click", function () {

        console.log("취소 버튼 클릭");
        modal.style.display = "none";
      });

      $(document).ready(function () {
        const modal = $("#searchTimeModal1");

        let searchType = 0;  // 0: 시작 시간대, 1: 종료 시간대
        let startTimeInput = $("#startTime input");
        let endTimeInput = $("#endTime input");
        let startModalHourInput = $("#searchTimeModal1 #hour input");
        let startMinuteHourInput = $("#searchTimeModal1 #minute input");
        const timeTypes = $(".timeType").get();
        let timeType = "";
        const cancelBtn = $(".btn.cancel");
        const settingBtn = $(".btn.setting");

        const modal2 = $("#usingTimeModal");
        let usingTimeInput = $("#hoursOfUse input");
        let usingModalHourInput = $("#usingTimeModal #hour input");
        let usingModalMinuteInput = $("#usingTimeModal #minute input");
        const usingTimeSettingBtn = $("#usingTimeSettingBtn");

        // 검색시간대 - 시작 모달 열기
        $("#startTime").on("click", function () {
            searchType = 0;
            let tmp1 = $("#hiddenTime1").val().split(":");
            startModalHourInput.val(tmp1[0]);
            startMinuteHourInput.val(tmp1[1]);

            // if(startModalHourInput.val() < 12) {
            //     timeTypes[0].classList.add("active");
            //     timeTypes[1].classList.remove("active");
            // } else if(timeType === "PM") {
            //     timeTypes[0].classList.remove("active");
            //     timeTypes[1].classList.add("active");
            // }
           
            if(startModalHourInput.val() < 12) {
                timeTypes[0].classList.add("active");
                timeTypes[1].classList.remove("active");
            } else if(startModalHourInput.val() >= 12) {
                timeTypes[0].classList.remove("active");
                timeTypes[1].classList.add("active");
            }
          modal.css("display", "flex");
        });

        // 검색시간대 - 종료 모달 열기
        $("#endTime").on("click", function () {
            searchType = 1;
            let tmp2 = $("#hiddenTime2").val().split(" : ");
            startModalHourInput.val(tmp2[0]);
            startMinuteHourInput.val(tmp2[1]);
            if(startModalHourInput.val() < 12) {
                timeTypes[0].classList.add("active");
                timeTypes[1].classList.remove("active");
            } else if(startModalHourInput.val() >= 12) {
                timeTypes[0].classList.remove("active");
                timeTypes[1].classList.add("active");
            }
          modal.css("display", "flex");
        });

        cancelBtn.on("click", function () {
          modal.css("display", "none");
        });


        // AM, PM 선택한 div에 테두리 색 변경
        timeTypes.forEach(function (option) {
            option.addEventListener("click", function () {
            timeTypes.forEach(function (item) {
                item.classList.remove("active");
            });
            option.classList.add("active");
                timeType = option.innerHTML;
            });
        });

        settingBtn.on("click", function () {
          let hour = startModalHourInput.val();
          let minute = startMinuteHourInput.val();

          if(timeType == "") {
            alert("AM 또는 PM 시간대를 설정해주세요!");
          }
          if (startModalHourInput.val() === "" || startMinuteHourInput.val() === "") {
            alert("시, 분 모두 입력해주세요!");
            return;
          } else {
            if(hour > 12) {
                alert("'시'는 0~12 사이의 값으로 입력 가능합니다.");
            }
            if(minute > 59) {
                alert("'분'은 0~59사이의 값으로 입력 가능합니다.");
            }
            if(timeType === "PM") {
                hour = parseInt(hour) + 12;
            }
          }

          if(searchType === 0) {
            startTimeInput.val(hour + ":" + minute);
            $("#startTimeInput").val(hour + ":" + minute);
            $("#hiddenTime1").val(hour + ":" + minute);
        } else if(searchType === 1) {
            endTimeInput.val(hour + ":" + minute);
            $("#hiddenTime2").val(hour + ":" + minute);
          }

          modal.css("display", "none");
        });

        // 이용시간 설정 모달 열기
        $("#hoursOfUse").on("click", function () {
          $("#usingTimeModal").css("display", "flex");
        });

        // 이용시간 모달 닫기
        $("#usingTimeModal .closeBtn").on("click", function () {
          $("#usingTimeModal").css("display", "none");
        });

        // const modal2 = $("#usingTimeModal");
        // let usingTimeInput = $("#hoursOfUse input");
        // let usingModalHourInput = $("#usingTimeModal #hour input");
        // let usingModalMinuteInput = $("#usingTimeModal #minute input");
        // const usingTimeSettingBtn = $("#usingTimeSettingBtn");

        usingTimeSettingBtn.on("click", function() {
            let hour = usingModalHourInput.val();
            let minute = usingModalMinuteInput.val();

            if(hour === "" && minute === "") {
                alert("이용하실 시간을 입력해주세요!");
                return;
            } else {
                if(hour === "" && minute !== "" && minute < 10) {
                    alert("최소 이용 시간은 10분입니다 :)");
                    return;
                }
                if(minute > 59) {
                    alert("'분'은 0~59 사이의 값으로 입력 가능합니다.");
                    return;
                }
                if(hour === "" && minute !== "" && minute > 10) {
                    hour = 0;
                }
                usingTimeInput.val(hour+":"+minute);
                $("#hiddenTime3").val(hour + ":" + minute);
            }

            modal2.css("display", "none");
        })        
      });
      
      // 선택한 추가 조건 배열로 임시 저장
      let chkAdditionalOptions = ["0", "0", "0", "0"];
      searchOptions.forEach(function (option) {
        option.addEventListener("click", function () {
        	const clickedOptionName = this.querySelector(".optionName").textContent;
            console.log(clickedOptionName);
            switch(clickedOptionName) {
            case "주차 가능":
            	if(chkAdditionalOptions[0] === "1") {
            		chkAdditionalOptions[0] = "0";
            	} else {
            		chkAdditionalOptions[0] = "1";
            	}
            	break;
            case "지상층":
            	if(chkAdditionalOptions[1] === "1") {
            		chkAdditionalOptions[1] = "0";
            	} else {
            		chkAdditionalOptions[1] = "1";
            	}
            	break;
            case "냉난방":
            	if(chkAdditionalOptions[2] === "1") {
            		chkAdditionalOptions[2] = "0";
            	} else {
            		chkAdditionalOptions[2] = "1";
            	}
            	break;
            case "단체 가능":
            	if(chkAdditionalOptions[3] === "1") {
            		chkAdditionalOptions[3] = "0";
            	} else {
            		chkAdditionalOptions[3] = "1";
            	}
            	break;
            }
        });
      });
     
      
      // 검색 버튼 클릭하여 선택한 조건의 노래방 검색하기
      $("#searchKKBtn").click(function() {
    	  let searchGu = $(".label").text();
    	  let chkCount = 0;
    	  for(let t=0; t<chkAdditionalOptions.length; t++) {
    		  if(chkAdditionalOptions[t] === "1") {
    			  chkCount ++;
    		  }
    	  }
    	  console.log(chkCount);
    	  // 검색 시간대 및 이용 시간에 대한 검색 필터링은 추후 작업 예정
    	  // jQuery.ajaxSettings.traditional = true;
    	  // 참고) chkAdditionalOptions는 ["1", "0", "1", "1"] 문자열 배열이다.
    	  $.ajax({
    		  url: "controller?cmd=searchForKKWithOptions",
    		  data: {searchGu: searchGu, 
    			  chkAdditionalOptions: chkAdditionalOptions,
    			  chkCount: chkCount},
    		  traditional: true,
    		  success: function(result) {
    			  console.log(result);
    			  if(result == 0) {
    				  console.log("필터링 검색 성공");
    			  } else {
    				  console.log("필터링 검색 실패");
    			  }
    			  /* console.log(data);
    			  console.log(status); */
    		  }
    	  })
    	  location.replace("controller?cmd=searchForKKWithOptions&searchGu="+searchGu+"&chkAdditionalOptions="+chkAdditionalOptions+"&chkCount="+chkCount);
      })
      
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
    </script>
  </body>
</html>
${result}
