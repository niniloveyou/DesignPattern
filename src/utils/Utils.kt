package utils

import com.intellij.psi.PsiDirectory
import com.intellij.psi.impl.file.PsiDirectoryFactory
import model.DesignPatternEnum

/**
 * @author deadline
 * @time 2018/5/10
 */
object Utils {

    /**
     * 获取设计模式名称
     */
    fun getNameByDesignPattern(pattern: DesignPatternEnum)
            : String = when (pattern) {
        DesignPatternEnum.Singleton -> "单例模式"
        DesignPatternEnum.Bridge -> "桥接模式"
        DesignPatternEnum.Builder -> "建造者模式"
        DesignPatternEnum.Strategy -> "策略模式"
        DesignPatternEnum.Adapter -> "适配器模式"
        DesignPatternEnum.Factory -> "工厂模式"
        DesignPatternEnum.Chain -> "责任链模式"
    }

    fun getPackageName(directoryFactory: PsiDirectoryFactory, directory: PsiDirectory?): String = directoryFactory.getQualifiedName(directory!!, false)

    fun getCodePackageName(packageName: String): String = if (packageName.isNotEmpty()) "package $packageName;" else ""
}