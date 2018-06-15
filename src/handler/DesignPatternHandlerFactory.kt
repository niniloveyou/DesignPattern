package handler

import model.DesignPatternEnum
import model.DesignPatternModel

/**
 * @author deadline
 * @time 2018/5/11
 */
class DesignPatternHandlerFactory {

    companion object {

        /**
         * 创建对应的handler
         */
        fun generateHandler(model: DesignPatternModel) : IDesignPatternHandler {
            return when(model.patternEnum) {
                DesignPatternEnum.Singleton -> SingletonHandler(true, true)
                DesignPatternEnum.Bridge -> SingletonHandler(true, true)
                DesignPatternEnum.Builder -> SingletonHandler(true, true)
                DesignPatternEnum.Strategy -> SingletonHandler(true, true)
                DesignPatternEnum.Adapter -> SingletonHandler(true, true)
                DesignPatternEnum.Factory -> SingletonHandler(true, true)
                DesignPatternEnum.Chain -> SingletonHandler(true, true)
            }
        }
    }
}