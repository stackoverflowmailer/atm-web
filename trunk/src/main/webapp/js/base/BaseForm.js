com.dj.project.base.AbstractForm = Ext.extend(Ext.form.FormPanel, {

    monitorValid : true,
    monitorPoll : 900,

    constructor : function(config) {
        config = config || {};
        Ext.applyIf(config, {
            trackResetOnLoad : true
        });
        com.dj.project.base.AbstractForm.superclass.constructor.call(this, config)
    },

    getValues : function() {
        return this.getForm().getValues();
    },

    //----------------------------Newly added section --------------------------------------

    getFieldValuesAsObject : function () {
        var obj = {};
        var fi = this.getForm().items.items;
        var pl = fi.length;
        for (var i = 0; i < pl; i++) {
            var mf = fi[i];
            if (mf.xtype == "compositefield") {
                var cfi = mf.items.items;
                var cpl = cfi.length;
                for (var j = 0; j < cpl; j++) {
                    var f =  cfi[j];
                    if (f.xtype !== "displayfield") {
                        var val = f.getValue();
                        var name = f.getName();
                        if (this.isQualifiedName(name)) {
                            this.createObjectStructure(name, obj, val);
                        } else {
                            obj[name] = val;
                        }
                    }
                }
            } else {
                if (mf.xtype !== "displayfield" && mf.clientOnly !== true) {
                    var val = mf.getValue();
                    var name = mf.getName();
                    obj[name] = val;
                }
            }
        }
        return obj;
    },

    isQualifiedName : function(name) {
        return !(name.indexOf(".") === -1);
    },

    createObjectStructure : function (name, obj, val) {
        var o, d;
        Ext.each(name, function(v) {
            d = v.split(".");
            o = obj[d[0]] = obj[d[0]] || {};
            Ext.each(d.slice(1), function(v2) {
                if (d.indexOf(v2) === (d.length - 1)) {
                    o[v2] = val;
                } else {
                    o = o[v2] = o[v2] || {};
                }
            });
        });
        //return o;
    },

    //---------------------------End of newly added section --------------------------------

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