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
                        xtype: 'compositefield',
                        fieldLabel: 'Name',
                        // anchor    : '-20',
                        // anchor    : null,
                        msgTarget: 'side',
                        items: [
                            {
                                xtype: 'displayfield',
                                value: 'First'
                            },
                            {
                                flex : 1,
                                xtype: 'textfield',
                                name: 'name.firstName',
                                allowBlank: false
                            },
                            {
                                xtype: 'displayfield',
                                value: 'Last'
                            },
                            {
                                flex : 1,
                                xtype: 'textfield',
                                name: 'name.lastName',
                                allowBlank: false
                            }
                        ]
                    },
                    {
                        xtype: 'textfield',
                        flex : 1,
                        name : 'username',
                        hiddenName : 'username',
                        fieldLabel: 'User Name',
                        allowBlank: false
                    },
                    {
                        inputType  : 'password',
                        flex : 1,
                        name : 'password',
                        fieldLabel: 'Password',
                        allowBlank: false
                    },
                    {
                        inputType  : 'password',
                        flex : 1,
                        name : 'repassword',
                        fieldLabel: 'Re-enter password',
                        allowBlank : false,
                        clientOnly : true
                    },
                    {
                        xtype     : 'datefield',
                        name      : 'creationDate',
                        format : 'd/m/Y',
                        fieldLabel: 'Date'
                    }
                ]

            }
        ]

    },

    onSave : function() {
        if (this.isValid()) {
            this.fireEvent('save', this, this.getValues());
        }
    }
    ,
    onReset : function() {
        this.fireEvent('reset', this, this.getValues());
    }

})
        ;

Ext.reg('userform', com.dj.project.user.UserForm);