package ui

import handler.HandlerFactory
import model.ActionModel
import model.ActionType
import model.DesignPatternEnum
import model.DesignPatternModel
import java.awt.Color
import java.awt.GridLayout
import java.awt.event.ActionListener
import javax.swing.*
import javax.swing.plaf.metal.MetalButtonUI

/**
 * @author deadline
 * 设计模式选择页面
 */
class DesignPatternJFrame(title: String?, actionType: ActionType) : JFrame(title) {

    private val supportPatternModels = ArrayList<DesignPatternModel>()
    private var gridLayout: GridLayout? = null
    private lateinit var actionModel: ActionModel

    init {
        for ((index, item) in DesignPatternEnum.values().withIndex()) {
            if (actionType == ActionType.Update && item.supportUpdate
                    || actionType == ActionType.Create && item.supportCreate) {
                val model = DesignPatternModel(item, item.title, index)
                supportPatternModels.add(model)
                addItemByDesignPatternModel(model)
            }
        }
        generateGridLayout()

    }

    /**
     * 创建GridLayout
     */
    private fun generateGridLayout() {
        gridLayout = GridLayout(supportPatternModels.size / 3, 3)
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
        val handler = HandlerFactory.generateHandler(model)
        handler.handle(actionModel, model)
    }

    /**
     * 更新数据源
     */
    fun updateActionModel(actionModel: ActionModel) {
        this.actionModel = actionModel
    }

    /**
     * 关闭
     */
    private fun closeJFrame() {
        isVisible = false
        dispose()
    }
}