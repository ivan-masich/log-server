define(['util/request', 'knockout'], function(request, ko) {
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
            },
            logMessages: function() {
                var data = {
                    messages: ko.observableArray([])
                };
                controller.changePage('logMessages', data);
                setInterval(function(){
                    data.messages.push({
                        timestamp: '15.12.2014 15:30:59',
                        thread: 'Thread1.12$13',
                        level: 'DEBUG',
                        logger: 'n.m.l.s.u.d.l.e.LogMessageEntity',
                        message: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.'
                    });
                }, 1000);
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