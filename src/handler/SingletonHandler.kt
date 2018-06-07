package handler

import callback.ProgressCodeWriterCallback
import code.DesignPatternCodeGenerateFactory
import code.ICodeGenerate
import code.SingletonHungryGenerate
import code.SingletonType
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import handler.BaseHandler
import mode.ActionModel
import mode.CodeType
import mode.DesignPatternModel
import mode.entity.BaseEntity
import mode.entity.SingletonEntity
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
         val virtualFile = actionModel.psiFile.virtualFile

         entity.filePath = virtualFile.path.replace(virtualFile.name, "")
         DesignPatternCodeWriter.write(actionModel, model, entity, CodeType.Poet,
                DesignPatternCodeGenerateFactory.generateCodeGenerate(model, entity),
                ProgressCodeWriterCallback())
    }

    private fun generateJFrame() {

    }

    private fun onConfirmClick() {


    }
}