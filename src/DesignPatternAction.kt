import com.intellij.codeInsight.CodeInsightActionHandler
import com.intellij.codeInsight.generation.actions.BaseGenerateAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElementFactory
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiUtilBase
import mode.ActionModel
import ui.DesignPatternJFrame
import java.awt.Dimension
import javax.swing.WindowConstants
import javax.tools.JavaFileObject

class DesignPatternAction : BaseGenerateAction {

    private var psiElementFactory: PsiElementFactory? = null
    private val mDialog = DesignPatternJFrame("DesignPattern")

    constructor() : super(null)

    constructor(handler: CodeInsightActionHandler) : super(handler)

    override fun isValidForClass(targetClass: PsiClass?): Boolean {
        return super.isValidForClass(targetClass)
    }

    override fun isValidForFile(project: Project, editor: Editor, file: PsiFile): Boolean {
         return true
        // return super.isValidForFile(project, editor, file)
    }

    override fun actionPerformed(event: AnActionEvent) {

        val project = event.getData(PlatformDataKeys.PROJECT)
        val editor = event.getData(PlatformDataKeys.EDITOR)
        val mFile = PsiUtilBase.getPsiFileInEditor(editor!!, project!!)
        val psiClass = getTargetClass(editor, mFile!!)
        psiElementFactory = JavaPsiFacade.getElementFactory(project)
        //psiElementFactory.createClassFromText()
        mDialog.updateActionModel(ActionModel(project, editor, mFile, psiClass))
        showDesignPatternJFrame(event)
    }

    private fun showDesignPatternJFrame(e: AnActionEvent) {
        mDialog.defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
        mDialog.preferredSize = Dimension(600, 600)
        mDialog.pack()
        mDialog.setLocationRelativeTo(null)
        mDialog.isVisible = true
    }

    private fun closeJFrame() {
        mDialog.isVisible = false
        mDialog.dispose()

    }
}
