package me.riddhimanadib.formmaster.renderer;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import java.util.Calendar;

import me.riddhimanadib.formmaster.helper.FormBuildHelper;
import me.riddhimanadib.formmaster.holder.FormPickerHolder;
import me.riddhimanadib.formmaster.model.FormPickerDateElement;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormPickerDateRenderer extends FormPickerRenderer<FormPickerDateElement> {

    public Calendar mCalendarCurrentDate;

    public FormPickerDateRenderer(int type, Context context, FormBuildHelper formBuilder) {
        super(type, context, formBuilder);

        mCalendarCurrentDate = Calendar.getInstance();
    }

    @Override
    public void bindView(FormPickerDateElement formElement, FormPickerHolder holder) {
        super.bindView(formElement, holder);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                date,
                formElement.getValue().getYear(),
                formElement.getValue().getMonth(),
                formElement.getValue().getDayOfMonth());

        // TODO: this could be used to set a minimum date
        // datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        setOnClickForHolder(holder, formElement.getTag(), datePickerDialog);
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            if (clickedTag != -1) {
                // get current form element, existing value and new value
                FormPickerDateElement formElement = (FormPickerDateElement)getFormBuilder().getFormElement(clickedTag);
                FormPickerDateElement.DateHolder currentValue = formElement.getValue();
                FormPickerDateElement.DateHolder newValue = new FormPickerDateElement.DateHolder(dayOfMonth, monthOfYear+1, year);

                // trigger event only if the value is changed
                if (!currentValue.equals(newValue)) {
                    formElement.setValue(newValue);
                    formElement.setError(null); // Reset after value change
                    getFormBuilder().onValueChanged(formElement);
                    getFormBuilder().refreshView();
                }
            }

            clickedTag = -1;
        }
    };
}
