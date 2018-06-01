package code

import com.squareup.javapoet.JavaFile
import groovy.lang.Tuple2
import mode.entity.SingletonEntity

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
        return emptyList()
    }

    override fun generatePsi(entity: SingletonEntity) {

    }
}


class SingletonLazyGenerate(): ICodeGenerate<SingletonEntity>{

    override fun generatePoet(entity: SingletonEntity): List<Tuple2<String, JavaFile>> {
        return emptyList()
    }

    override fun generatePsi(entity: SingletonEntity) {

    }
}