package me.riddhimanadib.formmaster.model;

import android.os.Parcel;

import java.io.Serializable;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormTextViewElement<T extends Serializable> extends BaseFormElement<T> {
    public static FormTextViewElement<String> createInstance() {
        return new FormTextViewElement<>();
    }

    public static <T extends Serializable> FormTextViewElement<T> createGenericInstance() { return new FormTextViewElement<T>(); }

    @Override
    public int getType() {
        return TYPE_TEXTVIEW;
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

    public FormTextViewElement() {
    }

    protected FormTextViewElement(Parcel in) {
        super(in);
    }

    public static final Creator<FormTextViewElement> CREATOR = new Creator<FormTextViewElement>() {
        @Override
        public FormTextViewElement createFromParcel(Parcel source) {
            return new FormTextViewElement(source);
        }

        @Override
        public FormTextViewElement[] newArray(int size) {
            return new FormTextViewElement[size];
        }
    };
}