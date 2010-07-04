
/**
 * @class com.dj.project.base.Shortcuts
 * @extends Ext.Container
 * Container for application shortcuts
 */
com.dj.project.base.Shortcuts = Ext.extend(Ext.Container, {
  /**
   * Creates the HTML markup for the shortcuts container
   * @param {Ext.BoxComponent} ct The container into which this container will be rendered
   */
  onRender: function(ct) {
    this.el = ct.createChild({
      cls: 'x-shortcuts-wrapper',
      children: [
        {cls: 'x-shortcuts-header'},
        {cls: 'x-shortcuts'},
        {cls: 'x-shortcuts-footer'}
        /*,{cls: 'x-shortcuts-add', tag: 'button'}*/
      ]
    });

    com.dj.project.base.Shortcuts.superclass.onRender.apply(this, arguments);

    this.shortcutsHolder = this.el.child('.x-shortcuts');
  },

  //tells the container which element to add child components into
  getLayoutTarget: function() {
    return this.shortcutsHolder;
  }
});

/**
 * @class com.dj.project.base.ShortcutHolder
 * @extends Ext.Component
 * Clickable shortcut class which renders some HTML for a standard application shortcut
 */
com.dj.project.base.ShortcutHolder = function(config) {
    var config = config || {};

    //apply some defaults
    /*Ext.applyIf(config, {
        text: 'Shortcut Name',
        icon: 'default_shortcut.gif'
    });*/

    //call the super class constructor
    com.dj.project.base.ShortcutHolder.superclass.constructor.call(this, config);
};
Ext.extend(com.dj.project.base.ShortcutHolder, Ext.Container, {
    onRender: function(ct) {
        this.el = ct.createChild({
            cls: 'x-shortcut',
            children: [
                {
                    tag: 'img',
                    src: this.initialConfig.icon
                },
                {
                    tag:  'span',
                    clazz: 'x-shortcut-style',
                    html: this.initialConfig.text
                }
            ]
        });

        com.dj.project.base.ShortcutHolder.superclass.onRender.apply(this, arguments);
    }
});

Ext.reg('shortcut', com.dj.project.base.ShortcutHolder);