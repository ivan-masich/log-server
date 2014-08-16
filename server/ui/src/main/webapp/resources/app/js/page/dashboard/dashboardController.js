define(['jquery', 'util/request'], function($, request) {
    return {
        controllerDispatcher: null,
        dashboard: function() {
            var controller = this;
            request.post('dashboard', {}, function(data) {
                controller.controllerDispatcher.showDashboard();
            }, function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status == 401) {
                    controller.controllerDispatcher.showSignIn();
                }
            });
        }
    };
});