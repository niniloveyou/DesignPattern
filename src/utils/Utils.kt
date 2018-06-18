package utils

import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.OpenFileDescriptor
import com.intellij.openapi.fileEditor.ex.FileEditorProviderManager
import com.intellij.openapi.project.Project
import java.io.File
import java.io.IOException




/**
 * @author deadline
 * @time 2018/5/10
 */
object Utils {

    /**
     * 打开类文件
     * @param filePath
     * @param project
     */
    fun openFileInPanel(filePath: String, project: Project) {
        ApplicationManager.getApplication().invokeLater {
            val file = LocalFileSystem.getInstance().refreshAndFindFileByPath(filePath)
            if (file != null && file.isValid) {
                val providers = FileEditorProviderManager.getInstance()
                        .getProviders(project, file)
                if (providers.isNotEmpty()) {
                    val descriptor = OpenFileDescriptor(project, file)
                    FileEditorManager.getInstance(project).openTextEditor(descriptor, false)
                }
            }
        }
    }

    /**
     * 打开文件夹
     */
    fun openDirectory(directory: String) {
        val file = File(directory)
        if (!file.exists() || !file.isDirectory) {
            return
        }
        try {
            java.awt.Desktop.getDesktop().open(file)
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

}