com.dj.project.allocation.AllocationManager = Ext.extend(com.dj.project.base.BaseManager, {
    border : false,
    layout : {
        type  : 'hbox',
        align : 'stretch'
    },
    localMsg : {

    },
    initComponent : function() {
        this.items = [
            this.buildAllocationGridPanel()
        ];
        com.dj.project.allocation.AllocationManager.superclass.initComponent.call(this);
    },


    buildAllocationGridPanel : function() {

        return {
            xtype     : 'allocationgridpanel',
            itemId    : 'allocationGridPanel',
            width     : 190,
            border    : false,
            style     : 'border-right: 1px solid #99BBE8;',
            title     : 'Developers',
            listeners : {
                scope  : this,
                click  : this.onAllocationGridPanelClick
            }
        };
    },
    onAllocationGridPanelClick : function() {
        var record = this.getComponent('allocationGridPanel').getSelected();
        //console.log(record);
        var msg = String.format(
                this.globalMsg.fetchingDataFor,
                record.get('lastName'),
                record.get('firstName')
                );

        Ext.getBody().mask(msg, 'x-mask-loading');

    },
    onGenerateReport : function() {
        var el = Ext.getDom('reportFrame');
        if (el) {
            Ext.removeNode(el);
        }
        var report = new com.dj.project.report.Report({
            renderTo: Ext.getBody(),
            id: 'reportFrame'
        });
        report.load({
            //method: 'POST', ? why  'POST' is not working
            url: 'webresources/developer/report',
            params: {
                task: 'csv',
                query: 'query'
            }
        });

    },

    cleanSlate : function() {
        this.getComponent('allocationGridPanel').refreshView();
       
    }


});

Ext.reg('developermanager', com.dj.project.allocation.AllocationManager);
