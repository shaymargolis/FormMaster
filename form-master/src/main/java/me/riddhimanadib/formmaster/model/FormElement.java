package me.riddhimanadib.formmaster.model;

import android.os.Parcel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adib on 16-Apr-17.
 */

public class FormElement<T extends Serializable> implements FormObject {

    // different types for the form elements
    public static final int TYPE_EDITTEXT_TEXT_SINGLELINE = 1;
    public static final int TYPE_EDITTEXT_TEXT_MULTILINE = 2;
    public static final int TYPE_EDITTEXT_NUMBER = 3;
    public static final int TYPE_EDITTEXT_EMAIL = 4;
    public static final int TYPE_EDITTEXT_PHONE = 5;
    public static final int TYPE_PICKER_DATE = 6;
    public static final int TYPE_PICKER_TIME = 7;
    public static final int TYPE_SPINNER_DROPDOWN = 8;
    public static final int TYPE_PICKER_MULTI_CHECKBOX = 9;
    public static final int TYPE_EDITTEXT_PASSWORD = 10;

    // private variables
    private int mTag; // unique tag to identify the object
    private int mType; // type for the form element
    private String mTitle; // title to be shown on left
    private T mValue; // value to be shown on right
    private List<T> mOptions; // list of options for single and multi picker
    private List<T> mOptionsSelected; // list of selected options for single and multi picker
    private String mHint; // value to be shown if mValue is null
    private String mError;
    private boolean mRequired; // value to set is the field is required
    private boolean mVisible = true;

    public FormElement() {
    }

    /**
     * static method to create instance
     *
     * @return
     */
    public static FormElement<String> createInstance() {
        return new FormElement<>();
    }

    /**
     * static method to create instance using
     * custom generic value type
     * @return
     */
    public static <T extends Serializable> FormElement<T> createGenericInstance() { return new FormElement<T>(); }

    // getters and setters
    public FormElement<T> setTag(int mTag) {
        this.mTag = mTag;
        return this;
    }

    public FormElement<T> setType(int mType) {
        this.mType = mType;
        return this;
    }

    public FormElement<T> setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    public FormElement<T> setValue(T mValue) {
        this.mValue = mValue;
        return this;
    }

    public FormElement<T> setHint(String mHint) {
        this.mHint = mHint;
        return this;
    }

    public FormElement<T> setError(String error) {
        this.mError = error;
        return this;
    }

    public FormElement<T> setRequired(boolean required) {
        this.mRequired = required;
        return this;
    }

    public FormElement<T> setVisible(boolean visible) {
        this.mVisible = visible;
        return this;
    }

    public FormElement<T> setOptions(List<T> mOptions) {
        this.mOptions = mOptions;
        return this;
    }

    public FormElement<T> setOptionsSelected(List<T> mOptionsSelected) {
        this.mOptionsSelected = mOptionsSelected;
        return this;
    }

    public int getTag() {
        return this.mTag;
    }

    public int getType() {
        return this.mType;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public T getValue() {
        return this.mValue;
    }

    public String getValueAsString() {
        return (this.mValue == null) ? "" : this.mValue.toString();
    }

    public String getHint() {
        return (this.mHint == null) ? "" : this.mHint;
    }

    public String getError() {
        return this.mError;
    }

    public boolean isRequired() {
        return this.mRequired;
    }

    public boolean isVisible() {
        return this.mVisible;
    }

    public List<T> getOptions() {
        return (this.mOptions == null) ? new ArrayList<T>() : this.mOptions;
    }

    public List<T> getOptionsSelected() {
        return (this.mOptionsSelected == null) ? new ArrayList<T>() : this.mOptionsSelected;
    }

    @Override
    public boolean isHeader() {
        return false;
    }

    @Override
    public String toString() {
        return "TAG: " + String.valueOf(this.mTag) + ", TITLE: " + this.mTitle + ", VALUE: " + this.mValue + ", REQUIRED: " + String.valueOf(this.mRequired);
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
        dest.writeInt(this.mTag);
        dest.writeInt(this.mType);
        dest.writeString(this.mTitle);
        dest.writeSerializable(this.mValue);

        /**
         * We need special method to store array of generic objects
         * more here: https://stackoverflow.com/a/31979348/3625638
         */

        // mOptions
        if (mOptions == null || mOptions.size() == 0) {
            dest.writeInt(0);
        } else {
            dest.writeInt(mOptions.size());

            final Class<?> objectsType = mOptions.get(0).getClass();
            dest.writeSerializable(objectsType);
            dest.writeList(mOptions);
        }

        // mOptionsSelected
        if (mOptionsSelected == null || mOptionsSelected.size() == 0) {
            dest.writeInt(0);
        } else {
            dest.writeInt(mOptionsSelected.size());

            final Class<?> objectsType = mOptionsSelected.get(0).getClass();
            dest.writeSerializable(objectsType);
            dest.writeList(mOptionsSelected);
        }

        dest.writeList(this.mOptions);
        dest.writeList(this.mOptionsSelected);
        dest.writeString(this.mHint);
        dest.writeString(this.mError);
        dest.writeByte(this.mRequired ? (byte) 1 : (byte) 0);
        dest.writeByte(this.mVisible ? (byte) 1 : (byte) 0);
    }

    protected FormElement(Parcel in) {
        this.mTag = in.readInt();
        this.mType = in.readInt();
        this.mTitle = in.readString();
        this.mValue = (T) in.readSerializable();

        /**
         * We need special method to store array of generic objects
         * more here: https://stackoverflow.com/a/31979348/3625638
         */

        // mOptions
        int optionSize = in.readInt();
        if (optionSize == 0) {
            mOptions = null;
        } else {
            Class<?> type = (Class<?>) in.readSerializable();

            mOptions = new ArrayList<>(optionSize);
            in.readList(mOptions, type.getClassLoader());
        }

        // mOptionsSelected
        int selectedOptionSize = in.readInt();
        if (selectedOptionSize == 0) {
            mOptionsSelected = null;
        } else {
            Class<?> type = (Class<?>) in.readSerializable();

            mOptionsSelected = new ArrayList<>(selectedOptionSize);
            in.readList(mOptionsSelected, type.getClassLoader());
        }

        this.mHint = in.readString();
        this.mError = in.readString();
        this.mRequired = in.readByte() != 0;
        this.mVisible = in.readByte() != 0;
    }

    public static final Creator<FormElement> CREATOR = new Creator<FormElement>() {
        @Override
        public FormElement createFromParcel(Parcel source) {
            return new FormElement(source);
        }

        @Override
        public FormElement[] newArray(int size) {
            return new FormElement[size];
        }
    };
}
