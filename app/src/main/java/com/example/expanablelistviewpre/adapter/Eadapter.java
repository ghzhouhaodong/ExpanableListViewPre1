package com.example.expanablelistviewpre.adapter;

import java.util.List;

import com.example.expanablelistviewpre.R;
import com.example.expanablelistviewpre.R.drawable;
import com.example.expanablelistviewpre.R.id;
import com.example.expanablelistviewpre.R.layout;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Eadapter extends BaseExpandableListAdapter{

	Context context;//上下文
	List<String> gList;//分组数据
	List<List<String>> cLists;//每个组的数据
	List<List<Integer>> cImgLists;//每个组图标
	
	public Eadapter(Context context, List<String> gList,
			List<List<String>> cLists, List<List<Integer>> cImgLists) {
		this.context = context;
		this.gList = gList;
		this.cLists = cLists;
		this.cImgLists = cImgLists;
	}

	/**
	 * 获取组的个数
	 */
	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return gList.size();
	}

	/**
	 * 根据组的位置 获取子元素的个数
	 */
	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return cLists.get(groupPosition).size();
	}

	/**
	 * 获取组的数据
	 */
	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return gList.get(groupPosition);
	}

	/**
	 * 根据组的位置 获取组中子元素的数据
	 */
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return cLists.get(groupPosition).get(childPosition);
	}

	/**
	 * 获取组的id 要唯一
	 */
	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	/**
	 * 获取组中子元素的id
	 */
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	/**
	 * 组和子元素之间是否持有稳定的id 意思是数据改变不会影响他们
	 */
	@Override
	public boolean hasStableIds() {
		return true;
	}

	/**
	 * 获取组显示的视图
	 * int groupPosition, 租的位置
	 *  boolean isExpanded, 该组是展开状态还是伸缩状态
	 *  View convertView, 
	 *  ViewGroup parent
	 */
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		GHolder holder;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.group_layout, null);
			holder = new GHolder();
			
			holder.textView = (TextView) convertView.findViewById(R.id.group_name);
			holder.imageView = (ImageView) convertView.findViewById(R.id.img);
			
			convertView.setTag(holder);
		}else {
			holder = (GHolder) convertView.getTag();
		}
		
		if (! isExpanded) {
			holder.imageView.setImageResource(R.drawable.a);
		}else {
			holder.imageView.setImageResource(R.drawable.b);
		}
		
		holder.textView.setText(gList.get(groupPosition));
		return convertView;
	}

	/**
	 * 获取组中子条目的视图
	 * int groupPosition, 组的位置
	 * int childPosition,子元素的位置
	 * boolean isLastChild, 子条目是否是该组中最后一个
	 * View convertView, 
	 * ViewGroup parent
	 */
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		CHolder holder;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.child_layout, null);
			holder = new CHolder();
			
			holder.textView = (TextView) convertView.findViewById(R.id.name);
			holder.imageView = (ImageView) convertView.findViewById(R.id.iam);
			
			convertView.setTag(holder);
		}else {
			holder = (CHolder) convertView.getTag();
		}
		
		
		holder.textView.setText(cLists.get(groupPosition).get(childPosition));
		holder.imageView.setImageResource(cImgLists.get(groupPosition).get(childPosition));
		return convertView;
	}

	/**
	 * 指定位置上的子元素是否可选中
	 * false不响应子条目点击
	 */
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}
	
	class GHolder {
		TextView textView;
		ImageView imageView;
	}
	class CHolder {
		TextView textView;
		ImageView imageView;
	}

}
