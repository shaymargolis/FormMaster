package me.riddhimanadib.formmaster.model;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormEditTextElement<T> extends BaseFormElement<T> {
    public static FormEditTextElement<String> createInstance() {
        return new FormEditTextElement<>();
    }

    public static <T> FormEditTextElement<T> createGenericInstance() { return new FormEditTextElement<T>(); }
}
