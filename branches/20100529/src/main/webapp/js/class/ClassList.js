Ext.ns('com.dj.project.team');

com.dj.project.team.TeamList = Ext.extend(com.dj.project.base.BaseListPanel, {
    url : 'class/getList',
    buildListView : function() {
        return {
            xtype         : 'listview',
            singleSelect  : true,
            store         : this.buildStore(),
            style         : 'background-color: #FFFFFF;',
            columns       : [
                {
                    header    : 'Class/Division Name',
                    dataIndex : 'name'
                }
            ]
        };
    },

    buildStore : function() {
        return  {
            xtype    : 'jsonstore',
            autoLoad : this.autoLoadStore,
            url      : this.url,
            fields   : [ 'name', 'id', 'description', 'dateActive' ],
            sortInfo : {
                field : 'name',
                dir   : 'ASC'
            }
        };
    }

});
Ext.reg('classlist', com.dj.project.team.TeamList);
