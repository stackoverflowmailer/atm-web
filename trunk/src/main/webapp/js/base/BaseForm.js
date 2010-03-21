Ext.ns('com.dj.project.base');

com.dj.project.base.BaseForm = Ext.extend(Ext.form.FormPanel, {    
    constructor : function(config) {
        config = config || {};
        Ext.applyIf(config, {
            trackResetOnLoad : true
        });
        com.dj.project.base.BaseForm.superclass.constructor.call(this, config)
    },

    getValues : function() {
        //if (!this.getCustomFormValues) {
        return this.getForm().getValues();
        //}
        //return this.getCustomFormValues();
    },
    isValid : function() {
        return this.getForm().isValid();
    },
    clearForm : function() {
        var values = this.getForm().getValues();
        var clearValues = {};

        for (var vName in values) {
            clearValues[vName] = '';
        }

        this.getForm().setValues(clearValues);
        this.data = null;
    },
    reset : function() {
        this.getForm().reset();
    },

    loadData : function(data) {
        if (data) {
            this.data = data;
            this.getForm().setValues(data);
        }
        else {
            this.clearForm();
        }
    },
    setValues : function(o) {
        return this.getForm().setValues(o || {});
    }
});