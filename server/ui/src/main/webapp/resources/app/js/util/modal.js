define(['jquery', 'page/globalModel'], function($, globalModel) {
    return {
        alert: function(title, content) {
            globalModel.alertModalData.title(title);
            globalModel.alertModalData.content(content);
            $('#alertModal').modal();
        }
    };
});