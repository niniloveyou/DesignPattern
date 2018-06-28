package ui.widget

/**
 * N choose type
 * N input dialog return content
 * N checkbox with title return key true/false
 *
 */
class Widget private constructor(builder: Builder) {

    init {
    }

    class Builder {
        var chooses: List<Pair<String, *>>? = null
        var inputs: List<String>? = null
        var checkboxs: List<Pair<String, Boolean>>? = null

        fun setChooses(chooses: List<Pair<String, *>>): Builder {
            this.chooses = chooses
            return this
        }

        fun setInputs(inputs: List<String>?): Builder {
            this.inputs = inputs
            return this
        }

        fun build(): Widget {
            return Widget(this)
        }
    }

    companion object {

        fun newBuilder(): Builder {
            return Builder()
        }
    }
}
