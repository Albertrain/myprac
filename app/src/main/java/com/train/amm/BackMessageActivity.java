package com.train.amm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Xml;
import android.view.View;

import com.train.amm.domain.Message;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BackMessageActivity extends AppCompatActivity {
    List<Message> list;
    List<Message> msgList;
    Message msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_message);

        list = new ArrayList<Message>();
        for (int i = 0; i <= 9; i++) {
            Message msg = new Message("message" + i, System.currentTimeMillis() + "", "152" + i, "1");
            list.add(msg);
        }
    }

    public void backupMessage(View view) {
        //在内存中把xml备份短信的格式拼接出来
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version='1.0' encoding='utf-8' standalone='yes' ?>");
        sb.append("<messages>");
        for (Message sms : list) {
            sb.append("<sms>");

            sb.append("<body>");
            sb.append(sms.getBody());
            sb.append("</body>");

            sb.append("<date>");
            sb.append(sms.getDate());
            sb.append("</date>");

            sb.append("<type>");
            sb.append(sms.getType());
            sb.append("</type>");

            sb.append("<address>");
            sb.append(sms.getAddress());
            sb.append("</address>");

            sb.append("</sms>");
        }
        sb.append("</messages>");

        File file = new File("sdcard/msg.xml");

        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(sb.toString().getBytes());
            fos.close();
            System.out.println("backup success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backupMessage2(View view) {
        //使用xm序列化器生成xml文件
        //获取xml序列化器对象
        XmlSerializer xmls = Xml.newSerializer();

        //初始化
        File file = new File("sdcard/sms.xml");

        try {
            FileOutputStream fos = new FileOutputStream(file);
            xmls.setOutput(fos, "utf-8");

            //开始生成
            //这的encoding是xml文件头节点里的属性值
            xmls.startDocument("utf-8", true);


            xmls.startTag(null, "message");

            for (Message msg : list) {
                xmls.startTag(null, "sms");

                xmls.startTag(null, "body");
                xmls.text(msg.getBody());
                xmls.endTag(null, "body");

                xmls.startTag(null, "date");
                xmls.text(msg.getDate());
                xmls.endTag(null, "date");

                xmls.startTag(null, "address");
                xmls.text(msg.getAddress());
                xmls.endTag(null, "address");

                xmls.startTag(null, "type");
                xmls.text(msg.getType());
                xmls.endTag(null, "type");

                xmls.endTag(null, "sms");
            }

            xmls.endTag(null, "message");

            //标记结束
            xmls.endDocument();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //解析xml文件
    public void showXml(View view) {
        //得到解析器对象
        XmlPullParser xp = Xml.newPullParser();
        try {
            FileInputStream fis = new FileInputStream("sdcard/sms.xml");
            xp.setInput(fis, "utf-8");

            //获取节点类型
            int eventType = xp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                //根据节点类型不同进行不同的 操作
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        //获取当前节点名字
                        if ("message".equals(xp.getName())) {
                            //创建message集合对象
                            msgList = new ArrayList<Message>();
                        } else if ("sms".equals(xp.getName())) {
                            msg = new Message();
                        } else if ("body".equals(xp.getName())) {
                            String body = xp.nextText();
                            msg.setBody(body);
                        } else if ("type".equals(xp.getName())) {
                            String type = xp.nextText();
                            msg.setType(type);
                        } else if ("address".equals(xp.getName())) {
                            String address = xp.nextText();
                            msg.setAddress(address);
                        } else if ("date".equals(xp.getName())) {
                            String date = xp.nextText();
                            msg.setDate(date);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("sms".equals(xp.getName())) {
                            //把sms的javabean放到集合中
                            msgList.add(msg);
                        }
                        break;
                }

                //把指针移动到下一个节点，且返回该节点的事件类型
                eventType = xp.next();
            }

            //打印读取到的集合
            for (Message msg : msgList )
            {
                System.out.println(msg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
