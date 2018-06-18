package handler

import model.ActionModel
import model.DesignPatternModel

/**
 * @author deadline
 * @time 2018/6/18
 */
class AdapterHandler (private val supportCreate: Boolean,
                      private val supportUpdate: Boolean)
    : BaseHandler(supportCreate, supportUpdate) {

    override fun handle(actionModel: ActionModel, model: DesignPatternModel) {

    }

}