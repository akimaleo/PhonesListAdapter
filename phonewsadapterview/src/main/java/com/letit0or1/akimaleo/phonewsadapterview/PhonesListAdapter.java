package com.letit0or1.akimaleo.phonewsadapterview;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.pinball83.maskededittext.MaskedEditText;

import java.util.ArrayList;

/**
 * Created by akimaleo on 06.09.17.
 */


public class PhonesListAdapter extends LinearListAdapter {

    private ArrayList<String> mPhones;
    private boolean setFocus = false;
    private int maxSize = 0;

    public PhonesListAdapter(LinearLayout linearContainer, ArrayList<String> phones, int maxSize) {
        super(linearContainer, phones);
        mPhones = phones;
        linearContainer.setOrientation(LinearLayout.VERTICAL);
        this.maxSize = maxSize;
        if (maxSize > 0 && phones.size() > maxSize) {
            for (int i = maxSize; i < phones.size(); i++)
                phones.remove(i);
        }
    }

    @Override
    public void initialize() {
        super.initialize();
    }


    @Override
    public View getView(final int position, View view) {
        view = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.list_item_phonenumber, null);
        String phone = mPhones.get(position);

        final MaskedEditText editText = ((MaskedEditText) view.findViewById(R.id.phone_name_input));

        ImageView deleteButton = ((ImageButton) view.findViewById(R.id.add_phone));
        deleteButton.setScaleX(0.5f);
        deleteButton.setScaleY(0.5f);

        deleteButton.setVisibility(position + 1 == getCount() ? View.GONE : View.VISIBLE);

        editText.setMaskedText(phone);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    mPhones.set(position, editText.getUnmaskedText());
                    validateLast();
                } catch (Exception e) {
//                    e.printStackTrace();
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhones.remove(position);
                invalidate();
            }
        });
        if (setFocus && position == mPhones.size() - 1) {
            view.requestFocus();
            setFocus = false;
        }
        return view;
    }

    @Override
    public int getCount() {
        return mPhones.size();
    }

    @Override
    protected void invalidate() {
        super.invalidate();
        validateLast();
    }

    private void validateLast() {
        if (mPhones.size() == 0) {
            mPhones.add("");
            invalidate();
        }
        if (mPhones.get(mPhones.size() - 1).length() >= 10 && (maxSize > 0 && mPhones.size() < maxSize)) {
            mPhones.add("");
            setFocus = true;
            invalidate();
        }
    }

    public ArrayList<String> getmPhones() {
        return mPhones;
    }
}
