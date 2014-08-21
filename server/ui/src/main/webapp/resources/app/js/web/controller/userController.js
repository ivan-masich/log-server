define(['util/request'], function(request) {
    return function() {
        var controller = this;

        this.updateInfo = function(callback) {
            request.get('user/info', function(data) {
                controller.model().id(data.id);
                controller.model().name(data.name);
                controller.model().email(data.email);

                controller.router('application').changeUserInfo(true, controller.model());

                if (typeof callback != 'undefined') {
                    callback();
                }
            });
        };
    };
});