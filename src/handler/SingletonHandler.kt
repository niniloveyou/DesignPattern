package handler

import code.SingletonType
import model.ActionModel
import model.ActionType
import model.DesignPatternModel
import model.entity.SingletonEntity
import ui.widget.MDialogBuilder
import ui.widget.WidgetItem
import ui.widget.WidgetType
import utils.Utils


/**
 * 单例模式的处理
 * @author deadline
 * @time 2018/5/11
 */
class SingletonHandler : BaseHandler() {

    override fun handle(actionModel: ActionModel, model: DesignPatternModel) {

        var entity: SingletonEntity? = null
        if (actionModel.actionType == ActionType.Update) {
            val mode = Utils.showChooseDialog(arrayOf(SingletonType.Lazy, SingletonType.Hungry)) {it.name}
            entity = SingletonEntity(mode)
        } else {

            val list = listOf(
                    WidgetItem(0, WidgetType.Spinner,
                            listOf(Pair(SingletonType.Lazy.name, SingletonType.Lazy),
                                    Pair(SingletonType.Hungry.name, SingletonType.Hungry)), SingletonType.Lazy),

                    WidgetItem(1, WidgetType.InputText, listOf(Pair("Class Name ", "Example${this.javaClass.name}"))))
            MDialogBuilder(list, model.patternEnum).show()
            entity = SingletonEntity(list[0].result as SingletonType)
            entity.className = list[1].result as String?
        }

        DesignPatternCodeWriter.write(actionModel, model, entity)
    }
}