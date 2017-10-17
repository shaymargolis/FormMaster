package me.riddhimanadib.formmaster.model;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormPickerDateElement extends FormPickerElement<FormPickerDateElement.DateHolder> {
    public static class DateHolder {
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
}
