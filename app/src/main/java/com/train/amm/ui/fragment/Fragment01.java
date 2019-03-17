package com.train.amm.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.train.amm.FragmentActivity;
import com.train.amm.R;

public class Fragment01 extends Fragment {
    /**
     * 返回的view对象作为Fragment01的内容显示
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_01, null);
        Button button = inflate.findViewById(R.id.bt_toactivity);
        final EditText editText = inflate.findViewById(R.id.et_toactivity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                ((FragmentActivity)getActivity()).setText(text);
            }
        });
        return inflate;
    }
}
