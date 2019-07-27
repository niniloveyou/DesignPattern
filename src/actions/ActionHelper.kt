package actions

import com.intellij.ide.IdeView
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.editor.Editor
import com.intellij.psi.*
import com.intellij.psi.impl.file.PsiDirectoryFactory
import com.intellij.psi.util.PsiUtilBase
import model.ActionModel
import model.ActionType
import ui.DesignPatternJFrame
import java.awt.Dimension
import javax.swing.WindowConstants



/**
 * @author deadline
 * @time 2018/6/15
 *
 * 统一处理多个action的共有逻辑
 */
abstract class ActionHelper(private val actionType: ActionType) {


    private lateinit var psiElementFactory: PsiElementFactory
    private lateinit var mDialog: DesignPatternJFrame

    abstract fun getTargetClass(editor: Editor, file: PsiFile): PsiClass?

    fun performAction(event: AnActionEvent) {
        val project = event.getData(PlatformDataKeys.PROJECT)
        val editor = event.getData(PlatformDataKeys.EDITOR)
        var mFile: PsiFile? = null
        var psiClass: PsiClass? = null
        var directory: PsiDirectory? = null
        if (editor != null) {
            mFile = PsiUtilBase.getPsiFileInEditor(editor, project!!)
            if (mFile != null) {
                psiClass = getTargetClass(editor, mFile)
                directory = mFile.containingDirectory
            }
        } else {
            val dataContext = event.dataContext
            val view = LangDataKeys.IDE_VIEW.getData(dataContext) as IdeView?
            if (view != null) {
                directory = view.orChooseDirectory
            }
        }


        psiElementFactory = JavaPsiFacade.getElementFactory(project!!)
        mDialog = DesignPatternJFrame("DesignPattern", actionType)
        mDialog.updateActionModel(
                ActionModel(project,
                editor,
                mFile,
                psiClass, psiElementFactory,
                PsiFileFactory.getInstance(project),
                PsiDirectoryFactory.getInstance(project),
                directory,
                actionType))
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