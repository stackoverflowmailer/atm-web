Ext.ns('com.dj.project.base');

com.dj.project.base.BaseListPanel = Ext.extend(Ext.Panel, {
    layout  : 'fit',
    initComponent : function() {
        this.items = this.buildListView();

        com.dj.project.base.BaseListPanel.superclass.initComponent.call(this);

        // this component has to handle the 'click' events from the 'view' and
        // 'load' events from the associated 'store'
        this.relayEvents(this.getView(), ['click']);
        this.relayEvents(this.getStore(), ['load']);
    },
    getView : function() {
        return this.items.items[0];
    },

    buildListView : function() {
        return {};
    },

    buildStore : function() {
        return { xtype : 'jsonstore' };
    },

    refreshView : function() {
        this.getView().store.reload();
    },

    clearView : function() {
        this.getStore().removeAll();
    },

    createAndSelectRecord : function(o) {
        var view = this.getView();
        var record = new view.store.recordType(o);

        view.store.addSorted(record);

        var index = view.store.indexOf(record);
        view.select(index);

        return record;
    },

    clearSelections : function() {
        return this.getView().clearSelections();
    },

    getStore : function() {
        return this.getView().store;
    },

    getSelectedRecords : function() {
        return this.getView().getSelectedRecords();
    },
    getSelected : function() {
        return this.getSelectedRecords()[0];
    },

    selectById : function(id) {
        var view = this.getView();
        id = id || false;
        if (id) {
            var ind = view.store.find('id', id);
            view.select(ind);
        }
    },

    loadStoreByParams : function(params) {
        params = params || {};

        this.getStore().load({params:params});
    }

});