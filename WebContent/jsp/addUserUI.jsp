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
        <link rel="stylesheet" href="css/addUserUI.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.1.1/core.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.1.1/sha256.js"></script>

    </head>

    <body>
        <div id="mobileContainer">
            <!--헤더-->
            <header>
                <img src="img/left arrow.svg" alt="이전 페이지 이동">
                <span>회원가입</span>
            </header>
            <!-- 컨텐츠 컨테이너 -->
            <div id="container">
                <form action="controller?cmd=login" method="post">
                    <div id="inputField">
                        <!-- <input type="date" id="hiddenDate"> -->
                        <div><input name="userId" id="userId" placeholder="이메일 입력">
                            <img class="default">
                        </div>
                        <div><input name="nickname" id="nickname" placeholder="닉네임 입력">
                            <img class="default">
                        </div>
                        <!-- <input name="birthDate" id="birthDate" placeholder="생년월일" disabled> -->
                        <div><input type="date" name="birthDate" id="birthDate"></div>

                        <div><input name="password" type="password" id="password" placeholder="비밀번호 입력"><img
                                class="default">
                        </div>
                        <div><input id="passwordConfirm" type="password" placeholder="비밀번호 확인"><img class="default">
                        </div>
                    </div>
                    <div class="inputError"></div>
                    <button type="submit" class="inactivate" disabled>회원가입</button>

                </form>

            </div>

        </div>

        </div>
        <script>
            const userId = $("#userId");
            const nickname = $("#nickname")
            const birthDate = $("#birthDate");
            const password = $("#password");
            const passwordConfirm = $("#passwordConfirm");

            const errorMessage = $("div.inputError");
            const loginBtn = $("form button");

            $("header img").on("click", () => window.history.back());

            $("#userId").on("change focus", checkUserId);
            $("#nickname").on("change focus", checkNickname);
            $("#birthDate").on("change focus", checkBirthDate);
            $("#password").on("change focus", checkPassword);
            $("#passwordConfirm").on("change focus", checkPassword);
            $("#inputField input").on("change", checkLoginActivate);
            // $("#hiddenDate").on("change", (e) => { $("#birthDate").val(e.target.value); console.log(e.target.value) });

            // $("button").on("click", addUser);
            $("form").on("submit", addUser);

            async function addUser(e) {
                e.preventDefault();
                let result = false;
                try {
                    const res = await fetch("controller?cmd=addUser&userId=" + userId.val() + "&nickname=" + nickname.val() + "&birthDate=" + birthDate.val() + "&password=" + getEncryptedPw(password.val()));
                    const data = await res.json();
                    result = data.result;
                } catch {
                    console.log("todo");
                }
                if (result)
                    location.href = "controller?cmd=mainUI";
                else
                    alert("회원가입에 실패했습니다. 같은 현상이 반복될 시 관리자에게 문의해주세요!");

            }

            function getEncryptedPw(password) {
                const hash = CryptoJS.SHA256(password);
                let encryptedPw = hash.toString(CryptoJS.enc.Hex);
                encryptedPw = password;
                return encryptedPw;
            }


            function checkLoginActivate(e) {
                if (loginBtn.hasClass('userIdOk') && loginBtn.hasClass('nicknameOk') && loginBtn.hasClass('birthDateOk') && loginBtn.hasClass('passwordOk')) {
                    loginBtn.removeClass("inactivate");
                    loginBtn.removeAttr("disabled");
                }
                else {
                    loginBtn.addClass("inactivate");
                    loginBtn.attr("disabled", "disabled");
                }
            }

            async function checkUserId(e) {
                let flag = isExistUserId(userId.val())
                let valid = true;
                userId.next().get()[0].className = "check";
                if (userId.val() == "") {
                    valid = false;
                    userId.next().get()[0].className = "default";
                } else if (!checkEmail(userId.val())) {
                    valid = false;
                    errorMessage.text("유효한 이메일을 입력해주세요.");
                    userId.next().get()[0].className = "fail";
                } else if (await flag) {
                    valid = false;
                    errorMessage.text("해당 이메일로 가입된 계정이 이미 존재합니다.");
                    userId.next().get()[0].className = "fail";
                } else {
                    errorMessage.text("");
                }

                if (valid)
                    loginBtn.addClass("userIdOk");
                else
                    loginBtn.removeClass("userIdOk");
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
                } else {
                    errorMessage.text("");
                }

                if (valid)
                    loginBtn.addClass("nicknameOk");
                else
                    loginBtn.removeClass("nicknameOk");
            }

            function checkBirthDate(e) {
                let valid = true;
                if (birthDate.val() == "") {
                    valid = false;
                } else if (new Date(birthDate.val()) > new Date()) {
                    valid = false;
                    errorMessage.text("UN 총회 결의 제217호에 따라 시간여행자는 회원가입이 불가능합니다.");
                } else {
                    errorMessage.text("");
                }
                if (valid)
                    loginBtn.addClass("birthDateOk");
                else
                    loginBtn.removeClass("birthDateOk");
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

            async function isExistUserId(userId) {
                let result = false;
                try {
                    const res = await fetch("controller?cmd=isExistEmail&userId=" + userId);
                    const data = await res.json();
                    result = data.result;
                } catch {
                    console.log("아이디 검증 실패");
                }
                return result;
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