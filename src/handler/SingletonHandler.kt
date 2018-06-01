package handler

import callback.ProgressCodeWriterCallback
import code.DesignPatternCodeGenerateFactory
import code.ICodeGenerate
import code.SingletonHungryGenerate
import handler.BaseHandler
import mode.ActionModel
import mode.CodeType
import mode.DesignPatternModel
import mode.entity.BaseEntity
import mode.entity.SingletonEntity


/**
 * 单例模式的处理
 * @author deadline
 * @time 2018/5/11
 */
class SingletonHandler(private val supportPoet: Boolean,
                       private val supportPsi: Boolean)
                        : BaseHandler(supportPoet, supportPsi) {

    override fun handle(actionModel: ActionModel, model: DesignPatternModel) {
        // ui
        generateJFrame()
        // do set message
        var entity = SingletonEntity()

        DesignPatternCodeWriter.write(actionModel, model, entity, CodeType.Poet,
                DesignPatternCodeGenerateFactory.generateCodeGenerate(model, entity),
                ProgressCodeWriterCallback())
    }

    private fun generateJFrame() {

    }

    private fun onConfirmClick() {


    }
}