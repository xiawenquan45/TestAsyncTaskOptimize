package com.example.test.asynctask.optimize;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.BaseAdapter;

public class GetGridDataTask extends AsyncTask<Void, Void, Void> {
	private List<HashMap<String, Object>> mData;  
	private BaseAdapter mAdapter;
	private static final int DIALOG_PROGRESS = 0;  
	private Activity mActivity;
	public GetGridDataTask(List<HashMap<String, Object>> mData,BaseAdapter mAdapter,Activity mActivity){
		this.mAdapter = mAdapter;
		this.mData = mData;
		this.mActivity = mActivity;
	}
	
	@Override
	protected void onPreExecute () {  
        mData.clear();  
        mAdapter.notifyDataSetChanged();  
          
        mActivity.showDialog(DIALOG_PROGRESS);//打开等待对话框   
    }  
      
    @Override  
    protected Void doInBackground(Void... params) {  
          
        try {  
            Thread.sleep(500);//模拟耗时的网络操作   
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        for(int i = 0; i < 200; i++) {  
            HashMap<String, Object> hm = new HashMap<String, Object>();  
            hm.put("title", "Title");  
            mData.add(hm);  
        }  
          
        return null;  
    }  
     
    @Override
    protected void onPostExecute (Void result) {  
    	((CustomAdapter)mAdapter).setData(mData);
        mAdapter.notifyDataSetChanged();//通知ui界面更新   
        mActivity.dismissDialog(DIALOG_PROGRESS);//关闭等待对话框   
    }

}
