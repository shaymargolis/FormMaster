package me.riddhimanadib.formmaster.model;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormPickerElement<T> extends BaseFormElement<T> {
    public static FormPickerElement<String> createInstance() {
        return new FormPickerElement<>();
    }

    public static <T> FormPickerElement<T> createGenericInstance() { return new FormPickerElement<T>(); }
}
