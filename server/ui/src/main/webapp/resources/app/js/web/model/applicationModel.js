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
                this.controller().navigation.signOut();
            },
            dashboard: function() {
                this.controller().navigation.dashboard();
            },
            users: function() {
                this.controller().navigation.users();
            }
        };
    };
});