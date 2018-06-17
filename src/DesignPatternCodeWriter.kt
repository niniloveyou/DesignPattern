import callback.CodeWriterCallback
import callback.ProgressCodeWriterCallback
import code.ICodeGenerate
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.OpenFileDescriptor
import com.intellij.openapi.ui.MessageType
import com.intellij.psi.codeStyle.CodeStyleManager
import model.*
import model.entity.BaseEntity
import ui.Toast
import java.io.File

/**
 * @author deadline
 * @time 2018/5/16
 */
object DesignPatternCodeWriter {

    fun <T: BaseEntity> write(actionModel: ActionModel,
                              model: DesignPatternModel,
                              entity: T,
                              actionType: ActionType,
                              generate: ICodeGenerate<T>,
                              callback: CodeWriterCallback = ProgressCodeWriterCallback()) {

        WriteCommandAction.runWriteCommandAction(actionModel.project,
                Runnable {
                    if (actionType == ActionType.Update) {
                        generate.generateCode(entity, actionModel)
                        CodeStyleManager.getInstance(actionModel.project).reformat(actionModel.psiClass)
                    } else {
                        writeFile(actionModel, generate.generateFile(entity))
                    }
                })
    }

    private fun writeFile(actionModel: ActionModel, list: List<CodeFile>){
        var tempFile: File
        for (i in list.indices) {
            val item = list[i]
            val fileType = if (item.codeType == CodeType.Java) JavaFileType() else KotlinFileType()

            val psiFile = actionModel.psiFileFactory.createFileFromText(
                    item.file.typeSpec!!.name + fileType.defaultExtension,
                    fileType,
                    item.file.toString())

            if (actionModel.psiDirectory == null) {
                Toast.make(actionModel.project, MessageType.ERROR, "can not find directory!!!")
                return
            }

            actionModel.psiDirectory!!.add(psiFile)

             // 用编辑器打开指定文件
           /* FileEditorManager.getInstance(actionModel.project)
                    .openTextEditor(OpenFileDescriptor(actionModel.project, psiFile.virtualFile), true)*/
        }
    }
}