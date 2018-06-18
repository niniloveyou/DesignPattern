package handler

/**
 * @author deadline
 * @time 2018/5/25
 */
abstract class BaseHandler(private val supportCreate: Boolean = true,
                           private val supportUpdate: Boolean = true) : IDesignPatternHandler {

    override fun supportCreate(): Boolean {
        return supportCreate
    }

    override fun supportUpdate(): Boolean {
        return supportUpdate
    }
}