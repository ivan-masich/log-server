<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Log Server</title>

    <%--<link rel="stylesheet" type="text/css" href="resources/app/css/main.css" />--%>
    <link rel="stylesheet/less" type="text/css" href="resources/app/less/main.less" />
    <script type="text/javascript" src="resources/assets/less/1.7.4/less.js"></script>
    <script type="text/javascript" src="resources/assets/require-js/2.1.14/require.js" data-main="resources/app/js/main.js"></script>
</head>
<body>
    <div class="container" data-bind="template: 'contentTmpl'"></div>
    <script id="contentTmpl" type="text/html">
        <div class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Log Server</a>
                </div>
                <!-- ko if: user.authorized() -->
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="#" data-bind="click: navigation.dashboard"><i class="fa fa-tachometer"></i> Dashboard</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-file-text"></i> Messages <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="#"><i class="fa fa-download"></i> Inbox <span class="badge">14</span></a></li>
                                <li><a href="#"><i class="fa fa-upload"></i> Outbox</a></li>
                                <li class="divider"></li>
                                <li><a href="#"><i class="fa fa-bookmark"></i> Bookmarks</a></li>
                            </ul>
                        </li>
                        <li><a href="#"><i class="fa fa-desktop"></i> Applications</a></li>
                        <li><a href="#" data-bind="click: navigation.users"><i class="fa fa-users"></i> Users</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="fa fa-user"></i>
                                <span data-bind="text: user.data.name"></span>
                                <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="#"><i class="fa fa-cogs"></i> Settings</a></li>
                                <li><a href="#" data-bind="click: navigation.signOut"><i class="fa fa-sign-out"></i> Sign Out</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <!-- /ko -->
            </div>
        </div>

        <!-- ko if: page.id() == 'signIn' -->
        <!-- ko with: page.data -->
        <div class="container sign-in-container">
            <div class="row">
                <div class="col-xs-6 col-xs-offset-3">
                    <div class="panel panel-default">
                        <div class="panel-heading"><i class="fa fa-user"></i> Sign In</div>
                        <div class="panel-body">
                            <form method="post" action="" data-bond="submit: signIn">
                                <div data-bind="css: { 'has-error': error() }" class="form-group">
                                    <label for="inputEmail">Email</label>
                                    <input type="email" class="form-control" id="inputEmail" placeholder="Enter email"
                                           data-bind="value: email" />
                                </div>
                                <div data-bind="attr: { class: 'form-group' + (error() ? ' has-error' : '') }">
                                    <label for="inputPassword">Password</label>
                                    <input type="password" class="form-control" id="inputPassword" placeholder="Enter password"
                                           data-bind="value: password">
                                </div>
                                <!-- ko if: error() -->
                                <div class="alert alert-danger" role="alert" data-bind="text: errorMessage"></div>
                                <!-- /ko -->
                                <div class="pull-right">
                                    <button type="submit"
                                            class="btn btn-success"
                                            data-bind="click: signIn,
                                                       attr: { disabled: formProcessing() }">
                                        <i class="fa" data-bind="css: { 'fa-sign-in': !formProcessing(),
                                                                        'fa-spinner fa-spin': formProcessing()}"></i>
                                        Sign In
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /ko -->
        <!-- /ko -->

        <!-- ko if: page.id() == 'dashboard' -->
        <div class="container dashboard">
            <nav class="navbar navbar-default" role="navigation">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <span class="navbar-brand">Dashboard</span>
                    </div>
                </div>
            </nav>
        </div>
        <!-- /ko -->

        <!-- ko if: page.id() == 'users' -->
            <!-- ko with: page.data -->
                <div class="container dashboard">
                    <nav class="navbar navbar-default" role="navigation">
                        <div class="container-fluid">
                            <div class="navbar-header">
                                <span class="navbar-brand">Users</span>
                            </div>
                            <div class="navbar-right">
                                <button type="button" class="btn btn-default navbar-btn"
                                        data-bind="click: function(){ createUserActionHandler() }">Create User</button>
                            </div>
                        </div>
                    </nav>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th class="col-xs-1">#</th>
                                <th class="col-xs-4">Name</th>
                                <th class="col-xs-5">Email</th>
                                <th class="col-xs-1">Status</th>
                                <th class="col-xs-1">Actions</th>
                            </tr>
                        </thead>
                        <tbody data-bind="foreach: usersIds">
                            <tr>
                                <td data-bind="text: $parent.users[$data].id"></td>
                                <td data-bind="text: $parent.users[$data].name"></td>
                                <td data-bind="text: $parent.users[$data].email"></td>
                                <td>
                                    <!-- ko if: $parent.users[$data].active() --> <span class="label label-success">Active</span><!-- /ko -->
                                    <!-- ko if: !$parent.users[$data].active() --><span class="label label-warning">Inactive</span><!-- /ko -->
                                </td>
                                <td>
                                    <div class="btn-group btn-group">
                                        <button type="button" class="btn btn-xs btn-primary dropdown-toggle" data-toggle="dropdown">
                                            Action <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="#" data-bind="click: function() { $parent.editUserActionHandler($data) }">Edit</a></li>
                                            <li><a href="#" data-bind="click: function() { $parent.changeStatusActionHandler($data) }">
                                                <!-- ko if: $parent.users[$data].active() -->Deactivate<!-- /ko -->
                                                <!-- ko if: !$parent.users[$data].active() -->Activate<!-- /ko -->
                                            </a></li>
                                            <li><a href="#" data-bind="click: function() { $parent.deleteUserActionHandler($data) }">Delete</a></li>
                                        </ul>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <!-- ko with: userForm -->
                    <div class="modal fade" id="userFormModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                    <h4 class="modal-title">
                                        <!-- ko if: editMode() -->
                                            Edit User
                                        <!-- /ko -->
                                        <!-- ko if: !editMode() -->
                                            Create User
                                        <!-- /ko -->
                                    </h4>
                                </div>
                                <div class="modal-body">
                                    <!-- ko with: fields -->
                                        <form role="form" class="form-horizontal">
                                            <div class="form-group" data-bind="css: { 'has-error': name.error() }">
                                                <label class="col-sm-2 control-label" for="editUserName">Name</label>
                                                <div class="col-sm-10">
                                                    <input name="name" type="text" class="form-control" id="editUserName"
                                                           placeholder="Enter name" data-bind="value: name.value">
                                                    <!-- ko if: name.error() -->
                                                        <span class="help-block" data-bind="text: name.errorMessage"></span>
                                                    <!-- /ko -->
                                                </div>
                                            </div>
                                            <div class="form-group" data-bind="css: { 'has-error': email.error() }">
                                                <label class="col-sm-2 control-label" for="editUserEmail">Email</label>
                                                <div class="col-sm-10">
                                                    <input name="email" type="text" class="form-control" id="editUserEmail"
                                                           placeholder="Enter email" data-bind="value: email.value">
                                                    <!-- ko if: email.error() -->
                                                        <span class="help-block" data-bind="text: email.errorMessage"></span>
                                                    <!-- /ko -->
                                                </div>
                                            </div>
                                            <div class="form-group" data-bind="css: { 'has-error': password.error() }">
                                                <label class="col-sm-2 control-label" for="editUserPassword">Password</label>
                                                <div class="col-sm-10">
                                                    <input name="password" type="password" class="form-control" id="editUserPassword"
                                                           placeholder="Enter Password" data-bind="value: password.value">
                                                    <!-- ko if: password.error() -->
                                                        <span class="help-block" data-bind="text: password.errorMessage"></span>
                                                    <!-- /ko -->
                                                </div>
                                            </div>
                                        </form>
                                    <!-- /ko -->
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                    <button type="button" class="btn btn-success"
                                            data-bind="click: function(){ $parent.saveUserActionHandler(id()) }">Save</button>
                                </div>
                            </div>
                        </div>
                    </div>
                <!-- /ko -->
                <div class="modal fade" id="deleteUserConfirmationModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <h4 class="modal-title">Delete User Confirmation</h4>
                            </div>
                            <div class="modal-body">
                                Are you really want to delete user?
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                <button type="button" class="btn btn-danger"
                                        data-bind="click: function(){ deleteUserConfirmActionHandler(deleteConfirmationId()) }">Delete</button>
                            </div>
                        </div>
                    </div>
                </div>
            <!-- /ko -->
        <!-- /ko -->

        <div class="navbar navbar-default navbar-fixed-bottom">
            <div class="container">
                <p class="navbar-text">
                    <a class="navbar-link" href="https://github.com/mind-blowing/log-server" target="_blank"><i class="fa fa-github"></i> Project Home</a>,
                    <span>Version: 0.1</span>
                </p>
            </div>
        </div>

        <!-- Alert Modal -->
        <!-- ko with: modal.alert -->
        <div class="modal fade" id="alertModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title" id="myModalLabel" data-bind="text: title"></h4>
                    </div>
                    <div class="modal-body" data-bind="html: content"></div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- /ko -->
    </script>
</body>
</html>