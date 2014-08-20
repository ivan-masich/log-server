define(['util/request'], function(request) {
    return function() {
        this.showDashboard = function() {
            this.router('application').changePage('dashboard', this.model());
        }
    };
});