Ext.ns("com.dj.project.developer");

com.dj.project.developer.DeveloperForm = Ext.extend(com.dj.project.base.BaseForm, {
    title : 'Developer',
    // frame : true,
    border : true,
    autoScroll : true,
    bodyStyle : 'background-color: #DFE8F6; padding: 10px',
    layout : 'column',
    labelWidth : 80, // label's width
    defaults : {

        layout : 'form',
        border : false
    },
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
                xtype : 'fieldset',
                columnWidth : 0.5,
                title : 'Name',
                collapsible : false,
                anchor : -30,
                autoHeight : true,
                defaults : {
                    anchor : '-20', // leave room for error icon
                    allowBlank : false
                },
                defaultType : 'textfield',
                items : [
                    {
                        xtype : 'hidden',
                        name : 'id'
                    },
                    {
                        fieldLabel : 'First Name',
                        name : 'name.firstName'
                    },
                    {
                        fieldLabel : 'Middle Name',
                        name : 'name.middleName'
                    },
                    {
                        fieldLabel : 'Last Name',
                        name : 'name.lastName'
                    }
                ]
            },

            {
                // Fieldset in Column 2 - Panel inside

                xtype : 'fieldset',
                title : 'Phone Number',
                autoHeight : true,
                columnWidth : 0.3,
                layout : 'form',
                bodyStyle : 'padding:18px 18px 18px 18px',

                defaults : {
                    anchor : '-20', // leave room for error icon
                    allowBlank : false
                },
                defaultType : 'textfield',
                items : [
                    {
                        fieldLabel : 'Home ',
                        name : 'landPhone.number'

                    },
                    {
                        fieldLabel : 'Mobile ',
                        name : 'mobilePhone.number'
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
