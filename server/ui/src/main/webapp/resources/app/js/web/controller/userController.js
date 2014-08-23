define(['util/request'], function(request) {
    return function() {
        var controller = this;

        this.updateInfo = function(callback) {
            request.get('user/signed-in/info', function(data) {
                controller.model().signedInUser.id(data.id);
                controller.model().signedInUser.name(data.name);
                controller.model().signedInUser.email(data.email);

                controller.router('application').changeUserInfo(true, controller.model().signedInUser);

                if (typeof callback != 'undefined') {
                    callback();
                }
            });
        };

        this.list = function() {
            request.get('user/all', function(data) {
                controller.model().updateUsers(data.users);
                controller.router('application').changePage('users', controller.model());
            });
        };

        this.editUser = function(id, data) {
            request.post('user/' + id + '/edit', data, function(response) {
                controller.model().processResponse(id, response);
            });
        }
    };
});