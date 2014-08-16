define(['jquery'], function($) {
    return {
        post: function(url, data, successHandler, errorHandler) {
            $.ajax(url, {
                cache: false,
                data: data,
                dataType: 'json',
                error: function (jqXHR, textStatus, errorThrown) {
                    if (typeof errorHandler != 'undefined') {
                        errorHandler(jqXHR, textStatus, errorThrown);
                    }
                },
                success: function(data, textStatus, jqXHR) {
                    if (typeof successHandler != 'undefined') {
                        successHandler(data, textStatus, jqXHR);
                    }
                },
                type: 'POST'
            });
        }
    };
});