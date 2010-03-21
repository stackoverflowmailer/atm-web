Ext.ns("com.dj.project.developer");


com.dj.project.developer.DeveloperManager = Ext.extend(Ext.Panel, {
    border : false,
    layout : {
        type  : 'hbox',
        align : 'stretch'
    },
    msgs          : {
        immediateChanges : 'Warning! Changes are <span style="color: red;">immediate</span>.',
        errorsInForm     : 'There are errors in the form. Please correct and try again.',
        developerSavedSuccess  : 'Saved {0}, {1} successfully.',
        fetchingDataFor  : 'Fetching data for {0}, {1}',
        couldNotLoadData : 'Could not load data for {0}, {1}!',
        saving           : 'Saving {0}, {1}...',
        errorSavingData  : 'There was an error saving the form.',
        deletingDeveloper : 'Deleting developer {0}, {1}...',
        deleteEmpConfirm : 'Are you sure you want to delete developer {0}, {1}?',
        deleteEmpSuccess : 'Developer {0}, {1} was deleted successfully.',
        deleteEmpFailure : 'Developer {0}, {1} was not deleted due to a failure.'
    },
    initComponent : function() {
        this.items = [
            this.buildDeveloperList(),
            this.buildDeveloperForm()

        ];
        com.dj.project.developer.DeveloperManager.superclass.initComponent.call(this);
    },


    buildDeveloperForm : function() {
        return {
            xtype     : 'developerform',
            itemId    : 'developerForm',
            flex      : 1,
            border    : false,
            listeners : {
                scope : this,
                saveDeveloper: this.onSaveDeveloper
            }
        };
    },

    buildDeveloperList : function() {

        return {
            xtype     : 'developerlist',
            itemId    : 'developerList',
            width     : 190,
            border    : false,
            style     : 'border-right: 1px solid #99BBE8;',
            title     : 'Developers',
            listeners : {
                scope  : this,
                click  : this.onDeveloperListClick
            }
        };
    },
    onDeveloperListClick : function() {
        Ext.Msg.alert("Clicked");
    },

    onSaveDeveloper : function(developerForm, values) {
        if (developerForm.getForm().isValid()) {
            var msg = String.format(this.msgs.saving, values['name.lastName'], values['name.firstName']);

            Ext.getBody().mask(msg, 'x-mask-loading');
            var self = this;
            var form = developerForm.getForm();
            developerForm.getForm().doAction("jsonsubmit", {
                params   : {
                    data : self.toObject(form)
                },
                url     : 'webresources/developer/saveDeveloper',
                scope   : this,
                success : this.onDeveloperSaveSuccess,
                failure : this.onDeveloperSaveFailure
            });
        } else {
            Ext.MessageBox.alert('Error', this.msgs.errorsInForm);
        }
    },
    toObject : function(form) {
        var defaultValues = form.getValues(false);
        //console.dir(defaultValues);
        var name = {
            firstName  : defaultValues['name.firstName'],
            middleName : defaultValues['name.middleName'],
            lastName   : defaultValues['name.lastName']
        };
        var landPhone = {
            countryCode :  defaultValues['landPhone.number']
        };
        var developer = {
            id : defaultValues['id'],
            name : name,
            landPhone : landPhone
        };
        //return developer;
        return developer;
    },

    onDeveloperSaveSuccess : function(developerForm, action) {
        console.dir(action);
        var record = this.getComponent('developerList').getSelected();
        var defaultValues = developerForm.getValues(false);

        var firstName = defaultValues['name.firstName'];
        var lastName = defaultValues['name.lastName'];
        var msg = String.format(this.msgs.developerSavedSuccess, lastName, firstName);

        if (record) {
            record.set('lastName', lastName);
            record.set('firstName', firstName);
            record.commit();
        }
        else {
            var resultData = action.result.data;
            this.getComponent('developerList').createAndSelectRecord(resultData);
            this.getComponent('developerForm').setValues({});
        }
        Ext.MessageBox.alert('Success', msg);

        this.clearMask();
    },
    onDeveloperSaveFailure : function() {
        this.clearMask();
        Ext.MessageBox.alert('Error', this.msgs.errorSavingData);
    },
    clearMask : function() {
        Ext.getBody().unmask();
    },

    cleanSlate : function() {
        this.getComponent('developerList').refreshView();
        this.getComponent('developerForm').clearForm();
    }

});

Ext.reg('developermanager', com.dj.project.developer.DeveloperManager);