com.dj.project.base.main.Main = function () {
    //Ext.QuickTips.init();

    // turn on validation errors beside the field globally
    //Ext.form.Field.prototype.msgTarget = 'side';


    var viewport;
    var cardPanel;
    var loginWindow;
    var cookieUtil = Ext.util.Cookies;
    return {
        init : function() {
            if (!cookieUtil.get('atm-web')) {
                if (!loginWindow) {
                    loginWindow = this.buildLoginWindow();
                }
                loginWindow.show();
            } else {
                this.buildViewport();
            }

        },
        destroy : function() {
            viewport.destroy();
            viewport = null;
            cardPanel = null;
            this.init();
        },
        buildLoginWindow : function() {
            var formItemDefaults = {
                allowBlank : false,
                anchor     : '-5',
                listeners  : {
                    scope      : this,
                    specialkey : function(field, e) {
                        if (e.getKey() === e.ENTER) {
                            this.onLogin();
                        }
                    }
                }
            };

            var formLoginItems = [
                {
                    fieldLabel : 'User Name',
                    name       : 'username'
                },
                {
                    inputType  : 'password',
                    fieldLabel : 'Password',
                    name       : 'password'
                }
            ];

            return  new Ext.Window({
                width     : 300,
                height    : 125,
                modal     : true,
                draggable : false,
                title     : 'Login to NYTBD',
                layout    : 'fit',
                center    : true,
                closable  : false,
                resizable : false,
                border    : false,
                items     : {
                    xtype       : 'form',
                    defaultType : 'textfield',
                    labelWidth  : 70,
                    frame       : true,
                    url         : 'webresources/login/login',
                    labelAlign  : 'right',
                    defaults    : formItemDefaults,
                    items       : formLoginItems
                },
                buttons : [
                    {
                        text    : 'Login',
                        handler : this.onLogin,
                        scope   : this
                    }
                ]
            });
        },
        buildViewport : function() {
            cardPanel = new Ext.Panel({
                layout     : 'card',
                activeItem : 0,
                border     : false,
                items      :  {
                    xtype   : 'developermanager',
                    xtype   : 'usermanager'
                },
                tbar       : [
                    /* {
                     text         : 'Manage Developers',
                     iconCls      : 'icon-user_edit',
                     itemType     : 'developermanager',
                     toggleGroup  : 'navGrp',
                     enableToggle : true,
                     scope        : this,
                     handler      : this.onSwitchPanel
                     },*/
                    {
                        text         : 'Manage Users',
                        iconCls      : 'icon-user_edit',
                        itemType     : 'usermanager',
                        toggleGroup  : 'navGrp',
                        enableToggle : true,
                        scope        : this,
                        handler      : this.onSwitchPanel
                    },
                    {
                        text         : 'Manage Vouchers',
                        iconCls      : 'icon-user_edit',
                        itemType     : 'vouchermanager',
                        toggleGroup  : 'navGrp',
                        enableToggle : true,
                        scope        : this,
                        handler      : this.onSwitchPanel
                    },
                    '->',
                    {
                        id       : 'logout-btn',
                        text     : 'Log out',
                        iconCls  : 'icon-door_out',
                        scope    : this,
                        handler  : this.onLogOut
                    }
                ]
            });
            viewport = new Ext.Viewport({
                layout : 'fit',
                items  : cardPanel
            });
            Ext.getBody().unmask();
        },
        onLogin : function() {
            var form = loginWindow.get(0);
            if (form.getForm().isValid()) {
                loginWindow.el.mask('Please wait...', 'x-mask-loading');
                form.getForm().submit({
                    success : this.onLoginSuccess,
                    failure : this.onLoginFailure,
                    scope   : this
                });
            }
        },
        onLoginSuccess : function(form, action) {
            loginWindow.el.unmask();
            var cookie = cookieUtil.get('atm-web');
            if (cookie) {
                this.buildViewport();
                loginWindow.destroy();
                loginWindow = null;
            }
            else {
                this.onLoginFailure();
            }
        },
        onLoginFailure : function(form, action) {
            loginWindow.el.unmask();
            Ext.MessageBox.alert('Failure', "Login failed. Please try again");
        },
        onLogOut :function() {
            Ext.MessageBox.confirm(
                    'Please confirm',
                    'Are you sure you want to log out?',
                    function(btn) {
                        if (btn === 'yes') {
                            this.doLogOut();
                        }
                    },
                    this
                    );
        },
        doLogOut : function() {
            Ext.getBody().mask('Logging out...', 'x-mask-loading');

            Ext.Ajax.request({
                url          : 'webresources/login/logout',
                params       : {
                    user : cookieUtil.get('loginCookie')
                },
                scope        : this,
                callback     : this.onAfterAjaxReq,
                succCallback : this.onAfterLogout
            });
        },
        onAfterLogout : function() {
            this.destroy();
        },
        onSwitchPanel : function(btn) {
            var newPanel,
                    xtype = btn.itemType,
                    panels = cardPanel.findByType(xtype);

            if (panels.length < 1) {
                newPanel = cardPanel.add({
                    xtype     : xtype,
                    workspace : this
                });
                cardPanel.doLayout();
            }
            else {
                newPanel = panels[0];
            }

            var newCardIndex = cardPanel.items.indexOf(newPanel);
            this.switchToCard(newCardIndex);
        },
        switchToCard : function(newCardIndex) {
            var layout = cardPanel.getLayout();
            var activePanel = layout.activeItem;
            var activePanelIdx = cardPanel.items.indexOf(activePanel);

            if (activePanelIdx !== newCardIndex) {
                var newPanel = cardPanel.items.itemAt(newCardIndex);

                layout.setActiveItem(newCardIndex);

                if (newPanel.cleanSlate) {
                    newPanel.cleanSlate();
                }
            }
        },
        onAfterAjaxReq : function(options, success, result) {
            Ext.getBody().unmask();
            if (success === true) {
                var jsonData;
                try {
                    jsonData = Ext.decode(result.responseText);
                } catch (e) {
                    Ext.MessageBox.alert('Error!', 'Data returned is not valid!');
                }
                options.succCallback.call(options.scope, jsonData, options);

            } else {
                Ext.MessageBox.alert('Error!', 'The web transaction failed!');
            }
        }
    };
}();

Ext.onReady(com.dj.project.base.main.Main.init, com.dj.project.base.main.Main);
