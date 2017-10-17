package me.riddhimanadib.formmaster.model;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormPickerDropDownElement<T> extends FormPickerElement<T> {
    public static FormPickerDropDownElement<String> createInstance() {
        return new FormPickerDropDownElement<>();
    }

    public static <T> FormPickerDropDownElement<T> createGenericInstance() { return new FormPickerDropDownElement<T>(); }

    @Override
    public int getType() {
        return TYPE_PICKER_DROP_DOWN;
    }
}
