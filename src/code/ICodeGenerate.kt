package code

import com.squareup.javapoet.JavaFile
import groovy.lang.Tuple2
import model.ActionModel
import model.CodeFile
import model.entity.BaseEntity

/**
 * @author deadline
 * @time 2018/6/1
 */
interface ICodeGenerate<T: BaseEntity> {

    /** 在当前文件直接修改 **/
    fun generateCode(entity: T, actionModel: ActionModel)

    /** 生成新的java 文件 **/
    fun generateFile(entity: T): List<CodeFile>
}