package com.xmy.recylerviewdemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.xmy.recylerviewdemo.adapter.MyAdapter;
import com.xmy.recylerviewdemo.bean.MyItemBean;
import com.xmy.recylerviewdemo.decoration.MyDecoration;
import com.xmy.recylerviewdemo.layoutmanager.MyLayoutManager;

public class MainActivity extends Activity {

	private RecyclerView mRecyclerView;

	private List<MyItemBean> mData;
	private MyAdapter mAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initData();
	}
	
	/**
	 * ��ʼ��RecylerView
	 */
	private void initView(){
		mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
		MyLayoutManager manager = new MyLayoutManager(this);
		manager.setOrientation(LinearLayout.HORIZONTAL);//Ĭ����LinearLayout.VERTICAL
		mRecyclerView.setLayoutManager(manager);
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
	}
	
	private void initData(){
		this.mData = new ArrayList<MyItemBean>();
		for(int i=0;i<20;i++){
			MyItemBean bean = new MyItemBean();
			bean.tv = "Xmy"+i;
			mData.add(bean);
		}
		this.mAdapter = new MyAdapter(mData);
		this.mRecyclerView.setAdapter(mAdapter);
		RecyclerView.ItemDecoration decoration = new MyDecoration(this);
		this.mRecyclerView.addItemDecoration(decoration);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int size = mData.size();
		switch (item.getItemId()) {
		case R.id.action_add:
			MyItemBean bean = new MyItemBean();
			bean.tv = "Xmy"+(size);
			mData.add(bean);
			mAdapter.notifyItemInserted(1);
			break;
		case R.id.action_remove:
			mData.remove(size-1);
			mAdapter.notifyItemRemoved(1);
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}