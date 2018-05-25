package handler.singleton

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.command.WriteCommandAction.runWriteCommandAction
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiJavaFile
import com.intellij.psi.codeStyle.CodeStyleManager
import com.intellij.psi.impl.source.PsiJavaFileImpl
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.TypeSpec
import handler.IDesignPatternHandler
import mode.ActionModel
import mode.ClassMessage
import mode.DesignPatternModel
import javax.lang.model.element.Modifier
import com.squareup.javapoet.TypeName
import com.squareup.javapoet.FieldSpec
import handler.BaseHandler
import java.io.File
import java.nio.file.Path
import javax.tools.JavaFileObject


/**
 * 单例模式的处理
 * @author deadline
 * @time 2018/5/11
 */
class SingletonHandler() : BaseHandler() {

    override fun generateModel(): ClassMessage {
        return ClassMessage()
    }

    override fun handle(actionModel: ActionModel, model: DesignPatternModel) {


        val fieldSpec = FieldSpec.builder(TypeName.get(String::class.java),
                "number",
                Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                .initializer("\"" + "number" + "\"")
                .build()
        // 创建类相关信息
        val realm = TypeSpec.classBuilder("SingletonClass")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addJavadoc("\n@author deadline\n@time 2018/5/23\n")
                .addField(fieldSpec)
                .build()

        // 创建java 文件
        val javaFile = JavaFile
                .builder("convert", realm)
                .build()

        val classString = javaFile.toString()

        runWriteCommandAction(actionModel.project,
                Runnable {
                    val psiClass = actionModel.psiElementFactory.createClassFromText(classString, null)
                    val file = File("/Users/duoke/Documents/Calculate/app/src/main/java/deadline/calculate/SingletonClass.java")
                    javaFile.writeTo(file)
                    //val psiField = actionModel.psiElementFactory.createFieldFromText("var number = 0", null)
                    //CodeStyleManager.getInstance(actionModel.project).reformat(psiField)
                    //actionModel.psiClass!!.add(psiField)
                })
    }
}