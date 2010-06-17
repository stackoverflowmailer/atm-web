com.dj.project.voucher.VoucherManager = Ext.extend(com.dj.project.base.BaseManager, {
    border : false,
    layout : {
        type  : 'hbox',
        align : 'stretch'
    },
    localMsg : {

    },
    initComponent : function() {
        this.items = [
            this.buildVoucherList(),
            this.buildVoucherForm()
        ];
        com.dj.project.voucher.VoucherManager.superclass.initComponent.call(this);
    },

    buildVoucherForm : function() {
        return {
            xtype     : 'voucherform',
            itemId    : 'voucherForm',
            flex      : 1,
            border    : false,
            listeners : {
                scope : this,
                saveVoucher: this.onSaveVoucher,
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
                    {
                        name: 'voucherNo'
                    },
                    {
                        name: 'voucherDate',
                        convert:this.convertDate
                    },
                    {
                        name: 'issueDate'
                    },
                    {
                        name : 'issueTo'
                    },
                    {
                        name: 'voucherAmt'
                    },
                    {
                        name: 'issueFor'
                    }
                ]
            })
        };
    },
    buildVoucherList : function() {

        return {
            xtype     : 'voucherlist',
            itemId    : 'voucherList',
            width     : 190,
            border    : false,
            style     : 'border-right: 1px solid #99BBE8;',
            title     : 'Vouchers',
            listeners : {
                scope  : this,
                click  : this.onVoucherListClick
            }
        };
    },
    onVoucherListClick : function() {
        var record = this.getComponent('voucherList').getSelected();
        //console.log(record);
        var msg = String.format(
                this.globalMsg.fetchingDataFor,
                record.get('voucherDate'),
                record.get('issueFor')
                );

        Ext.getBody().mask(msg, 'x-mask-loading');

        this.getComponent('voucherForm').load({
            url     : 'webresources/voucher/getVoucher',
            scope   : this,
            success : this.clearMask,
            failure : this.onVoucherFormLoadFailure,
            params  : {
                id : record.get('id')
            }
        });
    },
    onVoucherFormLoadFailure : function() {
        var record = this.getComponent('voucherList').getSelected();
        var msg = String.format(
                this.globalMsg.couldNotLoadData,
                record.get('voucherDate'),
                record.get('issueFor')
                );

        Ext.MessageBox.show({
            title   : 'Error',
            msg     : msg,
            buttons : Ext.MessageBox.OK,
            icon    : Ext.MessageBox.WARNING
        });

        this.clearMask();
    },
    onSaveVoucher : function(voucherForm, values) {
        if (voucherForm.getForm().isValid()) {
            var msg = String.format(this.globalMsg.saving, values['name.lastName'], values['name.firstName']);

            Ext.getBody().mask(msg, 'x-mask-loading');
            var self = this;
            var form = voucherForm.getForm();
            voucherForm.getForm().doAction("jsonsubmit", {
                params   : {
                    data : voucherForm.getFieldValuesAsObject()
                },
                url     : 'webresources/voucher/saveVoucher',
                scope   : this,
                success : this.onVoucherSaveSuccess,
                failure : this.onVoucherSaveFailure
            });
        } else {
            Ext.MessageBox.alert('Error', this.globalMsg.errorsInForm);
        }
    },
    onVoucherSaveSuccess : function(voucherForm, action) {
        var record = this.getComponent('voucherList').getSelected();
        var defaultValues = voucherForm.getValues(false);

        var voucherDate = defaultValues['voucherDate'];
        var issuedFor = defaultValues['issueFor'];
        var msg = String.format(this.globalMsg.saveSuccessful, lastName, firstName);

        if (record) {
            record.set('voucherDate', voucherDate);
            record.set('issueFor', issuedFor);
            record.commit();
        }
        /*else {
         var resultData = action.result.data;
         this.getComponent('voucherList').createAndSelectRecord(resultData);
         }*/
        Ext.MessageBox.alert('Success', msg);
        this.clearMask();
        this.getComponent('voucherList').refreshView();
        this.onReset(voucherForm);

    },
    onVoucherSaveFailure : function() {
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
            url: 'webresources/voucher/report',
            params: {
                task: 'csv',
                query: 'query'
            }
        });

    },

    cleanSlate : function() {
        this.getComponent('voucherList').refreshView();
        this.getComponent('voucherForm').clearForm();
    },
    onClientValidation : function(formPanel, valid) {
        this.enableDisableButtons(!valid, 'voucherSaveBtn');
        //this.enableDisableSaveButton(!valid, 'voucherResetBtn');
    },
    onReset : function(voucherForm) {
        this.getComponent('voucherForm').clearForm();
        this.getComponent('voucherForm').reset();
    },
    enableDisableButtons : function(e, btnId) {
        var btn = Ext.getCmp(btnId);
        btn.setDisabled(e);
    }

});

Ext.reg('vouchermanager', com.dj.project.voucher.VoucherManager);
