import callback.CodeWriterCallback
import com.intellij.openapi.command.WriteCommandAction
import mode.ActionModel
import mode.ClassMessage
import java.io.File

/**
 * @author deadline
 * @time 2018/5/16
 */
object DesignPatternCodeWriter {

    @JvmStatic fun write(actionModel: ActionModel,
                         action: DesignPatternAction,
                         classMessage: ClassMessage,
                         callback: CodeWriterCallback) {

        WriteCommandAction.runWriteCommandAction(actionModel.project,
                Runnable {

                })
    }
}