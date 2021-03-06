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
  	    {//定义一个Handler，用于处理下载线程与UI间通讯
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
  	         //   Toast.makeText(main.this, "文件下载完成", 1).show();
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
        // 获取虚拟的数据，数据的格式有严格的要求哦  
        ArrayList<HashMap<String, Object>> data = getData();  
        //模仿SimpleAdapter实现的自己的adapter  
        MyAdapter adapter = new MyAdapter(this, data);  
          
        /** 
         * 有些人很迷糊，我们都知道vlist2.xml相当于存储一行数据的组件布局，我们在前边的代码中，都是有一个主布局文件main.xml的， 
         * 组件布局文件是放在主布局文件上显示的，一般代码中都是通过setContentView()来指定主布局文件的。为何这里根本就没有用到 
         * ，但是listView还能有一个界面来呈现呢。 
         * 让我们看看setListAdapter在ListActivity中的实现， 
         * public void setListAdapter(ListAdapter adapter) { 
            synchronized (this) { 
            ensureList(); 
            mAdapter = adapter; 
            mList.setAdapter(adapter); 
        } 
    } 
    里面调用了一个ensureList方法，我们再来看看这个方法： 
     private void ensureList() { 
        if (mList != null) { 
            return; 
        } 
        setContentView(com.android.internal.R.layout.list_content); 
 
    } 
    现在看到了，这里有个 setContentView方法，里面设置了我们的组件在一个android自己提供的界面上显示。 
    原来，我们的理论还是适用的，只不过ListActivity给我进行了隐藏实现。 
         */  
        setListAdapter(adapter);  
        
      /*
        
        new Thread(){
        	public void run(){
        		//try {
        			 HttpDownloader downloader = new HttpDownloader();  
        	            int lrc = downloader.downFile("http://192.168.7.66/test.zip","test/");  
        	            System.out.println(lrc);  
					//下载文件，参数：第一个URL，第二个存放路径
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
     * @description 准备一些测试数据 
     * @return 一个包含了数据信息的hashMap集合 
     */  
    private ArrayList<HashMap<String, Object>> getData(){  
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String,Object>>();  
        for(int i=0;i<3;i++){  
            HashMap<String, Object> tempHashMap = new HashMap<String, Object>();  
            tempHashMap.put("image", R.drawable.ic_launcher);  
            tempHashMap.put("title", "标题"+i);  
            tempHashMap.put("info", "描述性信息");  
           // tempHashMap.put("title", "2222"); 
           // tempHashMap.put("info", "描述性信息"); 
            arrayList.add(tempHashMap);  
        }  
        return arrayList;  
    }  
  
    @Override  
    protected void onListItemClick(ListView l, View v, int position, long id) {  
          
        Log.i("输出信息",v.toString() );  
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