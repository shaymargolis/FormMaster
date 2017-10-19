package me.riddhimanadib.formmaster.renderer;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import java.io.Serializable;

import me.riddhimanadib.formmaster.helper.FormBuildHelper;
import me.riddhimanadib.formmaster.holder.FormPickerHolder;
import me.riddhimanadib.formmaster.model.BaseFormElement;
import me.riddhimanadib.formmaster.model.FormPickerDropDownElement;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormPickerDropDownRenderer extends FormPickerRenderer<FormPickerDropDownElement> {
    public FormPickerDropDownRenderer(int type, Context context, FormBuildHelper formBuilder) {
        super(type, context, formBuilder);
    }

    @Override
    public void bindView(FormPickerDropDownElement formElement, FormPickerHolder holder) {
        super.bindView(formElement, holder);

        setSingleOptionsDialog(holder, formElement.getTag());
    }

    private void setSingleOptionsDialog(final FormPickerHolder holder, final int tag) {
        // get the element
        final BaseFormElement currentObj = getFormBuilder().getFormElement(tag);

        // reformat the options in format needed
        final CharSequence[] options = new CharSequence[currentObj.getOptions().size()];
        for (int i = 0; i < currentObj.getOptions().size(); i++) {
            options[i] = currentObj.getOptions().get(i).toString();
        }

        // prepare the dialog
        final AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setTitle("Pick one")
                .setItems(options, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        holder.mEditTextValue.setText(options[which]);
                        BaseFormElement element = getFormBuilder().getFormElement(tag);
                        element.setValue((Serializable)currentObj.getOptions().get(which));
                        element.setError(null); // Reset after value change
                        setError(holder,null); // Reset after value change
                        getFormBuilder().refreshView();
                    }
                })
                .create();

        setOnClickForHolder(holder, tag, dialog);
    }
}
