package me.riddhimanadib.formmaster.model;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormPickerMultiCheckBoxElement<T> extends FormPickerElement<T> {
    public static FormPickerMultiCheckBoxElement<String> createInstance() {
        return new FormPickerMultiCheckBoxElement<>();
    }

    public static <T> FormPickerMultiCheckBoxElement<T> createGenericInstance() { return new FormPickerMultiCheckBoxElement<T>(); }

    @Override
    public int getType() {
        return TYPE_PICKER_MULTI_CHECKBOX;
    }
}
