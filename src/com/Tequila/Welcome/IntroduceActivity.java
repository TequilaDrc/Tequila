package com.Tequila.Welcome;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;

import com.Tequila.Main.MainActivity;
import com.Tequila.Main.R;

/*
 * @author IntroduceActivity
 * 
 * 功能：Application的介绍界面
 * 
 * */

public class IntroduceActivity extends Activity{
	
	private ViewPager viewPager; 		// 声明ViewPager的对象
	private ImageView imageView; 		// 声明ImageView的对象
	private ArrayList<View> pageViews; 	// 创建一个View数组，用来存放每个页面要显示的View
	private ImageView[] imageViews;		// 创建一个ImageView类型的数组，用来表示导航小圆点
	private ViewGroup viewPictures;		// 装显示图片的ViewGroup
	private ViewGroup viewPoints;		// 导航小圆点的ViewGroup

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);		// 用来隐藏标题栏的函数
		
		LayoutInflater inflater = getLayoutInflater();
		pageViews = new ArrayList<View>();
		pageViews.add(inflater.inflate(R.layout.activity_introduce_01, null));
		pageViews.add(inflater.inflate(R.layout.activity_introduce_02, null));
		pageViews.add(inflater.inflate(R.layout.activity_introduce_03, null));
		pageViews.add(inflater.inflate(R.layout.activity_introduce_04, null));
		
		imageViews = new ImageView[pageViews.size()];		// 小圆点数组，大小是图片的个数
		viewPictures = (ViewGroup) inflater.inflate(R.layout.viewpagers, null);		// 从指定的XML文件中加载视图
		
		viewPager = (ViewPager)viewPictures.findViewById(R.id.guidePagers);
		viewPoints = (ViewGroup) viewPictures.findViewById(R.id.viewPoints);
		
		// 添加小圆点导航的图片
		for (int i = 0; i < pageViews.size(); i++) {
			imageView = new ImageView(IntroduceActivity.this);
			imageView.setLayoutParams(new LayoutParams(20, 20));
			imageView.setPadding(5, 0, 5, 0);
			imageViews[i] = imageView;		// 把小圆点放进数据中
			
			if (i == 0) {
				// 把第一圆点设置成选中的图片
				imageViews[i].setImageDrawable(getResources().getDrawable(R.drawable.page_indicator_focused));
			} else {
				// 把剩下的圆点设置成没有选中的图片
				imageViews[i].setImageDrawable(getResources().getDrawable(R.drawable.page_indicator_unfocused));
			}
			
			viewPoints.addView(imageViews[i]); // 将imageviews添加到小圆点视图组
		}
		
		setContentView(viewPictures);
		
		viewPager.setAdapter(new NavigationPageAdapter());
		
		viewPager.setOnPageChangeListener(new NavigationPageChangeListener());		// 为viewpager添加监听，当view发生变化时的响应
	}
	
	// 导航图片view的适配器，必须要实现的是下面四个方法
	class NavigationPageAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return pageViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
		
		// 初始化每个Item
		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager) container).addView(pageViews.get(position));
			return pageViews.get(position);
		}
		
		// 销毁每个Item
		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(pageViews.get(position));
		}
	}
	
	// viewpager的监听器，主要是onPageSelected要实现
	class NavigationPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int position) {
			
			// 循环主要是控制导航中每个小圆点的状态
			for (int i = 0; i < imageViews.length; i++) {
				// 当前view下设置小圆点为选中状态
				imageViews[i].setImageDrawable(getResources().getDrawable(R.drawable.page_indicator_focused));
				
				// 其余设置为飞选中状态
				if (position != i) {
					imageViews[i].setImageDrawable(getResources().getDrawable(R.drawable.page_indicator_unfocused));
				}
			}
		}
	}

	// 开始按钮方法，开始按钮在XML文件中onClick属性设置；
	// 我试图把按钮在本activity中实例化并设置点击监听，但总是报错，使用这个方法后没有报错，原因没找到
	public void startbutton(View v) {
		Intent intent = new Intent(IntroduceActivity.this, MainActivity.class);
		startActivity(intent);
		IntroduceActivity.this.finish();
	}
}
