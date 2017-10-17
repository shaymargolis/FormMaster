package me.riddhimanadib.formmaster.holder;

import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import me.riddhimanadib.formmaster.R;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormPickerHolder extends BaseFormHolder {
    public AppCompatEditText mEditTextValue;

    public FormPickerHolder(View v) {
        super(v);
        mEditTextValue = (AppCompatEditText) v.findViewById(R.id.formElementValue);
    }
}