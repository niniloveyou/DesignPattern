package handler

import mode.ActionModel
import mode.ClassMessage
import mode.DesignPatternModel

/**
 * @author deadline
 * @time 2018/5/11
 */
interface IDesignPatternHandler {

    fun handle(actionModel: ActionModel, model: DesignPatternModel)

    fun generateModel() : ClassMessage
}