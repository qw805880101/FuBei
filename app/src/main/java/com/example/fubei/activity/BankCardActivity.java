package com.example.fubei.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.fubei.R;
import com.example.fubei.utils.DialogUtils;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 银行卡界面
 * Created by tc on 2016/11/28.
 */

public class BankCardActivity extends BaseActivity {

    @BindView(R.id.lin_add_card)
    AutoLinearLayout mLinAddCard;
    @BindView(R.id.list_bank_card)
    ListView mListBankCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_card);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.lin_add_card)
    public void onClick() {
        DialogUtils.showReleaseGoodsDialog();
    }
}
