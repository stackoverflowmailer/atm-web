Ext.ns("com.dj.project.developer");

com.dj.project.developer.DeveloperForm = Ext.extend(com.dj.project.base.BaseForm, {
    title : 'Developer',

    layout: 'column',
    autoHeight: true,
    bodyStyle : 'background-color: #DFE8F6; padding: 10px',
    
    initComponent : function() {
        Ext.applyIf(this, {
            tbar : this.buildToolbar(),
            items : this.buildFormItems()
        });

        com.dj.project.developer.DeveloperForm.superclass.initComponent
                .call(this);

        this.addEvents({
            'saveDeveloper' : true
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
                text : 'Save',
                iconCls : 'icon-disk',
                scope : this,
                handler : this.onSave
            },
            '-',
            {
                text : 'Reset',
                iconCls : 'icon-arrow_undo',
                scope : this,
                handler : this.onReset
            },
            '-',
            {
                text : 'New Developer',
                iconCls : 'icon-user_add',
                scope : this,
                handler : this.onNew
            },
            '->',
            {
                text : 'Delete Developer',
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
                title: 'Basic Information',
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
                        xtype : 'compositefield',
                        anchor: '-20',
                        msgTarget: 'side',
                        fieldLabel: 'Full Name',
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
                                        {name : 'Mr',   value: 'mr'},
                                        {name : 'Mrs',  value: 'mrs'},
                                        {name : 'Miss', value: 'miss'}
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
                                name : 'name.middleName',
                                fieldLabel: 'Middle',
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
                        xtype:          'combo',
                        mode:           'local',
                        value:          'B',
                        triggerAction:  'all',
                        forceSelection: true,
                        editable:       false,
                        fieldLabel:     'Band',
                        name:           'band',
                        hiddenName:     'band',
                        displayField:   'name',
                        valueField:     'value',
                        store:          new Ext.data.JsonStore({
                            fields : ['name', 'value'],
                            data   : [
                                {name : 'Band A',   value: 'A'},
                                {name : 'Band B',  value: 'B'},
                                {name : 'Band C', value: 'C'}
                            ]
                        })
                    },
                    {
                        xtype: 'compositefield',
                        fieldLabel: 'Date of joining (Company & Project)',
                        msgTarget : 'side',
                        anchor    : '-20',
                        defaults: {
                            flex: 1
                        },
                        items: [
                            {
                                xtype     : 'datefield',
                                name      : 'company',
                                fieldLabel: 'Start'
                            },
                            {
                                xtype     : 'datefield',
                                name      : 'project',
                                fieldLabel: 'End'
                            }
                        ]
                    }
                ]
            },

            {
                xtype: 'fieldset',
                title : 'Telephone Details',
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
                defaults : {
                    xtype: 'textfield',
                    allowBlank:false,
                    anchor: '-20'
                },
                items : [
                    {
                        xtype: 'compositefield',
                        fieldLabel: 'Home',
                        // anchor    : '-20',
                        // anchor    : null,
                        msgTarget: 'side',
                        items: [
                            {xtype: 'displayfield', value: '('},
                            {xtype: 'numberfield',    name: 'landPhone.countryCode', width: 35, allowBlank: true},
                            {xtype: 'displayfield', value: ')'},
                            {xtype: 'numberfield', name: 'landPhone.stdCode', width: 55, allowBlank: true, margins: '0 5 0 0'},
                            {xtype: 'numberfield',    name: 'landPhone.number', width: 100, allowBlank: true}
                        ]
                    },
                    {
                        xtype: 'compositefield',
                        fieldLabel: 'Mobile',
                        // anchor    : '-20',
                        // anchor    : null,
                        msgTarget: 'side',
                        items: [
                            {xtype: 'displayfield', value: '('},
                            {xtype: 'numberfield',    name: 'mobilePhone.countryCode', width: 35, allowBlank: true},
                            {xtype: 'displayfield', value: ')'},
                            {xtype: 'numberfield',    name: 'mobilePhone.stdCode', width: 55, allowBlank: true, margins: '0 5 0 0'},
                            {xtype: 'numberfield',    name: 'mobilePhone.number', width: 100, allowBlank: false}
                        ]
                    }
                ]
            },
            {
                xtype: 'fieldset',
                title : 'Other Details',
                collapsible: true,
                bodyStyle: Ext.isIE ? 'padding:0px 0 5px 15px;' : 'padding:10px 15px;',
                style: {
                    "margin-left": "10px", // when you add custom margin in IE 6...
                    "margin-right": Ext.isIE6 ? (Ext.isStrict ? "-10px" : "-13px") : "0"  // you have to adjust for it somewhere else
                },

                layout: 'form',
                columnWidth: .40,
                autoHeight: true,
                anchor: '-20',
                defaults : {
                    allowBlank:false,
                    anchor: '-30'
                },
                items : [
                    {
                        xtype:          'combo',
                        mode:           'local',
                        value:          'A+VE',
                        triggerAction:  'all',
                        forceSelection: true,
                        editable:       false,
                        fieldLabel:     'Blood Group',
                        name:           'bloodGroup',
                        hiddenName:     'bloodGroup',
                        displayField:   'name',
                        valueField:     'value',
                        store:          new Ext.data.JsonStore({
                            fields : ['name', 'value'],
                            data   : [
                                {name : 'A +VE',  value: 'A+'},
                                {name : 'B +VE',  value: 'B+'},
                                {name : 'O +VE',  value: 'O+'},
                                {name : 'AB +VE', value: 'AB'},
                                {name : 'A -VE',  value: 'A-'},
                                {name : 'B -VE',  value: 'B-'},
                                {name : 'O -VE',  value: 'O-'},
                                {name : 'AB -VE', value: 'AB'}
                            ]
                        })
                    }
                ]
            }
        ];
    },

    onSave : function() {
        if (this.isValid()) {
            this.fireEvent('saveDeveloper', this, this.getValues());
        }
    },
    loadFormAfterRender : function() {
        this.load({
            url : 'developers/getDeveloper',
            params : {
                id : this.record.get('id')
            }
        });
    }

});

Ext.reg('developerform', com.dj.project.developer.DeveloperForm);
