package me.riddhimanadib.formmaster.model;

import android.os.Parcel;

import java.io.Serializable;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormPickerTimeElement extends FormPickerElement<FormPickerTimeElement.TimeHolder> {
    public static class TimeHolder implements Serializable {
        private int hourOfDay;
        private int minute;

        public TimeHolder(int hourOfDay, int minute) {
            this.hourOfDay = hourOfDay;
            this.minute = minute;
        }

        public String toString() {
            return String.format("%02d:%02d", hourOfDay, minute);
        }

        public int getHourOfDay() {
            return hourOfDay;
        }

        public int getMinute() {
            return minute;
        }

        public boolean equals(TimeHolder another) {
            return another.minute == this.minute && another.hourOfDay == this.hourOfDay;
        }
    }

    public static FormPickerTimeElement createTimeInstance() {
        return new FormPickerTimeElement();
    }

    @Override
    public int getType() {
        return TYPE_PICKER_TIME;
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

    public FormPickerTimeElement() {
    }

    protected FormPickerTimeElement(Parcel in) {
        super(in);
    }

    public static final Creator<FormPickerTimeElement> CREATOR = new Creator<FormPickerTimeElement>() {
        @Override
        public FormPickerTimeElement createFromParcel(Parcel source) {
            return new FormPickerTimeElement(source);
        }

        @Override
        public FormPickerTimeElement[] newArray(int size) {
            return new FormPickerTimeElement[size];
        }
    };
}