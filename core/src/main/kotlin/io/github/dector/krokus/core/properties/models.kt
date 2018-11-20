package io.github.dector.krokus.core.properties


sealed class Property<T> {
    abstract val value: T
}

data class Scalar<T>(override var value: T) : Property<T>() {

    override fun toString() = value.toString()
}

data class Computable<T>(var provider: () -> T) : Property<T>() {

    override val value
        get() = provider()

    override fun toString() = value.toString()
}