$(function () {
    let books = {
        init: function() {
            $.ajax({
                jsonp: false
                , url: "/book/list"
                , type: "GET"
                , dataType: "json"
                , contentType: "application/json; charset=utf-8"
                , cache: false
                , success: function (data) {
                    $(".book-item").remove();
                    $("#books .container").find(".row").html(books.generate.list(data.data));
                    $("[id^='booksModal']").remove();
                    $(".scroll-to-top").after(books.generate.modal(data.data));
                }, error: function (data, textStatus) {
                    alert("처리 중 오류가 발생했습니다.");
                    return false;
                }
            });
        }, generate: {
            list: function(data) {
                let html = "";
                $(data).each(function(index, item) {
                    html += "<div class=\"col-md-6 col-lg-4\">";
                    html += "    <div class=\"books-item mx-auto\" data-toggle=\"modal\" data-target=\"#booksModal"+item.bookNo+"\">";
                    html += "        <div class=\"books-item-caption d-flex align-items-center justify-content-center h-100 w-100\">";
                    html += "            <div class=\"books-item-caption-content text-center text-white\"><i class=\"fas fa-plus fa-3x\"></i></div>";
                    html += "        </div>";
                    html += "        <img class=\"img-fluid\" src=\""+((item.bookImgPath != null && item.bookImgPath != "") ? item.bookImgPath : '/image/books/safe.png' )+"\" alt=\"\"/>";
                    html += "    </div>";
                    html += "</div>";
                });

                return html;
            }, modal: function(data) {
                let html = "";
                $(data).each(function(index, item) {
                    html += "<div class=\"books-modal modal fade\" id=\"booksModal"+item.bookNo+"\" tabIndex=\"-1\" role=\"dialog\" aria-labelledby=\"booksModal"+item.bookNo+"Label\" aria-hidden=\"true\">";
                    html += "    <div class=\"modal-dialog modal-xl\" role=\"document\">";
                    html += "        <div class=\"modal-content\">";
                    html += "            <button class=\"close\" type=\"button\" data-dismiss=\"modal\" aria-label=\"Close\">";
                    html += "                <span aria-hidden=\"true\"><i class=\"fas fa-times\"></i></span>";
                    html += "            </button>";
                    html += "            <div class=\"modal-body text-center\">";
                    html += "                <div class=\"container\">";
                    html += "                    <div class=\"row justify-content-center\">";
                    html += "                        <div class=\"col-lg-8\">";
                    html += "                            <h2 class=\"books-modal-title text-secondary text-uppercase mb-0\" id=\"booksModal"+item.bookNo+"Label\">"+item.bookNm+"</h2>";
                    html += "                            <h3 class=\"books-modal-title text-secondary text-uppercase mb-0\">"+item.bookDesc+"</h3>";
                    html += "                            <div class=\"divider-custom\">";
                    html += "                                <div class=\"divider-custom-line\"></div>";
                    html += "                                <div class=\"divider-custom-icon\"><i class=\"fas fa-star\"></i></div>";
                    html += "                                <div class=\"divider-custom-line\"></div>";
                    html += "                            </div>";
                    html += "                            <img class=\"img-fluid rounded mb-5\" src=\""+((item.bookImgPath != null && item.bookImgPath != "") ? item.bookImgPath : '/image/books/safe.png' )+"\" alt=\"\"/>";
                    html += "                            <p class=\"mb-3\">"+item.bookPublisher+"</p>";
                    html += "                            <p class=\"mb-3\">"+item.bookAuthor+"</p>";
                    html += "                            <p class=\"mb-3\">"+setComma(item.bookPrice)+"원</p>";
                    html += "                           <button class=\"btn btn-primary\" data-dismiss=\"modal\">";
                    html += "                               <i class=\"fas fa-times fa-fw\"></i>";
                    html += "                               Close Window";
                    html += "                           </button>";
                    html += "                       </div>";
                    html += "                   </div>";
                    html += "               </div>";
                    html += "           </div>";
                    html += "       </div>";
                    html += "   </div>";
                    html += "</div>";
                });

                return html;
            }, message : function(type, message) {
                $("#success").html("<div class=\"alert alert-"+type+"\">");
                $("#success > .alert-"+type).html("<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;").append("</button>");
                $("#success > .alert-"+type).append("<strong>"+message+"</strong>");
                $("#success > .alert-"+type).append("</div>");
                $("#frmBookStorage").trigger("reset");
            }
        }
    };

    $("#frmBookStorage input,#frmBookStorage textarea,#frmBookStorage button").jqBootstrapValidation({
        preventSubmit: true
        , submitError: function ($form, event, errors) {

        }, submitSuccess: function ($form, event) {
            event.preventDefault();
            let $this = $("#btnBookStorage");
            $this.prop("disabled", true);

            $.ajax({
                jsonp: false
                , url: "/book/storage"
                , type: "POST"
                , data: JSON.stringify($("#frmBookStorage").serializeObject())
                , dataType: "json"
                , contentType: "application/json; charset=utf-8"
                , cache: false
                , success: function (data) {
                    books.generate.message("success", "정상 등록되었습니다.");
                    books.init();
                }, error: function (data, textStatus) {
                    let errorData = data.responseJSON;
                    let errorMessage = "등록 중 오류 발생가 발생했습니다.";
                    try {
                        if (errorData != "" && errorData.error.code == "DUPLICATE_DATA") {
                            errorMessage = "이미 등록되어 있습니다.";
                        }
                    } catch (e) {

                    }
                    books.generate.message("danger", errorMessage);
                }, complete: function () {
                    setTimeout(function () {
                        $this.prop("disabled", false);
                    }, 1000);
                }
            });
        }, filter: function () {
            return $(this).is(":visible");
        },
    });


    $("a[data-toggle='tab']").on("click", function (e) {
        e.preventDefault();
        $(this).tab("show");
    });

    $("#bookNm").on("focus", function () {
        $("#success").html("");
    });
});

var setComma = function(txt) {
    var reg = /(^[+-]?\d+)(\d{3})/;
    var number = txt + "";

    while (reg.test(number)) {
        number = number.replace(reg, "$1" + "," + "$2");
    }

    return number;
};