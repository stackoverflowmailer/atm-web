Ext.Ajax.defaultHeaders = {
    'Powered-By': 'ATM-WEB'
};

// Example: show a spinner during all Ajax requests
//Ext.Ajax.on('beforerequest', this.showSpinner, this);
Ext.Ajax.on('requestcomplete', function(request, response, object) {
    //console.dir(request);
    //console.dir(response);
    //console.dir(object);
    if (response.responseText == 'NOT_A_VALID_USER') {
        Ext.getBody().unmask();
        Ext.Msg.show({
            title:'Session Expiry',
            msg: 'Your session expired. Please press Yes to navigate to login page, press No stay on the current page',
            buttons: Ext.Msg.YESNO,
            fn: function(){
                
            },
            animEl: 'elId',
            icon: Ext.MessageBox.QUESTION
        });
    }

}, this);
//Ext.Ajax.on('requestexception', this.hideSpinner, this);