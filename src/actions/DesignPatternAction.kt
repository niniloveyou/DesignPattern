package actions

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
import model.ActionModel
import model.ActionType
import ui.DesignPatternJFrame
import java.awt.Dimension
import javax.swing.WindowConstants

/**
 * shortcut action
 */
class DesignPatternAction(handler: CodeInsightActionHandler? = null) : BaseGenerateAction(handler) {

    private var actionHelper: ActionHelper = object : ActionHelper(ActionType.Update) {
        override fun getTargetClass(editor: Editor, file: PsiFile): PsiClass? {
            return this@DesignPatternAction.getTargetClass(editor, file)
        }
    }

    override fun actionPerformed(event: AnActionEvent) {
       actionHelper.performAction(event)
    }
}
