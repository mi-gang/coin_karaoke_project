<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>나의 예약</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
      crossorigin="anonymous"
    />
    <link rel="stylesheet" href="css/common.css" />
    <link rel="stylesheet" href="css/reservation.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <links
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
        <img />
        <span>나의 예약</span>
      </header>
      <!-- 컨텐츠 컨테이너 -->
      <div id="container">
        <div id="reservation-wrapper">
          <div id="reservations-status">
            <button class="reservations-status-button" id="status-1">
              이용 중/예정
            </button>
            <button class="reservations-status-button" id="status-2">
              이용 완료
            </button>
            <button class="reservations-status-button" id="status-3">
              취소
            </button>
          </div>
          <div id="reservation-list">
            <div id="total-reservation">
              <span>총 </span>
              <span id="content-amount"></span>
              <span>건</span>
            </div>
            <div id="reservation-contents-wrapper"></div>
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
          <img src="img/reservationIcon_on.svg" alt="나의 예약 내역 페이지" />
        </div>
        <div><img src="img/userIcon.svg" alt="마이페이지" /></div>
      </nav>
    </div>

    <!-- 부트스트랩 -->
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
      crossorigin="anonymous"
    ></script>

    <!-- 모달 -->

    <!-- 시간 추가 모달 1 -->
    <div class="modal" id="addTimeModal">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <!-- Modal Header -->
          <div class="modal-header">
            <!-- <div id="modal-title-wrapper"> -->
            <!-- <h4 class="modal-title">시간 추가</h4> -->
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
            ></button>
            <!-- </div> -->
          </div>

          <!-- Modal body -->
          <div class="modal-body">
            <div id="add1-modal-body-wrapper">
              <h4 class="modal-title">시간 추가</h4>
              <div id="add1-modal-body-title">
                <span class="add-kkname"></span>
              </div>
              <div id="add1-modal-body-contents">
                <div class="add1-modal-body-content">
                  <span class="add1-modal-body-content-title"
                    >기존 예약 시간</span
                  >
                  <div class="add1-modal-body-content-time">
                    <div>
                      <span id="add1-original-startHour"></span>
                      <span>:</span>
                      <span id="add1-original-startMinute"></span>
                    </div>
                    <span>-</span>
                    <div>
                      <span id="add1-original-endHour"></span>
                      <span>:</span>
                      <span id="add1-original-endMinute"></span>
                    </div>
                  </div>
                </div>
                <hr />
                <div class="add1-modal-body-content">
                  <span class="add1-modal-body-content-title"
                    >추가 가능 시간</span
                  >
                  <div class="add1-addTime">
                    <div id="additional-hour-a">
                      <span class="additional-hour-a-status">1</span>
                      <span>시간</span>
                    </div>
                    <div id="additional-minute-a">
                      <span class="additional-minute-a-status">34</span>
                      <span>분</span>
                    </div>
                  </div>
                </div>
                <hr />
                <div class="add1-modal-body-content">
                  <span class="add1-modal-body-content-title">추가시간</span>
                  <div id="add1-add-time-arrow-img">
                    <div>
                      <span class="set-hour target-hour">--</span>
                      <span>:</span>
                      <span class="set-minute target-minute">--</span>
                    </div>
                    <button
                      type="button"
                      class="add1-add-time-arrow"
                      data-bs-toggle="modal"
                      data-bs-target="#addTimeModal2"
                    ></button>
                  </div>
                </div>
                <span id="add1-notice"></span>
              </div>
            </div>
          </div>

          <!-- Modal footer -->
          <div class="modal-footer">
            <div>
              <button
                type="button"
                disabled="disabled"
                class="submit-button add-button"
                id="add1-add-time-button"
                data-bs-toggle="modal"
                data-bs-target="#addTimeModal3"
                data-bs-dismiss="modal"
              >
                추가하기
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 시간 추가 모달 2 : 추가 시간 설정 -->
    <!-- The Modal -->
    <div class="modal" id="addTimeModal2">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <!-- Modal Header -->
          <div class="modal-header">
            <h4 class="modal-title">이용시간</h4>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
            ></button>
          </div>

          <!-- Modal body -->
          <div class="modal-body">
            <div id="add2-modal-body-wrapper">
              <div id="add2-modal-time">
                <span>시</span>
                <span>분</span>
              </div>
              <div id="add2-modal-time-setting">
                <input
                  type="text"
                  class="add2-time-setting-input"
                  id="setting-hour"
                  oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
                  maxlength="2"
                  placeholder="00"
                />
                <span>:</span>
                <input
                  type="text"
                  class="add2-time-setting-input"
                  id="setting-minute"
                  oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
                  maxlength="2"
                  placeholder="00"
                />
              </div>
            </div>
          </div>

          <!-- Modal footer -->
          <div class="modal-footer">
            <button
              type="button"
              class="submit-button add-button"
              id="add2-add-time-button"
              data-bs-toggle="modal"
              data-bs-dismiss="modal"
            >
              설정
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- 시간 추가 모달 3 : 예약 내용 확인 -->
    <!-- The Modal -->
    <div class="modal" id="addTimeModal3">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <!-- Modal Header -->
          <div class="modal-header"></div>

          <!-- Modal body -->
          <div class="modal-body">
            <div id="add1-modal-body-wrapper">
              <h4 class="modal-title">예약 내용을 확인해주세요 :)</h4>
              <div id="add1-modal-body-title">
                <span class="add-kkname"></span>
              </div>
              <div id="add1-modal-body-contents">
                <div class="add1-modal-body-content">
                  <span class="add1-modal-body-content-title">추가 시간</span>
                  <div id="add1-add-time-arrow-img">
                    <div>
                      <span class="set-hour"></span>
                      <span>:</span>
                      <span class="set-minute"></span>
                    </div>
                  </div>
                </div>
                <hr />
                <div class="add1-modal-body-content">
                  <span class="add1-modal-body-content-title"
                    >이용 종료 시간</span
                  >
                  <div id="add1-add-time-arrow-img">
                    <div>
                      <span id="end-hour">18</span>
                      <span>:</span>
                      <span id="end-minute">00</span>
                    </div>
                  </div>
                </div>
                <div id="add3-pay-amount">
                  <span id="">결제금액 : </span>
                  <span id="add3-amount"></span>
                  <span id="">원</span>
                </div>
              </div>
            </div>
          </div>

          <!-- Modal footer -->
          <div class="modal-footer">
            <button
              type="button"
              class="submit-button add-button back-button"
              data-bs-toggle="modal"
              data-bs-target="#addTimeModal"
              data-bs-dismiss="modal"
            >
              이전
            </button>
            <button
              type="button"
              class="submit-button add-button"
              id="time-setting-button"
              data-bs-toggle="modal"
              data-bs-dismiss="modal"
            >
              결제
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- 시간 추가 모달 4 : 추가 완료 모달 -->
    <!-- The Modal -->
    <div class="modal" id="addTimeModal4">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <!-- Modal Header -->
          <div class="modal-header">
            <!-- <h4 class="modal-title">이용시간</h4> -->
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
            ></button>
          </div>

          <!-- Modal body -->
          <div class="modal-body">
            <div id="add4-modal-body-wrapper">
              <span>시간 추가 완료되었습니다.</span>
            </div>
          </div>

          <!-- Modal footer -->
          <div class="modal-footer">
            <button
              type="button"
              class="submit-button add-button"
              id="add4-add-time-button"
              data-bs-toggle="modal"
              data-bs-dismiss="modal"
            >
              닫기
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- 예약 취소 모달 1 -->
    <div class="modal" id="cancelReservationModal1">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <!-- Modal Header -->
          <div class="modal-header">
            <!-- <h4 class="modal-title">이용시간</h4> -->
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
            ></button>
          </div>

          <!-- Modal body -->
          <div class="modal-body">
            <div class="cancel-modal-body-wrapper">
              <span class="cancel-modal-title">예약을 취소하시겠습니까?</span>
              <span class="cancel-modal-content"
                >노래방 이용 시작 시간 기준 <b>20분</b> 내 취소 시 <br />취소
                수수료가 부과됩니다.</span
              >
            </div>
          </div>

          <!-- Modal footer -->
          <div class="modal-footer">
            <button
              type="button"
              class="submit-button add-button"
              id="reservation-delete-button"
              data-bs-toggle="modal"
              data-bs-dismiss="modal"
            >
              취소하기
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- 예약 취소 모달 2 -->
    <div class="modal" id="cancelReservationModal2">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <!-- Modal Header -->
          <div class="modal-header">
            <!-- <h4 class="modal-title">이용시간</h4> -->
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
            ></button>
          </div>

          <!-- Modal body -->
          <div class="modal-body">
            <div class="cancel-modal-body-wrapper">
              <span class="cancel-modal-title">취소 완료</span>
              <span class="cancel-modal-content"
                >예약이 취소되었습니다. <br />세부 내역은 나의 예약 - 취소
                내역에서 확인해주세요.</span
              >
            </div>
          </div>

          <!-- Modal footer -->
          <div class="modal-footer">
            <button
              type="button"
              class="submit-button add-button"
              data-bs-toggle="modal"
              data-bs-dismiss="modal"
            >
              닫기
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- 리뷰 작성 모달 -->
    <div class="modal" id="addReviewModal">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <!-- Modal Header -->
          <div class="modal-header">
            <!-- <h4 class="modal-title">이용시간</h4> -->
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
            ></button>
          </div>

          <!-- Modal body -->
          <div class="modal-body">
            <div class="review-modal-body-wrapper">
              <!-- <span class="cancel-modal-title">취소 완료</span> -->
              <span class="review-modal-content" id="review-kkname"></span>
              <div class="star-wrapper">
              <span id="star">별점</span>
              <input
                type="text"
                class="review-star"
                id="review-star"
                maxlength="3"
              />
              </div>
              <textarea
                id="review-description"
                class="description"
                placeholder="이용한 노래방에 대한 솔직한 리뷰를 남겨주세요."
              ></textarea>
            </div>
          </div>

          <!-- Modal footer -->
          <div class="modal-footer">
            <button
              type="button"
              class="submit-button add-button"
              id="add-review-button"
              data-bs-toggle="modal"
              data-bs-dismiss="modal"
            >
              리뷰 등록
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- 리뷰 작성 완료 모달 -->
    <!-- The Modal -->
    <div class="modal" id="addReviewModal2">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <!-- Modal Header -->
          <div class="modal-header">
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
            ></button>
          </div>

          <!-- Modal body -->
          <div class="modal-body">
            <div class="cancel-modal-body-wrapper">
              <span class="cancel-modal-title">등록 완료</span>
              <span class="cancel-modal-content">리뷰가 등록되었습니다.</span>
            </div>
          </div>

          <!-- Modal footer -->
          <div class="modal-footer">
            <button
              type="button"
              class="submit-button add-button"
              data-bs-toggle="modal"
              data-bs-dismiss="modal"
            >
              닫기
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- 문의 작성 모달 -->
    <div class="modal" id="addInquireModal">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <!-- Modal Header -->
          <div class="modal-header">
            <!-- <h4 class="modal-title">이용시간</h4> -->
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
            ></button>
          </div>
          <!-- 		<form action="" method="post" id="inquire-action">
 -->
          <!-- Modal body -->
          <div class="modal-body">
            <div class="inquire-modal-body-wrapper">
              <!-- <span class="cancel-modal-title">취소 완료</span> -->
              <span class="review-modal-content" id="inquire-kkname"
                >세븐스타코인노래연습장 철산역점</span
              >
              <textarea
                id="inquire-description"
                class="description"
                name="content"
                placeholder="노래방에 문의 또는 신고할 내용을 작성해주세요."
              ></textarea>
            </div>
          </div>

          <!-- Modal footer -->
          <div class="modal-footer">
            <button
              type="button"
              class="submit-button add-button inquire-button"
              id="inquire-button"
              data-bs-toggle="modal"
              data-bs-dismiss="modal"
            >
              문의/신고하기
            </button>
          </div>
          <!-- </form> -->
        </div>
      </div>
    </div>

    <!-- 문의/신고 작성 완료 모달 -->
    <!-- The Modal -->
    <div class="modal" id="addInquireModal2">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <!-- Modal Header -->
          <div class="modal-header">
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
            ></button>
          </div>

          <!-- Modal body -->
          <div class="modal-body">
            <div class="cancel-modal-body-wrapper">
              <span class="cancel-modal-title">등록 완료</span>
              <span class="cancel-modal-content"
                >문의/신고가 접수되었습니다.</span
              >
            </div>
          </div>

          <!-- Modal footer -->
          <div class="modal-footer">
            <button
              type="button"
              class="submit-button add-button"
              data-bs-toggle="modal"
              data-bs-dismiss="modal"
            >
              닫기
            </button>
          </div>
        </div>
      </div>
    </div>

    <script src="js/reservation.js"></script>

    <script>
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
