package model.entity

import code.SingletonType
import com.intellij.psi.PsiField

/**
 * @author deadline
 * @time 2018/6/1
 */
open class BaseEntity {

    lateinit var className: String

    lateinit var packageName: String
}

/**
 * 单例
 */
class SingletonEntity(val type: SingletonType) : BaseEntity()

/**
 * builder
 */
class BuilderEntity() : BaseEntity() {
    lateinit var fields: List<PsiField>
}

class AdapterEntity() : BaseEntity()

class FactoryEntity() : BaseEntity()