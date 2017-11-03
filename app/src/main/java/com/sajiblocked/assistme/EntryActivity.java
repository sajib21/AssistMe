package com.sajiblocked.assistme;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Shahriar Hossain Sajib on 10/28/2017.
 */

public class EntryActivity extends AppCompatActivity {

    private Switch eType;
    private EditText eDescription;
    private EditText eAmount;

    private String loadedEntryTag;
    private Entry loadedEntry;

    private DbHelperWallet dbHelperWallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        dbHelperWallet = DbHelperWallet.getInstance();

        eType = (Switch) findViewById(R.id.entry_type); 
        eDescription = (EditText) findViewById(R.id.entry_description);
        eAmount = (EditText) findViewById(R.id.entry_amount);

        eType.setText("Expense");
        eType.setTextColor(Color.RED);

        loadedEntryTag = getIntent().getStringExtra("ENTRY_TAG");

        if(loadedEntryTag != null && !loadedEntryTag.isEmpty()) {
            loadedEntry = dbHelperWallet.getEntry(loadedEntryTag);

            if(loadedEntry != null) {
                eType.setText(loadedEntry.getType());
                if(loadedEntry.getType().equals("expense")) {
                    eType.setChecked(false); /// false means expence
                    eType.setText("Expense");
                    eType.setTextColor(Color.RED);
                }
                else {
                    eType.setChecked(true);
                    eType.setText("Income");
                    eType.setTextColor(Color.GREEN);
                }

                eDescription.setText(loadedEntry.getDescription());
                eAmount.setText(Integer.toString(loadedEntry.getAmount()));
            }
        }

        eType.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!eType.isChecked()) {
                    eType.setText("Expense");
                    eType.setTextColor(Color.RED);
                }
                else {
                    eType.setText("Income");
                    eType.setTextColor(Color.GREEN);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_memory, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_action_save:
                if(saveEntry()) return true;
                break;
            case R.id.item_action_delete:
                deleteEntry();
                break;
        }
        return true;
    }

    private boolean saveEntry() {
        Entry entry = null;
        if(eDescription.getText().toString().trim().isEmpty() ||
                eAmount.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Nothing to Save!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(loadedEntry == null) {
            entry = new Entry(System.currentTimeMillis(), eType.getText().toString(),
                    eDescription.getText().toString(), eAmount.getText().toString());
        } else {
            entry = new Entry(loadedEntry.getTag(), eType.getText().toString(), eDescription.getText().toString(),
                    eAmount.getText().toString() );
        }

        Toast.makeText(getApplicationContext(), "SAVED ", Toast.LENGTH_SHORT).show();

        DbHelperWallet.getInstance().insertNewEntry(entry);
        finish();
        return true;
    }

    private void deleteEntry() {
        if(loadedEntry == null) finish();
        else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                    .setTitle("Delete").setMessage("Confirm delete " + eDescription.getText().toString() + " ?")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DbHelperWallet.getInstance().deleteEntry(loadedEntry);
                            Toast.makeText(getApplicationContext(), eDescription.getText().toString()
                                    + " is deleted!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })
                    .setNegativeButton("no", null).
                            setCancelable(false);
            dialog.show();
        }
    }
}
