package me.riddhimanadib.formmaster.helper;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.vivchar.rendererrecyclerviewadapter.RendererRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import me.riddhimanadib.formmaster.listener.OnFormElementValueChangedListener;
import me.riddhimanadib.formmaster.model.BaseFormElement;
import me.riddhimanadib.formmaster.renderer.FormAutoCompleteRenderer;
import me.riddhimanadib.formmaster.renderer.FormEditTextRenderer;
import me.riddhimanadib.formmaster.renderer.FormPickerDateRenderer;
import me.riddhimanadib.formmaster.renderer.FormPickerDropDownRenderer;
import me.riddhimanadib.formmaster.renderer.FormPickerMultiCheckBoxRenderer;
import me.riddhimanadib.formmaster.renderer.FormPickerTimeRenderer;

/** Wrapper class around the adapter to assist in building form
 * Created by Adib on 16-Apr-17.
 */

public class FormBuildHelper {

    private RendererRecyclerViewAdapter mFormAdapter;

    private List<BaseFormElement> mElements;

    private OnFormElementValueChangedListener mListener;

    /**
     * constructor without listener callback for changed values
     * @param context
     * @param recyclerView
     */
    public FormBuildHelper(Context context, RecyclerView recyclerView) {
        initializeFormBuildHelper(context, recyclerView, null);
    }

    /**
     * constructor with listener callback for changed values
     * @param context
     * @param recyclerView
     */
    public FormBuildHelper(Context context, RecyclerView recyclerView, OnFormElementValueChangedListener listener) {
        initializeFormBuildHelper(context, recyclerView, listener);
    }

    /**
     * private method for initializing form build helper
     * @param context
     * @param recyclerView
     * @param listener
     */
    private void initializeFormBuildHelper(Context context, RecyclerView recyclerView, OnFormElementValueChangedListener listener) {

        // initialize form adapter
        this.mElements = new ArrayList<>();

        this.mFormAdapter = new RendererRecyclerViewAdapter();
        this.mFormAdapter.registerRenderer(new FormEditTextRenderer(BaseFormElement.TYPE_EDITTEXT_TEXT_SINGLELINE, context, this));
        this.mFormAdapter.registerRenderer(new FormEditTextRenderer(BaseFormElement.TYPE_EDITTEXT_TEXT_MULTILINE, context, this));
        this.mFormAdapter.registerRenderer(new FormEditTextRenderer(BaseFormElement.TYPE_EDITTEXT_NUMBER, context, this));
        this.mFormAdapter.registerRenderer(new FormEditTextRenderer(BaseFormElement.TYPE_EDITTEXT_EMAIL, context, this));
        this.mFormAdapter.registerRenderer(new FormEditTextRenderer(BaseFormElement.TYPE_EDITTEXT_PHONE, context, this));
        this.mFormAdapter.registerRenderer(new FormEditTextRenderer(BaseFormElement.TYPE_EDITTEXT_PASSWORD, context, this));

        this.mFormAdapter.registerRenderer(new FormAutoCompleteRenderer(BaseFormElement.TYPE_EDITTEXT_AUTOCOMPLETE, context, this));

        this.mFormAdapter.registerRenderer(new FormPickerDateRenderer(BaseFormElement.TYPE_PICKER_DATE, context, this));
        this.mFormAdapter.registerRenderer(new FormPickerTimeRenderer(BaseFormElement.TYPE_PICKER_TIME, context, this));
        this.mFormAdapter.registerRenderer(new FormPickerMultiCheckBoxRenderer(BaseFormElement.TYPE_PICKER_MULTI_CHECKBOX, context, this));
        this.mFormAdapter.registerRenderer(new FormPickerDropDownRenderer(BaseFormElement.TYPE_PICKER_DROP_DOWN, context, this));

        this.mListener = listener;

        // set up the recyclerview with adapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setStackFromEnd(false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mFormAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    /**
     * add list of form elements to be shown
     * @param formElements
     */
    public void addFormElements(List<BaseFormElement> formElements) {
        this.mElements.addAll(formElements);
        this.mFormAdapter.setItems(this.mElements);
    }

    /**
     * redraws the view
     */
    public void refreshView() {
        this.mFormAdapter.notifyDataSetChanged();
    }

    /**
     * get value of specific form element
     * @param tag
     * @return
     */
    public BaseFormElement getFormElement(int tag) {
        for (BaseFormElement i : this.mElements) {
            if (i.isHeader())
                continue;

            if (i.getTag() == tag)
                return i;
        }

        return null;
    }

    public BaseFormElement getElementAtIndex(int index) {
        if (this.mElements.get(index) instanceof BaseFormElement) {
            return this.mElements.get(index);
        }

        return null;
    }

    public void onValueChanged(BaseFormElement element) {
        mListener.onValueChanged(element);


    }

    /**
     *
     * return true if the form is valid
     *
     * @return
     */
    public boolean isValidForm() {
        for (int i = 0; i < this.mFormAdapter.getItemCount(); i++) {
            BaseFormElement formElement = this.getElementAtIndex(i);
            if (formElement.isHeader() == false & formElement.isRequired() & formElement.getValue().toString().trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns all rows stored
     */
    public List<BaseFormElement> getAllObjects() {
        return this.mElements;
    }
}
