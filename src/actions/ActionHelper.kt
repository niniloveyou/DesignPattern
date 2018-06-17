package actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.PsiElementFactory
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.impl.file.PsiDirectoryFactory
import com.intellij.psi.util.PsiUtilBase
import model.ActionModel
import ui.DesignPatternJFrame

/**
 * @author deadline
 * @time 2018/6/15
 */
class ActionHelper {


    private lateinit var psiElementFactory: PsiElementFactory
    private lateinit var mDialog: DesignPatternJFrame

    /*fun performAction(event: AnActionEvent){
        val project = event.getData(PlatformDataKeys.PROJECT)
        val editor = event.getData(PlatformDataKeys.EDITOR)
        val mFile = PsiUtilBase.getPsiFileInEditor(editor!!, project!!)
        val psiClass = getTargetClass(editor, mFile!!)
        psiElementFactory = JavaPsiFacade.getElementFactory(project)
        var directory = mFile.containingDirectory

        mDialog = DesignPatternJFrame("DesignPattern")
        mDialog.updateActionModel(ActionModel(project,
                editor, mFile,
                psiClass!!, psiElementFactory,
                PsiFileFactory.getInstance(project),
                PsiDirectoryFactory.getInstance(project),
                directory))
        showDesignPatternJFrame(event)
    }*/
}