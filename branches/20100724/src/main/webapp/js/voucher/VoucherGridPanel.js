com.dj.project.voucher.VoucherGridPanel = Ext.extend(Ext.grid.GridPanel, {
    url           : 'voucher/listForVoucher',
    viewConfig    : { forceFit : true },
    columns       : [
        {
            header    : 'Voucher No',
            dataIndex : 'voucherNo',
            sortable  : true
        },
        {
            header    : 'Voucher Date',
            dataIndex : 'voucherDate',
            sortable  : true
        },
        {
            header    : 'Issued To',
            dataIndex : 'issuedTo',
            sortable  : true
        }
    ],
    initComponent : function() {
        this.store = this.buildStore();
        com.dj.project.voucher.VoucherGridPanel.superclass.initComponent.call(this);
    },
    buildStore : function() {
        return  {
            xtype    : 'jsonstore',
            url      : this.url,
            autoLoad : false,
            fields   : [
                'id', 'voucherNo', 'voucherDate', 'issueDate',
                'issueTo', 'voucherAmt', 'Job'
            ],
            sortInfo : {
                field : 'voucherNo',
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

Ext.reg('vouchergridpanel', com.dj.project.voucher.VoucherGridPanel);