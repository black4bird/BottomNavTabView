package com.example.bottomtabview;

import com.example.bottomtabview.BottomTabView.OnTabSeletedListener;

import android.app.Activity;
import android.os.Bundle;

public class TabActivity extends Activity {
  private static final String TAG = "TabActivity";
  private boolean isInit3;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.tab_activity_layout);
    isInit3 = getIntent().getBooleanExtra("isInit3", false);
    if (isInit3)
      init3Tabs();
    else
      init5Tabs();
  }

  /**
   * 初始化3个tab
   */
  private void init3Tabs() {
    String[] titles = new String[] { "首页", "积分商城", "我的" };
    BottomTabView tab = (BottomTabView) findViewById(R.id.tab);
    tab.setTextColors_nor(R.color.gray);
    tab.setTextColors_sel(R.color.white);
    tab.setBgColor_nor(R.color.white);
    tab.setBgColor_sel(R.color.gray);
    tab.setImgRes_nor(new Integer[] { R.drawable.a_nor, R.drawable.b_nor, R.drawable.c_nor });
    tab.setImgRes_sel(new Integer[] { R.drawable.a_sel, R.drawable.b_sel, R.drawable.c_sel });
    tab.setTitles(titles);
    tab.setPosition(0);

    tab.setOnTabSelectedListener(new OnTabSeletedListener() {

      @Override
      public void onSelected(int position, String title) {
        // Toast.makeText(MainActivity.this,
        // "position:"+position+",title:"+title, Toast.LENGTH_SHORT).show();
      }
    });
  }

  /**
   * 初始化5个tab
   */
  private void init5Tabs() {
    String[] titles = new String[] { "首页", "积分商城", "我的", "消息", "推荐" };
    BottomTabView tab = (BottomTabView) findViewById(R.id.tab);
    tab.setTextColors_nor(R.color.gray);
    tab.setTextColors_sel(R.color.white);
    tab.setBgColor_nor(R.color.white);
    tab.setBgColor_sel(R.color.gray);
    tab.setTitles(titles);
    tab.setImgRes_nor(new Integer[] { R.drawable.a_nor, R.drawable.b_nor, R.drawable.c_nor, R.drawable.a_nor,
        R.drawable.b_nor, });
    tab.setImgRes_sel(new Integer[] { R.drawable.a_sel, R.drawable.b_sel, R.drawable.c_sel, R.drawable.a_sel,
        R.drawable.b_sel });
    tab.setPosition(0);

    tab.setOnTabSelectedListener(new OnTabSeletedListener() {

      @Override
      public void onSelected(int position, String title) {
        // Toast.makeText(MainActivity.this,
        // "position:"+position+",title:"+title, Toast.LENGTH_SHORT).show();
      }
    });

  }
}
