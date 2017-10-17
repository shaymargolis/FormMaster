package me.riddhimanadib.formmaster.model;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormPickerDateElement<T> extends FormPickerElement<T> {
    public static FormPickerDateElement<String> createInstance() {
        return new FormPickerDateElement<>();
    }

    public static <T> FormPickerDateElement<T> createGenericInstance() { return new FormPickerDateElement<T>(); }

    @Override
    public int getType() {
        return TYPE_PICKER_DATE;
    }
}
