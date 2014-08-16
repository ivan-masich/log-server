define(['knockout'], function(ko) {
    return {
        pageId: ko.observable(""),
        pageData: null,
        alertModalData: {
            title: ko.observable(""),
            content: ko.observable("")
        },
        authorized: ko.observable(false)
    };
});