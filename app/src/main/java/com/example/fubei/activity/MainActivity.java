package com.example.fubei.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fubei.R;
import com.example.fubei.adapter.LeftItemAdapter;
import com.example.fubei.bean.AdEntity;
import com.example.fubei.utils.StatusBarUtils;
import com.example.fubei.widget.AutoScrollTextView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.fubei.utils.Utils.mToast;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.list_order)
    ListView mListOrder;
    @BindView(R.id.swipe)
    SwipeRefreshLayout mSwipe;
    @BindView(R.id.main_lin)
    LinearLayout mMainLin;
    @BindView(R.id.TextViewNotice)
    AutoScrollTextView mTextViewNotice;
    private ListView lv;

    private SlidingMenu menu;

    private ImageView im;

    private List<AdEntity> mList = new ArrayList<>();

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            mSwipe.setRefreshing(false);
        }
    };

    private View mStatusBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        StatusBarUtils.initStatusBar(this, R.color.colorAccent);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initContentViews();
        initMenuView();
        initView();
    }


    private void initView() {
        mStatusBar = (View) findViewById(R.id.status_bar);
        ViewGroup.LayoutParams linearParams = (ViewGroup.LayoutParams) mStatusBar.getLayoutParams();
        linearParams.height = StatusBarUtils.getStatusBarHeight(this);
        mStatusBar.setLayoutParams(linearParams);
        StatusBarUtils.setStatusBarViewVisibility(mStatusBar);
    }


    /**
     * 初始化布局控件
     */
    private void initContentViews() { //初始化主界面视图
        textInit();
        mMainLin.setOnClickListener(this);
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.sendEmptyMessageAtTime(0, 500);
            }
        });
        findViewById(R.id.lin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void initMenuView() { //初始化菜单视图
        View view = this.getLayoutInflater().inflate(R.layout.left_view_layout, null);
        lv = (ListView) view.findViewById(R.id.lv);
        lv.setAdapter(new LeftItemAdapter(this));
        menuInit(view);
    }

    private void textInit() { //滚动文字初始化
        mList.add(new AdEntity("", "欢迎使用富呗", "连接2"));
        mList.add(new AdEntity("", "收款金额通知，请查看", "连接1"));
        mTextViewNotice.setSpeed(3);
        mTextViewNotice.setInterval(5000);
        mTextViewNotice.setBackColor(Color.BLACK);
        mTextViewNotice.setTexts(mList);
        mTextViewNotice.setOnItemClickListener(new AutoScrollTextView.OnItemClickListener() {
            @Override
            public void onClick(String mUrl) {
                if (mToast == null) {
                    mToast = Toast.makeText(MainActivity.this, mUrl, Toast.LENGTH_LONG);
                } else {
                    mToast.setText(mUrl);
                }
                mToast.show();
            }
        });
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
        }
    }
}
