package handler

import model.ActionModel
import model.DesignPatternModel

/**
 * @author deadline
 * @time 2018/5/11
 */

interface IDesignPatternSupport {

    /**
     * 是否支持直接创建文件
     */
    fun supportCreate() : Boolean

    /**
     * 是否支持在当前文件上操作
     */
    fun supportUpdate() : Boolean
}

interface IDesignPatternHandler : IDesignPatternSupport {

    fun handle(actionModel: ActionModel, model: DesignPatternModel)
}