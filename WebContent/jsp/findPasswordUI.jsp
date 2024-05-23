<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="ko">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>비밀번호 찾기</title>
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
        <link rel="stylesheet" href="css/findPasswordUI.css">

    </head>

    <body>
        <div id="mobileContainer">
            <!--헤더-->
            <header>
                <img src="img/left arrow.svg" alt="이전 페이지 이동">
                <span>비밀번호 찾기</span>
            </header>
            <!-- 컨텐츠 컨테이너 -->
            <div id="container">
                <form action="" method="post">
                    <div id="inputField">
                        <!-- <input type="date" id="hiddenDate"> -->
                        <div><input name="userId" id="userId" placeholder="이메일 입력">
                            <img class="default">
                        </div>
                        <div><input name="validationNumber" id="vNumber" placeholder="인증번호 입력">
                            <!-- todo: 5분 타이머 만들기 -->
                            <button type="button" id="vNumberBtn">발송</button>
                        </div>
                        <div><input name="password" type="password" id="password" placeholder="새 비밀번호 입력"><img
                                class="default">
                        </div>
                        <div><input id="passwordConfirm" type="password" placeholder="새 비밀번호 확인"><img class="default">
                        </div>
                    </div>
                    <div class="inputError"></div>
                    <button type="submit" id="submitBtn" class="inactivate" disabled>비밀번호 재설정</button>

                </form>

            </div>

        </div>

        </div>
        <script>
            const userId = $("#userId");
            const password = $("#password");
            const passwordConfirm = $("#passwordConfirm");
            const vNumber = $("#vNumber");

            const errorMessage = $("div.inputError");
            const loginBtn = $("#submitBtn");

            $("header img").on("click", () => window.history.back());

            $("#userId").on("change focus", checkUserId);
            $("#password").on("change focus", checkPassword);
            $("#passwordConfirm").on("change focus", checkPassword);
            $("#inputField input").on("change", checkLoginActivate);
            $("form").on("submit", resetPassword);
            $("#vNumberBtn").on("click", sendVNumber);

            async function sendVNumber() {
                let result = false;
                try {
                    console.log(userId.val());
                    const res = await fetch("controller?cmd=sendValidationNumber&email=" + userId.val());
                    const data = await res.json();
                    result = data.result;

                } catch {
                    console.log("sendVNumber 오류");
                }
                if (result) {
                    console.log("인증번호가 발송되었습니다. :sendVNumber");
                } else {
                    alert("해당 이메일로 등록된 계정이 존재하지 않습니다. 이메일을 다시 한 번 확인하시고 문제가 지속될 시 고객센터로 문의하시기 바랍니다.:sendVNumber")
                }
            }

            async function resetPassword(e) {
                e.preventDefault();
                let result = false;
                try {
                    const res = await fetch("controller?cmd=resetPassword&password=" + password.val() + "&validationNumber=" + vNumber.val());
                    const data = res.json();
                    result = data.result;
                } catch {
                    console.log("todo");
                }
                if (result) {
                    alert("비밀번호가 재설정되었습니다.");
                    location.href = "controller?cmd=loginUI";
                } else
                    alert("인증번호를 다시 확인해주세요!");

            }


            function checkLoginActivate(e) {
                if (loginBtn.hasClass('userIdOk') && loginBtn.hasClass('passwordOk')) {
                    loginBtn.removeClass("inactivate");
                    loginBtn.removeAttr("disabled");
                }
                else {
                    loginBtn.addClass("inactivate");
                    loginBtn.attr("disabled", "disabled");
                }
            }

            async function checkUserId(e) {
                let valid = true;
                userId.next().get()[0].className = "check";
                if (userId.val() == "") {
                    valid = false;
                    userId.next().get()[0].className = "default";
                } else if (!checkEmail(userId.val())) {
                    valid = false;
                    errorMessage.text("유효한 이메일을 입력해주세요.");
                    userId.next().get()[0].className = "fail";
                } else {
                    errorMessage.text("");
                }

                if (valid)
                    loginBtn.addClass("userIdOk");
                else
                    loginBtn.removeClass("userIdOk");
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

            function checkEmail(email) {
                const pattern = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-za-z0-9\-]+/;
                return pattern.test(email);
            }

            function checkStrongPassword(password) {
                // return /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&-])[A-Za-z\d@$!%*#?&-]{8,}$/.test(password);
                return password.length > 0;
            }
        </script>
    </body>

    </html>