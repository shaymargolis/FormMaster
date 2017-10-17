package me.riddhimanadib.formmaster.model;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormTextViewElement<T> extends BaseFormElement<T> {
    public static FormTextViewElement<String> createInstance() {
        return new FormTextViewElement<>();
    }

    public static <T> FormTextViewElement<T> createGenericInstance() { return new FormTextViewElement<T>(); }

    @Override
    public int getType() {
        return TYPE_TEXTVIEW;
    }
}