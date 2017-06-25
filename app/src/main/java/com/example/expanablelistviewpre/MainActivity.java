package com.example.expanablelistviewpre;

import java.util.ArrayList;
import java.util.List;

import com.example.expanablelistviewpre.adapter.Eadapter;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class MainActivity extends Activity {

	private ExpandableListView listView;
	private List<String> gList;
	private List<String> cList;
	private List<List<String>> cLists;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		listView = (ExpandableListView) findViewById(R.id.e_list);
		//设置不使用默认的指示图标---对应着xml属性
		listView.setGroupIndicator(null);
		/**
		 * 1.位置只能放在固定的位置上
		 * 2.会覆盖在你原本的条目view上面
		 */
		//listView.setGroupIndicator(getResources().getDrawable(R.drawable.expa_select));
		//设置子元素的图标---对应着xml属性
		//listView.setChildIndicator(getResources().getDrawable(R.drawable.a));
		
		//组的数据
		gList = new ArrayList<String>();
		gList.add("我的好友");
		gList.add("我的家人");
		gList.add("同事");
		gList.add("同学");
		
		//一个组中的数据
		cList = new ArrayList<String>();
		cList.add("范冰冰");
		cList.add("高圆圆");
		cList.add("柳岩");
		cList.add("龙泽");
		//每个组的数据放在一个集合中,,,方便起见,数据使用一样的
		cLists = new ArrayList<List<String>>();
		cLists.add(cList);
		cLists.add(cList);
		cLists.add(cList);
		cLists.add(cList);
		
		
		//各个组里面的头像数据
		List<Integer> cImgList = new ArrayList<Integer>();
		cImgList.add(R.drawable.ic_launcher);
		cImgList.add(R.drawable.ic_launcher);
		cImgList.add(R.drawable.ic_launcher);
		cImgList.add(R.drawable.ic_launcher);
		//所有头像数据加到一个集合中
		List<List<Integer>> cImgLists = new ArrayList<List<Integer>>();
		cImgLists.add(cImgList);
		cImgLists.add(cImgList);
		cImgLists.add(cImgList);
		cImgLists.add(cImgList);
		
		Eadapter adapter = new Eadapter(MainActivity.this,gList,cLists,cImgLists);
		listView.setAdapter(adapter);
		
		listView.setOnGroupClickListener(new OnGroupClickListener() {
			
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				//listView.isGroupExpanded(groupPosition) 展开,缩起在点击之后执行
				Log.i("OnGroupClickListener", groupPosition+"---OnGroupClickListener---"+listView.isGroupExpanded(groupPosition));
				/**
				 * 先执行的是点击事件 再去执行展开或者缩起
				 * false表示点击的动作还会向下传递 ,只有向下传递了才会继续展开或者缩起
				 * 如果返回true表示点击的动作自己处理 不在向下传递 ,就不会展开
				 * 
				 * 所以 ...如果当前组里面 不存在子元素 可以返回true 不再打开
				 */
				return false;
			}
		});
		
		listView.setOnGroupExpandListener(new OnGroupExpandListener() {
			
			@Override
			public void onGroupExpand(int groupPosition) {
				Log.i("OnGroupExpandListener", groupPosition+"---OnGroupExpandListener--"+listView.isGroupExpanded(groupPosition));
				
			}
		});
		
		listView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
			
			@Override
			public void onGroupCollapse(int groupPosition) {
				Log.i("OnGroupCollapseListener", groupPosition+"---OnGroupCollapseListener");
			}
		});
		
		listView.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				//Log.i("OnChildClickListener", groupPosition+"--"+childPosition+"--"+cLists.get(groupPosition).get(childPosition));
				//发送广播 进行跳转传值
				Intent intent = new Intent();
				intent.setAction("com.action.fasong");
				intent.putExtra("name", cLists.get(groupPosition).get(childPosition));
				MainActivity.this.sendBroadcast(intent);
				return false;
			}
		});
		
		//展开指定组  在设置适配器之后
		//listView.expandGroup(0);
		//设置选择指定组 焦点会给它
		//listView.setSelectedGroup(3);
	}

}
