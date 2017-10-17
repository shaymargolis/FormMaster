package me.riddhimanadib.formmaster.renderer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import me.riddhimanadib.formmaster.R;
import me.riddhimanadib.formmaster.holder.FormPickerHolder;
import me.riddhimanadib.formmaster.model.FormTextViewElement;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormTextViewRenderer extends BaseFormRenderer<FormTextViewElement, FormPickerHolder> {
    public FormTextViewRenderer(int type, Context context) {
        super(type, context);
    }

    @Override
    public void bindView(FormTextViewElement formElement, FormPickerHolder holder) {
        super.bindView(formElement, holder);

        holder.mEditTextValue.setText(formElement.getValueAsString());
        holder.mEditTextValue.setHint(formElement.getHint());
        holder.mEditTextValue.setEnabled(false);
        holder.mEditTextValue.setFocusable(false);
    }

    @NonNull
    @Override
    public FormPickerHolder createViewHolder(@Nullable ViewGroup parent) {
        FormPickerHolder holder = new FormPickerHolder(inflate(R.layout.form_element, parent));
        return holder;
    }
}
