/*
 Ext.override(Ext.form.CompositeField, {
 bubble : Ext.Container.prototype.bubble,
 cascade : Ext.Container.prototype.cascade,
 findById : Ext.Container.prototype.findById,
 findByType : Ext.Container.prototype.findByType,
 find : Ext.Container.prototype.find,
 findBy : Ext.Container.prototype.findBy,
 get : Ext.Container.prototype.get
 });
 */


function getFieldValuesAsObject(form) {
    //var formObject = form.getFieldValues();
    var obj = {};
    var formItems = form.items.items;
    var propLen = formItems.length;
    for (var i = 0; i < propLen; i++) {
        if (formItems[i].xtype == "compositefield") {
            var compFieldItem = formItems[i].items.items;
            var compPropLen = compFieldItem.length;
            for (var j = 0; j < compPropLen; j++) {
                if (compFieldItem[j].xtype !== "displayfield") {
                    var val = compFieldItem[j].getValue();
                    var name = compFieldItem[j].getName();
                    if (isQualifiedName(name)) {
                        createObjectStructure(name, obj, val);
                    }
                }
            }
        } else {
            if (formItems[i].xtype !== "displayfield") {
                var val = formItems[i].value;
                var name = formItems[i].name;
                obj[name] = val;
            }
        }
    }
    return obj;
}

function isQualifiedName(name) {
    return !(name.indexOf(".") === -1);
}

function createObjectStructure(name, obj, val) {
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
    return o;
}
