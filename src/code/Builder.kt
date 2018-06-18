package code

import com.intellij.lang.jvm.JvmModifier
import com.intellij.psi.PsiField
import com.intellij.psi.PsiModifier
import com.squareup.javapoet.*
import model.ActionModel
import model.CodeFile
import model.entity.BuilderEntity
import javax.lang.model.element.Modifier

/**
 * @author deadline
 * @time 2018/6/15
 */
class BuilderGenerate(): ICodeGenerate<BuilderEntity> {

    override fun generateCode(entity: BuilderEntity, actionModel: ActionModel) {

        val className = actionModel.psiClass.nameIdentifier!!.text
        // 修改类的修饰符
        // 修改字段的修饰符，筛选修饰符
        // 修改构造函数修饰符
        // 添加get方法添加newBuilder方法
        // 创建内部builder类
        //

        val fields = actionModel.psiClass.allFields
        var targetFields = ArrayList<PsiField>()
        for (item in fields) {
            if (!item.hasModifier(JvmModifier.STATIC)
                    && !(item.hasModifier(JvmModifier.ABSTRACT))){
                //item.modifierList.
                targetFields.add(item)
            }
        }


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

    override fun generateFile(entity: BuilderEntity): List<CodeFile> {
        val builderClassName = "Builder"
        val exampleFieldName = "number"
        val thisType = ClassName.get(entity.packageName, entity.className)
        val builderType = thisType.nestedClass(builderClassName)

        // 示例字段
        val builderExampleFieldSpec = FieldSpec
                .builder(TypeName.INT, exampleFieldName, Modifier.PRIVATE)
                .build()

        // 构造函数
        val builderConstructor = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .build()

        // set method
        val buildFieldSetParameter = ParameterSpec.builder(TypeName.INT, exampleFieldName).build()
        val buildFieldSetMethod = MethodSpec.methodBuilder("setNumber")
                .addModifiers(Modifier.PUBLIC)
                .addParameter(buildFieldSetParameter)
                .addCode("this.$exampleFieldName = $exampleFieldName; \n return this;\n")
                .returns(builderType)
                .build()

        // build 方法
        val builderMethod = MethodSpec.methodBuilder("build")
                .addModifiers(Modifier.PUBLIC)
                .addCode("return new ${entity.className}(this);\n")
                .returns(thisType)
                .build()

        // build class
        val buildClassSpec = TypeSpec.classBuilder(builderClassName)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addField(builderExampleFieldSpec)
                .addMethod(builderConstructor)
                .addMethod(buildFieldSetMethod)
                .addMethod(builderMethod)
                .build()


        val thisFieldSpec = FieldSpec
                .builder(TypeName.INT, exampleFieldName, Modifier.PRIVATE, Modifier.FINAL)
                .build()

        // 构造函数
        val builderParameter = ParameterSpec.builder(builderType, "builder").build()
        val constructMethod = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PRIVATE)
                .addParameter(builderParameter)
                .addCode("this.$exampleFieldName = builder.$exampleFieldName;\n")
                .build()

        // 返回字段信息方法
        val exampleGetMethod = MethodSpec.methodBuilder("getNumber")
                .addModifiers(Modifier.PUBLIC)
                .addCode("return $exampleFieldName;\n")
                .returns(TypeName.INT)
                .build()

        // 创建方法
        val newBuildMethod = MethodSpec.methodBuilder("newBuilder")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addCode("return new Builder();\n")
                .returns(builderType)
                .build()

        // 创建类相关信息
        val classSpec = TypeSpec.classBuilder(entity.className)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addField(thisFieldSpec)
                .addMethod(constructMethod)
                .addMethod(exampleGetMethod)
                .addMethod(newBuildMethod)
                .addType(buildClassSpec)
                .build()

        // 创建java 文件
        val javaFile = JavaFile
                .builder(entity.packageName, classSpec)
                .build()

        return arrayListOf(CodeFile(javaFile, entity.packageName))
    }
}