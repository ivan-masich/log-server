requirejs.config({
    paths: {
        jquery: "../../assets/jquery/2.1.1/jquery",
        bootstrap: "../../assets/bootstrap/3.2.0/js/bootstrap"
    },
    shim: {
        bootstrap: {
            deps: ["jquery"]
        }
    }
});

require(["jquery", "bootstrap"], function ($) {
    //TBD
});