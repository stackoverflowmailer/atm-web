Ext.ns('com.dj.project.developer');

com.dj.project.developer.DeveloperList = Ext.extend(com.dj.project.base.BaseListPanel, {
    url           : 'webresources/developer/developers',
    autoLoadStore : true,
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
            root        : 'data',
            idProperty  : 'id',
            fields      : [
                'id',
                {name: 'firstName', type: 'auto',mapping:'name.firstName'},
                {name: 'lastName',  type: 'auto',mapping:'name.lastName'},
                'doj',
                'bloodGroup'
            ],
            sortInfo    :  {
                field     :'firstName',
                direction : 'ASC'
            }
        };

    }
});
Ext.reg('developerlist', com.dj.project.developer.DeveloperList);