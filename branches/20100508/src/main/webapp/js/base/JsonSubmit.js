Ext.ns('com.dj.project.base');

com.dj.project.base.JsonSubmit = function(form, options) {
    Ext.form.Action.Submit.superclass.constructor.call(this, form, options);
};

Ext.extend(com.dj.project.base.JsonSubmit, Ext.form.Action.Submit, {
    type :'jsonsubmit',

    // private
    run : function() {
        var o = this.options;
        var method = this.getMethod();
        var isPost = method == 'POST';
        if (o.clientValidation === false || this.form.isValid()) {
            Ext.Ajax.request(Ext.apply(this.createCallback(), {
                headers: {
                    'Content-Type':'application/json; charset=UTF-8'
                },
                url:this.getUrl(!isPost),
                method: method,
                params:isPost ? this.getParams(o) : null,
                isUpload: this.form.fileUpload
            }));
        } else if (o.clientValidation !== false) {
            this.failureType = Ext.form.Action.CLIENT_INVALID;
            this.form.afterAction(this, false);
        }
    },

    getParams: function(o) {
        return Ext.util.JSON.encode(o.params.data);
    },

    // private
    success : function(response){
        var result = this.processResponse(response);
        if(result === true || result.success){
            this.form.afterAction(this, true);
            return;
        }
        if(result.errors){
            this.form.markInvalid(result.errors);
        }
        this.failureType = Ext.form.Action.SERVER_INVALID;
        this.form.afterAction(this, false);
    },

    // private
    handleResponse : function(response){
        if(this.form.errorReader){
            var rs = this.form.errorReader.read(response);
            var errors = [];
            if(rs.records){
                for(var i = 0, len = rs.records.length; i < len; i++) {
                    var r = rs.records[i];
                    errors[i] = r.data;
                }
            }
            if(errors.length < 1){
                errors = null;
            }
            return {
                success : rs.success,
                errors : errors
            };
        }
        return Ext.decode(response.responseText);
    }
});
Ext.form.Action.ACTION_TYPES.jsonsubmit = com.dj.project.base.JsonSubmit;