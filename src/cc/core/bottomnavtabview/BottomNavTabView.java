package cc.core.bottomnavtabview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * com.xjx.xjxpartner.view.BottomNavTabView<br/>
 * 底部的Tab 使用了BottomTab实现 {@link BottomTab}
 * 
 * @author YuanChao <br/>
 *         create at 2015年6月16日 下午2:35:13
 */
public class BottomNavTabView extends LinearLayout {
	private static final String TAG = "BottomNavTabView";
	private List<BottomTab> tabs = new ArrayList<BottomTab>();;
	private List<String> tabTitles;

	private List<Integer> imgRes_nor;// 正常图片
	private List<Integer> imgRes_sel;// 选中时的图片

	private int textColors_nor;// 正常颜色
	private int textColors_sel;// 选中时颜色

	private int bgColor_nor;// 正常时背景
	private int bgColor_sel;// 选中时的背景

	private int internalPadding, groupPadding;
	private int imgWidth, imgHeight;
	private int tabBg;
	private float textSize;

	public BottomNavTabView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.BottomNavTabView);
		internalPadding = ta.getDimensionPixelSize(R.styleable.BottomNavTabView_internal_padding, 0);
		imgWidth = ta.getDimensionPixelSize(R.styleable.BottomNavTabView_width_img, 0);
		imgHeight = ta.getDimensionPixelSize(R.styleable.BottomNavTabView_height_img, 0);
		textSize = ta.getDimension(R.styleable.BottomNavTabView_title_text_size, 0);
		groupPadding = ta.getDimensionPixelSize(R.styleable.BottomNavTabView_group_padding, 0);
		tabBg = ta.getResourceId(R.styleable.BottomNavTabView_tab_bg, 0);
		Log.i("CC", "internalPadding:" + internalPadding + ",imgWidth:" + imgWidth + ",imgHeight:" + imgHeight + ",groupPadding:" + groupPadding + ",textSize:" + textSize);
		init(context);
		ta.recycle();
	}

	public BottomNavTabView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		setOrientation(LinearLayout.HORIZONTAL);
		imgRes_nor = new ArrayList<Integer>();
		imgRes_sel = new ArrayList<Integer>();
	}

	/**
	 * <p>
	 * init with titles,this method or {@link #setTabs(List)} should be called
	 * first
	 * </p>
	 * 
	 * @param titles
	 */
	public void setTitles(String[] titles) {
		if (tabs.size() == 0 || tabs.size() == titles.length) {
			tabTitles = Arrays.asList(titles);

			for (int i = 0; i < tabTitles.size(); i++) {
				BottomTab tab = new BottomTab(getContext());
				tab.setTag(i);
				tab.setBackgroundDrawable(getResources().getDrawable(tabBg));
				tab.setOnClickListener(tabClickListener);
				tab.setTitle(tabTitles.get(i));
				tab.setBg_nor(bgColor_nor);
				tab.setBg_sel(bgColor_sel);
				tab.setTextColor_nor(textColors_nor);
				tab.setTextColor_sel(textColors_sel);
				if (i < imgRes_nor.size() && i < imgRes_sel.size()) {
					tab.setImg_nor(imgRes_nor.get(i));
					tab.setImg_sel(imgRes_sel.get(i));
				}
				tabs.add(tab);
			}
			updateViews();
		}
	}

	public void release() {
		removeAllViews();
		if (tabs != null)
			tabs.clear();
		if (imgRes_nor != null)
			imgRes_nor.clear();
		if (imgRes_sel != null)
			imgRes_sel.clear();
		if (tabTitles != null)
			tabTitles.clear();

	}

	/**
	 * <p>
	 * inti with tabs,you can call this methods or {@link #setTitles(String[])}
	 * first
	 * </p>
	 * 
	 * @param tabs
	 */
	public void setTabs(List<BottomTab> tabs) {
		this.tabs = tabs;
		if (tabs != null)
			for (int i = 0; i < tabs.size(); i++) {
				tabs.get(i).setTag(i);
				tabs.get(i).setOnClickListener(tabClickListener);
			}
	}

	/**
	 * <p>
	 * If you call this methods before you set listener,it will not call
	 * </p>
	 * {@link OnTabSeletedListener}
	 * 
	 * @param position
	 */
	public void setPosition(int position) {
		if (position > tabs.size() - 1)
			position = 0;
		tabs.get(position).performClick();
	}

	private void updateViews() {
		for (RelativeLayout layout : tabs) {
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
			lp.weight = 1;
			layout.setLayoutParams(lp);
			addView(layout);
		}
	}

	public List<Integer> getImgRes_nor() {
		return imgRes_nor;
	}

	public void setImgRes_nor(Integer[] imgRes_nor) {
		this.imgRes_nor = Arrays.asList(imgRes_nor);
		if (tabs.size() == 0)
			new RuntimeException("You should call setTitles(List<String> titles) before setImgRes!");
		if (imgRes_nor.length != tabs.size())
			new RuntimeException("Your tabs size is different whith imgRes_nor you set");
		for (int i = 0; i < tabs.size(); i++) {
			tabs.get(i).setImg_nor(this.imgRes_nor.get(i));
		}
	}

	public List<Integer> getImgRes_sel() {
		return imgRes_sel;
	}

	public void setImgRes_sel(Integer[] imgRes_sel) {

		this.imgRes_sel = Arrays.asList(imgRes_sel);
		if (tabs.size() == 0)
			new RuntimeException("You should call setTitles(List<String> titles) first!");
		if (this.imgRes_sel.size() != tabs.size())
			new RuntimeException("Your tabs size is different whith imgRes_sel you set");
		for (int i = 0; i < tabs.size(); i++) {
			tabs.get(i).setImg_sel(this.imgRes_sel.get(i));
		}
	}

	public int getColors_nor() {
		return textColors_nor;
	}

	public void setTextColors_nor(int textColor_nor) {
		this.textColors_nor = textColor_nor;
		if (tabs.size() == 0)
			new RuntimeException("You should call setTitles(List<String> titles) first!");
		for (int i = 0; i < tabs.size(); i++) {
			tabs.get(i).setTextColor_nor(textColors_nor);
		}
	}

	public int getColors_sel() {
		return textColors_sel;
	}

	public void setTextColors_sel(int colors_sel) {
		this.textColors_sel = colors_sel;
		if (tabs.size() == 0)
			new RuntimeException("You should call setTitles(List<String> titles) first!");
		for (int i = 0; i < tabs.size(); i++) {
			tabs.get(i).setTextColor_sel(colors_sel);
		}
	}

	public int getBgColor_nor() {
		return bgColor_nor;
	}

	public void setBgColor_nor(int bgColor_nor) {
		this.bgColor_nor = bgColor_nor;
		if (tabs.size() == 0)
			new RuntimeException("You should call setTitles(List<String> titles) first!");
		for (int i = 0; i < tabs.size(); i++) {
			tabs.get(i).setBg_nor(bgColor_nor);
		}
	}

	public int getBgColor_sel() {
		return bgColor_sel;
	}

	public void setBgColor_sel(int bgColor_sel) {
		this.bgColor_sel = bgColor_sel;
		if (tabs.size() == 0)
			new RuntimeException("You should call setTitles(List<String> titles) first!");
		for (int i = 0; i < tabs.size(); i++) {
			tabs.get(i).setBg_sel(bgColor_sel);
		}
	}

	public List<String> getTabTitles() {
		return tabTitles;
	}

	/**
	 * <p>
	 * 里面包含一个线性布局的相对布局，线性布局里包含一个ImageView和一个TextView
	 * </p>
	 * com.xjx.xjxpartner.view.BottomTab
	 * 
	 * @author YuanChao <br/>
	 *         create at 2015年6月16日 上午9:05:51
	 */
	class BottomTab extends RelativeLayout {

		RelativeLayout.LayoutParams lp;
		LinearLayout group;
		ImageView img;// tab的icon
		TextView text;// tab的标题
		String title;
		boolean isSelected = false;
		int img_nor = R.drawable.ic_launcher, img_sel = R.drawable.ic_launcher, textColor_nor = R.color.black, textColor_sel = R.color.black, bg_nor = R.color.background,
				bg_sel = R.color.background;

		public boolean isSelected() {
			return isSelected;
		}

		public void setSelected(boolean isSelected) {
			this.isSelected = isSelected;
			onSelected(isSelected);
		}

		public void setImg_nor(int img_nor) {
			this.img_nor = img_nor;
		}

		public void setImg_sel(int img_sel) {
			this.img_sel = img_sel;
		}

		public void setTextColor_nor(int textColor_nor) {
			this.textColor_nor = textColor_nor;
		}

		public void setTextColor_sel(int textColor_sel) {
			this.textColor_sel = textColor_sel;
		}

		public void setBg_nor(int bg_nor) {
			this.bg_nor = bg_nor;
		}

		public void setBg_sel(int bg_sel) {
			this.bg_sel = bg_sel;
		}

		public BottomTab(Context context, String title, int img_nor, int img_sel, int textColor_nor, int textColor_sel, int bg_nor, int bg_sel) {
			super(context);
			this.img_nor = img_nor;
			this.img_sel = img_sel;
			this.textColor_nor = textColor_nor;
			this.textColor_sel = textColor_sel;
			this.bg_nor = bg_nor;
			this.bg_sel = bg_sel;
			this.title = title;
			init(context);
		}

		public BottomTab(Context context, AttributeSet attrs, int defStyleAttr) {
			super(context, attrs, defStyleAttr);
			init(context);
		}

		public BottomTab(Context context, AttributeSet attrs) {
			super(context, attrs);
			init(context);
		}

		public BottomTab(Context context) {
			super(context);
			init(context);
		}

		public void setTitle(String title) {
			this.title = title;
			text.setText(title);
		}

		public String getTitle() {
			return title;
		}

		void init(Context context) {
			lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

			group = new LinearLayout(context);
			group.setOrientation(LinearLayout.VERTICAL);
			group.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
			if (groupPadding != 0) {
				group.setPadding(0, groupPadding, 0, groupPadding);
			}

			// 初始化ImageView
			img = new ImageView(context);
			LinearLayout.LayoutParams imgLP = null;
			if (imgWidth != 0 && imgHeight != 0) {
				imgLP = new LinearLayout.LayoutParams(imgWidth, imgHeight);
				imgLP.gravity = Gravity.CENTER_HORIZONTAL;
				img.setLayoutParams(imgLP);
			}

			// 初始化TextView
			text = new TextView(context);
			text.setGravity(Gravity.CENTER);
			text.setSingleLine();
			if (internalPadding != 0)
				text.setPadding(0, internalPadding, 0, 0);
			if (textSize != 0)
				text.setTextSize(textSize);
			LinearLayout.LayoutParams textLpnew = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			textLpnew.gravity = Gravity.CENTER_HORIZONTAL;
			text.setLayoutParams(textLpnew);

			// 将两个控件添加到Relativelayout中
			group.addView(img);
			group.addView(text);

			lp.addRule(RelativeLayout.CENTER_IN_PARENT);
			group.setLayoutParams(lp);

			setRes(img_nor, textColor_nor, bg_nor);

			addView(group);
		}

		private void onSelected(boolean isSel) {
			if (isSel) {
				setResSel();
			} else {
				setResNor();
			}
		}

		public void setResNor() {
			setRes(img_nor, textColor_nor, bg_nor);
		}

		public void setResSel() {
			setRes(img_sel, textColor_sel, bg_sel);
		}

		private void setRes(int imgRes, int textColor, int bg_color) {
			img.setImageResource(imgRes);
			text.setTextColor(getResources().getColor(textColor));
			setBackgroundColor(getResources().getColor(bg_color));
		}
	}

	public void setOnTabSelectedListener(OnTabSeletedListener tabListener) {
		this.tabListener = tabListener;
	}

	private OnClickListener tabClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			for (int i = 0; i < tabs.size(); i++) {
				tabs.get(i).setResNor();
				tabs.get(i).setSelected(false);
				tabs.get(i).setBackgroundDrawable(getResources().getDrawable(tabBg));
			}
			tabs.get((Integer) v.getTag()).setSelected(true);
			if (tabListener != null)
				tabListener.onSelected((Integer) v.getTag(), ((BottomTab) v).getTitle());
		}
	};

	private OnTabSeletedListener tabListener;

	/**
	 * 回调 com.xjx.xjxpartner.view.OnTabSeletedListener
	 * 
	 * @author YuanChao <br/>
	 *         create at 2015年6月16日 下午2:56:07
	 */
	public interface OnTabSeletedListener {
		public void onSelected(int position, String title);
	}
}
