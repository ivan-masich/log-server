requirejs.config({
    paths: {
        baseUrl: './',
        jquery: '../../assets/jquery/2.1.1/jquery',
        bootstrap: '../../assets/bootstrap/3.2.0/js/bootstrap',
        knockout: '../../assets/knockout/3.1.0/knockout'
    },
    shim: {
        bootstrap: {
            deps: ['jquery']
        }
    }
});

require(['knockout', 'web/router', 'bootstrap'], function (ko, router) {
    router.init();
    ko.applyBindings(router.controller('application').model());

    router.controller('signIn').showSignIn();
});