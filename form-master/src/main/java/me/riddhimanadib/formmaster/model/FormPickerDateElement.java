package me.riddhimanadib.formmaster.model;

import android.os.Parcel;

import java.io.Serializable;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormPickerDateElement extends FormPickerElement<FormPickerDateElement.DateHolder> {
    public static class DateHolder implements Serializable {
        private int year;
        private int month;
        private int dayOfMonth;

        public DateHolder(int dayOfMonth, int month, int year) {
            this.dayOfMonth = dayOfMonth;
            this.month = month;
            this.year = year;
        }

        public String toString() {
            return String.format("%02d/%02d/%02d", dayOfMonth, month, year);
        }

        public int getYear() {
            return year;
        }

        public int getMonth() {
            return month;
        }

        public int getDayOfMonth() {
            return dayOfMonth;
        }

        public boolean equals(DateHolder another) {
            return another.year == this.year && another.month == this.month && another.dayOfMonth == this.dayOfMonth;
        }
    }


    public static FormPickerDateElement createDateInstance() {
        return new FormPickerDateElement();
    }

    @Override
    public int getType() {
        return TYPE_PICKER_DATE;
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

    public FormPickerDateElement() {
    }

    protected FormPickerDateElement(Parcel in) {
    }

    public static final Creator<FormPickerDateElement> CREATOR = new Creator<FormPickerDateElement>() {
        @Override
        public FormPickerDateElement createFromParcel(Parcel source) {
            return new FormPickerDateElement(source);
        }

        @Override
        public FormPickerDateElement[] newArray(int size) {
            return new FormPickerDateElement[size];
        }
    };
}
