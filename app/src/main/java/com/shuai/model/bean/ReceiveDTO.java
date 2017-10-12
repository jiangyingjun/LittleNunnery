package com.shuai.model.bean;

public class ReceiveDTO {

    private int errorCode;
    private String errorMsg;
    private String jsonResult;


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(String jsonResult) {
        this.jsonResult = jsonResult;
    }


    @Override
    public String toString() {
        return "ReceiveDTO{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", jsonResult='" + jsonResult + '\'' +
                '}';
    }
}
