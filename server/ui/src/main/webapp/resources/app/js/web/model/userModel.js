define(['jquery', 'knockout', 'knockout.mapping'], function($, ko, mapping) {
    return function() {
        var model = this;

        this.signedInUser = {
            id: ko.observable(""),
            name: ko.observable(""),
            email: ko.observable("")
        };

        this.users = {};
        this.usersIds = ko.observableArray([]);

        this.editForm = {
            id: {
                value: ko.observable(""),
                error: ko.observable(false),
                errorMessage: ko.observable("")
            },
            name: {
                value: ko.observable(""),
                error: ko.observable(false),
                errorMessage: ko.observable("")
            },
            email: {
                value: ko.observable(""),
                error: ko.observable(false),
                errorMessage: ko.observable("")
            },
            password:  {
                value: ko.observable(""),
                error: ko.observable(false),
                errorMessage: ko.observable("")
            },
            show: function(data) {
                var form = model.editForm;

                form.clearErrors();
                form.id.value(data.id());
                form.name.value(data.name());
                form.email.value(data.email());
                form.password.value("");

                $('#editUserModal').modal();
            },
            save: function(id) {
                model.controller().editUser(id, {
                    id: id,
                    name: model.editForm.name.value(),
                    email: model.editForm.email.value(),
                    password: model.editForm.password.value()
                });
            },
            clearErrors: function() {
                $.each(model.editForm, function(key, field) {
                    if (typeof field != 'function') {
                        field.error(false);
                    }
                });
            },
            processResponse: function(id, response) {
                var form = model.editForm;
                form.clearErrors();

                if (response.hasErrors) {
                    $.each(response.errors, function(key, error) {
                        form[error.fieldName].error(true);
                        form[error.fieldName].errorMessage(error.message);
                    });
                } else {
                    model.updateUser(id, response.data);
                    $('#editUserModal').modal('hide');
                }
            }
        };

        this.getUser = function(id) {
            return this.users[id];
        };

        this.updateUser = function(id, data) {
            mapping.fromJS(data, this.users[id]);
        };
        this.updateUsers = function(users) {
            model.usersIds.removeAll();
            model.users = {};
            $.each(users, function(key, data) {
                model.users[data.id] = mapping.fromJS(data);
                model.usersIds.push(data.id);
            });
        };

        this.editUser = function(id) {
            model.editForm.show(model.users[id]);
        };

        this.processResponse = function(id, response) {
            model.editForm.processResponse(id, response);
        }
    };
});