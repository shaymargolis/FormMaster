package me.riddhimanadib.formmaster.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormAutoCompleteElement<T> extends BaseFormElement<T> {
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
}
