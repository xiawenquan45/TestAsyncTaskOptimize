package com.example.test.asynctask.optimize;

import java.util.HashMap;
import java.util.List;

import android.os.AsyncTask;

public class GetItemImageTask extends AsyncTask<Void, Void, Void> {
	
	private List<HashMap<String, Object>> mData;  
	private CustomAdapter mAdapter;
	private int pos;  
    
    GetItemImageTask(int pos,List<HashMap<String, Object>> mData,CustomAdapter mAdapter) {  
        this.pos = pos;  
        this.mAdapter = mAdapter;
        this.mData =mData;
    }  

    @Override  
    protected Void doInBackground(Void... params) {  
        try {  
            Thread.sleep(2000); //模拟耗时的网络操作   
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        mData.get(pos).put("pic", R.drawable.ic_launcher);  
        return null;  
    }  
      
    protected void onPostExecute (Void result) {  
    	((CustomAdapter)mAdapter).setData(mData);
        mAdapter.notifyDataSetChanged();//通知ui界面更新   
    }  
}
