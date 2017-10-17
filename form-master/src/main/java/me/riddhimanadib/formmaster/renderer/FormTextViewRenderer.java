package me.riddhimanadib.formmaster.renderer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import me.riddhimanadib.formmaster.R;
import me.riddhimanadib.formmaster.holder.FormEditTextHolder;
import me.riddhimanadib.formmaster.model.FormTextViewElement;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormTextViewRenderer extends BaseFormRenderer<FormTextViewElement, FormEditTextHolder> {
    public FormTextViewRenderer(int type, Context context) {
        super(type, context);
    }

    @Override
    public void bindView(FormTextViewElement formElement, FormEditTextHolder holder) {
        super.bindView(formElement, holder);

        holder.mFormCustomEditTextListener.updateTag(formElement.getTag());

        holder.mEditTextValue.setText(formElement.getValueAsString());
        holder.mEditTextValue.setHint(formElement.getHint());
        holder.mEditTextValue.setEnabled(false);
        holder.mEditTextValue.setFocusable(false);
    }

    @NonNull
    @Override
    public FormEditTextHolder createViewHolder(@Nullable ViewGroup parent) {
        FormEditTextHolder holder = new FormEditTextHolder(inflate(R.layout.form_element, parent), null);
        return holder;
    }
}
