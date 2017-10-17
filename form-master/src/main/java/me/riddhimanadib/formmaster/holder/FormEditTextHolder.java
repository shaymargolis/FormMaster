package me.riddhimanadib.formmaster.holder;

import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import me.riddhimanadib.formmaster.R;
import me.riddhimanadib.formmaster.renderer.FormEditTextRenderer;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormEditTextHolder extends BaseFormHolder {
    public AppCompatEditText mEditTextValue;
    public FormEditTextRenderer.FormCustomEditTextListener mFormCustomEditTextListener;

    public FormEditTextHolder(View v, FormEditTextRenderer.FormCustomEditTextListener listener) {
        super(v);
        mEditTextValue = (AppCompatEditText) v.findViewById(R.id.formElementValue);
        mFormCustomEditTextListener = listener;
        mFormCustomEditTextListener.updateViewHolder(this);

        if (mEditTextValue != null)
            mEditTextValue.addTextChangedListener(mFormCustomEditTextListener);
    }
}