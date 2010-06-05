com.dj.project.user.UserList = Ext.extend(com.dj.project.base.BaseListPanel, {
    url           : 'webresources/user/users',
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
                {name: 'lastName',  type: 'auto',mapping:'name.lastName'}
            ],
            sortInfo    :  {
                field     :'firstName',
                direction : 'ASC'
            }
        };

    }
});
Ext.reg('userlist', com.dj.project.user.UserList);