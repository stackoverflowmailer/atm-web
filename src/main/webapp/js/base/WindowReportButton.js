com.dj.project.window.report.QuickReportButton = Ext.extend(Ext.Button, {

    text:'Report **',
    tooltip:'Quick Report',
    iconCls:'icon-lightning',
    fileType:'pdf',
    url  :'',

    initComponent:function() {
        this.plugins = [
            new com.dj.project.window.report.plugin.QuickReport({
                url : this.url,
                fileType:this.fileType
            })
        ];
        com.dj.project.window.report.QuickReportButton.superclass.initComponent.apply(this, arguments);
    }
});
Ext.reg('reportButton', com.dj.project.window.report.QuickReportButton);


Ext.ns('com.dj.project.window.report.plugin');
com.dj.project.window.report.plugin.QuickReport = Ext.extend(Object, {

    fileType:'html',

    constructor:function(cfg) {
        Ext.apply(this, cfg);
    },
    init:function(b) {
        b.handler = this.generateReport;
        b.scope = this;

    },
    generateReport:function() {

        var win = new Ext.Window({
            width:750,
            height:500,
            autoScroll:false,
            html:'',
            modal:true,
            maximizable:true,
            cls:'x-window-body-report',
            title:String.format('{0} Quick Report', 'ATM Web')
        });
        win.on('close', function() {
            if (Ext.isIE) {
                win.body.dom.firstChild.src = "javascript:false";
            }
        }, win);


        // Magic from doFormUpload()
        var id = Ext.id();
        var frame = document.createElement('iframe');

        frame.id = id;
        frame.name = id;
        frame.frameBorder = '0';
        frame.width = '100%';
        frame.height = '100%';
        frame.src = Ext.isIE ? Ext.SSL_SECURE_URL : "javascript:;";

        win.show();
        win.body.appendChild(frame);

        // Seems to be workaround for IE having name readonly.
        if (Ext.isIE) {
            document.frames[id].name = id;
        }



        var form = new Ext.FormPanel({
            //url:String.format('{0}/report.{1}', this.url, this.fileType),
      url:String.format('{0}', this.url),
            renderTo:Ext.getBody(),
            standardSubmit:true,
            method:'POST',
            defaultType:'hidden'/*,
            items:[].concat(formItems)*/
        });

        form.getForm().getEl().dom.action = form.url;
        form.getForm().getEl().dom.target = id;


        // http://extjs.com/forum/showthread.php?t=31461
        // display/none seems to force a reload of Adobe Acrobat Reader in all FF <3.1

       /* if (!Ext.isGecko) {
         var mask = new Ext.LoadMask(win.id, {msg:"Loading..."});
         mask.show();
         }

         Ext.EventManager.on(frame, 'load', function() {
         if (mask !== undefined) {
         mask.hide();
         }
         form.destroy();
         });*/

        Ext.emptyFn.defer(200); // frame on ready?
        form.getForm().submit();


    }
});



