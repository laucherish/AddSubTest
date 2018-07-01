package com.laucherish.addsubtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView ivAdd;
    private ImageView ivSub;
    private AddSubView asView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivAdd = findViewById(R.id.iv_add);
        ivSub = findViewById(R.id.iv_sub);
        asView = findViewById(R.id.as_view);
        // 设置初始刻度为2
        asView.setCurrent(2);
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asView.add();
                Toast.makeText(MainActivity.this, "当前刻度值：" + asView.getCurrent(), Toast.LENGTH_SHORT).show();
            }
        });
        ivSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asView.sub();
                Toast.makeText(MainActivity.this, "当前刻度值：" + asView.getCurrent(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
