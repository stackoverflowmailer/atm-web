Ext.ns('com.dj.project.developer');

com.dj.project.developer.DeveloperList = Ext.extend(com.dj.project.base.BaseListPanel, {
    url           : 'webresources/developer/developers',
    buildListView : function() {
        return {
            xtype         : 'listview',
            singleSelect  : true,
            store         : this.buildStore(),
            style         : 'background-color: #FFFFFF;',
            columns       : [
                {
                    header    : 'Last Name',
                    dataIndex : 'lastName'
                },
                {
                    header    : 'First Name',
                    dataIndex : 'firstName'
                }
            ]
        };
    },
    buildStore : function() {
         return {
             xtype       : 'jsonstore',
             autoLoad    : this.autoLoadStore || false,
             url         : this.url,
             fields      : [ 'lastName', 'firstName', 'id' ],
             sortInfo    :  {
                 field     : 'lastName',
                 direction : 'ASC'
             }
         };
    }
});
Ext.reg('developerlist', com.dj.project.developer.DeveloperList);