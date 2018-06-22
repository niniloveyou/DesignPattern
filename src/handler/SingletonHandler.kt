package handler

import code.DesignPatternCodeGenerateFactory
import code.SingletonType
import model.ActionModel
import model.ActionType
import model.CodeType
import model.DesignPatternModel
import model.entity.SingletonEntity
import utils.PsiUtils


/**
 * 单例模式的处理
 * @author deadline
 * @time 2018/5/11
 */
class SingletonHandler : BaseHandler() {

    override fun handle(actionModel: ActionModel, model: DesignPatternModel) {
       // Messages.showInputDialog(actionModel.project, "What is your name?", "Input your name", Messages.getQuestionIcon())
        // do set message
        val entity = SingletonEntity(SingletonType.Lazy)
         entity.packageName = PsiUtils.getPackageName(actionModel.psiDirectoryFactory, actionModel.psiDirectory)
         entity.className = "FunctionMenuManager"

         DesignPatternCodeWriter.write(actionModel, model, entity, ActionType.Update, CodeType.Java,
                 DesignPatternCodeGenerateFactory.generateCodeGenerate(model, entity))
    }

    private fun generateJFrame() {

    }

    private fun onConfirmClick() {


    }
}