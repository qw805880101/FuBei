package com.example.fubei.Http;

import com.example.fubei.utils.Info;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {

    private static HttpUtils httpUtils = null;

    public static HttpUtils createHttpUtils() {
        if (httpUtils == null)
            httpUtils = new HttpUtils();
        return httpUtils;
    }

    public String getUrlContext(String urlStr, String contentStr) {
        int responseCode = -2;
        InputStream inputStream = null;
        InputStreamReader inputReader = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(urlStr.trim());
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setConnectTimeout(Info.netTimeOut);
            connection.setReadTimeout(Info.netTimeOut);
            connection.setRequestProperty("Authorization", "Basic");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setRequestProperty("Content-type",
                    "text/xml; charset=UTF-8");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.getOutputStream().write(
                    contentStr.trim().getBytes("UTF-8"));
            responseCode = connection.getResponseCode();

            if (responseCode >= 200 && responseCode <= 299) {
                inputStream = connection.getInputStream();
                inputReader = new InputStreamReader(inputStream);
                reader = new BufferedReader(inputReader);
                String inputLine;
                StringBuffer strBuffer = new StringBuffer();
                try {
                    while ((inputLine = reader.readLine()) != null) {
                        strBuffer.append(inputLine);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
                String result = strBuffer.toString();
                connection.disconnect();
                if (result == null || result.equals("")) {
                    return null;
                } else {
                    return result;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        } finally {
            try {
                if (inputReader != null) {
                    inputReader.close();
                    inputReader = null;
                }
                if (reader != null) {
                    reader.close();
                    reader = null;
                }
                if (inputStream != null) {
                    inputStream.close();
                    inputStream = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
