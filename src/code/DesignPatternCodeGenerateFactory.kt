package code

import mode.DesignPatternEnum
import mode.DesignPatternModel
import mode.entity.BaseEntity

/**
 * @author deadline
 * @time 2018/5/11
 */
class DesignPatternCodeGenerateFactory {

    companion object {

        /**
         * 创建对应的handler
         */
        fun <T: BaseEntity> generateCodeGenerate(
                model: DesignPatternModel, entity: T): ICodeGenerate<T> {

            return SingletonHungryGenerate() as ICodeGenerate<T>
        }
    }
}