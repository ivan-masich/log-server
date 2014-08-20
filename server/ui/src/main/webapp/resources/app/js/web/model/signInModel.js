define(['knockout'], function(ko) {
    return function() {
        this.email = ko.observable("");
        this.password = ko.observable("");

        this.error = ko.observable(false);
        this.errorMessage = ko.observable();

        this.signIn = function() {
            this.error(false);
            this.controller().signIn();
        }
    };
});