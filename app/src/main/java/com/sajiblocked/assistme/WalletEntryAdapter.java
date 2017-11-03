package com.sajiblocked.assistme;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Shahriar Hossain Sajib on 10/28/2017.
 */

public class WalletEntryAdapter extends ArrayAdapter<Entry> {

    public WalletEntryAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Entry> entries) {
        super(context, resource, entries);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.wallet_row, null);
        }

        Entry entry = getItem(position);
        if(entry != null) {
//            TextView type = (TextView) convertView.findViewById(R.id.loaded_entry_description);
            TextView description = (TextView) convertView.findViewById(R.id.loaded_entry_description);
            TextView amount = (TextView) convertView.findViewById(R.id.loaded_entry_amount);

            if(entry.getType().equalsIgnoreCase("Expense")) description.setTextColor(Color.RED);
            else description.setTextColor(Color.GREEN);

//            description.set

            description.setText(entry.getDescription());
            amount.setText( Integer.toString( entry.getAmount() ) );
        }
        return convertView;
    }
}
