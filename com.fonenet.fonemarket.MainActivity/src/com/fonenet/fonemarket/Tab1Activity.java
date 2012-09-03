package com.fonenet.fonemarket;
/*
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Tab1Activity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_tab1, menu);
        return true;
    }
}
*/

import java.io.File;
import java.util.ArrayList;  
import java.util.HashMap;  
  
import android.app.ListActivity;  
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;  
import android.os.Handler;
import android.os.Message;
import android.util.Log;  
import android.view.View;  
import android.widget.ListView;  
  
public class Tab1Activity extends ListActivity {  

    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        
        handler = new Handler()
  	  {
  	    @Override
  	    public void handleMessage(Message msg)
  	    {//����һ��Handler�����ڴ��������߳���UI��ͨѶ
  	      if (!Thread.currentThread().isInterrupted())
  	      {
  	        switch (msg.what)
  	        {
  	          case 0:
  	          //  pb.setMax(fileSize);
  	          case 1:
  	           // pb.setProgress(downLoadFileSize);
  	          //  int result = downLoadFileSize * 100 / fileSize;
  	          //  tv.setText(result + "%");
  	          {
  	        	  String fileName = (String)msg.obj;
  	        	installApk(fileName);
  	          }
  	            break;
  	          case 2:
  	         //   Toast.makeText(main.this, "�ļ��������", 1).show();
  	            break;

  	          case -1:
  	         //   String error = msg.getData().getString("error");
  	         //   Toast.makeText(main.this, error, 1).show();
  	            break;
  	        }
  	      }
  	      super.handleMessage(msg);
  	    }
  	  };
        // ��ȡ��������ݣ����ݵĸ�ʽ���ϸ��Ҫ��Ŷ  
        ArrayList<HashMap<String, Object>> data = getData();  
        //ģ��SimpleAdapterʵ�ֵ��Լ���adapter  
        MyAdapter adapter = new MyAdapter(this, data);  
          
        /** 
         * ��Щ�˺��Ժ������Ƕ�֪��vlist2.xml�൱�ڴ洢һ�����ݵ�������֣�������ǰ�ߵĴ����У�������һ���������ļ�main.xml�ģ� 
         * ��������ļ��Ƿ����������ļ�����ʾ�ģ�һ������ж���ͨ��setContentView()��ָ���������ļ��ġ�Ϊ�����������û���õ� 
         * ������listView������һ�������������ء� 
         * �����ǿ���setListAdapter��ListActivity�е�ʵ�֣� 
         * public void setListAdapter(ListAdapter adapter) { 
            synchronized (this) { 
            ensureList(); 
            mAdapter = adapter; 
            mList.setAdapter(adapter); 
        } 
    } 
    ���������һ��ensureList��������������������������� 
     private void ensureList() { 
        if (mList != null) { 
            return; 
        } 
        setContentView(com.android.internal.R.layout.list_content); 
 
    } 
    ���ڿ����ˣ������и� setContentView�������������������ǵ������һ��android�Լ��ṩ�Ľ�������ʾ�� 
    ԭ�������ǵ����ۻ������õģ�ֻ����ListActivity���ҽ���������ʵ�֡� 
         */  
        setListAdapter(adapter);  
        
      /*
        
        new Thread(){
        	public void run(){
        		//try {
        			 HttpDownloader downloader = new HttpDownloader();  
        	            int lrc = downloader.downFile("http://192.168.7.66/test.zip","test/");  
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
          
         */
          
    }  
    
    /** 
     * @author chenzheng_java 
     * @description ׼��һЩ�������� 
     * @return һ��������������Ϣ��hashMap���� 
     */  
    private ArrayList<HashMap<String, Object>> getData(){  
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String,Object>>();  
        for(int i=0;i<3;i++){  
            HashMap<String, Object> tempHashMap = new HashMap<String, Object>();  
            tempHashMap.put("image", R.drawable.ic_launcher);  
            tempHashMap.put("title", "����"+i);  
            tempHashMap.put("info", "��������Ϣ");  
           // tempHashMap.put("title", "2222"); 
           // tempHashMap.put("info", "��������Ϣ"); 
            arrayList.add(tempHashMap);  
        }  
        return arrayList;  
    }  
  
    @Override  
    protected void onListItemClick(ListView l, View v, int position, long id) {  
          
        Log.i("�����Ϣ",v.toString() );  
    } 
    private void installApk(String paramString)
    {
      
      File localFile = new File(paramString);
      Intent localIntent = new Intent();
    //  localIntent.addFlags(268435456);
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.setDataAndType(Uri.fromFile(localFile), "application/vnd.android.package-archive");
      this.startActivity(localIntent);
      Log.e("success", "the end");
    }
      
      
	public Handler handler;    
}  