package setting

import com.intellij.openapi.options.Configurable
import javax.swing.JComponent
import javax.swing.JTextField
import javax.swing.JPanel
import com.intellij.ide.util.PropertiesComponent





/**
 * @author deadline
 * @time 2018/6/22
 */
class DesignPatternSettings : Configurable {

    val PREFIX = "design_pattern_prefix"

    private val mPanel: JPanel? = null
    private val mHolder: JTextField? = null
    private val mPrefix: JTextField? = null

    override fun isModified(): Boolean {
        return true
    }

    override fun getDisplayName(): String {
        return "Design Pattern"
    }

    override fun createComponent(): JComponent? {
        reset()
        return mPanel
    }

    override fun apply() {
        PropertiesComponent.getInstance().setValue(PREFIX, mPrefix?.text)
    }

    override fun reset() {
       // mPrefix.setText(Utils.getPrefix())
       // mHolder.setText(Utils.getViewHolderClassName())
    }

    override fun disposeUIResources() {

    }
}