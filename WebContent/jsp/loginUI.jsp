<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="ko">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>loginUI</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <link rel="stylesheet" href="css/loginUI.css">

        <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.1.1/core.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.1.1/sha256.js"></script>

    </head>

    <body>
        <div id="container">
            <img id="logo" src="img/logo.svg" style="width:21.6rem;height:21.6rem;">
            <form id="loginForm" action="controller?cmd=login" method="post">
                <div id="inputField">
                    <input name="prevURL" id="prevURL" type="hidden" />
                    <input name="userId" id="userId" placeholder="이메일 입력">
                    <input name="password" type="password" id="password" placeholder="비밀번호 입력">
                </div>
                <div class="inputError"></div>
                <button class="inactivate" disabled>로그인</button>

            </form>
            <div id="links">
                <a href="controller?cmd=findPasswordUI">비밀번호 찾기</a>
                <a href="controller?cmd=addUserUI">아직 계정이 없으신가요?</a>
            </div>

            <div id="socialLoginBox">
                <img src="img/naver_btn.png"></a>
                <img src="img/google_btn.svg"></a>
                <img src="img/kakao_btn.svg"></a>
            </div>

        </div>
        <script>
            const prevURL = sessionStorage.getItem("prevURL");
            console.log(prevURL);
            $("#prevURL").val(prevURL);

            const userId = $("#userId");
            const password = $("#password");
            const errorMessage = $("div.inputError");
            const loginBtn = $("form button");

            $("#inputField input").on("keyup", checkInput);

            function checkInput(e) {
                loginBtn.addClass("inactivate");
                loginBtn.attr("disabled", true);
                if (userId.val() == "" && password.val() == "")
                    errorMessage.text("");
                else if (!checkEmail(userId.val())) {
                    errorMessage.text("유효한 이메일을 입력해주세요.")
                    errorMessage.css("text-align", "center");
                }
                else if (!checkPassword(password.val())) {
                    // errorMessage.text("비밀번호는 영문 대소문자와 숫자, 기호를 포함한 6자 이상이어야 합니다.");
                    errorMessage.html("비밀번호는 영문 대소문자와 숫자,<br/>기호를 포함한 6자 이상이어야 합니다.");
                    errorMessage.css("text-align", "center");
                }
                else {
                    errorMessage.text("");
                    loginBtn.removeClass("inactivate");
                    loginBtn.attr("disabled", false);
                }
            }
            function checkEmail(email) {
                const pattern = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-za-z0-9\-]+/;
                if (pattern.test(email) === false) { return false; }
                else { return true; }
            }

            function checkPassword(password) {
                return /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&-])[A-Za-z\d@$!%*#?&-]{8,}$/.test(password);
                // return password.length > 0;
            }

            document.getElementById('loginForm').addEventListener("submit", function () {
                event.preventDefault();

                const userId = document.getElementById("userId").value;
                const password = document.getElementById("password").value;

                $.ajax({
                    url: "controller?cmd=login",
                    data: { userId: userId, password: getEncryptedPw(password), prevURL: prevURL },
                    dataType: "json",
                    success: function (result) {
                        if (result.result === true) {
                            console.log("result SUCCESS");
                            if (sessionStorage.getItem("prevURL") != null) {
                                window.location.href = sessionStorage.getItem("prevURL");
                            } else {
                                window.location.href = "controller?cmd=mainUI";
                            }
                            return;
                        } else {
                            errorMessage.html("아이디 또는 비밀번호를 잘못 입력했습니다. <br/>입력하신 내용을 다시 확인해주세요.");
                            errorMessage.css("text-align", "center");
                        }
                    }
                });
            });

            function getEncryptedPw(password) {
                const hash = CryptoJS.SHA256(password);
                let encryptedPw = hash.toString(CryptoJS.enc.Hex);
                return encryptedPw;
            }

        </script>
    </body>

    </html>