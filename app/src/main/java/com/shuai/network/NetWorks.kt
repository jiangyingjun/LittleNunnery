package com.shuai.network


import com.shuai.model.bean.ReceiveDTO
import com.shuai.model.bean.RequestDTO
import com.shuai.utils.Config
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.http.*
import rx.Observable
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

object NetWorks {

    //创建实现接口调用

    private val service = RetrofitUtils.retrofit.create(NetService::class.java)

    private interface NetService {
        //"Accept: textml,application/xml,application/xhtml+xml,textml;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5",
//        @Headers( "Content-Type: text/plain;charset=UTF-8")
//        @GET("test/test")
        @POST("/test/test")
        fun homeData(): Observable<String>

        @Streaming
        @GET("product/detail?productID=159602")
        fun  downLoadFileNet(@Url url:String):Observable<ResponseBody>

        @GET("product/detail?productID=159602")
        fun  dataFromData():Observable<ReceiveDTO>

        @Headers( "Content-Type: application/json")//;charset=UTF-8
        @POST("/login/login")
        fun login(@Body requestDTO: RequestDTO):Observable<ReceiveDTO>

//        @FormUrlEncoded
        @Multipart
        @POST
        fun FacePPdetectApi(@Url url: String, @Part("api_key") api_key: RequestBody, @Part("api_secret") api_secret:RequestBody, @Part("return_landmark")return_landmark:RequestBody, @Part("return_attributes")return_attributes:RequestBody, @Part img_file: MultipartBody.Part):Observable<String>


        @POST("/start/img")
        fun startAppPacture(): Observable<String>


    }

    private fun <T> setSubscribe(observable: Observable<T>, observer: Observer<T>) {
        observable.subscribeOn(Schedulers.newThread())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer)
    }


    fun userLogin(observer: Observer<ReceiveDTO>,requestDTO: RequestDTO){
        setSubscribe(service.login(requestDTO),observer)
    }


    fun getStartAppPacture(observer: Observer<String>) {
        setSubscribe(service.startAppPacture(), observer)
    }

//    fun  downLoadFile (url: String,subscriber:Subscriber){
//        service!!.downLoadFileNet(url).
//
//    }

    fun getDataFromData(observer:Observer<ReceiveDTO>){
        setSubscribe(service.dataFromData(),observer )
    }


    fun facePPdetectApi(url: String, api_key: RequestBody,api_secret: RequestBody,return_landmark:RequestBody,return_attributes:RequestBody,img_file: MultipartBody.Part, observer:Observer<String>){
        setSubscribe(service.FacePPdetectApi(url,api_key,api_secret,return_landmark,return_attributes,img_file),observer)
    }
}
