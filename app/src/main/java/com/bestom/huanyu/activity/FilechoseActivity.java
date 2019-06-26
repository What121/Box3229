package com.bestom.huanyu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bestom.huanyu.MyActivity;
import com.bestom.huanyu.R;
import com.bestom.huanyu.view.fileadapter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilechoseActivity extends MyActivity implements AdapterView.OnItemClickListener{

    ListView listView;
    fileadapter adapter;
    String rootPath = Environment.getExternalStorageDirectory().getPath();
    String currentPath = rootPath;
    List<Map<String, Object>> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filechose);

        listView=findViewById(R.id.list_file);
        adapter=new fileadapter(list,this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        refreshListItems(currentPath);
    }

    private void refreshListItems(String path) {
        setTitle(path);
        File[] files = new File(path).listFiles();
        list.clear();
        if (files != null) {
            for (File file : files) {
                Map<String, Object> map = new HashMap<>();
                if (file.isDirectory()) {
                    map.put("img", R.drawable.ic_launcher_foreground);
                } else {
                    map.put("img", "");
                }
                map.put("name", file.getName());
                map.put("currentPath", file.getPath());
                list.add(map);
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        currentPath = (String) list.get(i).get("currentPath");
        File file = new File(currentPath);
        if (file.isDirectory())
            refreshListItems(currentPath);
        else {
            Intent intent = new Intent();
            intent.putExtra("apk_path", file.getPath());
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (rootPath.equals(currentPath)) {
            super.onBackPressed();
        } else {
            File file = new File(currentPath);
            currentPath = file.getParentFile().getPath();
            refreshListItems(currentPath);
        }
    }
}
