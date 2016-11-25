package com.example.fubei.bean;
/**
 * 初始化
 * Created by tc on 2016/7/25.
 */
public class TerminalInit_Bean {

    private String updateStatus; //更新状态
    private String updateAddress; //更新地址
    private String msgExt; //附加信息 更新描述
    private String downParam; //参数下载  例如：0|1|0
                                        //第一位：银行总行码表
                                        //第二位：店铺类型码表
                                        //第三位：商品类型码表
    private String respCode; //00：成功；其他为失败
    private String respDesc; //返回详细的操作结果信息

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    public String getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }

    public String getUpdateAddress() {
        return updateAddress;
    }

    public void setUpdateAddress(String updateAddress) {
        this.updateAddress = updateAddress;
    }

    public String getMsgExt() {
        return msgExt;
    }

    public void setMsgExt(String msgExt) {
        this.msgExt = msgExt;
    }

    public String getDownParam() {
        return downParam;
    }

    public void setDownParam(String downParam) {
        this.downParam = downParam;
    }
}
