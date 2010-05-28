<html>
<head>
    <!-- characters, just make ALL strings, on the page, json and database utf-8. -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <!-- Ext relies on its default css so include it here. -->
    <!-- This must come BEFORE javascript includes! -->
    <link rel="stylesheet" type="text/css" href="extjs-3.2.1/resources/css/ext-all.css">
    <link rel="stylesheet" type="text/css" href="css/atm-web.css">

    <!-- Include here your own css files if you have them. -->

    <!-- First of javascript includes must be an adapter... -->
    <script type="text/javascript" src="extjs-3.2.1/adapter/ext/ext-base-debug.js"></script>

    <!-- ...then you need the Ext itself, either debug or production version. -->
    <script type="text/javascript" src="extjs-3.2.1/ext-all-debug.js"></script>

    <!-- Include here your extended classes if you have some. -->

    <!-- Include here you application javascript file if you have it. -->
    <!-- Set a title for the page (id is not necessary). -->
    <title id="page-title">Advanced Task Management</title>

    <!-- You can have onReady function here or in your application file. -->
    <!-- If you have it in your application file delete the whole -->
    <!-- following script tag as we must have only one onReady. -->
    <script type="text/javascript" src="js/base/Global.js"></script>
    <script type="text/javascript" src="js/base/JsonSubmit.js"></script>
    <script type="text/javascript" src="js/base/Report.js"></script>
    <script type="text/javascript" src="js/base/WindowReportButton.js"></script>
    <script type="text/javascript" src="js/base/BaseManager.js"></script>
    <script type="text/javascript" src="js/base/BaseForm.js"></script>
    <script type="text/javascript" src="js/base/BaseListPanel.js"></script>
    <script type="text/javascript" src="js/class/ClassList.js"></script>
    <script type="text/javascript" src="js/developer/DeveloperGridPanel.js"></script>
    <script type="text/javascript" src="js/developer/DeveloperForm.js"></script>
    <script type="text/javascript" src="js/developer/DeveloperList.js"></script>
    <script type="text/javascript" src="js/developer/DeveloperManager.js"></script>
    <script type="text/javascript" src="js/Main.js"></script>

    <script type="text/javascript">

        // Path to the blank image must point to a valid location on your server
        Ext.BLANK_IMAGE_URL = 'extjs-3.2.1/resources/images/default/s.gif';
        Ext.USE_NATIVE_JSON = true;

    </script>
</head>

<!-- You can leave the body empty in many cases, or you write your content in it. -->
<body>
<div class="container" style="width:500px">
    <div id="user-grid"></div>
</div>


</body>

<!-- Close html tag at last -->
</html>
