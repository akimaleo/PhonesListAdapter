package com.letit0or1.akimaleo.phonewsadapterview;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akimaleo on 06.09.17.
 */


public abstract class LinearListAdapter<T> {

    private Context context;
    private LinearLayout mLinearContainer;

    protected List<View> mViewDataSet;

    private OnItemClickListener<T> onItemClickListener;


    private List<T> dataSet;

    public LinearListAdapter(LinearLayout linearContainer, List<T> dataset) {
        linearContainer.removeAllViews();
        this.mLinearContainer = linearContainer;
        context = mLinearContainer.getContext();
        this.dataSet = dataset;
    }

    public void initialize() {
        invalidate();
    }

    protected void invalidate() {

        mLinearContainer.removeAllViews();
        mViewDataSet = new ArrayList<>();

        for (int i = 0; i < getCount(); i++) {
            View v;
            if (mViewDataSet.size() < i + 1) {
                mViewDataSet.add(v = getView(i, null));
            } else {
                mViewDataSet.set(i, v = getView(i, mViewDataSet.get(i)));
            }

            mLinearContainer.addView(mViewDataSet.get(i));

            final int finalI = i;
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null)
                        onItemClickListener.onItemClick(v, dataSet.get(finalI), finalI);
                }
            });
        }
    }

    public abstract View getView(int position, View view);

    protected int getCount() {
        return dataSet.size();
    }

    public Context getContext() {
        return context;
    }

    public void setDataSet(List<T> dataSet) {
        this.dataSet = dataSet;
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
