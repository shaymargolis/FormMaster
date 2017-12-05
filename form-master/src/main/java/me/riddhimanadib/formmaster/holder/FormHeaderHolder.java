package me.riddhimanadib.formmaster.holder;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import me.riddhimanadib.formmaster.R;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormHeaderHolder extends RecyclerView.ViewHolder {
    public AppCompatTextView mTextViewTitle;

    public FormHeaderHolder(View v) {
        super(v);
        mTextViewTitle = (AppCompatTextView) v.findViewById(R.id.formElementTitle);
    }

    public static FormHeaderHolder createInstance(View v) {
        return new FormHeaderHolder(v);
    }
}