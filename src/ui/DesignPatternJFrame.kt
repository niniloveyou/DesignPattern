package ui

import handler.DesignPatternHandlerFactory
import model.ActionModel
import model.DesignPatternEnum
import model.DesignPatternModel
import utils.Utils
import java.awt.Color
import java.awt.GridLayout
import java.awt.event.ActionListener
import javax.swing.*
import javax.swing.plaf.metal.MetalButtonUI

/**
 * @author deadline
 * 设计模式选择页面
 */
class DesignPatternJFrame(title: String?) : JFrame(title) {

    private val supportPatternEnums = LinkedHashSet<DesignPatternEnum>()
    private val supportPatternModels = ArrayList<DesignPatternModel>()
    private var gridLayout: GridLayout? = null
    private lateinit var actionModel: ActionModel

    init {
        supportPatternEnums.addAll(DesignPatternEnum.values())
        for ((index, item) in supportPatternEnums.withIndex()) {
            val model = DesignPatternModel(item, item.title, index)
            supportPatternModels.add(model)
            generateGridLayout()
            addItemByDesignPatternModel(model)
        }
    }

    /**
     * 创建GridLayout
     */
    private fun generateGridLayout() {
        gridLayout = GridLayout(supportPatternModels.size / 2, 2)
        layout = gridLayout
    }

    /**
     * 添加button
     */
    private fun addItemByDesignPatternModel(model: DesignPatternModel) {
        UIManager.put("Button.select", Color.gray)
        val button = JButton(model.title)
        button.ui = MetalButtonUI()
        button.border = BorderFactory.createEmptyBorder()
        button.verticalTextPosition = SwingConstants.CENTER
        button.horizontalTextPosition = SwingConstants.CENTER
        button.addActionListener(ActionListener { dealPatternClick(model) })
        contentPane.add(button)
    }

    /**
     * 处理点击事件
     */
    private fun dealPatternClick(model: DesignPatternModel) {
        closeJFrame()
        val handler = DesignPatternHandlerFactory.generateHandler(model)
        handler.handle(actionModel, model)
    }

    fun updateActionModel(actionModel: ActionModel) {
        this.actionModel = actionModel
    }

    private fun closeJFrame() {
        isVisible = false
        dispose()
    }
}