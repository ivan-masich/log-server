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

        this.deleteConfirmationId = ko.observable("")

        this.userForm = {
            editMode: ko.observable(false),
            id: ko.observable(""),
            fields: {
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
                }
            },
            showAdd: function() {
                model.userForm.clear();
                $('#userFormModal').modal();
            },
            showEdit: function(data) {
                var form = model.userForm;
                form.clear();

                form.editMode(true);
                form.id(data.id());
                form.fields.name.value(data.name());
                form.fields.email.value(data.email());
                form.fields.password.value("");

                $('#userFormModal').modal();
            },
            clear: function() {
                model.userForm.editMode(false);
                model.userForm.id("");

                $.each(model.userForm.fields, function(key, field) {
                    field.value("");
                    field.error(false);
                    field.errorMessage("");
                });
            },
            clearErrors: function() {
                $.each(model.userForm.fields, function(key, field) {
                    field.error(false);
                });
            },
            processResponse: function(response) {
                var form = model.userForm;
                form.clearErrors();

                if (response.hasErrors) {
                    $.each(response.errors, function(key, error) {
                        form.fields[error.fieldName].error(true);
                        form.fields[error.fieldName].errorMessage(error.message);
                    });
                } else {
                    if (form.editMode()) {
                        model.updateUser(response.data);
                    } else {
                        model.addNewUser(response.data);
                    }

                    $('#userFormModal').modal('hide');
                }
            }
        };

        this.getUser = function(id) {
            return this.users[id];
        };

        this.addNewUser = function(data) {
            model.users[data.id] = mapping.fromJS(data);
            model.usersIds.push(data.id);
        };
        this.updateUser = function(data) {
            mapping.fromJS(data, this.users[data.id]);
        };
        this.updateUsers = function(users) {
            model.usersIds.removeAll();
            model.users = {};
            $.each(users, function(key, data) {
                model.users[data.id] = mapping.fromJS(data);
                model.usersIds.push(data.id);
            });
        };

        this.processResponse = function(response) {
            model.userForm.processResponse(response);
        };

        this.createUserActionHandler = function() {
            model.userForm.showAdd();
        };

        this.editUserActionHandler = function(id) {
            model.userForm.showEdit(model.users[id]);
        };

        this.deleteUserActionHandler = function(id) {
            model.deleteConfirmationId(id);
            $('#deleteUserConfirmationModal').modal();
        };

        this.deleteUserConfirmActionHandler = function(id) {
            model.controller().deleteUser(id);
            $('#deleteUserConfirmationModal').modal('hide');
        };

        this.processDeleteResponse = function(response) {
            if (response.status) {
                model.usersIds.remove(response.id);
                delete model.users[response.id];
            }
        };

        this.changeStatusActionHandler = function(id) {
            if (model.users[id].active()) {
                model.controller().deactivateUser(id);
            } else {
                model.controller().activateUser(id);
            }
        };

        this.processChangingStatusResponse = function(response) {
            if (response.status) {
                model.users[response.data.id].active(response.data.active);
            }
        };

        this.saveUserActionHandler = function(id) {
            var data = {
                name: model.userForm.fields.name.value(),
                email: model.userForm.fields.email.value(),
                password: model.userForm.fields.password.value()
            };

            if (model.userForm.editMode()) {
                data.id = id;
                model.controller().editUser(id, data);
            } else {
                model.controller().createUser(data);
            }
        };
    };
});