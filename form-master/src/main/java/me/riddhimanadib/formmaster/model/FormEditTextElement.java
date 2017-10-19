package me.riddhimanadib.formmaster.model;

import android.os.Parcel;

import java.io.Serializable;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormEditTextElement<T extends Serializable> extends BaseFormElement<T> {
    public static FormEditTextElement<String> createInstance() {
        return new FormEditTextElement<>();
    }

    public static <T extends Serializable> FormEditTextElement<T> createGenericInstance() { return new FormEditTextElement<T>(); }

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

    public FormEditTextElement() {
    }

    protected FormEditTextElement(Parcel in) {
        super(in);
    }

    public static final Creator<FormEditTextElement> CREATOR = new Creator<FormEditTextElement>() {
        @Override
        public FormEditTextElement createFromParcel(Parcel source) {
            return new FormEditTextElement(source);
        }

        @Override
        public FormEditTextElement[] newArray(int size) {
            return new FormEditTextElement[size];
        }
    };
}
