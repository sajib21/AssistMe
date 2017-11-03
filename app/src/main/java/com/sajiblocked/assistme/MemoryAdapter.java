package com.sajiblocked.assistme;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Shahriar Hossain Sajib on 10/28/2017.
 */

public class MemoryAdapter extends ArrayAdapter<Memory> {

    public MemoryAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Memory> memories) {
        super(context, resource, memories);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.items_memory, null);
        }

        Memory memory = getItem(position);
        if(memory != null) {
            TextView title = (TextView) convertView.findViewById(R.id.list_memory_title);
            TextView date = (TextView) convertView.findViewById(R.id.list_memory_date);
            TextView content = (TextView) convertView.findViewById(R.id.list_memory_content);

            title.setText(memory.getTitle());
            date.setText(memory.getMemoryTime(getContext()));

            if(memory.getMsg().length() > 50) {
                content.setText(memory.getMsg().substring(0, 50));
            } else {
                content.setText(memory.getMsg());
            }
        }
        return convertView;
    }
}
