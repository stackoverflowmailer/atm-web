Ext.ns('com.dj.project.report');

com.dj.project.report.Report = Ext.extend(Ext.Component, {
    autoEl: {
        tag: 'iframe',
        cls: 'x-hidden',
        src: Ext.SSL_SECURE_URL
    },
    load: function(config) {
        this.getEl().dom.src = config.url + (config.params ? '?' + Ext.urlEncode(config.params) : '');
    }
});

//Ext.reg('ux.report', com.dj.project.report.Report);
