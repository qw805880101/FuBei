package com.example.fubei.utils;

import android.view.View;

import com.example.fubei.R;
import com.example.fubei.widget.ActionSheetDialog;

/**
 * Created by tc on 2016/11/28.
 */

public class DialogUtils {

    /* 显示发布新商品对话框 */
    public static void showReleaseGoodsDialog() {
        final ActionSheetDialog dialog = new ActionSheetDialog(Info.currentActivity);
        dialog.builder();
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setOnClick(new ActionSheetDialog.OnSheetItemClickListener() {
            @Override
            public void onClick(View view, int pos) {
                switch (view.getId()) {
                    case R.id.txt_mod_bank_card:
                        System.out.println("更改银行卡");
                        break;
                    case R.id.txt_del_bank_card:
                        System.out.println("删除银行卡");
                        break;
                    default:
                        dialog.dismiss();
                        break;
                }
            }
        });
        dialog.show();
    }

}
