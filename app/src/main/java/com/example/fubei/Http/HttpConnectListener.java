package com.example.fubei.Http;

public interface HttpConnectListener {
	void onSuccess(Object result, String requestHead);
	void onFail(String failLog);
}
