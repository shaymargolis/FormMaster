package me.riddhimanadib.formmaster.holder;

import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.view.View;

import me.riddhimanadib.formmaster.R;
import me.riddhimanadib.formmaster.renderer.FormAutoCompleteRenderer;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormAutoCompleteHolder extends BaseFormHolder {
    public AppCompatAutoCompleteTextView mEditTextValue;
    public FormAutoCompleteRenderer.FormCustomEditTextListener mFormCustomEditTextListener;

    public FormAutoCompleteHolder(View v, FormAutoCompleteRenderer.FormCustomEditTextListener listener) {
        super(v);
        mEditTextValue = (AppCompatAutoCompleteTextView) v.findViewById(R.id.formElementValue);
        mFormCustomEditTextListener = listener;
        mFormCustomEditTextListener.updateViewHolder(this);

        if (mEditTextValue != null)
            mEditTextValue.addTextChangedListener(mFormCustomEditTextListener);
    }
}