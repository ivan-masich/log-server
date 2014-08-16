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

require(['page/controllerDispatcher', 'bootstrap'], function (controllerDispatcher) {
    controllerDispatcher.init();
});