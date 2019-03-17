
package com.train.amm;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.train.amm.domain.Contact;

/**
 * 联系人数据库
 * raw_contacts表
 * contact_id：联系人id
 * data表：存放联系人的详细的信息，每行数据是单独的一条联系人信息
 * data1：联系人的具体的信息
 * rawcontactid：该行信息属于哪个联系人
 * mimetype_id：该行信息属于什么类型
 * mimetypes表：mimetype_id对应的类型的字符串
 */
public class SystemContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_contact);
    }

    /**
     * 查询联系人
     * @param view
     */
    public void toContact(View view) {
        //读写联系人权限
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(Uri.parse("content://com.android.contacts/raw_contacts"), new String[]{"contact_id"}, null, null, null);
        while (cursor.moveToNext()) {
            String contactId = cursor.getString(cursor.getColumnIndex("contact_id"));
            Cursor cursor1 = contentResolver.query(Uri.parse("content://com.android.contacts/data"), new String[]{"data1", "mimetype"},
                    "raw_contact_id = ?", new String[]{contactId}, null);

            Contact con = new Contact();
            while (cursor1.moveToNext()){
                String data1 = cursor1.getString(0);
                String mimetype = cursor1.getString(1);
                //通过mimetype的判断，把data1存入对应的属性
                if("vnd.android.cursor.item/email_v2".equals(mimetype)){
                    con.setEmail(data1);
                }
                else if("vnd.android.cursor.item/phone_v2".equals(mimetype)){
                    con.setPhone(data1);
                }
                else if("vnd.android.cursor.item/name".equals(mimetype)){
                    con.setName(data1);
                }
            }
            System.out.println(con.toString());
        }
    }

    /**
     * 插入联系人
     * @param view
     */
    public void insertContact(View view){
        ContentResolver cr = getContentResolver();
        //先查询raw_contacts表，获取最新联系人的主键，然后主键+1，就是要插入的联系人的id
        Cursor cursorContactId = cr.query(Uri.parse("content://com.android.contacts/raw_contacts"), new String[]{"_id"}, null, null, null);
        //默认联系人id就是1
        int contact_id = 1;
        if(cursorContactId.moveToLast()){
            //拿到主键
            int _id = cursorContactId.getInt(0);
            //主键+1，就是要插入的联系人id
            contact_id = ++_id;
        }

        ContentValues values = new ContentValues();
        values.put("contact_id", contact_id);
        //把联系人id插入raw_contacts数据库
        cr.insert(Uri.parse("content://com.android.contacts/raw_contacts"), values);

        values.clear();
        values.put("data1", "insertcontact1");
        values.put("mimetype", "vnd.android.cursor.item/name");
        values.put("raw_contact_id", contact_id);
        cr.insert(Uri.parse("content://com.android.contacts/data"), values);

        values.clear();
        values.put("data1", "1344567");
        values.put("mimetype", "vnd.android.cursor.item/phone_v2");
        values.put("raw_contact_id", contact_id);
        cr.insert(Uri.parse("content://com.android.contacts/data"), values);
    }
}
