package code

import com.squareup.javapoet.*
import groovy.lang.Tuple2
import mode.entity.SingletonEntity
import utils.Space
import javax.lang.model.element.Modifier
import mode.ActionModel
import com.intellij.psi.PsiModifier


/**
 * @author deadline
 * @time 2018/6/1
 */
enum class SingletonType {
    Hungry,
    Lazy
}

class SingletonHungryGenerate(): ICodeGenerate<SingletonEntity>{

    override fun generatePoet(entity: SingletonEntity): List<Tuple2<String, JavaFile>> {
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

        return arrayListOf(Tuple2(entity.filePath, javaFile))
    }

    override fun generatePsi(entity: SingletonEntity, actionModel: ActionModel) {
        val className = actionModel.psiClass.nameIdentifier!!.text

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

class SingletonLazyGenerate(): ICodeGenerate<SingletonEntity>{

    override fun generatePoet(entity: SingletonEntity): List<Tuple2<String, JavaFile>> {
        return emptyList()
    }

    override fun generatePsi(entity: SingletonEntity, actionModel: ActionModel) {

    }
}