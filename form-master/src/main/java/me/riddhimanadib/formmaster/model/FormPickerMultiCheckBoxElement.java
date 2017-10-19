package me.riddhimanadib.formmaster.model;

import android.os.Parcel;

import java.io.Serializable;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormPickerMultiCheckBoxElement<T extends Serializable> extends FormPickerElement<T> {
    public static FormPickerMultiCheckBoxElement<String> createInstance() {
        return new FormPickerMultiCheckBoxElement<>();
    }

    public static <T extends Serializable> FormPickerMultiCheckBoxElement<T> createGenericInstance() { return new FormPickerMultiCheckBoxElement<T>(); }

    @Override
    public int getType() {
        return TYPE_PICKER_MULTI_CHECKBOX;
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

    public FormPickerMultiCheckBoxElement() {
    }

    protected FormPickerMultiCheckBoxElement(Parcel in) {
        super(in);
    }

    public static final Creator<FormPickerMultiCheckBoxElement> CREATOR = new Creator<FormPickerMultiCheckBoxElement>() {
        @Override
        public FormPickerMultiCheckBoxElement createFromParcel(Parcel source) {
            return new FormPickerMultiCheckBoxElement(source);
        }

        @Override
        public FormPickerMultiCheckBoxElement[] newArray(int size) {
            return new FormPickerMultiCheckBoxElement[size];
        }
    };
}
