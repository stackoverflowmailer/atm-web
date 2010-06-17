com.dj.project.voucher.VoucherList = Ext.extend(com.dj.project.base.BaseListPanel, {
    url           : 'webresources/voucher/vouchers',
    autoLoadStore : true,
    buildListView : function() {
        return {
            xtype         : 'listview',
            singleSelect  : true,
            store         : this.buildStore(),
            style         : 'background-color: #FFFFFF;',
            columns       : [

                {
                    header    : 'Voucher Date',
                    dataIndex : 'voucherDate'
                },

                {
                    header    : 'Issued To',
                    dataIndex : 'issueTo'
                }
            ]
        };
    },
    buildStore : function() {
        return {
            xtype       : 'jsonstore',
            autoLoad    : this.autoLoadStore || false,
            url         : this.url,
            root        : 'data',
            idProperty  : 'id',
            fields      : [
                'id',

                {
                    name: 'voucherDate',
                    type: 'auto',
                    mapping:'voucherDate'
                },
                {
                    name: 'issueTo',
                    type: 'auto',
                    mapping:'issueTo'
                }
            ],
            sortInfo    :  {
                field     :'voucherDate',
                direction : 'ASC'
            }
        };

    }
});
Ext.reg('voucherlist', com.dj.project.voucher.VoucherList);