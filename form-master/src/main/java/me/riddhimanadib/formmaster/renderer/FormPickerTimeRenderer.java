package me.riddhimanadib.formmaster.renderer;

import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import me.riddhimanadib.formmaster.helper.FormBuildHelper;
import me.riddhimanadib.formmaster.holder.FormPickerHolder;
import me.riddhimanadib.formmaster.model.BaseFormElement;
import me.riddhimanadib.formmaster.model.FormPickerTimeElement;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormPickerTimeRenderer extends FormPickerRenderer<FormPickerTimeElement> {
    public Calendar mCalendarCurrentTime;
    public int clickedTag = -1;

    public FormPickerTimeRenderer(int type, Context context, FormBuildHelper formBuilder) {
        super(type, context, formBuilder);

        mCalendarCurrentTime = Calendar.getInstance();
    }

    @Override
    public void bindView(FormPickerTimeElement formElement, FormPickerHolder holder) {
        super.bindView(formElement, holder);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                time,
                mCalendarCurrentTime.get(Calendar.HOUR),
                mCalendarCurrentTime.get(Calendar.MINUTE),
                true);

        setOnClickForHolder(holder, formElement.getTag(), timePickerDialog);
    }

    TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mCalendarCurrentTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            mCalendarCurrentTime.set(Calendar.MINUTE, minute);

            String myFormatTime = "HH:mm"; // custom format
            SimpleDateFormat sdfTime = new SimpleDateFormat(myFormatTime, Locale.US);

            // act only if clicked position is a valid index
            if (clickedTag >= 0) {
                // get current form element, existing value and new value
                BaseFormElement formElement = getFormBuilder().getFormElement(clickedTag);
                String currentValue = formElement.getValue().toString();
                String newValue = sdfTime.format(mCalendarCurrentTime.getTime());

                // trigger event only if the value is changed
                if (!currentValue.equals(newValue)) {
                    formElement.setValue(newValue);
                    formElement.setError(null); // Reset after value change
                    getFormBuilder().onValueChanged(formElement);
                }

                // change clicked position to default value
                clickedTag = -1;
            }
        }
    };
}
