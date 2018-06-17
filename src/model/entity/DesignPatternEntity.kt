package model.entity

import code.SingletonType

/**
 * @author deadline
 * @time 2018/6/1
 */
open class BaseEntity {

    lateinit var className: String

    lateinit var packageName: String
}

class SingletonEntity(val type: SingletonType) : BaseEntity()

class BuilderEntity() : BaseEntity()