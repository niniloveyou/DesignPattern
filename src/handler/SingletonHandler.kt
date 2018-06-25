package handler

import code.SingletonType
import com.intellij.openapi.ui.DialogBuilder
import model.ActionModel
import model.ActionType
import model.DesignPatternModel
import model.entity.SingletonEntity
import ui.DesignPatternJFrame
import utils.Utils
import javax.swing.JButton
import javax.swing.JPanel


/**
 * 单例模式的处理
 * @author deadline
 * @time 2018/5/11
 */
class SingletonHandler : BaseHandler() {

    override fun handle(actionModel: ActionModel, model: DesignPatternModel) {

        var entity: SingletonEntity? = null
        if (actionModel.actionType == ActionType.Update) {
            val mode = Utils.showChooseDialog(arrayOf(SingletonType.Lazy, SingletonType.Hungry))
            entity = SingletonEntity(mode)
        } else {
            //val mode = Utils.showChooseDialog(arrayOf(SingletonType.Lazy, SingletonType.Hungry))
            entity = SingletonEntity(SingletonType.Lazy)
            var builder = DialogBuilder()
            var panel = JPanel()
            panel.add(JButton("fdsfd"))
            builder.setCenterPanel(panel)
            builder.show()
        }

        DesignPatternCodeWriter.write(actionModel, model, entity)
    }
}