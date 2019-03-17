package com.train.amm.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.train.amm.R;

public class Fragment03 extends Fragment {

    private TextView textView;

    /**
     * 返回的view对象作为Fragment01的内容显示
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_03, null);
        textView = inflate.findViewById(R.id.tv_fromactivity);
        return inflate;
    }

    public void setText(String string){
        textView.setText(string);
    }
}
