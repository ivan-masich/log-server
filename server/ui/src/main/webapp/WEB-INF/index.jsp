<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Log Server</title>

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
                        <li><a href="#"><i class="fa fa-tachometer"></i> Dashboard</a></li>
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
                        <li><a href="#"><i class="fa fa-users"></i> Users</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="fa fa-user"></i>
                                <span data-bind="text: user.data.name"></span>
                                <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="#"><i class="fa fa-cog"></i> Settings</a></li>
                                <li><a href="#"><i class="fa fa-sign-out"></i> Sign Out</a></li>
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
                        <div class="panel-heading">Sign In</div>
                        <div class="panel-body">
                            <div data-bind="attr: { class: 'form-group' + (error() ? ' has-error' : '') }" class="form-group">
                                <label for="inputEmail">Email address</label>
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
                                <button type="button" class="btn btn-success"
                                        data-bind="click: signIn">Sign In</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /ko -->
        <!-- /ko -->

        <!-- ko if: page.id() == 'dashboard' -->
        <div class="container dashboard">
            <h2>Dashboard</h2>
        </div>
        <!-- /ko -->

        <!-- Alert Modal -->
        <!-- ko with: modal.alert -->
        <div class="modal fade" id="alertModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title" id="myModalLabel" data-bind="text: title"></h4>
                    </div>
                    <div class="modal-body" data-bind="text: content"></div>
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