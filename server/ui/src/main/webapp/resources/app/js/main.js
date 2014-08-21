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

require([
    'knockout',
    'web/router',
    'util/request',
    'bootstrap'
], function (
    ko,
    router,
    request
) {
    router.init();
    ko.applyBindings(router.controller('application').model());
    request.addErrorHandler(function(jqXHR){
        if (jqXHR.status == 401) {
            router.controller('application').unloadUserInfoAndGoToSignIn();
        }
    });

    router.controller('application').loadUserInfoAndGoToHome();
});