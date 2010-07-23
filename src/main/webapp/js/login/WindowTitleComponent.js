Ext.namespace('com.dj.project.login');

com.dj.project.login.WindowTitleComponent = Ext.extend(Ext.BoxComponent, {
    /** Properties **/
    _textEl : undefined
    ,
    _labelEl : undefined,

    /** Generate html tags **/
    autoEl : {
        tag : 'div',
        id  : 'com.dj.project.login.window-title-component-id',
        children : [{
            tag      : 'div',
            cls      : 'window-title-container',
            children : [{
                tag  : 'div',
                cls  :  'window-title-label',
                html : ''
            }, {
                tag      : 'div',
                cls      : 'window-title-outer',
                children : [{
                    tag  : 'div',
                    cls  : 'window-title-inner',
                    html : '&nbsp;'
                }]
            }]
        }, {
            tag  : 'div',
            html : '',
            cls  : 'window-title-description'
        }]
    },
    setLabel : function(label){
        this.labelText(label);
        if (this.rendered) {
            this._labelEl.update(label);
        }
    },
    setText : function(text)
    {
        this.text = text;

        if (this.rendered) {
            if (this.text != undefined) {
                this._textEl.update(this.text);
                this._textEl.setDisplayed(true);
            } else {
                this._textEl.setDisplayed(false);
            }
        }
    },

    /**
     * Override the BoxComponent's onRender() and provide render
     * according to our needs.
     *
     * @ct -
     * @position - 
     */
    onRender : function(ct, position)
    {
        com.dj.project.login.WindowTitleComponent.superclass.onRender.call(this, ct, position);

        this._labelEl = new Ext.Element(this.el.dom.firstChild.firstChild);
        this._textEl  = new Ext.Element(this.el.dom.lastChild);

        if (this.labelText) {
            this._labelEl.update(this.labelText);
        }

        if (this.text != undefined) {
            this._textEl.update(this.text);
        } else {
            this._textEl.setDisplayed(false);
        }

        if (this.imageClass) {
            this._textEl.addClass(this.imageClass);
        }

    }
});
