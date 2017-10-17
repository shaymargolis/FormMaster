package me.riddhimanadib.formmaster.renderer;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;

import me.riddhimanadib.formmaster.helper.FormBuildHelper;
import me.riddhimanadib.formmaster.holder.FormPickerHolder;
import me.riddhimanadib.formmaster.model.BaseFormElement;
import me.riddhimanadib.formmaster.model.FormPickerMultiCheckBoxElement;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormPickerMultiCheckBoxRenderer extends FormPickerRenderer<FormPickerMultiCheckBoxElement> {
    public FormPickerMultiCheckBoxRenderer(int type, Context context, FormBuildHelper formBuilder) {
        super(type, context, formBuilder);
    }

    @Override
    public void bindView(FormPickerMultiCheckBoxElement formElement, FormPickerHolder holder) {
        super.bindView(formElement, holder);

        setMultipleOptionsDialog(holder, formElement.getTag());
    }

    private void setMultipleOptionsDialog(final FormPickerHolder holder, final int tag) {
        // get the element
        final BaseFormElement currentObj = getFormBuilder().getFormElement(tag);

        // reformat the options in format needed
        final CharSequence[] options = new CharSequence[currentObj.getOptions().size()];
        final boolean[] optionsSelected = new boolean[currentObj.getOptions().size()];
        final ArrayList<Integer> mSelectedItems = new ArrayList();

        for (int i = 0; i < currentObj.getOptions().size(); i++) {
            Object obj = currentObj.getOptions().get(i);

            options[i] = obj.toString();
            optionsSelected[i] = false;

            if (currentObj.getOptionsSelected().contains(obj)) {
                optionsSelected[i] = true;
                mSelectedItems.add(i);
            }
        }

        // prepare the dialog
        final AlertDialog dialog  = new AlertDialog.Builder(getContext())
                .setTitle("Pick one or more")
                .setMultiChoiceItems(options, optionsSelected,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    mSelectedItems.add(which);
                                } else if (mSelectedItems.contains(which)) {
                                    // Else, if the item is already in the array, remove it
                                    mSelectedItems.remove(Integer.valueOf(which));
                                }
                            }
                        })
                // Set the action buttons
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String s = "";
                        for (int i = 0; i < mSelectedItems.size(); i++) {
                            s += options[mSelectedItems.get(i)];

                            if (i < mSelectedItems.size() - 1) {
                                s += ", ";
                            }
                        }
                        holder.mEditTextValue.setText(s);
                        BaseFormElement element = getFormBuilder().getFormElement(tag);
                        element.setValue(s);
                        element.setError(null);
                        getFormBuilder().refreshView();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .create();

        setOnClickForHolder(holder, tag, dialog);
    }
}
