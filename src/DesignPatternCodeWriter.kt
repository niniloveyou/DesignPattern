import callback.CodeWriterCallback
import callback.ProgressCodeWriterCallback
import code.DesignPatternCodeGenerateFactory
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.ui.Messages
import com.intellij.psi.codeStyle.CodeStyleManager
import model.*
import model.entity.BaseEntity
import utils.PsiUtils
import utils.Utils

/**
 * @author deadline
 * @time 2018/5/16
 */
object DesignPatternCodeWriter {

    fun <T: BaseEntity> write(actionModel: ActionModel,
                              model: DesignPatternModel,
                              entity: T,
                              callback: CodeWriterCallback = ProgressCodeWriterCallback()) {

        WriteCommandAction.runWriteCommandAction(actionModel.project,
                Runnable {
                    entity.packageName = PsiUtils.getPackageName(actionModel.psiDirectoryFactory, actionModel.psiDirectory)
                    val codeType = Utils.getDefaultCodeType()
                    val generate = DesignPatternCodeGenerateFactory.generateCodeGenerate(model, entity)
                    if (actionModel.actionType == ActionType.Update) {
                        generate.generateJavaCode(entity, actionModel)
                        CodeStyleManager.getInstance(actionModel.project).reformat(actionModel.psiClass!!)
                    } else if (codeType == CodeType.Java){
                        if (entity.className.isNullOrEmpty()) {
                            return@Runnable
                        }
                        writeFile(actionModel, generate.generateJavaFile(entity))
                    } else if (codeType == CodeType.Kotlin) {
                        if (entity.className.isNullOrEmpty()) {
                            return@Runnable
                        }
                        writeFile(actionModel, generate.generateKotlinFile(entity))
                    }
                })
    }

    private fun writeFile(actionModel: ActionModel, list: List<CodeFile>){
        for (i in list.indices) {
            val item = list[i]
            val fileType = if (item.codeType == CodeType.Java) JavaFileType() else KotlinFileType()
            val fileName = if (item.codeType == CodeType.Java) item.javaFile?.typeSpec?.name + fileType.defaultExtension else item.kotlinFile?.name + fileType.defaultExtension
            val classContent = if (item.codeType == CodeType.Java) item.javaFile.toString() else item.kotlinFile.toString()
            val psiFile = actionModel.psiFileFactory.createFileFromText(fileName, fileType, classContent)

            if (actionModel.psiDirectory == null) {
                Messages.showErrorDialog("提示", "can not find directory!!!")
                return
            }

            actionModel.psiDirectory!!.add(psiFile)

            // 格式化代码
            CodeStyleManager.getInstance(actionModel.project).reformat(psiFile)

             // 用编辑器打开指定文件
            Utils.openFileInPanel(actionModel.psiDirectory!!.virtualFile.path + "/" + fileName, actionModel.project)
        }
    }
}