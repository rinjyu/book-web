var login = {
    process: function() {

        if($("#memId").val() === "") {
            alert("아이디를 입력해주세요.");
            $("#memId").focus();
            return false;
        }

        if($("#memPwd").val() === "") {
            alert("비밀번호를 입력해주세요.");
            $("#memPwd").focus();
            return false;
        }

        $.ajax({
            jsonp: false
            , url: "/login/check"
            , type: "POST"
            , data: JSON.stringify($("#frmLogin").serializeObject())
            , dataType: "json"
            , contentType: "application/json; charset=utf-8"
            , cache: false
            , success: function (data) {
                try {
                    if (data.success.code === "SELECT") {
                        if ($(this).is(":checked")) {
                            cookie.setCookie("rememberMe", $("#memId").val(), "31536000");
                        } else {
                            cookie.deleteCookie("rememberMe");
                        }
                        $("#frmLogin").submit();
                    }
                } catch (e) {
                    alert("처리 중 오류가 발생했습니다.");
                    $("#frmLogin")[0].reset();
                    return false;
                }
            }, error: function (data) {
                let errorData = data.responseJSON;
                let errorMessage = "처리 중 오류가 발생했습니다.";
                try {
                    if (errorData !== "" && errorData.error.code === "NOT_FOUND") {
                        errorMessage = "로그인 정보가 없습니다.";
                    }
                } catch (e) {

                }
                alert(errorMessage);
                $("#frmLogin")[0].reset();
                return false;
            }
        });
    }
};