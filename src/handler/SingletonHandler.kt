package handler

import callback.ProgressCodeWriterCallback
import code.DesignPatternCodeGenerateFactory
import code.SingletonType
import model.ActionModel
import model.ActionType
import model.DesignPatternModel
import model.entity.SingletonEntity
import utils.Utils


/**
 * 单例模式的处理
 * @author deadline
 * @time 2018/5/11
 */
class SingletonHandler(private val supportPoet: Boolean,
                       private val supportPsi: Boolean)
                        : BaseHandler(supportPoet, supportPsi) {

    override fun handle(actionModel: ActionModel, model: DesignPatternModel) {

        // do set message
        val entity = SingletonEntity(SingletonType.Hungry)
         entity.packageName = Utils.getPackageName(actionModel.psiDirectoryFactory, actionModel.psiDirectory)
         entity.className = "FunctionMenuManager"

         DesignPatternCodeWriter.write(actionModel, model, entity, ActionType.Create,
                DesignPatternCodeGenerateFactory.generateCodeGenerate(model, entity))
    }

    private fun generateJFrame() {

    }

    private fun onConfirmClick() {


    }
}