package com.example.test.asynctask.optimize;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
	
	private Context mContext;
	private List<HashMap<String,Object>> mData ;
	
	public CustomAdapter(Context mContext,List<HashMap<String,Object>> mData){
		this.mContext = mContext;
		this.mData = mData;
	}
	
	public void setData(List<HashMap<String,Object>> mData ){
		this.mData = mData;
	}
	
	@Override  
    public int getCount() {  
        return mData.size();  
    }  

    @Override  
    public Object getItem(int position) {  
        return mData.get(position);  
    }  

    @Override  
    public long getItemId(int position) {  
        return position;  
    }  

    @Override  
    public View getView(int position, View convertView, ViewGroup parent) {  
        View view = convertView;  
        ViewHolder vh;  
        if(view == null) {  
            view = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);  
            vh = new ViewHolder();  
            vh.tv = (TextView) view.findViewById(R.id.textView);  
            vh.iv = (ImageView) view.findViewById(R.id.imageView);  
            view.setTag(vh);  
        }  
        vh = (ViewHolder) view.getTag();  
        vh.tv.setText((String) mData.get(position).get("title"));  
        Integer id = (Integer) mData.get(position).get("pic");  
        if(id != null) {  
            vh.iv.setImageResource(id);  
        }  
        else {  
            vh.iv.setImageBitmap(null);  
        }  
          
        AsyncTask task = (AsyncTask) mData.get(position).get("task");  
        if(task == null || task.isCancelled()) {  
            Log.d("Test", "" + position);  
//            mData.get(position).put("task", new GetItemImageTask(position).execute(null));//执行获取图片的任务   
            mData.get(position).put("task", new GetItemImageTask(position, mData, CustomAdapter.this).execute(null));//执行获取图片的任务   
        }  
          
        return view;  
    }  
    
     class ViewHolder {  
        TextView tv;  
        ImageView iv;  
    }
      
      
}  
  

