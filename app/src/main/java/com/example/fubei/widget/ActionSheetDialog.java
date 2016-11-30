package com.example.fubei.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.fubei.R;

/**
 * 底部对话框样式
 */
public class ActionSheetDialog {
    private Context context;
    private Dialog dialog;
    private TextView txt_cancel, txt_mod, txt_del;
    private Display display;

    private OnSheetItemClickListener mClickListener;

    public ActionSheetDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    @SuppressWarnings("deprecation")
    public ActionSheetDialog builder() {
        View view = LayoutInflater.from(context).inflate(
                R.layout.view_actionsheet, null);

        view.setMinimumWidth(display.getWidth());

        txt_cancel = (TextView) view.findViewById(R.id.txt_cancel);
        txt_cancel.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        txt_mod = (TextView) view.findViewById(R.id.txt_mod_bank_card);
        txt_mod.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onClick(v, v.getId());
            }
        });

        txt_del = (TextView) view.findViewById(R.id.txt_del_bank_card);
        txt_del.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onClick(v, v.getId());
            }
        });

        dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);
        return this;
    }

    public ActionSheetDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public ActionSheetDialog setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    public void show() {
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public void setOnClick(OnSheetItemClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }

    public interface OnSheetItemClickListener {
        void onClick(View view, int pos);
    }
}
