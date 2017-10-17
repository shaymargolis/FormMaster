package me.riddhimanadib.formmaster.renderer;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import me.riddhimanadib.formmaster.R;
import me.riddhimanadib.formmaster.helper.FormBuildHelper;
import me.riddhimanadib.formmaster.holder.FormPickerHolder;
import me.riddhimanadib.formmaster.model.FormPickerElement;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormPickerRenderer<M extends FormPickerElement> extends BaseFormRenderer<M,FormPickerHolder> {
    private int clickedTag;
    private FormBuildHelper formBuilder;

    public FormPickerRenderer(int type, Context context, FormBuildHelper formBuilder) {
        super(type, context);

        this.formBuilder = formBuilder;
    }

    @Override
    public void bindView(M formElement, FormPickerHolder holder) {
        super.bindView(formElement, holder);

        holder.mEditTextValue.setText(formElement.getValueAsString());
        holder.mEditTextValue.setHint(formElement.getHint());
    }

    public void setOnClickForHolder(FormPickerHolder holder, final int tag, final Dialog d) {
        holder.mEditTextValue.setFocusable(false);

        // display the dialog on click
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedTag = tag;
                d.show();
            }
        };

        holder.itemView.setOnClickListener(listener);
        holder.mEditTextValue.setOnClickListener(listener);
    }

    @NonNull
    @Override
    public FormPickerHolder createViewHolder(@Nullable ViewGroup parent) {
        return new FormPickerHolder(inflate(R.layout.form_element, parent));
    }

    public FormBuildHelper getFormBuilder() {
        return this.formBuilder;
    }
}