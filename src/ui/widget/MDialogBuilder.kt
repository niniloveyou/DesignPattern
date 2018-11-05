package ui.widget

import com.intellij.openapi.ui.DialogBuilder
import model.DesignPatternEnum
import javax.swing.JPanel
import javax.swing.JSpinner

/**
 * @author deadline
 * @time 2018/6/24
 */
class MDialogBuilder(var list: List<WidgetItem>, patternEnum: DesignPatternEnum) : DialogBuilder() {

    init {
        setCenterPanel(generatePanelByWidget())
        setTitle(patternEnum.title)
    }

    private fun generatePanelByWidget(): JPanel {
        val jPanel: JPanel = JPanel()
        for (widgetItem in list) {
            when (widgetItem.widgetType) {
               // WidgetType.Spinner -> JSpinner()
            }
        }
        return jPanel
    }
}

enum class WidgetType {
    InputText,
    Spinner,
    CheckBox
}

data class WidgetItem (
        /** 唯一Id标识 **/
        val id: Int,
        /** 控件类型 **/
        val widgetType: WidgetType,
        /** 数据源 **/
        var list: List<Pair<String, Any?>>,
        /** 结果 **/
        var result: Any? = null)