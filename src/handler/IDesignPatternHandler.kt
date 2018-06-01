package handler

import mode.ActionModel
import mode.DesignPatternModel

/**
 * @author deadline
 * @time 2018/5/11
 */

interface IDesignPatternSupport {

    /**
     * 是否支持直接创建文件
     */
    fun supportPoet() : Boolean

    /**
     * 是否支持在当前文件上操作
     */
    fun supportPsi() : Boolean
}

interface IDesignPatternHandler : IDesignPatternSupport {

    fun handle(actionModel: ActionModel, model: DesignPatternModel)
}