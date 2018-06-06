import com.intellij.codeInsight.CodeInsightActionHandler
import com.intellij.codeInsight.generation.actions.BaseGenerateAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ModuleRootManager
import com.intellij.psi.*
import com.intellij.psi.impl.file.PsiDirectoryFactory
import com.intellij.psi.util.PsiUtilBase
import mode.ActionModel
import ui.DesignPatternJFrame
import java.awt.Dimension
import javax.swing.WindowConstants


class DesignPatternAction : BaseGenerateAction {

    private lateinit var psiElementFactory: PsiElementFactory
    private lateinit var mDialog: DesignPatternJFrame

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
        mDialog.updateActionModel(ActionModel(project,
                editor, mFile,
                psiClass!!, psiElementFactory,
                PsiFileFactory.getInstance(project),
                PsiDirectoryFactory.getInstance(project),
                getPsiDirectory(project, event)))
        showDesignPatternJFrame(event)

    }

    /**
     * 获取当前操作的文件夹
     */
    private fun getPsiDirectory(project: Project, event: AnActionEvent): PsiDirectory? {
        var directory: PsiDirectory? = null
        val dataContext = event.dataContext
        val module = LangDataKeys.MODULE.getData(dataContext)
        module?.let {
            val navigatable = LangDataKeys.NAVIGATABLE.getData(dataContext)
            directory = if (navigatable is PsiDirectory) {
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
        }

        return directory
    }

    private fun showDesignPatternJFrame(e: AnActionEvent) {
        mDialog.defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
        mDialog.preferredSize = Dimension(600, 600)
        mDialog.pack()
        mDialog.setLocationRelativeTo(null)
        mDialog.isVisible = true
    }
}
