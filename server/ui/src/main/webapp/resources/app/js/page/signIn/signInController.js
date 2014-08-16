define(['jquery', 'util/request'], function($, request) {
    return {
        controllerDispatcher: null,
        signIn: function(email, password, callback) {
            var controller = this;
            request.post('sign-in', {email: email, password: password}, function(resultData) {
                if (!resultData.status) {
                    callback(resultData);
                } else {
                    controller.controllerDispatcher.signInCompleted();
                }
            });
        }
    };
});