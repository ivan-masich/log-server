define(['util/request'], function(request) {
    return function() {
        var controller = this;

        this.navigation = {
            signOut: function() {
                request.get('sign-out', function() {
                    controller.unloadUserInfoAndGoToSignIn();
                });
            },
            dashboard: function() {
                controller.router('dashboard').showDashboard();
            },
            users: function() {
                controller.router('user').list();
            }
        };

        this.loadUserInfoAndGoToHome = function() {
            controller.router('user').updateInfo(function() {
                controller.navigation.dashboard();
            });
        };

        this.unloadUserInfoAndGoToSignIn = function() {
            controller.changeUserInfo(false, null);
            controller.router('signIn').showSignIn();
        };

        this.changePage = function(id, model) {
            this.model().page.data = model;
            this.model().page.id(id);
        };

        this.changeUserInfo = function(authorized, data) {
            this.model().user.data = data;
            this.model().user.authorized(authorized);
        };

        this.alert = function(title, content) {
            this.model().modal.alert.title(title);
            this.model().modal.alert.content(content);

            $('#alertModal').modal();
        }
    };
});