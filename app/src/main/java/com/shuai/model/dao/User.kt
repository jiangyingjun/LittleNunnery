package com.shuai.model.dao

import org.greenrobot.greendao.annotation.Entity
import org.greenrobot.greendao.annotation.Id

/**
 * Created by jiangyingjun on 2017/8/3.
 */
@Entity
class User{
    @Id(autoincrement = true)
    var id:Long?=null

    var name:String?=null

    var password:String?=null

    var gender:Int?=null

    var address:String?=null

}