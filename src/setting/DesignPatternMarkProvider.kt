package setting

import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerProvider
import com.intellij.psi.PsiElement

/**
 * @author deadline
 * @time 2018/6/22
 */
class DesignPatternMarkProvider : LineMarkerProvider {
    override fun getLineMarkerInfo(p0: PsiElement): LineMarkerInfo<*> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun collectSlowLineMarkers(p0: MutableList<PsiElement>, p1: MutableCollection<LineMarkerInfo<PsiElement>>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
