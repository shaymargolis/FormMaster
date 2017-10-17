package me.riddhimanadib.formmaster.model;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormPickerTimeElement<T> extends FormPickerElement<T> {
    public static FormPickerTimeElement<String> createInstance() {
        return new FormPickerTimeElement<>();
    }

    public static <T> FormPickerTimeElement<T> createGenericInstance() { return new FormPickerTimeElement<T>(); }

    @Override
    public int getType() {
        return TYPE_PICKER_TIME;
    }
}