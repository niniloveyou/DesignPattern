package code

import model.DesignPatternEnum
import model.DesignPatternModel
import model.entity.BaseEntity
import model.entity.SingletonEntity

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
           when(model.patternEnum) {
                DesignPatternEnum.Singleton -> {
                    val singleton = entity as SingletonEntity
                    return if (singleton.type == SingletonType.Hungry) {
                        SingletonHungryGenerate() as ICodeGenerate<T>
                    } else {
                        SingletonLazyGenerate() as ICodeGenerate<T>
                    }
                }

               DesignPatternEnum.Builder -> {
                    return BuilderGenerate() as ICodeGenerate<T>
               }
            }
            throw IllegalArgumentException("unknown design pattern !!!")
        }
    }
}