package com.example.navigationdarawerexpandablelistview.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.navigationdarawerexpandablelistview.R;

import java.util.List;
import java.util.Map;

public class Adapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<String> listTitles;
    private Map<String,List<String>> listItems;

    public Adapter(Context mContext, List<String> titles, Map<String, List<String>> listItems) {
        this.mContext = mContext;
        this.listTitles = titles;
        this.listItems = listItems;
    }

    @Override
    public int getGroupCount() {
        return listTitles.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listItems.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listTitles.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listItems.get(listTitles.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String groupTitle = (String) getGroup(groupPosition);

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_group_header,null);
        }

        TextView lblListHeader = convertView.findViewById(R.id.lblListHeader);

        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(groupTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String childTitle = (String) getChild(groupPosition,childPosition);

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_group_child,null);
        }

        TextView lblListHeader = convertView.findViewById(R.id.lblListItem);

        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(childTitle);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
