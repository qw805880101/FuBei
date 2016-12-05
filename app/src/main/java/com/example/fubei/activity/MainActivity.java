package com.example.fubei.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fubei.R;
import com.example.fubei.adapter.LeftItemAdapter;
import com.example.fubei.adapter.OrderAdapter;
import com.example.fubei.utils.StatusBarUtils;
import com.example.fubei.utils.Utils;
import com.example.fubei.widget.BannerView;
import com.example.fubei.widget.VerticalTextview;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.zbar.lib.CaptureActivity;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主界面
 * Created by tc on 2016/11/28.
 */

public class MainActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    @BindView(R.id.ib_head)
    ImageButton mIbHead;
    @BindView(R.id.tx_title)
    TextView mTxTitle;
    @BindView(R.id.ib_message)
    ImageButton mIbMessage;
    @BindView(R.id.rl_top)
    AutoRelativeLayout mRlTop;
    @BindView(R.id.lin_qr_scand)
    AutoLinearLayout mLinQrScand;
    @BindView(R.id.lin_qr_code)
    AutoLinearLayout mLinQrCode;
    @BindView(R.id.banner)
    BannerView mBanner;
    @BindView(R.id.ib_message2)
    ImageButton mIbMessage2;
    @BindView(R.id.TextViewNotice)
    VerticalTextview mTextViewNotice;
    @BindView(R.id.tx_more)
    TextView mTxMore;
    @BindView(R.id.tx_refresh_num)
    TextView mTxRefreshNum;
    @BindView(R.id.ib_list_more)
    ImageButton mIbListMore;
    @BindView(R.id.bt_order)
    Button mBtOrder;
    @BindView(R.id.list_order)
    ListView mListOrder;
    @BindView(R.id.swipe)
    SwipeRefreshLayout mSwipe;
    @BindView(R.id.main_lin)
    AutoLinearLayout mMainLin;

    private SlidingMenu menu;

    private ArrayList<String> mLists = new ArrayList<>();

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            mSwipe.setRefreshing(false);
        }
    };

    private boolean isTranstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isTranstatus = StatusBarUtils.initStatusBar(this, R.color.colorAccent);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initContentViews();
        initMenuView();
    }

    int[] res = {R.mipmap.app_home_banner01, R.mipmap.app_top_bg, R.mipmap.home_banner02};

    /**
     * 初始化布局控件
     */
    private void initContentViews() { //初始化主界面视图
        textInit();
        mIbHead.setOnClickListener(this);
        mLinQrCode.setOnClickListener(this);
        mMainLin.setOnClickListener(this);
        mLinQrScand.setOnClickListener(this);
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.sendEmptyMessageAtTime(0, 500);
            }
        });
        mBanner.setImagesRes(res);
        if (isTranstatus) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams
                    .MATCH_PARENT, 150);
            layoutParams.setMargins(0, StatusBarUtils.getStatusBarHeight(this), 0, 0);
            mRlTop.setLayoutParams(layoutParams);
        }
        mListOrder.setAdapter(new OrderAdapter(this));
        Utils.log("StatusBarUtils.getStatusBarHeight(this) = " + StatusBarUtils.getStatusBarHeight(this));
    }


    private void initMenuView() { //初始化菜单视图
        View view = this.getLayoutInflater().inflate(R.layout.left_view_layout, null);
        ListView lv = (ListView) view.findViewById(R.id.lv);
        lv.setAdapter(new LeftItemAdapter(this));
        lv.setOnItemClickListener(this);
        LinearLayout lin_top = (LinearLayout) view.findViewById(R.id.lin_top);
        if (isTranstatus) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams
                    .MATCH_PARENT, 200);
            layoutParams.setMargins(60, StatusBarUtils.getStatusBarHeight(this), 0, 0);
            lin_top.setLayoutParams(layoutParams);
        }
        menuInit(view);
    }

    private void textInit() { //滚动文字初始化
        mLists.add("欢迎使用富呗");
        mLists.add("收款金额通知，请查看");
        mTextViewNotice.setTextList(mLists);//加入显示内容,集合类型
        mTextViewNotice.setText(17, 5, Color.BLACK);//设置属性,具体跟踪源码
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
        if (v == mLinQrCode) {
            Intent intent = new Intent(this, QRCodeActivity.class);
            this.startActivity(intent);
        } else if (v == mLinQrScand) {
            startActivity(new Intent(MainActivity.this, CaptureActivity.class));
        } else if (v == mIbHead) {
            menu.showMenu();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                break;
            case 1:
                startActivity(new Intent(MainActivity.this, BankCardActivity.class));
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }
}
