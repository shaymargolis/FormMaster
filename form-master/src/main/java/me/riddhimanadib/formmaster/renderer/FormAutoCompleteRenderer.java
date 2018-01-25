package me.riddhimanadib.formmaster.renderer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;

import java.io.Serializable;

import me.riddhimanadib.formmaster.R;
import me.riddhimanadib.formmaster.helper.FormBuildHelper;
import me.riddhimanadib.formmaster.holder.FormAutoCompleteHolder;
import me.riddhimanadib.formmaster.model.BaseFormElement;
import me.riddhimanadib.formmaster.model.FormAutoCompleteElement;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormAutoCompleteRenderer extends BaseFormRenderer<FormAutoCompleteElement, FormAutoCompleteHolder> {
    private FormBuildHelper formBuilder;

    public FormAutoCompleteRenderer(int type, Context context, FormBuildHelper formBuilder) {
        super(type, context);

        this.formBuilder = formBuilder;
    }

    @Override
    public void bindView(FormAutoCompleteElement formElement, FormAutoCompleteHolder holder) {
        super.bindView(formElement, holder);

        holder.mFormCustomEditTextListener.updateTag(formElement.getTag());
        if (!formElement.getValueAsString().equals(formElement.getTypedString())) { // If the typedString is not one of the values, keep it
            holder.mEditTextValue.setText(formElement.getTypedString());
        } else {
            holder.mEditTextValue.setText(formElement.getValueAsString());
        }
        holder.mEditTextValue.setHint(formElement.getHint());

        setEditTextFocusEnabled(holder);

        if (formElement.getOptions().size() == 0) {
            return;
        }

        ArrayAdapter<?> itemsAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, formElement.getOptions());
        holder.mEditTextValue.setAdapter(itemsAdapter);
    }

    /**
     * brings focus when clicked on the whole container
     * @param holder
     */
    private void setEditTextFocusEnabled(final FormAutoCompleteHolder holder) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mEditTextValue.requestFocus();
                holder.mEditTextValue.setSelection(holder.mEditTextValue.getText().length());
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(holder.mEditTextValue, InputMethodManager.SHOW_IMPLICIT);
            }
        });
    }

    @NonNull
    @Override
    public FormAutoCompleteHolder createViewHolder(@Nullable ViewGroup parent) {
        FormCustomEditTextListener listener = new FormCustomEditTextListener();
        FormAutoCompleteHolder holder = new FormAutoCompleteHolder(inflate(R.layout.form_element_auto_complete, parent), listener);

        listener.updateViewHolder(holder);

        return holder;
    }

    /**
     * Text watcher for Edit texts
     */
    public class FormCustomEditTextListener implements TextWatcher {
        private FormAutoCompleteHolder formViewHolder;
        private int tag;

        public void updateViewHolder(FormAutoCompleteHolder formViewHolder) {
            this.formViewHolder = formViewHolder;
        }

        public void updateTag(int tag) {
            this.tag = tag;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }

        public Object getElementWithString(BaseFormElement element, String stringVal) {
            for (Object i : element.getOptions()) {
                if (i.toString().equals(stringVal)) {
                    return i;
                }
            }

            return null;
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            // get current form element, existing value and new value
            FormAutoCompleteElement formElement = (FormAutoCompleteElement)formBuilder.getFormElement(tag);
            String newValue = charSequence.toString();

            formElement.setTypedString(newValue);

            // trigger only if the value exists as one of the string options,
            // and if the string was changed.
            if (formElement.getStringOptions().contains(newValue) && !newValue.equals(formElement.getValueAsString())) {
                formElement.setValue((Serializable)getElementWithString(formElement, newValue));
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
