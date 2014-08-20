define(['util/request'], function(request) {
    return function() {
        this.updateInfo = function() {
            this.model().id(1);
            this.model().name('Ivan Masich');
            this.model().email('w3cvalid@gmail.com');

            this.router('application').changeUserInfo(true, this.model());
        };
    };
});