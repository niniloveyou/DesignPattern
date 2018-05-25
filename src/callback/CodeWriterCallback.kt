package callback

/**
 * @author deadline
 * @time 2018/5/25
 */
interface CodeWriterCallback {

    /** 开始 **/
    fun onStart()

    /** 成功 **/
    fun onSuccess()

    /** 失败 **/
    fun onFailed(message: String)

    /** 异常 **/
    fun onException(throwable: Throwable)

    /** 结束 **/
    fun onFinish()
}