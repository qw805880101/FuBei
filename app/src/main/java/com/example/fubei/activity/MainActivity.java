package com.example.fubei.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fubei.R;
import com.example.fubei.adapter.LeftItemAdapter;
import com.example.fubei.bean.AdEntity;
import com.example.fubei.widget.VerticalTextview;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.zbar.lib.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    @BindView(R.id.list_order)
    ListView mListOrder;
    @BindView(R.id.swipe)
    SwipeRefreshLayout mSwipe;
    @BindView(R.id.main_lin)
    LinearLayout mMainLin;
    @BindView(R.id.TextViewNotice)
    VerticalTextview mTextViewNotice;
    @BindView(R.id.lin_qrcode)
    LinearLayout mLinQrcode;
    @BindView(R.id.lin_qr_scand)
    LinearLayout mLinQrScand;
    private ListView lv;

    private SlidingMenu menu;

    private ImageView im;

    private List<AdEntity> mList = new ArrayList<>();
    private ArrayList<String> mLists = new ArrayList<>();

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            mSwipe.setRefreshing(false);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initContentViews();
        initMenuView();
    }

    /**
     * 初始化布局控件
     */
    private void initContentViews() { //初始化主界面视图
        textInit();
        mLinQrcode.setOnClickListener(this);
        mMainLin.setOnClickListener(this);
        mLinQrScand.setOnClickListener(this);
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.sendEmptyMessageAtTime(0, 500);
            }
        });
    }

    private void initMenuView() { //初始化菜单视图
        View view = this.getLayoutInflater().inflate(R.layout.left_view_layout, null);
        lv = (ListView) view.findViewById(R.id.lv);
        lv.setAdapter(new LeftItemAdapter(this));
        lv.setOnItemClickListener(this);
        menuInit(view);
    }

    private void textInit() { //滚动文字初始化
        mList.add(new AdEntity("", "欢迎使用富呗", "连接2"));
        mList.add(new AdEntity("", "收款金额通知，请查看", "连接1"));


        mLists.add("欢迎使用富呗");
        mLists.add("收款金额通知，请查看");
        mTextViewNotice.setTextList(mLists);//加入显示内容,集合类型
        mTextViewNotice.setText(17, 5, Color.RED);//设置属性,具体跟踪源码
        mTextViewNotice.setTextStillTime(5000);//设置停留时长间隔
        mTextViewNotice.setAnimTime(500);//设置进入和退出的时间间隔
        //对单条文字的点击监听
        mTextViewNotice.setOnItemClickListener(new VerticalTextview.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // TO DO
                Toast.makeText(MainActivity.this, "position  = " + position, Toast.LENGTH_LONG);
            }
        });
        mTextViewNotice.startAutoScroll();
    }

    private void menuInit(View view) { //菜单初始化
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        // 设置菜单触摸
        menu.setTouchModeBehind(SlidingMenu.TOUCHMODE_FULLSCREEN);
        // 设置滑动菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);

        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);
        menu.setOffsetFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

        //为侧滑菜单设置布局
        menu.setMenu(view);
    }

    @Override
    public void onBackPressed() {
        if (menu.isMenuShowing()) {
            menu.showContent();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        if (v == im) {
            System.out.println("ssssssssssssss");
        } else if (v == mLinQrcode) {
            Intent intent = new Intent(this, QRCodeActivity.class);
            this.startActivity(intent);
        } else if (v == mLinQrScand) {
            startActivity(new Intent(MainActivity.this, CaptureActivity.class));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                break;
            case 1:
                startActivity(new Intent(MainActivity.this, BankCardListActivity.class));
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }
}
