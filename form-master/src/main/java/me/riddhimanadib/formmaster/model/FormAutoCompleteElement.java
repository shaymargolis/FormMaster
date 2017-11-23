package me.riddhimanadib.formmaster.model;

import android.os.Parcel;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormAutoCompleteElement<T extends Serializable> extends BaseFormElement<T> {
    /**
     * Because the written text in the EditText not always matched the
     * Values in options array, we keep the TypedString: what the user typed.
     */
    private String mTypedString;
    /**
     * To determine what strings can be typed, we keep a set of the options
     * converted to strings
     */
    private HashSet<String> mStringOptions;

    public static FormAutoCompleteElement<String> createInstance() {
        return new FormAutoCompleteElement<>();
    }

    public static <T extends Serializable> FormAutoCompleteElement<T> createGenericInstance() { return new FormAutoCompleteElement<T>(); }

    @Override
    public int getType() {
        return TYPE_EDITTEXT_AUTOCOMPLETE;
    }

    @Override
    public BaseFormElement<T> setValue(T mValue) {
        mTypedString = mValue.toString();

        return super.setValue(mValue);
    }

    @Override
    public FormAutoCompleteElement<T> setOptions(List<T> mOptions) {
        super.setOptions(mOptions);
        mStringOptions = new HashSet<>();
        for (T i : mOptions) {
            mStringOptions.add(i.toString());
        }
        return this;
    }

    public Set<String> getStringOptions() {
        return (mStringOptions != null) ? mStringOptions : new HashSet<String>();
    }

    public String getTypedString() {
        return this.mTypedString;
    }

    public void setTypedString(String typedString) {
        this.mTypedString = typedString;
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
        dest.writeString(this.mTypedString);
        dest.writeSerializable(this.mStringOptions);
    }

    public FormAutoCompleteElement() {
    }

    protected FormAutoCompleteElement(Parcel in) {
        super(in);
        this.mTypedString = in.readString();
        this.mStringOptions = (HashSet<String>)in.readSerializable();
    }

    public static final Creator<FormAutoCompleteElement> CREATOR = new Creator<FormAutoCompleteElement>() {
        @Override
        public FormAutoCompleteElement createFromParcel(Parcel source) {
            return new FormAutoCompleteElement(source);
        }

        @Override
        public FormAutoCompleteElement[] newArray(int size) {
            return new FormAutoCompleteElement[size];
        }
    };
}
