requirejs.config({
    paths: {
        baseUrl: './',
        jquery: '../../assets/jquery/2.1.1/jquery',
        bootstrap: '../../assets/bootstrap/3.2.0/js/bootstrap',
        knockout: '../../assets/knockout/3.1.0/knockout',
        'knockout.mapping': '../../assets/knockout/plugins/mapping/2.4.1/mapping'
    },
    shim: {
        bootstrap: {
            deps: ['jquery']
        },
        'knockout.mapping': {
            deps: ['knockout']
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
        } else {
            router.controller('application').alert('Request Error',
                    'Url: ' + this.url + '<br />' +
                    'Method: ' + this.type + '<br />' +
                    'Status: ' + jqXHR.status + '<br />' +
                    'Status Text: ' + jqXHR.statusText);
        }
    });

    router.controller('application').loadUserInfoAndGoToHome();
});