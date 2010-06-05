com.dj.project.developer.DeveloperManager = Ext.extend(com.dj.project.base.BaseManager, {
    border : false,
    layout : {
        type  : 'hbox',
        align : 'stretch'
    },
    localMsg : {

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
                saveDeveloper: this.onSaveDeveloper,
                reset: this.onReset,
                generateReport:this.onGenerateReport,
                clientValidation : this.onClientValidation
            },

            reader : new Ext.data.JsonReader({
                idProperty      : 'id',
                root            : 'data',
                successProperty : 'success',
                //messageProperty: "msg",
                //totalProperty  : 'total',

                fields          : [
                    'id',
                    {name: 'name.firstName', type: 'auto', mapping:'name.firstName'},
                    {name: 'name.lastName',  type: 'auto', mapping:'name.lastName'},
                    {name: 'name.middleName',  type: 'auto',mapping:'name.middleName'},
                    {name : 'title', mapping : 'title'},
                    {name: 'landPhone.countryCode',  type: 'auto', mapping:'landPhone.countryCode'},
                    {name: 'landPhone.stdCode',  type: 'auto', mapping:'landPhone.stdCode'},
                    {name: 'landPhone.number',  type: 'auto', mapping:'landPhone.number'},
                    {name: 'mobilePhone.countryCode',  type: 'auto', mapping:'mobilePhone.countryCode'},
                    {name: 'mobilePhone.stdCode',  type: 'auto', mapping:'mobilePhone.stdCode'},
                    {name: 'mobilePhone.number',  type: 'auto', mapping:'mobilePhone.number'},
                    {name: 'band',  type: 'auto', mapping:'band'},
                    {name: 'dol', convert:this.convertDate},
                    {name: 'doj', convert:this.convertDate},
                    {name: 'bloodGroup',  type: 'auto',mapping:'bloodGroup'}
                ]
            })
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
        var record = this.getComponent('developerList').getSelected();
        //console.log(record);
        var msg = String.format(
                this.globalMsg.fetchingDataFor,
                record.get('lastName'),
                record.get('firstName')
                );

        Ext.getBody().mask(msg, 'x-mask-loading');

        this.getComponent('developerForm').load({
            url     : 'webresources/developer/getDeveloper',
            scope   : this,
            success : this.clearMask,
            failure : this.onDeveloperFormLoadFailure,
            params  : {
                id : record.get('id')
            }
        });
    },
    onDeveloperFormLoadFailure : function() {
        var record = this.getComponent('developerList').getSelected();
        var msg = String.format(
                this.globalMsg.couldNotLoadData,
                record.get('lastName'),
                record.get('firstName')
                );

        Ext.MessageBox.show({
            title   : 'Error',
            msg     : msg,
            buttons : Ext.MessageBox.OK,
            icon    : Ext.MessageBox.WARNING
        });

        this.clearMask();
    },
    onSaveDeveloper : function(developerForm, values) {
        if (developerForm.getForm().isValid()) {
            var msg = String.format(this.globalMsg.saving, values['name.lastName'], values['name.firstName']);

            Ext.getBody().mask(msg, 'x-mask-loading');
            var self = this;
            var form = developerForm.getForm();
            developerForm.getForm().doAction("jsonsubmit", {
                params   : {
                    data : developerForm.getFieldValuesAsObject()
                },
                url     : 'webresources/developer/saveDeveloper',
                scope   : this,
                success : this.onDeveloperSaveSuccess,
                failure : this.onDeveloperSaveFailure
            });
        } else {
            Ext.MessageBox.alert('Error', this.globalMsg.errorsInForm);
        }
    },
    onDeveloperSaveSuccess : function(developerForm, action) {
        var record = this.getComponent('developerList').getSelected();
        var defaultValues = developerForm.getValues(false);

        var firstName = defaultValues['name.firstName'];
        var lastName = defaultValues['name.lastName'];
        var msg = String.format(this.globalMsg.developerSavedSuccess, lastName, firstName);

        if (record) {
            record.set('lastName', lastName);
            record.set('firstName', firstName);
            record.commit();
        }
        /*else {
            var resultData = action.result.data;
            this.getComponent('developerList').createAndSelectRecord(resultData);
        }*/
        Ext.MessageBox.alert('Success', msg);
        this.clearMask();
        this.getComponent('developerList').refreshView();
        this.onReset(developerForm);

    },
    onDeveloperSaveFailure : function() {
        this.clearMask();
        Ext.MessageBox.alert('Error', this.globalMsg.errorSavingData);
    },

    onGenerateReport : function() {
        var el = Ext.getDom('reportFrame');
        if (el) {
            Ext.removeNode(el);
        }
        var report = new com.dj.project.report.Report({
            renderTo: Ext.getBody(),
            id: 'reportFrame'
        });
        report.load({
            //method: 'POST', ? why  'POST' is not working
            url: 'webresources/developer/report',
            params: {
                task: 'csv',
                query: 'query'
            }
        });

    },
    
    cleanSlate : function() {
        this.getComponent('developerList').refreshView();
        this.getComponent('developerForm').clearForm();
    },
    onClientValidation : function(formPanel, valid){
        this.enableDisableButtons(!valid, 'developerSaveBtn');
        //this.enableDisableSaveButton(!valid, 'developerResetBtn');
    },
    onReset : function(developerForm){
        this.getComponent('developerForm').clearForm();
        this.getComponent('developerForm').reset();
    },
    enableDisableButtons : function(e, btnId){
      var btn = Ext.getCmp(btnId);
      btn.setDisabled(e);
    }

});

Ext.reg('developermanager', com.dj.project.developer.DeveloperManager);
