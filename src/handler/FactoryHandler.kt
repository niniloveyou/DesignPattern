package handler

import code.FactoryType
import model.ActionModel
import model.DesignPatternModel
import utils.PsiUtils
import model.*
import model.entity.FactoryEntity

/**
 * @author deadline
 * @time 2018/6/18
 */
class FactoryHandler : BaseHandler() {

    override fun handle(actionModel: ActionModel, model: DesignPatternModel) {
        val entity = FactoryEntity(FactoryType.Factory)
        entity.packageName = PsiUtils.getPackageName(actionModel.psiDirectoryFactory, actionModel.psiDirectory)
        entity.className = "XxFactory"

        DesignPatternCodeWriter.write(actionModel, model, entity)
    }

}