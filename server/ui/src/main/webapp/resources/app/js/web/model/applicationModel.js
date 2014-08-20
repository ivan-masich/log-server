define(['knockout'], function(ko) {
    return function() {
        this.page = {
            id: ko.observable(""),
            data: null
        };

        this.modal = {
            alert: {
                title: ko.observable(""),
                content: ko.observable("")
            }
        };

        this.user = {
            authorized: ko.observable(false),
            data: null
        };

        this.navigation = {
            signOut: function() {
                this.controller().signOut();
            },
            dashboard: function() {
                this.controller().showDashboard();
            }
        };
    };
});