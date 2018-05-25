package ui

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.MessageType
import com.intellij.openapi.ui.popup.Balloon
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.openapi.wm.WindowManager
import com.intellij.ui.awt.RelativePoint
import javax.swing.JComponent

/**
 * Created by dim on 15/8/24.
 */
object Toast {

    /**
     * Display simple notification of given type
     *
     * @param project
     * @param type
     * @param text
     */
    fun make(project: Project, jComponent: JComponent, type: MessageType, text: String) {

        val statusBar = WindowManager.getInstance().getStatusBar(project)

        JBPopupFactory.getInstance()
                .createHtmlTextBalloonBuilder(text, type, null)
                .setFadeoutTime(7500)
                .createBalloon()
                .show(RelativePoint.getCenterOf(jComponent), Balloon.Position.above)
    }

    /**
     * Display simple notification of given type
     *
     * @param project
     * @param type
     * @param text
     */
    fun make(project: Project, type: MessageType, text: String) {

        val statusBar = WindowManager.getInstance().getStatusBar(project)

        JBPopupFactory.getInstance()
                .createHtmlTextBalloonBuilder(text, type, null)
                .setFadeoutTime(7500)
                .createBalloon()
                .show(RelativePoint.getCenterOf(statusBar.component), Balloon.Position.atRight)
    }
}
