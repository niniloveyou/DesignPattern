package code

import com.squareup.javapoet.JavaFile
import groovy.lang.Tuple2
import mode.ActionModel
import mode.entity.BaseEntity

/**
 * @author deadline
 * @time 2018/6/1
 */
interface ICodeGenerate<T: BaseEntity> {

    /** 在当前文件直接修改 **/
    fun generatePsi(entity: T, actionModel: ActionModel)

    /** 生成新的java 文件 **/
    fun generatePoet(entity: T): List<Tuple2<String, JavaFile>>
}