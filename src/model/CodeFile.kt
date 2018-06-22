package model

import com.squareup.javapoet.JavaFile
import com.squareup.kotlinpoet.FileSpec

/**
 * @author deadline
 * @time 2018/6/14
 */
data class CodeFile(val packageName: String,
                    var javaFile: JavaFile? = null,
                    var kotlinFile: FileSpec? = null,
                    val codeType: CodeType = CodeType.Java)