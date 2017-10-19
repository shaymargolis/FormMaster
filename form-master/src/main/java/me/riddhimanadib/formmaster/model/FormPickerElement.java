package me.riddhimanadib.formmaster.model;

import android.os.Parcel;

import java.io.Serializable;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormPickerElement<T extends Serializable> extends BaseFormElement<T> {
    public static FormPickerElement<String> createInstance() {
        return new FormPickerElement<>();
    }

    public static <T extends Serializable> FormPickerElement<T> createGenericInstance() { return new FormPickerElement<T>(); }

    /**
     * Parcelable boilerplate
     */
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    public FormPickerElement() {
    }

    protected FormPickerElement(Parcel in) {
        super(in);
    }

}
