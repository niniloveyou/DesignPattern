package code

import model.ActionModel
import model.CodeFile
import model.entity.BaseEntity

/**
 * @author deadline
 * @time 2018/6/20
 */
open class BaseCodeGenerate<T: BaseEntity> : ICodeGenerate<T> {

    override fun generateCode(entity: T, actionModel: ActionModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun generateFile(entity: T): List<CodeFile> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}