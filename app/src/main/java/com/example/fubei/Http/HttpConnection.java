/**
 *
 */
package com.example.fubei.Http;

import android.os.AsyncTask;

import com.example.fubei.utils.Info;
import com.example.fubei.utils.Utils;

import java.io.ByteArrayInputStream;

/**
 * @author dengzhuanglei
 */
public class HttpConnection extends AsyncTask<Void, Void, String> {

    private HttpConnectListener listener;
    private String contentStr, requestHead;
    private Class cla;
    public static boolean isDestroy = false;

    //    private static String httpURL = "http://172.21.10.170:8080/ipos-pay/mpi.ac";
    private static String httpURL = "http://140.207.38.90:8282/ipos-pay/mpi.ac";

    /**
     * 网络连接
     *
     * @param listener   连接成功或失败监听
     * @param contentStr 报文
     * @param cla        对象类型
     */
    public HttpConnection(HttpConnectListener listener, String contentStr, Class cla, String requestHead) throws
            Exception {
        this.listener = listener;
        this.requestHead = requestHead;
        this.contentStr = Encryption.encode(contentStr);
        this.cla = cla;
        Encryption.getCR();
    }

    @Override
    protected String doInBackground(Void... params) {
        if (Info.isTimeOut) {
            return null;
        }
        HttpUtils httpUtils = HttpUtils.createHttpUtils();
        String result = httpUtils.getUrlContext(httpURL, contentStr);
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        if (isDestroy) {
            if (result == null || result.equals("")) {
                if (Info.isTimeOut) {
//                dialogFinish("登录超时,请重新登录...");
                } else {
                    listener.onFail("网络错误,请稍后重试...");
                }
            } else {
                if (!Encryption.decode(result))
                    listener.onFail("报文解析失败");
                else {
                    Utils.log("Info.netResult = " + Info.netResult);
                    //xml报文解析为对象返回
                    Object obj = null;
                    try {
                        obj = XmlUtils.getBeanListByParseXml(new ByteArrayInputStream(Info.netResult.getBytes()),
                                "upPay", cla);
                    } catch (Exception e) {
                        listener.onFail("xml报文解析失败");
                    }
                    if (obj != null)
                        listener.onSuccess(obj, requestHead);
                    else
                        listener.onFail("请求无返回");
                }
            }
        } else {
            //取消网络请求
            System.out.println("isDestroy = " + isDestroy);
        }
    }

//    private void dialogFinish(String name) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(Info.currentActivity);
//        builder.setMessage(name);
//        builder.setTitle("提示");
//        builder.setCancelable(false);
//        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//                Info.isTimeOut = false;
//                Intent intent = new Intent(Info.currentActivity, UserLogin_Activity.class);
//                //返回并且不销毁activity
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                Info.currentActivity.startActivity(intent);
//            }
//        });
//        builder.create().show();
//    }
}
