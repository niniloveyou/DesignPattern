import com.intellij.codeInsight.CodeInsightActionHandler
import com.intellij.codeInsight.generation.actions.BaseGenerateAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.*
import com.intellij.psi.util.PsiUtilBase
import mode.ActionModel
import ui.DesignPatternJFrame
import java.awt.Dimension
import javax.swing.WindowConstants

class DesignPatternAction : BaseGenerateAction {

    private lateinit var psiElementFactory: PsiElementFactory
    private lateinit var mDialog : DesignPatternJFrame

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
        mDialog = DesignPatternJFrame("DesignPattern")
        mDialog.updateActionModel(ActionModel(project, editor, mFile,
                psiClass!!, psiElementFactory, PsiFileFactory.getInstance(project)))
        showDesignPatternJFrame(event)

    }

    private fun showDesignPatternJFrame(e: AnActionEvent) {
        mDialog.defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
        mDialog.preferredSize = Dimension(600, 600)
        mDialog.pack()
        mDialog.setLocationRelativeTo(null)
        mDialog.isVisible = true
    }

    val directory: PsiDirectory? =
            if (navigatable is PsiDirectory) {
                navigatable
            } else if (navigatable is PsiFile) {
                navigatable.containingDirectory
            } else {
                val root = ModuleRootManager.getInstance(module)
                var tempDirectory: PsiDirectory? = null
                for (file in root.sourceRoots) {
                    tempDirectory = PsiManager.getInstance(project).findDirectory(file)
                    if (tempDirectory != null) {
                        break
                    }
                }
                tempDirectory
            }
    directory?.let {
        val directoryFactory = PsiDirectoryFactory.getInstance(directory.getProject())
        val packageName = directoryFactory.getQualifiedName(directory, false)
        val psiFileFactory = PsiFileFactory.getInstance(project)
        val packageDeclare = if (packageName.isNotEmpty()) "package $packageName" else ""
}
