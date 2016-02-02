package uk.ivanc.archimvvm.bindingadapter;

import android.databinding.BaseObservable;

import java.util.Objects;

/**
 * Created by PanHuaChao on 2016/1/14.
 * Description:
 */
public class BindableString extends BaseObservable {
    private String value="";
    public String get() {
        return value != null ? value : "";
    }
    public void set(String _value) {
        if (this.value.equals(_value)==false) {
            this.value = _value;
            notifyChange();
        }
    }
    public boolean isEmpty() {
        return value == null || value.isEmpty();
    }
}
