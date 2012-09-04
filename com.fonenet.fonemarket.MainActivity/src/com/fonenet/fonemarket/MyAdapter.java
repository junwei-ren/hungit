package com.fonenet.fonemarket;

import java.io.File;
import java.util.ArrayList;  
import java.util.HashMap;  
  
import android.app.AlertDialog;  
import android.content.Context;  
import android.content.DialogInterface;  
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;  
import android.view.View;  
import android.view.ViewGroup;  
import android.view.View.OnClickListener;  
import android.widget.BaseAdapter;  
import android.widget.Button;  
import android.widget.ImageView;  
import android.widget.TextView;  
/** 
 * @author chenzheng_java 
 * @description ����Ĳ���ʵ��ģ����SimpleAdapter 
 */  
public class MyAdapter extends BaseAdapter {  
	
	Handler uiHandler;
      
    private ArrayList<HashMap<String, Object>> data;  
    /** 
     * LayoutInflater ���Ǵ���ʵ���л�ȡ�����ļ�����Ҫ��ʽ 
     *LayoutInflater layoutInflater = LayoutInflater.from(context); 
     *View convertView = layoutInflater.inflate(); 
     *LayoutInflater��ʹ��,��ʵ�ʿ�����LayoutInflater����໹�Ƿǳ����õ�,�������������� findViewById(), 
    ��ͬ����LayoutInflater��������layout��xml�����ļ�������ʵ������ 
    ��findViewById()���Ҿ���xml�µľ��� widget�ؼ�(��:Button,TextView��)�� 
     */  
    private LayoutInflater layoutInflater;  
    private Context context;  
      
      
    public MyAdapter(Context context,ArrayList<HashMap<String, Object>> data) {  
          
        this.context = context;  
        this.data = data;  
        this.layoutInflater = LayoutInflater.from(context); 
        this.uiHandler = ((Tab1Activity)context).handler;
    }  
  
    /** 
     *��ȡ����  
     */  
    public int getCount() {  
        return data.size();  
    }  
    /** 
     *��ȡĳһλ�õ�����  
     */  
    public Object getItem(int position) {  
        return data.get(position);  
    }  
    /** 
     *��ȡΨһ��ʶ 
     */  
    public long getItemId(int position) {  
        return position;  
    }  
  
    /** 
     * android����ÿһ�е�ʱ�򣬶������������� 
     */  
    public View getView(final int position, View convertView, ViewGroup parent) {  
        ZuJian zuJian = null;  
        if(convertView==null){  
            zuJian = new ZuJian();  
            // ��ȡ�������  
            convertView = layoutInflater.inflate(R.layout.activity_tab1, null);  
            zuJian.imageView = (ImageView) convertView.findViewById(R.id.image);  
            zuJian.titleView = (TextView) convertView.findViewById(R.id.title);  
            zuJian.infoView = (TextView) convertView.findViewById(R.id.info);  
            zuJian.button = (Button) convertView.findViewById(R.id.view_btn);  
            // ����Ҫע�⣬��ʹ�õ�tag���洢���ݵġ�  
            convertView.setTag(zuJian);  
        }  
        else {  
            zuJian = (ZuJian) convertView.getTag();  
        }  
        // �����ݡ��Լ��¼�����  
        
        zuJian.imageView.setBackgroundResource((Integer) data.get(position).get("image"));  
        zuJian.titleView.setText((String)data.get(position).get("title"));  
        zuJian.infoView.setText((String)data.get(position).get("info")); 
        zuJian.button.setOnClickListener(new OnClickListener(){  
  
            public void onClick(View v) { 
            	int p = position;
            	String z = (String)data.get(position).get("title");
  
            	
                showInfo(p); 
                
                 // download file
                new Thread(){
                	public void run(){
                		//try {
                			 HttpDownloader downloader = new HttpDownloader(uiHandler);  
                	            int lrc = downloader.downFile("http://192.168.7.66/Market.apk","test/");  
                	            System.out.println(lrc);  
        					//�����ļ�����������һ��URL���ڶ������·��
        			//	} catch (ClientProtocolException e) {
        					// TODO Auto-generated catch block
        				//	e.printStackTrace();
        			//	} catch (IOException e) {
        					// TODO Auto-generated catch block
        			//		e.printStackTrace();
        			//	}
                	}
                }.start();
                
               // installApk(Environment.getExternalStorageDirectory()+"/test/Market.apk");
            }  
              
        });  
        return convertView;  
    }  
  
    /** 
     *���û������ťʱ�������¼����ᵯ��һ��ȷ�϶Ի��� 
     */  
     public void showInfo(int  pos){    
  
                 new AlertDialog.Builder(context)    
  
                 .setTitle("�ҵ�listview")    
  
                .setMessage("����..."+ pos)    
  
                .setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {    
  
                public void onClick(DialogInterface dialog, int which) {    
  
                     }    
  
                 })    
  
               .show();    
  
            }  
    
  
}  