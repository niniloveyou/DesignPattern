package handler

/**
 * @author deadline
 * @time 2018/5/25
 */
abstract class BaseHandler(private val supportCreate: Boolean,
                           private val supportUpdate: Boolean) : IDesignPatternHandler {

    override fun supportCreate(): Boolean {
        return supportCreate
    }

    override fun supportUpdate(): Boolean {
        return supportUpdate
    }
}