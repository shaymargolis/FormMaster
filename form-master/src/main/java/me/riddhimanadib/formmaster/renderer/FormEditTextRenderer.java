package me.riddhimanadib.formmaster.renderer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import me.riddhimanadib.formmaster.R;
import me.riddhimanadib.formmaster.helper.FormBuildHelper;
import me.riddhimanadib.formmaster.holder.FormEditTextHolder;
import me.riddhimanadib.formmaster.model.BaseFormElement;
import me.riddhimanadib.formmaster.model.FormEditTextElement;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormEditTextRenderer extends BaseFormRenderer<FormEditTextElement, FormEditTextHolder> {

    private FormBuildHelper formBuilder;

    public FormEditTextRenderer(int type, Context context, FormBuildHelper formBuilder) {
        super(type, context);

        this.formBuilder = formBuilder;
    }

    @Override
    public void bindView(FormEditTextElement formElement, FormEditTextHolder holder) {
        super.bindView(formElement, holder);

        holder.mFormCustomEditTextListener.updateTag(formElement.getTag());

        holder.mEditTextValue.setText(formElement.getValueAsString());
        holder.mEditTextValue.setHint(formElement.getHint());

        setEditTextFocusEnabled(holder);

        switch (this.getType()) {
            case BaseFormElement.TYPE_EDITTEXT_TEXT_SINGLELINE:
                holder.mEditTextValue.setMaxLines(1);
                break;
            case BaseFormElement.TYPE_EDITTEXT_TEXT_MULTILINE:
                holder.mEditTextValue.setSingleLine(false);
                holder.mEditTextValue.setMaxLines(4);
                break;
            case BaseFormElement.TYPE_EDITTEXT_NUMBER:
                holder.mEditTextValue.setRawInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                break;
            case BaseFormElement.TYPE_EDITTEXT_EMAIL:
                holder.mEditTextValue.setRawInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS|InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                break;
            case BaseFormElement.TYPE_EDITTEXT_PHONE:
                holder.mEditTextValue.setRawInputType(InputType.TYPE_CLASS_PHONE|InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
                break;
            case BaseFormElement.TYPE_EDITTEXT_PASSWORD:
                holder.mEditTextValue.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                // TODO: holder.mEditTextValue.setSelection(holder.mEditTextValue.getText().length());
                break;
            default:
                break;
        }
    }

    /**
     * brings focus when clicked on the whole container
     * @param holder
     */
    private void setEditTextFocusEnabled(final FormEditTextHolder holder) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mEditTextValue.requestFocus();
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(holder.mEditTextValue, InputMethodManager.SHOW_IMPLICIT);
            }
        });
    }

    @NonNull
    @Override
    public FormEditTextHolder createViewHolder(@Nullable ViewGroup parent) {
        FormCustomEditTextListener listener = new FormCustomEditTextListener();
        FormEditTextHolder holder = new FormEditTextHolder(inflate(R.layout.form_element, parent), listener);

        listener.updateViewHolder(holder);

        return holder;
    }

    /**
     * Text watcher for Edit texts
     */
    public class FormCustomEditTextListener implements TextWatcher {
        private FormEditTextHolder formViewHolder;
        private int tag;

        public void updateViewHolder(FormEditTextHolder formViewHolder) {
            this.formViewHolder = formViewHolder;
        }

        public void updateTag(int tag) {
            this.tag = tag;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            // get current form element, existing value and new value
            BaseFormElement formElement = formBuilder.getFormElement(tag);
            String currentValue = formElement.getValueAsString();
            String newValue = charSequence.toString();

            // trigger event only if the value is changed
            if (!currentValue.equals(newValue)) {
                formElement.setValue(newValue);
                formElement.setError(null);
                setError(formViewHolder, null);

                formBuilder.onValueChanged(formElement);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
