package com.sajiblocked.assistme;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Shahriar Hossain Sajib on 10/28/2017.
 */

public class WalletActivity extends AppCompatActivity {

    DbHelperWallet dbHelperWallet;
    ListView listViewDebug;
    TextView balance;
    LinearLayout balanceLine;
    TextView blnc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        dbHelperWallet = DbHelperWallet.getInstance(this);
        listViewDebug = (ListView) findViewById(R.id.list_view_debugg);
        balance = (TextView) findViewById(R.id.textview_wallet_balance);
        balanceLine = (LinearLayout) findViewById(R.id.balance_line);
        blnc = (TextView) findViewById(R.id.blnc);

        balanceLine.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
        blnc.setTextColor(getResources().getColor(android.R.color.white));

        loadEntryList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadEntryList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add_task:
                startActivity(new Intent(this, EntryActivity.class));
                loadEntryList();
                break;
        }
        return true;
    }

    private void loadEntryList() {
        balance.setText(String.valueOf( dbHelperWallet.getBalance() ) );
        balance.setTextColor(getResources().getColor(android.R.color.white));

        ArrayList<Entry> entryList = dbHelperWallet.getEntryList();

        if(entryList == null || entryList.size() == 0) {
            Toast.makeText(this, "Nothing to Show!", Toast.LENGTH_SHORT).show();
        }
        else {
            WalletEntryAdapter wa = new WalletEntryAdapter(this, R.layout.wallet_row, entryList);
            listViewDebug.setAdapter(wa);
        }

        listViewDebug.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tagName = ((Entry)listViewDebug.getItemAtPosition(position)).getTag();
                Intent viewEntryIntent = new Intent(getApplicationContext(), EntryActivity.class);
                viewEntryIntent.putExtra("ENTRY_TAG", tagName);
                startActivity(viewEntryIntent);
            }
        });
    }
}
