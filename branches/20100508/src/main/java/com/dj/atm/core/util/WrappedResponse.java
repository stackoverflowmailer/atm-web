package com.dj.atm.core.util;

/**
 * This class wraps XHR output in a way Ext Js expects.
 * For eg. Ext Js expects a property 'success' in all form submission responses
 * if this property is missing, the user is shown a message that the processing
 * is failed.
 * <p/>
 * Including the above mentioned 'success' property all the entities doesn't
 * make sense. So the normal response is wrapped in this class.
 *
 * @author Script Runner
 * @since 0.0.1
 */
public class WrappedResponse<T> {

    private boolean success;

    private T data;

    public WrappedResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
