define(['knockout'], function(ko) {
    return function() {
        this.email = ko.observable("");
        this.password = ko.observable("");

        this.error = ko.observable(false);
        this.errorMessage = ko.observable();

        this.formProcessing = ko.observable(false);

        this.signIn = function() {
            this.error(false);
            this.controller().signIn();
        };

        this.clear = function() {
            this.email('');
            this.password('');
            this.error(false);
            this.errorMessage('');
            this.formProcessing(false);
        };
    };
});