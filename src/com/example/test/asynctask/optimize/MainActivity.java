package com.example.test.asynctask.optimize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.GridView;
/**
 * 需要在列表快速滑动
11-05 14:53:28.847: E/AndroidRuntime(16560): FATAL EXCEPTION: main
11-05 14:53:28.847: E/AndroidRuntime(16560): java.util.concurrent.RejectedExecutionException: Task android.os.AsyncTask$3@42663500 rejected from java.util.concurrent.ThreadPoolExecutor@421f5390[Running, pool size = 128, active threads = 127, queued tasks = 10, completed tasks = 207]
11-05 14:53:28.847: E/AndroidRuntime(16560): 	at java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:1979)
11-05 14:53:28.847: E/AndroidRuntime(16560): 	at java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:786)
11-05 14:53:28.847: E/AndroidRuntime(16560): 	at java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1307)
11-05 14:53:28.847: E/AndroidRuntime(16560): 	at android.os.AsyncTask.executeOnExecutor(AsyncTask.java:589)
11-05 14:53:28.847: E/AndroidRuntime(16560): 	at android.os.AsyncTask.execute(AsyncTask.java:534)
11-05 14:53:28.847: E/AndroidRuntime(16560): 	at com.example.test.asynctask.optimize.CustomAdapter.getView(CustomAdapter.java:70)
11-05 14:53:28.847: E/AndroidRuntime(16560): 	at android.widget.AbsListView.obtainView(AbsListView.java:2161)
11-05 14:53:28.847: E/AndroidRuntime(16560): 	at android.widget.GridView.makeAndAddView(GridView.java:1341)
11-05 14:53:28.847: E/AndroidRuntime(16560): 	at android.widget.GridView.makeRow(GridView.java:341)
11-05 14:53:28.847: E/AndroidRuntime(16560): 	at android.widget.GridView.fillDown(GridView.java:283)
11-05 14:53:28.847: E/AndroidRuntime(16560): 	at android.widget.GridView.fillGap(GridView.java:243)
11-05 14:53:28.847: E/AndroidRuntime(16560): 	at android.widget.AbsListView.trackMotionScroll(AbsListView.java:4970)
11-05 14:53:28.847: E/AndroidRuntime(16560): 	at android.widget.AbsListView$FlingRunnable.run(AbsListView.java:4127)
11-05 14:53:28.847: E/AndroidRuntime(16560): 	at android.view.Choreographer$CallbackRecord.run(Choreographer.java:749)
11-05 14:53:28.847: E/AndroidRuntime(16560): 	at android.view.Choreographer.doCallbacks(Choreographer.java:562)
11-05 14:53:28.847: E/AndroidRuntime(16560): 	at android.view.Choreographer.doFrame(Choreographer.java:531)
11-05 14:53:28.847: E/AndroidRuntime(16560): 	at android.view.Choreographer$FrameDisplayEventReceiver.run(Choreographer.java:735)
11-05 14:53:28.847: E/AndroidRuntime(16560): 	at android.os.Handler.handleCallback(Handler.java:730)
11-05 14:53:28.847: E/AndroidRuntime(16560): 	at android.os.Handler.dispatchMessage(Handler.java:92)
11-05 14:53:28.847: E/AndroidRuntime(16560): 	at android.os.Looper.loop(Looper.java:137)
11-05 14:53:28.847: E/AndroidRuntime(16560): 	at android.app.ActivityThread.main(ActivityThread.java:5103)
11-05 14:53:28.847: E/AndroidRuntime(16560): 	at java.lang.reflect.Method.invokeNative(Native Method)
11-05 14:53:28.847: E/AndroidRuntime(16560): 	at java.lang.reflect.Method.invoke(Method.java:525)
11-05 14:53:28.847: E/AndroidRuntime(16560): 	at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:737)
11-05 14:53:28.847: E/AndroidRuntime(16560): 	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:553)
11-05 14:53:28.847: E/AndroidRuntime(16560): 	at dalvik.system.NativeStart.main(Native Method)

 */
public class MainActivity extends Activity {

	private GridView mGridView;
	private List<HashMap<String, Object>> mData;

	private BaseAdapter mAdapter;
	private ProgressDialog mProgressDialog;

	private static final int DIALOG_PROGRESS = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mGridView = (GridView) findViewById(R.id.gridview);
		mData = new ArrayList<HashMap<String, Object>>();
		mAdapter = new CustomAdapter(getApplicationContext(), mData);
		mGridView.setAdapter(mAdapter);

	}

	protected void onStart() {
		super.onStart();
		new GetGridDataTask(mData, mAdapter, MainActivity.this).execute();//执行获取数据的任务
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_PROGRESS:
			mProgressDialog = new ProgressDialog(MainActivity.this);
			mProgressDialog.setMessage("正在获取数据");
			mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

			return mProgressDialog;

		}
		return null;
	}

}
