package handler

import callback.ProgressCodeWriterCallback
import code.DesignPatternCodeGenerateFactory
import code.SingletonType
import com.intellij.openapi.ui.Messages
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
class SingletonHandler(private val supportCreate: Boolean,
                       private val supportUpdate: Boolean)
                        : BaseHandler(supportCreate, supportUpdate) {

    override fun handle(actionModel: ActionModel, model: DesignPatternModel) {
       // Messages.showInputDialog(actionModel.project, "What is your name?", "Input your name", Messages.getQuestionIcon())
        // do set message
        val entity = SingletonEntity(SingletonType.Lazy)
         entity.packageName = Utils.getPackageName(actionModel.psiDirectoryFactory, actionModel.psiDirectory)
         entity.className = "FunctionMenuManager"

         DesignPatternCodeWriter.write(actionModel, model, entity, ActionType.Update,
                DesignPatternCodeGenerateFactory.generateCodeGenerate(model, entity))
    }

    private fun generateJFrame() {

    }

    private fun onConfirmClick() {


    }
}