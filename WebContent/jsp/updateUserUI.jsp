<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="ko">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>회원가입</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <link rel="stylesheet" href="css/common.css">
        <link rel="stylesheet" href="css/updateUserUI.css">

    </head>

    <body>
        <div id="mobileContainer">
            <!--헤더-->
            <header>
                <img src="img/left arrow.svg" alt="이전 페이지 이동">
                <span>회원정보 수정</span>
            </header>
            <!-- 컨텐츠 컨테이너 -->
            <div id="container">
                <form action="controller?cmd=login" method="post">
                    <div id="inputField">
                        <!-- <input type="date" id="hiddenDate"> -->
                        <div><input name="userId" id="userId" placeholder="hellonuri" value="hellonuri" readonly>
                        </div>
                        <div><input name="nickname" id="nickname" data-original-id="소리꾼123" value="소리꾼123">
                            <img class="default">
                        </div>
                        <!-- <input name="birthDate" id="birthDate" placeholder="생년월일" disabled> -->
                        <div><input type="date" name="birthDate" id="birthDate" value="2024-05-01" readonly></div>

                        <div><input type="password" placeholder="••••••••" readonly><img class="editPencil"
                                id="pwEditPencil">
                        </div>
                    </div>
                    <div class="inputError" id="errorMessage"></div>
                    <button type="submit" class="inactivate" disabled>수정</button>

                </form>

            </div>
            <!-- The Modal -->
            <div class="modal" id="updatePasswordModal">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <!-- Modal Header -->
                        <div class="modal-header">
                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                        </div>

                        <!-- Modal body -->
                        <div class="modal-body">
                            <div id="add1-modal-body-wrapper">
                                <h4 class="modal-title">비밀번호 변경</h4>
                                <div id="add1-modal-body-contents">
                                    <form action="controller?cmd=login" method="post">
                                        <div id="pwInputField">
                                            <div><input name="password" type="password" id="oldPassword"
                                                    placeholder="기존 비밀번호"><img class="default">
                                            </div>
                                            <div><input name="password" type="password" id="password"
                                                    placeholder="비밀번호 입력"><img class="default">
                                            </div>
                                            <div><input id="passwordConfirm" type="password" placeholder="비밀번호 확인"><img
                                                    class="default"></div>
                                        </div>
                                        <div class="inputError" id="pwErrorMessage"></div>

                                    </form>
                                </div>
                            </div>
                        </div>

                        <!-- Modal footer -->
                        <div class="modal-footer">
                            <button type="button" class="submit_button add-button" id="hidePasswordModal"
                                data-bs-toggle="modal" data-bs-dismiss="modal">
                                취소
                            </button>
                            <button type="button" class="submit_button add-button" id="time-setting-button">
                                변경
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal" id="updatePasswordModal2">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <!-- Modal Header -->
                        <div class="modal-header">
                            <!-- <h4 class="modal-title">이용시간</h4> -->
                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                        </div>

                        <!-- Modal body -->
                        <div class="modal-body">
                            <div id="add4-modal-body-wrapper">
                                <span>비밀번호가 변경되었습니다.</span>
                            </div>
                        </div>

                        <!-- Modal footer -->
                        <div class="modal-footer">
                            <button type="button" class="submit_button add-button" id="add2-add-time-button"
                                data-bs-toggle="modal" data-bs-dismiss="modal">
                                닫기
                            </button>
                        </div>
                    </div>
                </div>
            </div>


        </div>
        <script>
            const nickname = $("#nickname")
            const password = $("#password");
            const passwordConfirm = $("#passwordConfirm");

            const errorMessage = $("#errorMessage");
            const loginBtn = $("form button");

            $("header img").on("click", () => window.history.back());

            $("#nickname").on("change focus", checkNickname);
            $("#password").on("change focus", checkPassword);
            $("#passwordConfirm").on("change focus", checkPassword);
            $("#inputField input").on("change", checkLoginActivate);
            $("form").on("submit", updateUser);

            $("#pwEditPencil").on("click", showPasswordModal);
            // let updatePasswordModalEL = document.getElementById('updatePassword');

            function showPasswordModal() {
                const updatePasswordModal = new bootstrap.Modal(document.getElementById("updatePasswordModal"));
                updatePasswordModal.show();
            }


            function updateUser(e) {
                e.preventDefault();
            }

            $("#time-setting-button").on("click", updatePassword);
            async function updatePassword(e) {
                e.preventDefault();
                let result = false;
                const oldPassword = $("#oldPassword");
                const newPassword = $("#password");

                try {
                    const res = await fetch("controller?cmd=updatePassword&oldPassword" + oldPassword.val() + "&newPassword=" + newPassword.val());
                    const data = await res.json();
                    result = data.result;
                } catch {

                }
                if (result)
                    new bootstrap.Modal(document.getElementById("updatePasswordModal2")).show();
                else {
                    $("#pwErrorMessage").text("기존 비밀번호가 일치하지 않습니다.");
                }

            }

            function checkLoginActivate(e) {
                if (loginBtn.hasClass('nicknameOk')) {
                    loginBtn.removeClass("inactivate");
                    loginBtn.removeAttr("disabled");
                }
                else {
                    loginBtn.addClass("inactivate");
                    loginBtn.attr("disabled", "disabled");
                }
            }

            function checkNickname(e) {
                let valid = true;
                nickname.next().get()[0].className = "check";
                if (nickname.val() == "") {
                    valid = false;
                    nickname.next().get()[0].className = "default";
                } else if (nickname.val().length < 2) {
                    valid = false;
                    errorMessage.text("닉네임은 두 글자 이상이어야 합니다.");
                    nickname.next().get()[0].className = "fail";
                } else if (nickname.val() == nickname.data("originalId")) {
                    errorMessage.text("");
                    nickname.next().get()[0].className = "default";
                    valid = false;
                } else {
                    errorMessage.text("");
                }

                if (valid)
                    loginBtn.addClass("nicknameOk");
                else
                    loginBtn.removeClass("nicknameOk");
            }
            function checkPassword(e) {
                let valid = true;
                password.next().get()[0].className = "check";

                if (password.val() == "") {
                    valid = false;
                    password.next().get()[0].className = "default";
                } else if (!checkStrongPassword(password.val())) {
                    valid = false;
                    errorMessage.text("비밀번호는 영문 대소문자와 숫자, 기호를 포함한 6자 이상이어야 합니다.");
                    password.next().get()[0].className = "fail";
                } else if (passwordConfirm.val() == "") {
                    valid = false;
                    errorMessage.text("");
                    passwordConfirm.next().get()[0].className = "default";
                }
                else if (password.val() != passwordConfirm.val()) {
                    valid = false;
                    errorMessage.text("비밀번호가 일치하지 않습니다.");
                    passwordConfirm.next().get()[0].className = "fail";
                } else {
                    errorMessage.text("");

                    passwordConfirm.next().get()[0].className = "check";
                }



                if (valid)
                    loginBtn.addClass("passwordOk");
                else
                    loginBtn.removeClass("passwordOk");
            }

            function checkPasswordConfirm(password, passwordConfirm) {
                return password == passwordConfirm;
            }


            function checkStrongPassword(password) {
                // return /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&-])[A-Za-z\d@$!%*#?&-]{8,}$/.test(password);
                return password.length > 1;
            }
        </script>
    </body>

    </html>