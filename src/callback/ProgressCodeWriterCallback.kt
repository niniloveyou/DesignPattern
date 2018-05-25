package callback

import com.sun.deploy.ui.ProgressDialog
import ui.Toast

/**
 * @author deadline
 * @time 2018/5/25
 */
class ProgressCodeWriterCallback() : CodeWriterCallback {

    private lateinit var progressDialog: ProgressDialog

    override fun onStart() {

    }

    override fun onFinish() {

    }

    override fun onSuccess() {

    }

    override fun onFailed(message: String) {
        Toast.make()
    }

    override fun onException(throwable: Throwable) {

    }
}