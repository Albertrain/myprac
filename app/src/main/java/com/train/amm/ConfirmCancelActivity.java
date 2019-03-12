package com.train.amm;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ConfirmCancelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_cancel);
    }

    public void clickConfirm(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_launcher_background);
        builder.setTitle("删除文件");
        builder.setMessage("删除不可逆，请谨慎操作");

        //设置确定
        builder.setPositiveButton("confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ConfirmCancelActivity.this, "delete success", Toast.LENGTH_LONG).show();
            }
        });

        //设置取消
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ConfirmCancelActivity.this, "operate canceled", Toast.LENGTH_LONG).show();
            }
        });

        //使用创建器，生成对话框对象
        builder.show();
    }

    //单选对话框实现
    public void singleClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("select your sex");

        final String [] selection = new String[]{
            "male",
            "female"

        };

        builder.setSingleChoiceItems(selection, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                 Toast.makeText(ConfirmCancelActivity.this,"your sex is: "+selection,Toast.LENGTH_LONG).show();
                 dialog.dismiss();
            }
        });
        builder.show();
    }

    //多选对话框的实现
    public void mutiSelectClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("select your man");

        final String[] selection2 = new String[]{
                "albert",
                "alice",
                "amy",
                "allen"
        };

        final boolean [] checkedbox = new boolean[]{
                true,
                true,
                false,
                false
        };

        builder.setMultiChoiceItems(selection2, checkedbox, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    checkedbox[which] = isChecked;
            }
        });

        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String string = "";
                for(int i = 0;i<4;i++){
                    string += checkedbox[i] ? selection2[i] +",": "";
                }
                Toast.makeText(ConfirmCancelActivity.this,string,Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
