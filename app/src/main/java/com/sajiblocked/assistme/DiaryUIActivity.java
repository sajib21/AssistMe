package com.sajiblocked.assistme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Shahriar Hossain Sajib on 10/28/2017.
 */

public class DiaryUIActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_ui);

        listView = (ListView) findViewById(R.id.memory_list_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.diary_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_action_add:
                //start memoryActivity when new memory created
                startActivity(new Intent(this, MemoryActivity.class));
                break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadMemories();
    }

    public void loadMemories() {
        final ArrayList<Memory> memories = Utilities.getAllSavedMemories(this);

        if(memories == null || memories.size() == 0) {
            Toast.makeText(this, "Nothing to Show!", Toast.LENGTH_SHORT).show();

        } else {
            MemoryAdapter ma = new MemoryAdapter(this, R.layout.items_memory, memories);
            listView.setAdapter(ma);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String fileName = ((Memory)listView.getItemAtPosition(position)).
                        getTime() + Utilities.FILE_EXTENSION;
                System.out.println("FILENAME " + fileName);
                Intent viewMemoryIntent = new Intent(getApplicationContext(), MemoryActivity.class);
                viewMemoryIntent.putExtra("MEMORY_FILE", fileName);
                startActivity(viewMemoryIntent);
            }
        });
    }
}
