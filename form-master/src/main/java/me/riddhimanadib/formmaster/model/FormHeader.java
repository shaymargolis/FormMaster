package me.riddhimanadib.formmaster.model;

import android.os.Parcel;

/**
 * Object for header of the form lists
 * Created by Adib on 18-Apr-17.
 */

public class FormHeader extends BaseFormElement<String> {

    public FormHeader() {
    }

    /**
     * static method to create instance with title
     * @param title
     * @return
     */
    public static FormHeader createInstance(String title) {
        FormHeader formHeader = new FormHeader();
        formHeader.setTitle(title);
        return formHeader;
    }

    @Override
    public int getType() {
        return TYPE_HEADER;
    }

    @Override
    public boolean isHeader() {
        return true;
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

    protected FormHeader(Parcel in) {
        super(in);
    }

    public static final Creator<FormHeader> CREATOR = new Creator<FormHeader>() {
        @Override
        public FormHeader createFromParcel(Parcel source) {
            return new FormHeader(source);
        }

        @Override
        public FormHeader[] newArray(int size) {
            return new FormHeader[size];
        }
    };
}