package me.riddhimanadib.formmaster.renderer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.github.vivchar.rendererrecyclerviewadapter.ViewRenderer;

import me.riddhimanadib.formmaster.R;
import me.riddhimanadib.formmaster.holder.FormHeaderHolder;
import me.riddhimanadib.formmaster.model.FormHeader;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public class FormHeaderRenderer extends ViewRenderer<FormHeader,FormHeaderHolder> {
    public FormHeaderRenderer(int type, Context context) {
        super(type, context);
    }

    @Override
    public void bindView(FormHeader formElement, FormHeaderHolder holder) {
        holder.mTextViewTitle.setText(formElement.getTitle());
    }

    @NonNull
    @Override
    public FormHeaderHolder createViewHolder(@Nullable ViewGroup parent) {
        return FormHeaderHolder.createInstance(inflate(R.layout.form_element_header, parent));
    }
}
