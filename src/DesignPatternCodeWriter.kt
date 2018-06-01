import callback.CodeWriterCallback
import code.ICodeGenerate
import com.intellij.openapi.command.WriteCommandAction
import mode.ActionModel
import mode.CodeType
import mode.DesignPatternModel
import mode.entity.BaseEntity
import callback.ProgressCodeWriterCallback
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

                })
    }


    //generate.generatePsi(entity)

    //classMessage.generate("fd")
    // val psiClass = actionModel.psiElementFactory.createClassFromText(classString, null)
    //val file = File("/Users/duoke/Documents/Calculate/app/src/main/java/deadline/calculate/SingletonClass.java")
    //javaFile.writeTo(file)
    //val psiField = actionModel.psiElementFactory.createFieldFromText("var number = 0", null)
    //CodeStyleManager.getInstance(actionModel.project).reformat(psiField)
    //actionModel.psiClass!!.add(psiField)
}