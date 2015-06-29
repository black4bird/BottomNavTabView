package com.example.bottomtabview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.bottomtabview.BottomTabView.OnTabSeletedListener;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    findViewById(R.id.button1).setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, TabActivity.class);
        intent.putExtra("isInit3", true);
        startActivity(intent);
      }
    });
    findViewById(R.id.button2).setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, TabActivity.class);
        intent.putExtra("isInit3", false);
        startActivity(intent);
      }
    });
  }

}
