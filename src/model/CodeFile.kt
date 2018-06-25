package model

import com.squareup.javapoet.JavaFile
import com.squareup.kotlinpoet.FileSpec

/**
 * @author deadline
 * @time 2018/6/14
 */
class CodeFile {

    var codeType: CodeType
    var packageName: String
    var javaFile: JavaFile? = null
    var kotlinFile: FileSpec? = null

    constructor(packageName: String, javaFile: JavaFile) {
        codeType = CodeType.Java
        this.packageName = packageName
        this.javaFile = javaFile
    }

    constructor(packageName: String, kotlinFile: FileSpec) {
        codeType = CodeType.Kotlin
        this.packageName = packageName
        this.kotlinFile = kotlinFile
    }
}