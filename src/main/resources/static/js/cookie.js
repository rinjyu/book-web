var cookie = {
    setCookie: function (cookieName, value, expireDays) {
        const expireDate = new Date();
        expireDate.setDate(expireDate.getDate() + expireDays);
        var cookieValue = escape(value) + ((expireDays == null) ? "" : ";expires=" + expireDate.toGMTString());
        document.cookie = cookieName + "=" + cookieValue;

    }, deleteCookie: function (cookieName) {
        var expireDate = new Date();
        expireDate.setDate(expireDate.getDate() - 1);
        document.cookie = cookieName + "=" + "; expires=" + expireDate.toGMTString();

    }, getCookie: function (cookieName) {
        cookieName += "=";
        var cookieData = document.cookie;
        var start = cookieData.indexOf(cookieName);
        var cookieValue = "";
        if (start != -1) {
            start += cookieName.length;
            var end = cookieData.indexOf(";", start);
            if (end == -1) end = cookieData.length;
            cookieValue = cookieData.substring(start, end);
        }
        return unescape(cookieValue);
    }
};
