define([
    'jquery',
    'knockout',
    'page/signIn/signInController',
    'page/signIn/signInModel',
    'page/dashboard/dashboardController',
    'page/globalModel',
    'util/modal'
], function($, ko, signInController, signInModel, dashboardController, globalModel, modal) {
    var dispatcher = {
        init: function () {
            ko.applyBindings(globalModel);
            dashboardController.dashboard();
        },
        showSignIn: function () {
            globalModel.pageData = new signInModel(signInController);
            globalModel.pageId("signIn");
        },
        signInCompleted: function () {
            this.showDashboard();
        },
        showDashboard: function () {
            globalModel.authorized(true);
            globalModel.pageId("dashboard");
            globalModel.pageData = null;
        }
    };
    dashboardController.controllerDispatcher = dispatcher;
    signInController.controllerDispatcher = dispatcher;

    return  dispatcher;
});