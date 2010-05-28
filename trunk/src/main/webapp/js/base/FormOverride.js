


function getFieldValuesAsObject(form) {
    //var formObject = form.getFieldValues();
    var obj = {};
    var fi = form.items.items;
    var pl = fi.length;
    for (var i = 0; i < pl; i++) {
        if (fi[i].xtype == "compositefield") {
            var cfi = fi[i].items.items;
            var cpl = cfi.length;
            for (var j = 0; j < cpl; j++) {
                if (cfi[j].xtype !== "displayfield") {
                    var val = cfi[j].getValue();
                    var name = cfi[j].getName();
                    if (isQualifiedName(name)) {
                        createObjectStructure(name, obj, val);
                    }
                }
            }
        } else {
            if (fi[i].xtype !== "displayfield") {
                var val = fi[i].value;
                var name = fi[i].name;
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
