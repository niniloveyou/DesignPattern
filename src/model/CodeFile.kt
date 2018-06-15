package model

import com.squareup.javapoet.JavaFile
import model.CodeType

/**
 * @author deadline
 * @time 2018/6/14
 */
data class CodeFile(val file: JavaFile,
                    val packageName: String,
                    val codeType: CodeType = CodeType.Java) {

}