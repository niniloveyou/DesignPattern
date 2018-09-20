package code

import com.squareup.javapoet.*
import model.entity.SingletonEntity
import utils.Space
import javax.lang.model.element.Modifier
import model.ActionModel
import com.intellij.psi.PsiModifier
import com.squareup.kotlinpoet.FileSpec
import model.CodeFile
import com.squareup.kotlinpoet.TypeSpec as KotlinTypeSpec
/**
 * @author deadline
 * @time 2018/6/1
 */
enum class SingletonType(name: String) {
    Hungry("饿汉模式"),
    Lazy("懒汉模式")
}


class SingletonHungryGenerate : BaseCodeGenerate<SingletonEntity>() {

    override fun generateKotlinFile(entity: SingletonEntity): List<CodeFile> {
        return getKotlinFile(entity)
    }

    override fun generateJavaFile(entity: SingletonEntity): List<CodeFile> {
        val thisType = ClassName.get(entity.packageName, entity.className)

        val fieldSpec = FieldSpec.builder(thisType, "instance", Modifier.PRIVATE, Modifier.STATIC)
                .initializer("new" + Space + entity.className + "()")
                .build()

        // 构造函数
        val constructMethod = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PRIVATE)
                .build()

        // 创建方法
        val methodSpec = MethodSpec.methodBuilder("getInstance")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addCode("return instance;\n")
                .returns(thisType)
                .build()

        // 创建类相关信息
        val classSpec = TypeSpec.classBuilder(entity.className)
                .addModifiers(Modifier.PUBLIC)
                .addField(fieldSpec)
                .addMethod(constructMethod)
                .addMethod(methodSpec)
                .build()

        // 创建java 文件
        val javaFile = JavaFile
                .builder(entity.packageName, classSpec)
                .build()

        return arrayListOf(CodeFile(entity.packageName, javaFile))
    }

    override fun generateJavaCode(entity: SingletonEntity, actionModel: ActionModel) {
        val className = actionModel.psiClass?.nameIdentifier!!.text

        val instanceField = "private static $className instance = new $className();"
        val field = actionModel.psiElementFactory.createFieldFromText(instanceField, actionModel.psiClass)
        actionModel.psiClass.add(field)

        val constructor = actionModel.psiElementFactory.createConstructor()
        constructor.modifierList.setModifierProperty(PsiModifier.PRIVATE, true)
        actionModel.psiClass.add(constructor)

        val methodText = "public static " + className + " getInstance() {\n" +
                "        return instance ;\n"+
                "    }"

        val psiMethod = actionModel.psiElementFactory.createMethodFromText(methodText, actionModel.psiClass)
        actionModel.psiClass.add(psiMethod)
    }
}

class SingletonLazyGenerate : BaseCodeGenerate<SingletonEntity>() {

    override fun generateKotlinFile(entity: SingletonEntity): List<CodeFile> {
        return getKotlinFile(entity)
    }

    override fun generateJavaFile(entity: SingletonEntity): List<CodeFile> {
        val thisType = ClassName.get(entity.packageName, entity.className)

        val fieldSpec = FieldSpec.builder(thisType, "instance", Modifier.PRIVATE, Modifier.STATIC, Modifier.VOLATILE)
                .initializer("null")
                .build()

        // 构造函数
        val constructMethod = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PRIVATE)
                .build()

        val code = "if (instance == null) {\n" +
                "      synchronized (${entity.className}.class) {\n" +
                "          if (instance == null) {\n" +
                "             instance = new ${entity.className}();\n" +
                "          }\n" +
                "      }\n" +
                "  }\n" +
                "\n" +
                "return instance; \n"
        // 创建方法
        val methodSpec = MethodSpec.methodBuilder("getInstance")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addCode(code)
                .returns(thisType)
                .build()

        // 创建类相关信息
        val classSpec = TypeSpec.classBuilder(entity.className)
                .addModifiers(Modifier.PUBLIC)
                .addField(fieldSpec)
                .addMethod(constructMethod)
                .addMethod(methodSpec)
                .build()

        // 创建java 文件
        val javaFile = JavaFile
                .builder(entity.packageName, classSpec)
                .build()

        return arrayListOf(CodeFile(entity.packageName, javaFile))
    }

    override fun generateJavaCode(entity: SingletonEntity, actionModel: ActionModel) {
        val className = actionModel.psiClass?.nameIdentifier!!.text

        val instanceField = "private static volatile $className instance = null;"
        val field = actionModel.psiElementFactory.createFieldFromText(instanceField, actionModel.psiClass)
        actionModel.psiClass.add(field)

        val constructor = actionModel.psiElementFactory.createConstructor()
        constructor.modifierList.setModifierProperty(PsiModifier.PRIVATE, true)
        actionModel.psiClass.add(constructor)

        val methodText = "public static $className getInstance() {\n" +
                "\n" +
                "        if (instance == null) {\n" +
                "            synchronized ($className.class) {\n" +
                "                if (instance == null) {\n" +
                "                    instance = new $className();\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        return instance;\n" +
                "    }"

        val psiMethod = actionModel.psiElementFactory.createMethodFromText(methodText, actionModel.psiClass)
        actionModel.psiClass.add(psiMethod)
    }
}

private fun getKotlinFile(entity: SingletonEntity): List<CodeFile> {
    val classSpec = KotlinTypeSpec.objectBuilder(entity.className!!).build()
    return arrayListOf(CodeFile(entity.packageName, FileSpec.get(entity.packageName, classSpec)))
}