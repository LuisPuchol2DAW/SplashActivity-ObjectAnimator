package com.example.objectanimator;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmptyActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Activity has been created");
        super.onCreate(savedInstanceState);
        startStuff(savedInstanceState);



        String[] menuItems = getResources().getStringArray(R.array.list_items);

        //Mapeo para con el SimpleAdapter podamos poner los datos al listView
        List<Map<String, String>> data = getMaps(menuItems);

        SimpleAdapter adapter = getSimpleAdapter(data);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }

    private @NonNull SimpleAdapter getSimpleAdapter(List<Map<String, String>> data) {
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                data,
                R.layout.list_item,
                new String[]{"title"},
                new int[]{R.id.menuButton}
        );
        return adapter;
    }

    private static @NonNull List<Map<String, String>> getMaps(String[] menuItems) {
        List<Map<String, String>> data = new ArrayList<>();
        for (String item : menuItems) {
            Map<String, String> map = new HashMap<>();
            map.put("title", item);
            data.add(map);
        }
        return data;
    }

    public void startStuff(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_empty);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}