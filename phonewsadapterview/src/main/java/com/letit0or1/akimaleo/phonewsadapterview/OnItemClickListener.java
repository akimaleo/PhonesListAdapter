package com.letit0or1.akimaleo.phonewsadapterview;

import android.view.View;

/**
 * Created by akimaleo on 06.09.17.
 */

public interface OnItemClickListener<T> {
    void onItemClick(View v, T item, int position);
}
