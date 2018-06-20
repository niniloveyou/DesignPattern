package handler

import model.ActionModel
import model.DesignPatternModel

/**
 * @author deadline
 * @time 2018/5/11
 */

interface IDesignPatternHandler {

    fun handle(actionModel: ActionModel, model: DesignPatternModel)
}