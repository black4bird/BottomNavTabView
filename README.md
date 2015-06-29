# BottomNavTabView
主页导航Tab按钮自定义View
你不用再在xml中设置各种复杂的样式了，现在，你用几行代码就可以搞定。

###使用方法：
1. xml定义


```
<cc.core.bottomnavtabview.BottomNavTabView
        android:id="@+id/tab"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        tab:height_img="35dp"
        tab:tab_bg="@drawable/item_selector"
        tab:width_img="35dp" />
```


1. 初始化
```
 /**
   * 初始化3个tab
   */
  private void init3Tabs() {
    String[] titles = new String[] { "首页", "积分商城", "我的" };
    BottomNavTabView tab = (BottomNavTabView) findViewById(R.id.tab);
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
```

注意：Tab的数量是根据传入的titles的length来计算的。你应该保证你传入的每个数组(字符串数组，图片资源数组)的length是相同的。

###运行截图：
![3个Tab](http://git.oschina.net/uploads/images/2015/0629/175052_ea4c3eba_134008.png "3个Tab") ![5个Tab](http://git.oschina.net/uploads/images/2015/0629/175133_4b93759e_134008.png "5个Tab")
