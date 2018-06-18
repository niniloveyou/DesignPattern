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
            return when (model.patternEnum) {
                DesignPatternEnum.Factory -> FactoryHandler(true, true)
                DesignPatternEnum.Singleton -> SingletonHandler(true, true)
                DesignPatternEnum.Builder -> BuilderHandler(true, true)
                DesignPatternEnum.Prototype -> PrototypeHandler(false, true)
                DesignPatternEnum.Adapter -> AdapterHandler(true, true)

                DesignPatternEnum.Decorator -> SingletonHandler(true, true)
                DesignPatternEnum.Proxy -> BuilderHandler(true, true)
                DesignPatternEnum.Facade -> SingletonHandler(true, true)
                DesignPatternEnum.Bridge -> SingletonHandler(true, true)
                DesignPatternEnum.Composite -> SingletonHandler(true, true)
                DesignPatternEnum.FlyWeight -> BuilderHandler(true, true)
                DesignPatternEnum.Strategy -> SingletonHandler(true, true)
                DesignPatternEnum.Template -> SingletonHandler(true, true)
                DesignPatternEnum.Observer -> SingletonHandler(true, true)
                DesignPatternEnum.Iterator -> BuilderHandler(true, true)
                DesignPatternEnum.Chain -> SingletonHandler(true, true)
                DesignPatternEnum.Command -> SingletonHandler(true, true)
                DesignPatternEnum.Memento -> SingletonHandler(true, true)
                DesignPatternEnum.State -> BuilderHandler(true, true)
                DesignPatternEnum.Visitor -> SingletonHandler(true, true)
                DesignPatternEnum.Mediator -> SingletonHandler(true, true)
                DesignPatternEnum.Interpreter -> SingletonHandler(true, true)
                DesignPatternEnum.Delegation -> SingletonHandler(true, true)
                else -> {
                    throw IllegalArgumentException("unknown design pattern!!!")
                }
            }
        }
    }
}