package ui

import handler.DesignPatternHandlerFactory
import mode.ActionModel
import mode.DesignPatternEnum
import mode.DesignPatternModel
import utils.Utils
import java.awt.GridLayout
import java.awt.event.ActionListener
import javax.swing.BorderFactory
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.SwingConstants
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
        supportPatternEnums.add(DesignPatternEnum.Singleton)
        supportPatternEnums.add(DesignPatternEnum.Builder)
        supportPatternEnums.add(DesignPatternEnum.Factory)
        supportPatternEnums.add(DesignPatternEnum.Strategy)
        supportPatternEnums.add(DesignPatternEnum.Adapter)
        supportPatternEnums.add(DesignPatternEnum.Chain)
        supportPatternEnums.add(DesignPatternEnum.Bridge)

        for ((index, item) in supportPatternEnums.withIndex()) {
            val model = DesignPatternModel(item, Utils.getNameByDesignPattern(item), index)
            supportPatternModels.add(model)
            generateGridLayout()
            addItemByDesignPatternModel(model)
        }
    }

    /**
     * 创建GridLayout
     */
    private fun generateGridLayout() {
        gridLayout = GridLayout(supportPatternModels.size, 1)
        layout = gridLayout
    }

    /**
     * 添加button
     */
    private fun addItemByDesignPatternModel(model: DesignPatternModel) {
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