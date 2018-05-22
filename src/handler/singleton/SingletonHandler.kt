package handler.singleton

import handler.IDesignPatternHandler
import mode.ActionModel
import mode.ClassMessage
import mode.DesignPatternModel

/**
 * 单例模式的处理
 * @author deadline
 * @time 2018/5/11
 */
class SingletonHandler(): IDesignPatternHandler {

    override fun generateModel(): ClassMessage {
        return ClassMessage()
    }

    override fun handle(actionModel: ActionModel, model: DesignPatternModel) {

    }
}