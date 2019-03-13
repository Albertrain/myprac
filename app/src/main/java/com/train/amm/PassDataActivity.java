package com.train.amm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PassDataActivity extends AppCompatActivity {

    private EditText editText;
    private EditText editText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_data);
    }

    //用intent传递数据
    public void lookScore(View view) {
        EditText editText = findViewById(R.id.et_name1);
        EditText editText1 = findViewById(R.id.et_name2);

        String name1 = editText.getText().toString();
        String name2 = editText1.getText().toString();

        //使用intent传递数据到下一个Activity
        Intent intent = new Intent(this, ScoreShowActivity.class);
        intent.putExtra("name1", name1);
        intent.putExtra("name2", name2);
        startActivity(intent);
    }

    //用bundle传递数据
    public void lookScore2(View view){
        Intent intent = new Intent(this, ScoreShowActivity2.class);

        editText = findViewById(R.id.et_name1);
        editText1 = findViewById(R.id.et_name2);

        String name1 = editText.getText().toString();
        String name2 = editText1.getText().toString();


        Bundle bundle = new Bundle();
        bundle.putString("name1",name1);
        bundle.putString("name2",name2);
        intent.putExtras(bundle);

        startActivity(intent);

    }
}
