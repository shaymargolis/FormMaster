package me.riddhimanadib.formmaster.model;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormPickerTimeElement extends FormPickerElement<FormPickerTimeElement.TimeHolder> {
    public static class TimeHolder {
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
}