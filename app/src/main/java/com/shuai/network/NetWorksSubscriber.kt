package com.shuai.network

import rx.Subscriber

/**
 * Created by lianglaing on 2016-08-11.
 */
abstract class NetWorksSubscriber<T> : Subscriber<T>() {
    override fun onCompleted() {//网络请求完成时
        if (!this.isUnsubscribed) {
            this.unsubscribe()
        }
    }

    override fun onError(e: Throwable) {//网络请求出错时
        if (!this.isUnsubscribed) {
            this.unsubscribe()
        }
    }

    override fun onNext(t: T) {//网络请求的数据接收

    }

    /**
     * 对Subscriber的解绑
     */
    fun unSubscribe() {
        if (!this.isUnsubscribed) {
            this.unsubscribe()
        }
    }
}
