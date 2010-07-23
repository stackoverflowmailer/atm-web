Ext.namespace('com.dj.project.login');
com.dj.project.login.EnhancedLoginWindow = Ext.extend(Ext.Window, {

    initComponent: function() {

        this.softwareLabel = 'intraBuild 2.0',
        this.editionLabel = 'mRAM edition',
        this.versionLabel = 'V0.1a &quot;Amelie&quot;',
        this.windowTitle = this._createWindowTitle();
        this.buttonConfig = this._createToolbarButtons();
        this.formPanel = this._createFormContent();

        Ext.applyIf(this, {
            height : 335
        });


        Ext.apply(this, {
            cls : 'com-dj-project-login-EnhancedLoginWindow',
            closable : false,
            resizable : false,
            width : 465,
            draggable: false,
            items : [
                {
                    xtype  :'box',
                    cls    : 'softwareLabel',
                    autoEl : {
                        tag  : 'div',
                        html : this.softwareLabel
                    }},
                {
                    xtype  :'box',
                    cls    : 'editionLabel',
                    autoEl : {
                        tag  : 'div',
                        html : this.editionLabel
                    }},
                {
                    xtype  :'box',
                    cls    : 'versionLabel',
                    autoEl : {
                        tag  : 'div',
                        html : this.versionLabel
                    }},
                this.windowTitle,
                this.formPanel
            ],
            buttons : this.buttonConfig
        });


        this.addEvents({
            'beforeLogin': true,
            'exit' : true,
            'loginFailure' : true,
            'loginSuccess' : true
        });
        com.dj.project.login.EnhancedLoginWindow.superclass.initComponent.call(this);
    },

    //    _initUi : function(){
    //        this._createWindowTitle();
    //        this._createFormContent();
    //        this._createToolbarButtons();
    //    },

    _createWindowTitle : function() {
        return new com.dj.project.login.WindowTitleComponent({
            cls : 'formTitle',
            labelText : "Login",
            text : 'Please signin with your Username & Password and hit Enter \n ' +
                    '\n' +
                    'Unathorized access is prohibited.'
        });
    },

    _createFormContent : function() {
        return new Ext.form.FormPanel({
            monitorValid : true,
            url          : this.loginUrl,
            method       : 'post',
            labelAlign   : 'right',
            cls          : 'x-small-editor com-dj-project-login-EnhancedLoginWindow-formPanel',
            labelWidth   : 75,
            defaults     : {
                anchor     : '100%',
                labelStyle : 'width:75px;font-size:11px;',
                xtype : 'textfield'
            },
            border   : false,
            items    : [
                {
                    fieldLabel : 'User Name',
                    name : 'username'
                },
                {
                    inputType : 'password',
                    fieldLabel : 'Password',
                    name : 'password'
                }
            ],
            listeners : {
                clientvalidation : {
                    fn    : this._onClientValidation,
                    scope : this
                }
            }
        });
    },

    _createToolbarButtons : function() {
        return [
            {
                inputType : 'button',
                name : 'login',
                text : 'Login',
                handler : this._loginHandler
            },
            {
                inputType : 'button',
                name : 'exit',
                text : 'Exit',
                handler : this._exitHandler
            }
        ]
    },
    _onClientValidation : function() {

    },
    _loginHandler : function() {

    },
    _exitHandler : function() {

    }
});



