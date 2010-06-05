com.dj.project.developer.DevloperGridPanel = Ext.extend(Ext.grid.GridPanel, {
    url           : 'students/listForStandard',
    viewConfig    : { forceFit : true },
    columns       : [
        {
            header    : 'Last Name',
            dataIndex : 'lastName',
            sortable  : true
        },
        {
            header    : 'First Name',
            dataIndex : 'firstName',
            sortable  : true
        },
        {
            header    : 'Date of Joining',
            dataIndex : 'doj',
            sortable  : true
        },
        {
            header    : 'Band',
            dataIndex : 'band',
            sortable  : true
        }
    ],
    initComponent : function() {
        this.store = this.buildStore();
        com.dj.project.developer.DevloperGridPanel.superclass.initComponent.call(this);
    },
    buildStore : function() {
        return  {
            xtype    : 'jsonstore',
            url      : this.url,
            autoLoad : false,
            fields   : [
                'id', 'lastName', 'firstName', 'hName',
                'dob', 'rank',
            ],
            sortInfo : {
                field : 'lastName',
                dir   : 'ASC'
            }
        };
    },
    add : function(r) {
        var store = this.store;
        var sortInfo = store.sortInfo;

        if (Ext.isArray(r)) {
            Ext.each(r, function(rObj, ind) {
                if (! (rObj instanceof Ext.data.Record)) {
                    r[ind] = new this.store.recordType(rObj);
                }
            });
        }
        else if (Ext.isObject(r) && ! (r instanceof Ext.data.Record)) {
            r = new this.store.recordType(r);
        }

        store.add(r);
        store.sort(sortInfo.field, sortInfo.direction);
    },
    loadData : function(d) {
        return this.store.loadData(d);
    },
    load : function(o) {
        return this.store.load(o);
    },
    removeAll : function() {
        return this.store.removeAll();
    },

    remove    : function(r) {
        return this.store.remove(r);
    },
    getSelected : function() {
        return this.selModel.getSelections();
    }
});

Ext.reg('developergridpanel', com.dj.project.developer.DevloperGridPanel);