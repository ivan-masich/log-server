define([
    'util/request'
], function(request) {
    return function() {
        var model = this.model();
        var router = this.router;

        this.showSignIn = function() {
            router('application').changePage('signIn', model);
        };

        this.signIn = function() {
            model.error(false);

            if (model.email() == "" || model.password() == "") {
                router('application').alert("Empty Field", "You need to fill email and password before clicking Sing In.");
            } else {
                request.post(
                    'sign-in',
                    {
                        email: model.email(),
                        password: model.password()
                    },
                    function(resultData) {
                        if (!resultData.status) {
                            model.error(true);
                            model.errorMessage(resultData.errorMessage);
                        } else {
                            router('user').updateInfo();
                            router('dashboard').showDashboard();
                        }
                    }
                );
            }
        }
    };
});