package me.riddhimanadib.formmaster.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormAutoCompleteElement<T> extends BaseFormElement<T> {
    /**
     * Because the written text in the EditText not always matched the
     * Values in options array, we keep the TypedString: what the user typed.
     */
    private String mTypedString;
    /**
     * To determine what strings can be typed, we keep a set of the options
     * converted to strings
     */
    private Set<String> mStringOptions;

    public static FormAutoCompleteElement<String> createInstance() {
        return new FormAutoCompleteElement<>();
    }

    public static <T> FormAutoCompleteElement<T> createGenericInstance() { return new FormAutoCompleteElement<T>(); }

    @Override
    public int getType() {
        return TYPE_EDITTEXT_AUTOCOMPLETE;
    }

    @Override
    public FormAutoCompleteElement<T> setOptions(List<T> mOptions) {
        super.setOptions(mOptions);
        mStringOptions = new HashSet<>();
        for (T i : mOptions) {
            mStringOptions.add(i.toString());
        }
        return this;
    }

    public Set<String> getStringOptions() {
        return (mStringOptions != null) ? mStringOptions : new HashSet<String>();
    }

    public String getTypedString() {
        return this.mTypedString;
    }

    public void setTypedString(String typedString) {
        this.mTypedString = typedString;
    }
}
