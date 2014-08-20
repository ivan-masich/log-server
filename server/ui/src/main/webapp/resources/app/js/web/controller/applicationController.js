define(['util/request'], function(request) {
    return function() {
        var controller = this;

        this.signOut = function() {
            request.get('sign-out', function() {
                this.router('signIn').showSignIn();
            });
        };

        this.dashboard = function() {
            this.router('dashboard').showDashboard();
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