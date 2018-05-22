package handler

import handler.singleton.SingletonHandler
import mode.DesignPatternEnum
import mode.DesignPatternModel

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
                DesignPatternEnum.Singleton -> SingletonHandler()
                DesignPatternEnum.Bridge -> SingletonHandler()
                DesignPatternEnum.Builder -> SingletonHandler()
                DesignPatternEnum.Strategy -> SingletonHandler()
                DesignPatternEnum.Adapter -> SingletonHandler()
                DesignPatternEnum.Factory -> SingletonHandler()
                DesignPatternEnum.Chain -> SingletonHandler()
            }
        }
    }
}