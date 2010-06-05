Ext.Ajax.defaultHeaders = {
    'Powered-By': 'ATM-WEB'
};

//Ext.Ajax.on('beforerequest', this.showSpinner, this);

Ext.Ajax.on('requestcomplete', function(connection, response, object) {
    if (response.status == 401) {
        Ext.getBody().unmask();
        Ext.Msg.show({
            title:'Session Expired',
            msg: 'Your session has expired.' +
                    '</br></br>Press <b>Yes</b> to navigate to login page,' +
                    'or <b>No</b> stay on the current page',
            buttons: Ext.Msg.YESNO,
            fn: function(btn, text, option) {
                if (btn == 'yes') {
                    window.location.reload(false);
                }
            },
            animEl: 'logout-btn',
            icon: Ext.MessageBox.QUESTION
        });
        /*window.onerror = function(msg){
            return true;
        }*/
    }
}, this);

/*
Ext.Ajax.on('requestexception', function(connection, response, object) {
    console.dir(response);
}, this);*/
