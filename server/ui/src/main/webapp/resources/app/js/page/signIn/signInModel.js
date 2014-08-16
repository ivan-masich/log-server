define(['knockout', 'page/globalActions'], function(ko, globalActions) {
    return function signInModel(controller) {
        this.email = ko.observable("");
        this.password = ko.observable("");

        this.error = ko.observable(false);
        this.errorMessage = ko.observable();

        this.signIn = function() {
            this.error(false);

            if (this.email() == "" || this.password() == "") {
                globalActions.alert("Empty Field", "You need to fill email and password before clicking Sing In.");
            } else {
                var model = this;
                controller.signIn(this.email(), this.password(), function (actionResult) {
                    if (!actionResult.status) {
                        model.error(true);
                        model.errorMessage(actionResult.errorMessage);
                    }
                });
            }
        }
    };
});