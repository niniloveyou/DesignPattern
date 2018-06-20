package handler

import code.DesignPatternCodeGenerateFactory
import model.ActionModel
import model.DesignPatternModel
import utils.Utils
import model.entity.BuilderEntity
import model.ActionType
import utils.PsiUtils

/**
 * @author deadline
 * @time 2018/6/15
 */
class BuilderHandler : BaseHandler() {

    override fun handle(actionModel: ActionModel, model: DesignPatternModel) {
        val entity = BuilderEntity()
        entity.packageName = PsiUtils.getPackageName(actionModel.psiDirectoryFactory, actionModel.psiDirectory)
        entity.className = "ClassConfig"

        DesignPatternCodeWriter.write(
                actionModel,
                model,
                entity,
                ActionType.Create,
                DesignPatternCodeGenerateFactory.generateCodeGenerate(model, entity)
        )
    }
}