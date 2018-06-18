package handler

import code.DesignPatternCodeGenerateFactory
import model.ActionModel
import model.DesignPatternModel
import utils.PsiUtils
import model.ActionType
import model.entity.FactoryEntity

/**
 * @author deadline
 * @time 2018/6/18
 */
class FactoryHandler (private val supportCreate: Boolean,
                      private val supportUpdate: Boolean)
    : BaseHandler(supportCreate, supportUpdate) {

    override fun handle(actionModel: ActionModel, model: DesignPatternModel) {
        val entity = FactoryEntity()
        entity.packageName = PsiUtils.getPackageName(actionModel.psiDirectoryFactory, actionModel.psiDirectory)
        entity.className = "XxFactory"

        DesignPatternCodeWriter.write(
                actionModel,
                model,
                entity,
                ActionType.Create,
                DesignPatternCodeGenerateFactory.generateCodeGenerate(model, entity))
    }

}