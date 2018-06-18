package handler

import model.ActionModel
import model.DesignPatternModel

/**
 * @author deadline
 * @time 2018/6/18
 * https://blog.csdn.net/zhengzhb/article/details/7393528
 * 深浅拷贝
 */
class PrototypeHandler (private val supportCreate: Boolean,
                        private val supportUpdate: Boolean)
    : BaseHandler(supportCreate, supportUpdate) {

    override fun handle(actionModel: ActionModel, model: DesignPatternModel) {

    }

}