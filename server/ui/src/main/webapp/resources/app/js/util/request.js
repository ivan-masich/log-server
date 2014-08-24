define(['jquery'], function($) {
    var errorHandlers = [];

    function request(method, url, data, successHandler, errorHandler) {
        var options = {
            cache: false,
            dataType: 'json',
            type: method,
            error: errorHandlers
        };

        if (data != null) {
            options.data = data;
        }

        if (successHandler != null) {
            options.success = successHandler;
        }

        if (errorHandler != null) {
            options.error = options.error.concat(errorHandler);
        }

        $.ajax(url, options);
    }

    function detectRequestArgumentsAndExecute(method, url, data, successHandler, errorHandler) {
        if (typeof data == 'undefined') {
            data = null;
            successHandler = null;
            errorHandler = null;
        }

        if (typeof data == 'function') {
            if (typeof successHandler == 'function') {
                errorHandler = successHandler;
                successHandler = data;
            } else {
                successHandler = data;
                errorHandler = null;
            }
        }

        request(method, url, data, successHandler, errorHandler)
    }

    return {
        post: function(url, data, successHandler, errorHandler) {
            detectRequestArgumentsAndExecute('POST', url, data, successHandler, errorHandler);
        },
        get: function(url, data, successHandler, errorHandler) {
            detectRequestArgumentsAndExecute('GET', url, data, successHandler, errorHandler);
        },
        put: function(url, data, successHandler, errorHandler) {
            detectRequestArgumentsAndExecute('PUT', url, data, successHandler, errorHandler);
        },
        patch: function(url, data, successHandler, errorHandler) {
            detectRequestArgumentsAndExecute('PATCH', url, data, successHandler, errorHandler);
        },
        delete: function(url, data, successHandler, errorHandler) {
            detectRequestArgumentsAndExecute('DELETE', url, data, successHandler, errorHandler);
        },
        addErrorHandler: function(handler) {
            errorHandlers.push(handler);
        }
    };
});