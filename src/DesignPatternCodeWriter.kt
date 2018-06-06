import callback.CodeWriterCallback
import code.ICodeGenerate
import com.intellij.openapi.command.WriteCommandAction
import mode.ActionModel
import mode.CodeType
import mode.DesignPatternModel
import mode.entity.BaseEntity
import callback.ProgressCodeWriterCallback
import com.intellij.psi.codeStyle.CodeStyleManager
import com.squareup.javapoet.JavaFile
import groovy.lang.Tuple2
import java.io.File

/**
 * @author deadline
 * @time 2018/5/16
 */
object DesignPatternCodeWriter {

    fun <T: BaseEntity> write(actionModel: ActionModel,
              model: DesignPatternModel,
              entity: T, codeType: CodeType,
              generate: ICodeGenerate<T>,
              callback: CodeWriterCallback = ProgressCodeWriterCallback()) {

        WriteCommandAction.runWriteCommandAction(actionModel.project,
                Runnable {
                    if (codeType == CodeType.Psi) {
                        generate.generateCode(entity, actionModel)
                        CodeStyleManager.getInstance(actionModel.project).reformat(actionModel.psiClass!!);
                    } else {
                        writeFile(actionModel, generate.generateFile(entity))
                    }
                })

        /*ProgressManager.getInstance().run(object : Task.Backgroundable(actionModel.project, "DesignPattern") {

            override fun run(progressIndicator: ProgressIndicator) {
                progressIndicator.isIndeterminate = true
                execute()
                progressIndicator.isIndeterminate = false
                progressIndicator.fraction = 1.0
            }
        })*/
    }

    private fun writeFile(actionModel: ActionModel, list: List<Tuple2<String, JavaFile>>){
        var tempFile: File
        for (i in list.indices) {
            val item = list[i]
            tempFile = File(item.first)
            item.second.writeTo(tempFile)
           // actionModel.psiFileFactory.createFileFromText(tempFile.toString(), )
        }
    }

    //if (ConfigManager.enableAutoReformat) {
    //                var processor: AbstractLayoutCodeProcessor =
    //                    ReformatCodeProcessor(project, fileAdded as PsiFile, null, false)
    //                processor = OptimizeImportsProcessor(processor)
    //                processor = RearrangeCodeProcessor(processor)
    //                processor.run()
    //            }
}