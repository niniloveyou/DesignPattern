package code

import model.ActionModel
import model.CodeFile
import model.entity.BaseEntity

/**
 * @author deadline
 * @time 2018/6/20
 */
open class BaseCodeGenerate<T: BaseEntity> : ICodeGenerate<T> {
    override fun generateKotlinFile(entity: T): List<CodeFile> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun generateJavaCode(entity: T, actionModel: ActionModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun generateJavaFile(entity: T): List<CodeFile> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}