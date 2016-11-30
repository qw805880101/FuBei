package com.example.fubei.utils;


import com.example.fubei.R;
import com.example.fubei.bean.ItemBean;

import java.util.ArrayList;
import java.util.List;

public class ItemDataUtils {
    public static List<ItemBean> getItemBeans() {
        List<ItemBean> itemBeans = new ArrayList<>();
        itemBeans.add(new ItemBean(R.mipmap.menu_05, "收支明细"));
        itemBeans.add(new ItemBean(R.mipmap.menu_03, "银行卡管理"));
        itemBeans.add(new ItemBean(R.mipmap.menu_02, "推荐有礼"));
        itemBeans.add(new ItemBean(R.mipmap.menu_06, "我的奖励"));
        itemBeans.add(new ItemBean(R.mipmap.menu_01, "我的活动"));
        itemBeans.add(new ItemBean(R.mipmap.menu_04, "常见问题"));
        itemBeans.add(new ItemBean(R.mipmap.menu_07, "我的客服"));
        itemBeans.add(new ItemBean(R.mipmap.menu_08, "设置"));
        return itemBeans;
    }

}
