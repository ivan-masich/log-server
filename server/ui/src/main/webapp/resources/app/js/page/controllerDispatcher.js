define([
    'knockout',
    'page/signIn/signInController',
    'page/signIn/signInModel',
    'page/globalModel'
], function(ko, signInController, signInModel, globalModel) {
    return {
        init: function() {
            globalModel.pageId("signIn");
            globalModel.pageData = new signInModel(signInController);

            ko.applyBindings(globalModel);
        },
        signInCompleted: function() {
            globalModel.pageId("dashboard");
            globalModel.pageData = null;
        }
    };
});