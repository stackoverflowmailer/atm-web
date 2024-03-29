com.dj.project.user.UserManager = Ext.extend(com.dj.project.base.BaseManager, {
    border : false,
    layout : {
        type  : 'hbox',
        align : 'stretch'
    },
    localMsg : {

    },
    initComponent : function() {
        this.items = [
            this.buildUserList(),
            this.buildUserForm()
        ];
        com.dj.project.user.UserManager.superclass.initComponent.call(this);
    },

    buildUserForm : function() {
        return {
            xtype     : 'userform',
            itemId    : 'userForm',
            flex      : 1,
            border    : false,
            listeners : {
                scope : this,
                save  : this.onSave,
                reset : this.onReset
            },

            reader : new Ext.data.JsonReader({
                idProperty      : 'id',
                root            : 'data',
                successProperty : 'success',
                //messageProperty: "msg",
                //totalProperty  : 'total',

                fields          : [
                    'id',
                    {name: 'name', type: 'auto', mapping:'name'},
                    {name: 'username',  type: 'auto', mapping:'username'},
                    {name: 'creationDate', convert:this.convertDate}
                ]
            })
        };
    },
    buildUserList : function() {

        return {
            xtype     : 'userlist',
            itemId    : 'userList',
            width     : 190,
            border    : false,
            style     : 'border-right: 1px solid #99BBE8;',
            title     : 'Users',
            listeners : {
                scope  : this,
                click  : this.onUserListClick
            }
        };
    },
    onUserListClick : function() {
        var record = this.getComponent('userList').getSelected();
        //console.log(record);
        var msg = String.format(
                this.globalMsg.fetchingDataFor,
                record.get('username'),
                record.get('name')
                );

        Ext.getBody().mask(msg, 'x-mask-loading');

        this.getComponent('userForm').load({
            url     : 'webresources/user/get',
            scope   : this,
            success : this.clearMask,
            failure : this.onUserFormLoadFailure,
            params  : {
                id : record.get('id')
            }
        });
    },
    onUserFormLoadFailure : function() {
        var record = this.getComponent('userList').getSelected();
        var msg = String.format(
                this.globalMsg.couldNotLoadData,
                record.get('username'),
                record.get('name')
                );

        Ext.MessageBox.show({
            title   : 'Error',
            msg     : msg,
            buttons : Ext.MessageBox.OK,
            icon    : Ext.MessageBox.WARNING
        });

        this.clearMask();
    },
    onSave : function(userForm, values) {
        if (userForm.getForm().isValid()) {
            var msg = String.format(this.globalMsg.saving, values['username'], values['name']);

            Ext.getBody().mask(msg, 'x-mask-loading');
            var self = this;
            var form = userForm.getForm();
            userForm.getForm().doAction("jsonsubmit", {
                params   : {
                    data : userForm.getFieldValuesAsObject()
                },
                url     : 'webresources/user/save',
                scope   : this,
                success : this.onUserSaveSuccess,
                failure : this.onUserSaveFailure
            });
        } else {
            Ext.MessageBox.alert('Error', this.globalMsg.errorsInForm);
        }
    },
    onUserSaveSuccess : function(userForm, action) {
        var record = this.getComponent('userList').getSelected();
        var defaultValues = userForm.getValues(false);

        var name = defaultValues['name'];
        var username = defaultValues['username'];
        var msg = String.format(this.globalMsg.saveSuccessful, username, name);

        if (record) {
            record.set('username', username);
            record.set('name', name);
            record.commit();
        }
        Ext.MessageBox.alert('Success', msg);
        this.clearMask();
        this.getComponent('userList').refreshView();
        this.onReset(userForm);

    },
    onUserSaveFailure : function() {
        this.clearMask();
        Ext.MessageBox.alert('Error', this.globalMsg.errorSavingData);
    },

    cleanSlate : function() {
        this.getComponent('userList').refreshView();
        this.getComponent('userForm').clearForm();
    },
    onClientValidation : function(formPanel, valid) {
        //this.enableDisableButtons(!valid, 'userSaveBtn');
        //this.enableDisableSaveButton(!valid, 'userResetBtn');
    },
    onReset : function(userForm) {
        this.getComponent('userForm').clearForm();
        this.getComponent('userForm').reset();
    },
    enableDisableButtons : function(e, btnId) {
        var btn = Ext.getCmp(btnId);
        btn.setDisabled(e);
    }

});

Ext.reg('usermanager', com.dj.project.user.UserManager);
