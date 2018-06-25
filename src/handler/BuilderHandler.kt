package handler

import model.ActionModel
import model.DesignPatternModel
import model.entity.BuilderEntity
import model.ActionType
import utils.Utils

/**
 * @author deadline
 * @time 2018/6/15
 */
class BuilderHandler : BaseHandler() {

    override fun handle(actionModel: ActionModel, model: DesignPatternModel) {

        val entity = BuilderEntity()

        if (actionModel.actionType == ActionType.Create) {
            entity.className = Utils.showInputDialog()
        }

        DesignPatternCodeWriter.write(actionModel, model, entity)
    }
}