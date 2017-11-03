package com.sajiblocked.assistme;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Shahriar Hossain Sajib on 10/28/2017.
 */

public class MemoryActivity extends AppCompatActivity {

    private EditText mTitle;
    private EditText mContent;

    private String memoryFileName;
    private Memory loadMemory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        mTitle = (EditText) findViewById(R.id.memory_title);
        mContent = (EditText) findViewById(R.id.memory_msg);

        memoryFileName = getIntent().getStringExtra("MEMORY_FILE");
        if(memoryFileName != null && !memoryFileName.isEmpty()) {
            loadMemory = Utilities.getMemoryByName(this, memoryFileName);
            if(loadMemory != null) {
                mTitle.setText(loadMemory.getTitle());
                mContent.setText(loadMemory.getMsg());
            }
        }
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
                if(saveMemory()) return true;
                break;
            case R.id.item_action_delete:
                deleteMemory();
                break;
        }
        return true;
    }

    private boolean saveMemory() {
        Memory memory = null;

        if(mTitle.getText().toString().trim().isEmpty() ||
                mContent.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Nothing to Save!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(loadMemory == null) {
            memory = new Memory(System.currentTimeMillis(),
                    mTitle.getText().toString(), mContent.getText().toString());
        } else {
            memory = new Memory(loadMemory.getTime(), mTitle.getText().toString(),
                    mContent.getText().toString());
        }
        //Memory memory = new Memory(System.currentTimeMillis(), mTitle.getText().toString(), mContent.getText().toString());
        if(Utilities.saveMemory(this, memory)) {
            Toast.makeText(this, "Memory Saved!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Could Not Save!", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private void deleteMemory() {
        if(loadMemory == null) finish();
        else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                    .setTitle("Delete").setMessage("Confirm delete " + mTitle.getText().toString() + " ?")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Utilities.deleteMemory(getApplicationContext(),
                                    loadMemory.getTime() + Utilities.FILE_EXTENSION);
                            Toast.makeText(getApplicationContext(), mTitle.getText().toString() + " is deleted!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })
                    .setNegativeButton("no", null).
                            setCancelable(false);
            dialog.show();
            //Utilities.deleteMemory(getApplicationContext(), loadMemory.getTime() + Utilities.FILE_EXTENSION);
        }
    }
}
