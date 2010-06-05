com.dj.project.base.BaseManager = Ext.extend(Ext.Panel, {
    globalMsg : {
        immediateChanges : 'Warning! Changes are <span style="color: red;">immediate</span>.',
        errorsInForm     : 'There are errors in the form. Please correct and try again.',
        developerSavedSuccess  : 'Saved {0}, {1} successfully.',
        fetchingDataFor  : 'Fetching data for {0}, {1}',
        couldNotLoadData : 'Could not load data for {0}, {1}!',
        saving           : 'Saving {0}, {1}...',
        errorSavingData  : 'There was an error saving the form.',
        deletingDeveloper : 'Deleting developer {0}, {1}...',
        deleteEmpConfirm : 'Are you sure you want to delete developer {0}, {1}?',
        deleteEmpSuccess : 'Developer {0}, {1} was deleted successfully.',
        deleteEmpFailure : 'Developer {0}, {1} was not deleted due to a failure.'
    },
    clearMask:function() {
        Ext.getBody().unmask();
    },
    

    convertDate : function(v, record) {
        if (v) {
            return new Date(v);
        }
    },
    onClientValidation : function(formPanel, valid){
        //console.debug('Do something meaningful in subclass, Form Valid : ' + valid);
    }
});