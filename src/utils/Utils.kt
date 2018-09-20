package utils

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.OpenFileDescriptor
import com.intellij.openapi.fileEditor.ex.FileEditorProviderManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.util.IconLoader
import model.CodeType
import java.io.File
import java.io.IOException
import javax.swing.Icon


/**
 * @author deadline
 * @time 2018/5/10
 */
object Utils {

    fun <T> showChooseDialog(sourceArray: Array<Pair<String, T>>, block: (T) -> String): T {
        checkNotNull(sourceArray)
        val array: Array<String?> = arrayOfNulls<String>(sourceArray.size)
        for ((index, item) in sourceArray.withIndex()) {
            array[index] = item.first
        }
        val position = Messages.showChooseDialog("choose Type", "choose Type", array, array[0], null)
        return sourceArray[position].second
    }

    fun <T> showChooseDialog(sourceArray: Array<T>, block: (T) -> String): T {
        checkNotNull(sourceArray)
        val array: Array<String?> = arrayOfNulls<String>(sourceArray.size)
        for ((index, item) in sourceArray.withIndex()) {
            array[index] = block(item)
        }
        val position = Messages.showChooseDialog("choose Type", "choose Type", array, array[0], null)
        return sourceArray[position]
    }

    /**
     * 获取输入框dialog
     */
    fun showInputDialog() = Messages.showInputDialog(null, "Input class name", "Input class name", null)

    /**
     * 根据用户设置获取默认的语言
     */
    fun getDefaultCodeType(): CodeType {
        val value = PropertiesComponent.getInstance().getInt(Default_Language_Key, Default_Language_Java)
        return if (value == Default_Language_Java) CodeType.Java else CodeType.Kotlin
    }

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

    /**
     * 获取图片
     */
    fun getIcon(name: String): Icon = IconLoader.getIcon("/icons/$name")
}