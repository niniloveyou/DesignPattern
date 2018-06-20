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
                DesignPatternEnum.Factory -> FactoryHandler()
                DesignPatternEnum.Singleton -> SingletonHandler()
                DesignPatternEnum.Builder -> BuilderHandler()
                DesignPatternEnum.Prototype -> PrototypeHandler()
                DesignPatternEnum.Adapter -> AdapterHandler()

                DesignPatternEnum.Decorator -> SingletonHandler()
                DesignPatternEnum.Proxy -> BuilderHandler()
                DesignPatternEnum.Facade -> SingletonHandler()
                DesignPatternEnum.Bridge -> SingletonHandler()
                DesignPatternEnum.Composite -> SingletonHandler()
                DesignPatternEnum.FlyWeight -> BuilderHandler()
                DesignPatternEnum.Strategy -> SingletonHandler()
                DesignPatternEnum.Template -> SingletonHandler()
                DesignPatternEnum.Observer -> SingletonHandler()
                DesignPatternEnum.Iterator -> BuilderHandler()
                DesignPatternEnum.Chain -> SingletonHandler()
                DesignPatternEnum.Command -> SingletonHandler()
                DesignPatternEnum.Memento -> SingletonHandler()
                DesignPatternEnum.State -> BuilderHandler()
                DesignPatternEnum.Visitor -> SingletonHandler()
                DesignPatternEnum.Mediator -> SingletonHandler()
                DesignPatternEnum.Interpreter -> SingletonHandler()
                DesignPatternEnum.Delegation -> SingletonHandler()
                else -> {
                    throw IllegalArgumentException("unknown design pattern!!!")
                }
            }
        }
    }
}