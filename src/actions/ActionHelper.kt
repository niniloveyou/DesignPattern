package actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.editor.Editor
import com.intellij.psi.*
import com.intellij.psi.impl.file.PsiDirectoryFactory
import com.intellij.psi.util.PsiUtilBase
import model.ActionModel
import ui.DesignPatternJFrame
import java.awt.Dimension
import javax.swing.WindowConstants
import model.ActionType

/**
 * @author deadline
 * @time 2018/6/15
 */
abstract class ActionHelper(private val actionType: ActionType) {


    private lateinit var psiElementFactory: PsiElementFactory
    private lateinit var mDialog: DesignPatternJFrame

    abstract fun getTargetClass(editor: Editor, file: PsiFile): PsiClass?

    fun performAction(event: AnActionEvent){
        val project = event.getData(PlatformDataKeys.PROJECT)
        val editor = event.getData(PlatformDataKeys.EDITOR)
        val mFile = PsiUtilBase.getPsiFileInEditor(editor!!, project!!)
        val psiClass = getTargetClass(editor, mFile!!)
        psiElementFactory = JavaPsiFacade.getElementFactory(project)
        var directory = mFile.containingDirectory

        mDialog = DesignPatternJFrame("DesignPattern", actionType)
        mDialog.updateActionModel(ActionModel(project,
                editor, mFile,
                psiClass!!, psiElementFactory,
                PsiFileFactory.getInstance(project),
                PsiDirectoryFactory.getInstance(project),
                directory))
        showDesignPatternJFrame(event)

    }


    private fun showDesignPatternJFrame(e: AnActionEvent) {
        mDialog.defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
        mDialog.preferredSize = Dimension(600, 600)
        mDialog.pack()
        mDialog.setLocationRelativeTo(null)
        mDialog.isVisible = true
    }
}