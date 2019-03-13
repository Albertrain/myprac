package com.train.amm;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.train.amm.domain.News;
import com.train.amm.smartimage.com.loopj.android.image.SmartImageView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class NewsClientActivity extends AppCompatActivity {
    List<News> newsList;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ListView listView = findViewById(R.id.lv_news);
            listView.setAdapter(new BaseAdapter() {
                @Override
                public int getCount() {
                    //条目数量
                    return newsList.size();
                }

                @Override
                public Object getItem(int position) {
                    return null;
                }

                @Override
                public long getItemId(int position) {
                    return 0;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    //view对象， 填充
                    View view = null;
                    ViewHolder viewHolder;
                    if(convertView == null){
                        view = View.inflate(NewsClientActivity.this,R.layout.news_item_list,null);

                        viewHolder = new ViewHolder();
                        //把布局文件中的所有组建封装到viewholder对象中
                        viewHolder.tv_title = view.findViewById(R.id.tv_news_title);
                        viewHolder.tv_detail = view.findViewById(R.id.tv_news_content);
                        viewHolder.tv_comment = view.findViewById(R.id.tv_news_comment);
                        viewHolder.smartImageView = view.findViewById(R.id.iv_news_image);
                        //把viewholder对象封装到view中
                        view.setTag(viewHolder);
                    }else{
                        view = convertView;
                        viewHolder = (ViewHolder) view.getTag();
                    }

                    News news = newsList.get(position);

//                    TextView tv_title = view.findViewById(R.id.tv_news_title);
                    viewHolder.tv_title.setText(news.getTitle());

//                    TextView tv_detail = view.findViewById(R.id.tv_news_content);
                    viewHolder.tv_detail.setText(news.getDetail());

//                    TextView tv_comment = view.findViewById(R.id.tv_news_comment);
                    viewHolder.tv_comment.setText(news.getComment() + "条评论");

//                    SmartImageView smartImageView = view.findViewById(R.id.iv_news_image);
                    viewHolder.smartImageView.setImageUrl(news.getImageUrl());

                    return view;
                }


                class ViewHolder{
                    //条目里有啥内容这里就定义什么属性
                    TextView tv_title;
                    TextView tv_detail;
                    TextView tv_comment;
                    SmartImageView smartImageView;
                }
            });

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_client);

        getNewsInfo();


    }

    private void getNewsInfo() {
        //开启线程下载新闻数据
        Thread t = new Thread() {
            @Override
            public void run() {
                String path = "http://192.168.6.238:8080/news.xml";
                URL url = null;
                try {
                    url = new URL(path);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setConnectTimeout(5000);
                    urlConnection.setReadTimeout(5000);
                    if (urlConnection.getResponseCode() == 200) {
                        //解析此流
                        InputStream inputStream = urlConnection.getInputStream();
                        //用xml解析器解析流内容
                        parseXml(inputStream);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };t.start();
    }

    //xml解析器
    public void parseXml(InputStream is) {
        System.out.println("parse");
        XmlPullParser xmlPullParser = Xml.newPullParser();
        News news = null;
        try {
            xmlPullParser.setInput(is, "utf-8");

            int type = xmlPullParser.getEventType();

            while (type != XmlPullParser.END_DOCUMENT) {
                switch (type) {
                    case XmlPullParser.START_TAG:
                        if ("newslist".equals(xmlPullParser.getName())) {
                            newsList = new ArrayList<News>();
                        } else if ("news".equals(xmlPullParser.getName())) {
                            news = new News();
                        } else if ("title".equals(xmlPullParser.getName())) {
                            String title = xmlPullParser.nextText();
                            news.setTitle(title);
                        } else if ("detail".equals(xmlPullParser.getName())) {
                            String detail = xmlPullParser.nextText();
                            news.setDetail(detail);
                        } else if ("comment".equals(xmlPullParser.getName())) {
                            String comment = xmlPullParser.nextText();
                            news.setComment(comment);
                        } else if ("image".equals(xmlPullParser.getName())) {
                            String image = xmlPullParser.nextText();
                            news.setImageUrl(image);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if ("news".equals(xmlPullParser.getName())) {
                            newsList.add(news);
                        }
                        break;
                }

                //解析完成后，移动到下一个节点，且返回节点类型
                type = xmlPullParser.next();
            }

            //发送消息，让主线程设置listview Adapter
            //如果不需要携带数据，可发送空消息
            handler.sendEmptyMessage(1);

            for(News nw: newsList){
                System.out.println(nw.toString());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
