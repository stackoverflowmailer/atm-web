com.dj.project.voucher.VoucherForm = Ext.extend(com.dj.project.base.AbstractForm, {
    title : 'Voucher',

    layout: 'column',
    autoHeight: true,
    bodyStyle : 'background-color: #DFE8F6; padding: 10px',

    initComponent : function() {
        Ext.applyIf(this, {
            tbar : this.buildToolbar(),
            items : this.buildFormItems()
        });

        com.dj.project.voucher.VoucherForm.superclass.initComponent
                .call(this);

        this.addEvents({
            'saveVoucher' : true,
            'reset' : true,
            'generateReport' : true
        });
        if (this.record) {
            this.on({
                scope : this,
                render : {
                    single : true,
                    fn : this.loadFormAfterRender
                }
            });
        }
    },

    buildToolbar : function() {
        return [
            {
                id:'voucherSaveBtn',
                text : 'Save',
                iconCls : 'icon-disk',
                scope : this,
                handler : this.onSave
            },
            '-',
            {
                id:'voucherResetBtn',
                text : 'Reset',
                iconCls : 'icon-arrow_undo',
                scope : this,
                handler : this.onReset
            },
            '-',

            /*{
             text : 'Report',
             iconCls : 'icon-user_report',
             scope : this,
             handler : this.onReport
             },*/
            {
                text  : 'Voucher Report',
                url   : 'webresources/voucher/report',
                xtype : 'reportButton',
                fileType : 'html'

            },
            '->',
            {
                text : 'Delete Voucher',
                iconCls : 'icon-user_delete',
                scope : this,
                handler : this.onDelete
            }
        ];
    },
    buildFormItems : function() {
        return [
            {
                xtype: 'fieldset',
                title: 'Voucher Information',
                bodyStyle: Ext.isIE ? 'padding:0 0 5px 15px;' : 'padding:10px 15px;',
                style: {
                    "margin-left": "10px", // when you add custom margin in IE 6...
                    "margin-right": Ext.isIE6 ? (Ext.isStrict ? "-10px" : "-13px") : "0"  // you have to adjust for it somewhere else
                },


                layout: 'form',
                columnWidth: .60,
                autoHeight: true,
                anchor: '-20',
                defaults : {
                    xtype: 'textfield',
                    allowBlank:false,
                    anchor: '-20'
                },
                items: [
                    {
                        xtype : 'hidden',
                        name : 'id'
                    },
                    {
                        xtype: 'textfield',
                        flex : 1,
                        name : 'voucherNo',
                        fieldLabel: 'Voucher No',
                        allowBlank: false
                    },
                    {
                        xtype     : 'datefield',
                        name      : 'voucherDate',
                        fieldLabel: 'Voucher Date',
                        format : 'd/m/Y'
                    },
                    /*{
                        xtype     : 'datefield',
                        name      : 'issueDate',
                        fieldLabel: 'Issued Date',
                        format : 'd/m/Y'
                    },*/
                    {
                        xtype : 'compositefield',
                        anchor: '-20',
                        msgTarget: 'side',
                        fieldLabel: 'Issued To',
                        items : [
                            {
                                //the width of this field in the HBox layout is set directly
                                //the other 2 items are given flex: 1, so will share the rest of the space
                                width:          50,
                                xtype:          'combo',
                                mode:           'local',
                                value:          'mr',
                                triggerAction:  'all',
                                forceSelection: true,
                                editable:       false,
                                fieldLabel:     'Title',
                                name:           'title',
                                hiddenName:     'title',
                                displayField:   'name',
                                valueField:     'value',
                                store:          new Ext.data.JsonStore({
                                    fields : ['name', 'value'],
                                    data   : [
                                        {
                                            name : 'Mr',
                                            value: 'mr'
                                        },
                                        {
                                            name : 'Mrs',
                                            value: 'mrs'
                                        },
                                        {
                                            name : 'Miss',
                                            value: 'miss'
                                        }
                                    ]
                                })
                            },
                            {
                                xtype: 'textfield',
                                flex : 1,
                                name : 'name.firstName',
                                hiddenName : 'First Name',
                                fieldLabel: 'First',
                                allowBlank: false
                            },

                            {
                                xtype: 'textfield',
                                flex : 1,
                                name : 'name.lastName',
                                fieldLabel: 'Last',
                                allowBlank: false
                            }
                        ]
                    },
                    {
                        xtype: 'textfield',
                        flex : 1,
                        name : 'voucherAmt',
                        fieldLabel: 'Voucher Amount',
                        allowBlank: false
                    },
                    {
                        xtype: 'textarea',
                        flex : 1,
                        name : 'remarks',
                        fieldLabel: 'Voucher Remarks',
                        allowBlank: true
                    }

                ]
            },

            {
                xtype: 'fieldset',
                title : 'Other Links',
                collapsible: true,
                bodyStyle: Ext.isIE ? 'padding:0 0 5px 15px;' : 'padding:10px 15px;',
                style: {
                    "margin-left": "10px", // when you add custom margin in IE 6...
                    "margin-right": Ext.isIE6 ? (Ext.isStrict ? "-10px" : "-13px") : "0"  // you have to adjust for it somewhere else
                },

                layout: 'form',
                columnWidth: .40,
                autoHeight: true,
                anchor: '-20',

                items : [
                    new com.dj.project.base.Shortcuts({
                        items: [
                            new com.dj.project.base.ShortcutHolder(
                            {text: 'Shortcut 1', icon: 'resources/icons/item.gif'}),
                            { xtype: 'shortcut', text: 'Shortcut 2', icon: 'resources/icons/item.gif'},
                            { xtype: 'shortcut', text: 'Shortcut 2', icon: 'resources/icons/item.gif'}
                        ]
                    })

                ]
            }

        ];
    },

    onSave : function() {
        if (this.isValid()) {
            this.fireEvent('saveVoucher', this, this.getValues());
        }
    },
    onReset : function() {
        this.fireEvent('reset', this, this.getValues());
    },
    /*onReport : function() {
     this.fireEvent('generateReport',this);
     },*/
    loadFormAfterRender : function() {
        this.load({
            url : 'vouchers/getVoucher',
            params : {
                id : this.record.get('id')
            }
        });
    }

});

Ext.reg('voucherform', com.dj.project.voucher.VoucherForm);
