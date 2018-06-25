package actions

import com.intellij.ide.IdeView
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiFile
import model.ActionType
import com.intellij.openapi.actionSystem.LangDataKeys
import utils.Utils


class DesignPatternNewFileAction : AnAction(Utils.getIcon("design_pattern_file.png")) {

    private var actionHelper: ActionHelper = object : ActionHelper(ActionType.Create) {
        override fun getTargetClass(editor: Editor, file: PsiFile): PsiClass? {
            return null
        }
    }

    override fun actionPerformed(e: AnActionEvent) {
        actionHelper.performAction(e)
    }

    override fun update(event: AnActionEvent) {
        val presentation = event.presentation
        val dataContext = event.dataContext
        val project = CommonDataKeys.PROJECT.getData(dataContext)
        val view = LangDataKeys.IDE_VIEW.getData(dataContext) as IdeView?
        presentation.isEnabled = project != null && view != null && view.orChooseDirectory != null
    }
}
