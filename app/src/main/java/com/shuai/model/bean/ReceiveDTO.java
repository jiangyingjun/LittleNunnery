package com.shuai.model.bean;

/**
 * Author:   wuxingliang
 * Version：    5.4.8
 * Date:     2016/8/09
 * Description:
 * 		接口返回参数基本类
 *
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 *                                                          1.0
 * Why & What is modified:
 *      1.0：
 */
public class ReceiveDTO {
    /**
     * 错误信息ID 0表示请求成功
     */
    private int MC;
    /**
     * 错误信息的详述
     */
    private String MS;
//    /**
//     * 请求服务器返回的服务器时间（Long类型）
//     */
//    private long dateLong;
//
//    /**
//     * 请求服务器返回的服务器时间（String类型）yyyy-MM-dd HH:mm:ss
//     */
//    private String dateString;
//    /**
//     * 请求服务器返回的服务器时间（GMT类型）EEE, d MMM yyyy HH:mm:ss 'GMT'
//     */
//    private String dateGMT;
    /**
     * 经过序列化并DES加密的数据体
     */
    private String Content;

    public int getMC() {
        return MC;
    }

    public void setMC(int mC) {
        this.MC = mC;
    }

    public String getMS() {
        return MS;
    }

    public void setMS(String mS) {
        this.MS = mS;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        this.Content = content;
    }

//    public long getDateLong() {
//        return dateLong;
//    }
//
//    public void setDateLong(long dateLong) {
//        this.dateLong = dateLong;
//    }
//
//    public String getDateString() {
//        return dateString;
//    }
//
//    public void setDateString(String dateString) {
//        this.dateString = dateString;
//    }
//
//    public String getDateGMT() {
//        return dateGMT;
//    }
//
//    public void setDateGMT(String dateGMT) {
//        this.dateGMT = dateGMT;
//    }

    @Override
    public String toString() {
        return "CommonReceiveDTO{" +
                "mC=" + MC +
                ", mS='" + MS + '\'' +
//                ", dateLong=" + dateLong +
//                ", dateString='" + dateString + '\'' +
//                ", dateGMT='" + dateGMT + '\'' +
                ", content='" + Content + '\'' +
                '}';
    }
}
