package handler

/**
 * @author deadline
 * @time 2018/5/25
 */
abstract class BaseHandler(private val supportPoet: Boolean,
                           private val supportPsi: Boolean) : IDesignPatternHandler {

    override fun supportPoet(): Boolean {
        return supportPoet
    }

    override fun supportPsi(): Boolean {
        return supportPsi
    }
}