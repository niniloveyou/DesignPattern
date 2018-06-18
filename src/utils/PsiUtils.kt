package utils

import com.intellij.psi.PsiDirectory
import com.intellij.psi.impl.file.PsiDirectoryFactory

/**
 * @author deadline
 * @time 2018/6/17
 */
object PsiUtils {

    fun getPackageName(directoryFactory: PsiDirectoryFactory, directory: PsiDirectory?): String = directoryFactory.getQualifiedName(directory!!, false)

    fun getCodePackageName(packageName: String): String = if (packageName.isNotEmpty()) "package $packageName;" else ""
}