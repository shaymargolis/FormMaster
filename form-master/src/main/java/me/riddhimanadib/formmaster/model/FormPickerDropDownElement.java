package me.riddhimanadib.formmaster.model;

import android.os.Parcel;

import java.io.Serializable;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormPickerDropDownElement<T extends Serializable> extends FormPickerElement<T> {
    public static FormPickerDropDownElement<String> createInstance() {
        return new FormPickerDropDownElement<>();
    }

    public static <T extends Serializable> FormPickerDropDownElement<T> createGenericInstance() { return new FormPickerDropDownElement<T>(); }

    @Override
    public int getType() {
        return TYPE_PICKER_DROP_DOWN;
    }

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

    public FormPickerDropDownElement() {
    }

    protected FormPickerDropDownElement(Parcel in) {
        super(in);
    }

    public static final Creator<FormPickerDropDownElement> CREATOR = new Creator<FormPickerDropDownElement>() {
        @Override
        public FormPickerDropDownElement createFromParcel(Parcel source) {
            return new FormPickerDropDownElement(source);
        }

        @Override
        public FormPickerDropDownElement[] newArray(int size) {
            return new FormPickerDropDownElement[size];
        }
    };
}
