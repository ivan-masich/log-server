define(['jquery', 'page/controllerDispatcher'], function($, controllerDispatcher) {
    return {
        signIn: function(email, password, callback) {
            $.post('sign-in', {email: email, password: password}, function(resultData) {
                if (!resultData.status) {
                    callback(resultData);
                } else {
                    controllerDispatcher.signInCompleted();
                }
            }, 'json');
        }
    };
});