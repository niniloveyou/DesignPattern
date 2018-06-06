package mode

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.*
import com.intellij.psi.impl.file.PsiDirectoryFactory

/**
 * 共享数据单元
 * @author deadline
 * @time 2018/5/11
 */
data class ActionModel(
                       // current project
                       val project: Project,

                       val editor: Editor,

                       // current operate file
                       val psiFile: PsiFile,

                       // current psi class file
                       val psiClass: PsiClass,

                       // element factory modify current class
                       val psiElementFactory: PsiElementFactory,

                       // used to create new file(java/kotlin)
                       val psiFileFactory: PsiFileFactory,

                       // create directory
                       val psiDirectoryFactory: PsiDirectoryFactory,

                       // use to add psiFile to directory
                       var psiDirectory: PsiDirectory?)