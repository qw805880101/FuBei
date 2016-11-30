package com.example.fubei.Http;


import com.example.fubei.utils.Utils;

import java.util.Map;



/**
 * Created by tc on 2016/7/25.
 */
public class AsyncExecute {
    private HttpConnectListener listener;
    private String contentStr, requestHead;
    private Class cla;
    /**
     * @param listener    链接监听
     * @param map         传输数据
     * @param requestHead 报文头
     * @param cla         返回对象类型
     */
    public AsyncExecute(HttpConnectListener listener, Map<String, String> map, String requestHead, Class cla) {
        this.listener = listener;
        this.requestHead = requestHead;
        this.contentStr = Utils.getPubContent(map, requestHead);
        this.cla = cla;
        Utils.log(requestHead + "报文 = " + contentStr);
        http();
    }

    private void http() {
        try {
            HttpConnection connection = new HttpConnection(listener, contentStr, cla, requestHead);
            HttpConnection.isDestroy = true;
            connection.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
