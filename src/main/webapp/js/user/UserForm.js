com.dj.project.user.UserForm = Ext.extend(com.dj.project.base.AbstractForm, {
    title      : 'User',
    layout     : 'column',
    autoHeight : true,
    bodyStyle : 'background-color: #DFE8F6; padding: 10px',

    initComponent : function() {
        Ext.applyIf(this, {
            tbar : this.buildToolbar(),
            items : this.buildFormItems()
        });
        com.dj.project.user.UserForm.superclass.initComponent.call(this);
        this.addEvents('save', 'reset');
    },
    buildToolbar : function() {
        return [
            {
                id:'userSaveBtn',
                text : 'Save',
                iconCls : 'icon-disk',
                scope : this,
                handler : this.onSave
            },
            '-',
            {
                id:'userResetBtn',
                text : 'Reset',
                iconCls : 'icon-arrow_undo',
                scope : this,
                handler : this.onReset
            }
        ]
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
                                allowBlank: true
                            },
                            {
                                xtype: 'textfield',
                                flex : 1,
                                name : 'name.lastName',
                                fieldLabel: 'Last',
                                allowBlank: false
                            }
                        ]
                    }
                ]
            }
        ]
    },

    onSave : function() {
        if (this.isValid()) {
            this.fireEvent('save', this, this.getValues());
        }
    },
    onReset : function() {
        this.fireEvent('reset', this, this.getValues());
    }

});

Ext.reg('userform', com.dj.project.user.UserForm);