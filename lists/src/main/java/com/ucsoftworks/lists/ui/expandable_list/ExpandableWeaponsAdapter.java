package com.ucsoftworks.lists.ui.expandable_list;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ucsoftworks.lists.R;
import com.ucsoftworks.lists.model.GroupedWeapons;
import com.ucsoftworks.lists.model.Weapon;
import com.ucsoftworks.lists.ui.WeaponsViewHolder;
import com.ucsoftworks.lists.utils.WeaponListUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by pasencukviktor on 13/03/16
 */
public class ExpandableWeaponsAdapter extends BaseExpandableListAdapter {

    private List<GroupedWeapons> groupedWeaponsList;
    private Context context;

    public ExpandableWeaponsAdapter(List<GroupedWeapons> groupedWeaponsList, Context context) {
        this.groupedWeaponsList = groupedWeaponsList;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return groupedWeaponsList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return getGroup(i).getWeapons().size();
    }

    @Override
    public GroupedWeapons getGroup(int i) {
        return groupedWeaponsList.get(i);
    }

    @Override
    public Weapon getChild(int i, int i1) {
        return getGroup(i).getWeapons().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return getGroup(i).hashCode();
    }

    @Override
    public long getChildId(int i, int i1) {
        return getChild(i, i1).getId();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            Log.d("ADAPTER", "CREATE GROUP VIEW");
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.list_group_weapon, viewGroup, false);

        }
        ((TextView) convertView).setText(getGroup(i).getGroupName());
        return convertView;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View convertView, ViewGroup viewGroup) {
        WeaponsViewHolder weaponsViewHolder;
        if (convertView == null) {
            Log.d("ADAPTER", "CREATE VIEW");
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.list_item_weapon, viewGroup, false);

            weaponsViewHolder = new WeaponsViewHolder(convertView);
            convertView.setTag(weaponsViewHolder);
        } else
            weaponsViewHolder = (WeaponsViewHolder) convertView.getTag();

        final Weapon item = getChild(i, i1);
        WeaponListUtils.fillWeaponListItem(weaponsViewHolder, item);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

}
