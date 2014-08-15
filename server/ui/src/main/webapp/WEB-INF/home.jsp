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
    <div class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Log Server</a>
            </div>
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
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> Ivan Masich <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="#"><i class="fa fa-cog"></i> Settings</a></li>
                            <li><a href="#"><i class="fa fa-sign-out"></i> Sign Out</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <div class="container sign-in-container">
        <div class="row">
            <div class="col-xs-6 col-xs-offset-3">
                <div class="panel panel-default">
                    <div class="panel-heading">Sign In</div>
                    <div class="panel-body">
                        <div class="form-group">
                            <label for="inputEmail">Email address</label>
                            <input type="email" class="form-control" id="inputEmail" placeholder="Enter email">
                        </div>
                        <div class="form-group">
                            <label for="inputPassword">Password</label>
                            <input type="password" class="form-control" id="inputPassword" placeholder="Enter password">
                        </div>
                        <div class="pull-right">
                            <button type="button" class="btn btn-success">Sign In</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>