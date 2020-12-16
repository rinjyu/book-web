$(function () {
    $.fn.serializeObject = function() {
        let object = null;
        try {
            if (this[0].tagName && this[0].tagName.toUpperCase() === "FORM") {
                const array = this.serializeArray();
                if(array){ object = {};
                    $.each(array, function() {
                        object[this.name] = this.value; });
                }
            }
        } catch(e) {

        } finally {}
        return object;
    }
});