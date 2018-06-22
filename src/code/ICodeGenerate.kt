package code

import model.ActionModel
import model.CodeFile
import model.entity.BaseEntity

/**
 * @author deadline
 * @time 2018/6/1
 */
interface ICodeGenerate<T: BaseEntity> {

    /** 在当前文件直接修改java 代码 **/
    fun generateJavaCode(entity: T, actionModel: ActionModel)

    /** 生成新的java 文件 **/
    fun generateJavaFile(entity: T): List<CodeFile>

    /** 生成新的kotlin 文件 **/
    fun generateKotlinFile(entity: T): List<CodeFile>
}