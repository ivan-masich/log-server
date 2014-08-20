define(['knockout'], function(ko) {
    return function() {
        this.id = ko.observable("");
        this.name = ko.observable("");
        this.email = ko.observable("");
    };
});