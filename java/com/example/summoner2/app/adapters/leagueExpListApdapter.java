package com.example.summoner2.app.adapters;

/**
 * Created by alex on 14.05.14.
 */
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.example.summoner2.app.R;
import com.example.summoner2.app.models.League;
import com.example.summoner2.app.models.Category;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class leagueExpListApdapter extends BaseExpandableListAdapter {

    private List<Category> catList;
    private int itemLayoutId;
    private int groupLayoutId;
    private Context ctx;
    private int wins;

    public leagueExpListApdapter(List<Category> catList, Context ctx) {

        this.itemLayoutId = R.layout.league;
        this.groupLayoutId = R.layout.group_layout;
        this.catList = catList;
        this.ctx = ctx;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return catList.get(groupPosition).getItemList().get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return catList.get(groupPosition).getItemList().get(childPosition).hashCode();
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)ctx.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.league, parent, false);
        }

        //TextView tier = (TextView) v.findViewById(R.id.tier);
        TextView rank = (TextView) v.findViewById(R.id.rank);
        TextView lp = (TextView) v.findViewById(R.id.lp);
        ImageView im = (ImageView) v.findViewById(R.id.tierImage);

        League det = catList.get(groupPosition).getItemList().get(childPosition);

        //tier.setText(det.getTier().toString());
        rank.setText(det.getRank().toString());
        lp.setText(Integer.toString(det.getLeaguePoints()));

        try {
            Class res = R.drawable.class;
            Field field = res.getField(det.getTier().toLowerCase());
            int drawableId = field.getInt(null);
            im.setImageResource(drawableId);
        }
        catch (Exception e) {
            Log.e("MyTag", "Failure to get drawable id.", e);
        }

        return v;

    }

    @Override
    public int getChildrenCount(int groupPosition) {
        int size = catList.get(groupPosition).getItemList().size();
        System.out.println("Child for group ["+groupPosition+"] is ["+size+"]");
        return size;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return catList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return catList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return catList.get(groupPosition).hashCode();
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater)ctx.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.group_layout, parent, false);
        }

        TextView groupName = (TextView) v.findViewById(R.id.groupName);
        TextView groupDescr = (TextView) v.findViewById(R.id.groupDescr);


        Category cat = catList.get(groupPosition);

        groupName.setText(cat.getName());
        groupDescr.setText(cat.getDescr());

        return v;

    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
