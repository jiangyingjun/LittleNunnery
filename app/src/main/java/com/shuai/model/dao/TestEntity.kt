package com.shuai.model.dao

import org.greenrobot.greendao.annotation.Entity
import org.greenrobot.greendao.annotation.Id

/**
 * Created by jiangyingjun on 2017/10/19.
 */
@Entity
class TestEntity {

    @Id
    private val id: Int = 0


    private val name: String? = null
}
