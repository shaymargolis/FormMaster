package me.riddhimanadib.formmaster.renderer;

import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.TimePicker;

import java.util.Calendar;

import me.riddhimanadib.formmaster.helper.FormBuildHelper;
import me.riddhimanadib.formmaster.holder.FormPickerHolder;
import me.riddhimanadib.formmaster.model.FormPickerTimeElement;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormPickerTimeRenderer extends FormPickerRenderer<FormPickerTimeElement> {
    public Calendar mCalendarCurrentTime;

    public FormPickerTimeRenderer(int type, Context context, FormBuildHelper formBuilder) {
        super(type, context, formBuilder);

        mCalendarCurrentTime = Calendar.getInstance();
    }

    @Override
    public void bindView(FormPickerTimeElement formElement, FormPickerHolder holder) {
        super.bindView(formElement, holder);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                time,
                formElement.getValue().getHourOfDay(),
                formElement.getValue().getMinute(),
                true);

        setOnClickForHolder(holder, formElement.getTag(), timePickerDialog);
    }

    TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // act only if clicked position is a valid index
            if (clickedTag >= 0) {
                // get current form element, existing value and new value
                FormPickerTimeElement formElement = (FormPickerTimeElement)getFormBuilder().getFormElement(clickedTag);
                FormPickerTimeElement.TimeHolder currentValue = formElement.getValue();
                FormPickerTimeElement.TimeHolder newValue = new FormPickerTimeElement.TimeHolder(hourOfDay, minute);

                // trigger event only if the value is changed
                if (!currentValue.equals(newValue)) {
                    formElement.setValue(newValue);
                    formElement.setError(null); // Reset after value change
                    getFormBuilder().onValueChanged(formElement);
                    getFormBuilder().refreshView();
                }

                // change clicked position to default value
                clickedTag = -1;
            }
        }
    };
}
