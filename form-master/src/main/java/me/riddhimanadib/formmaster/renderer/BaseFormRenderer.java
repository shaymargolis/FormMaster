package me.riddhimanadib.formmaster.renderer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.github.vivchar.rendererrecyclerviewadapter.ViewRenderer;

import me.riddhimanadib.formmaster.holder.BaseFormHolder;
import me.riddhimanadib.formmaster.model.BaseFormElement;

/**
 * Created by shaymargolis on 17/10/2017.
 */

public abstract class BaseFormRenderer<M extends BaseFormElement, VH extends BaseFormHolder> extends ViewRenderer<M,VH> {
    public BaseFormRenderer(int type, Context context) {
        super(type, context);
    }

    @Override
    public void bindView(M formElement, VH holder) {
        // other wise, it just displays form element with respective type
        holder.mTextViewTitle.setText(formElement.getTitle());
        setError(holder, formElement.getError());

        if (formElement.isVisible()) {
            holder.itemView.setVisibility(View.VISIBLE);
            holder.itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        } else {
            holder.itemView.setVisibility(View.GONE);
            holder.itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0));
        }
    }

    /**
     * Sets mTextViewError visibility according to error
     * @param holder
     * @param error
     */
    public void setError(final VH holder, String error) {
        if (error == null || error.length() == 0) {
            holder.mTextViewError.setVisibility(View.GONE);
            return;
        }

        holder.mTextViewError.setText(error);
        holder.mTextViewError.setVisibility(View.VISIBLE);
    }
}
