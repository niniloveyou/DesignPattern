package mode

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiFile

/**
 * 共享数据单元
 * @author deadline
 * @time 2018/5/11
 */
data class ActionModel(val project: Project, val editor: Editor,
                       val psiFile: PsiFile, val psiClass: PsiClass?)