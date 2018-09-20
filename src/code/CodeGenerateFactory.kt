package code

import model.DesignPatternEnum
import model.DesignPatternModel
import model.entity.BaseEntity
import model.entity.SingletonEntity

/**
 * @author deadline
 * @time 2018/5/11
 */
object DesignPatternCodeGenerateFactory {

    /**
     * 创建对应的handler
     */
    fun <T : BaseEntity> generateCodeGenerate(
            model: DesignPatternModel, entity: T): ICodeGenerate<T> {
        return when (model.patternEnum) {

            DesignPatternEnum.Singleton -> {
                val singleton = entity as SingletonEntity
                if (singleton.type == SingletonType.Hungry) {
                    SingletonHungryGenerate()
                } else {
                    SingletonLazyGenerate()
                }
            }

            DesignPatternEnum.Builder -> {
                BuilderGenerate()
            }

            DesignPatternEnum.Factory -> {

            }

            DesignPatternEnum.Prototype -> {

            }
            else -> {
                throw IllegalArgumentException("unknown design pattern !!!")
            }

        } as ICodeGenerate<T>

    }

}