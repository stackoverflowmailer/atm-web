com.dj.project.base.ButtonPanel = Ext.extend(Ext.Panel, {
    layout:'table',
    defaultType: 'button',
    baseCls: 'x-plain',
    cls: 'btn-panel',
    renderTo : 'docbody',
    menu: undefined,
    split: false,

    layoutConfig: {
        columns:3
    },

    constructor: function(desc, buttons) {
        // apply test configs
        for (var i = 0, b; b = buttons[i]; i++) {
            b.menu = this.menu;
            b.enableToggle = this.enableToggle;
            b.split = this.split;
            b.arrowAlign = this.arrowAlign;
        }
        var items = [
            {
                xtype: 'box',
                autoEl: {tag: 'h3', html: desc, style:"padding:15px 0 3px;"},
                colspan: 3
            }
        ].concat(buttons);

        ButtonPanel.superclass.constructor.call(this, {
            items: items
        });
    }
});


//Ext.reg('buttonpanel', com.dj.project.base.ButtonPanel);
