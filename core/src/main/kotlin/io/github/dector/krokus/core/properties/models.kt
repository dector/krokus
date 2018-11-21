package io.github.dector.krokus.core.properties

interface Property<T> {
    var ref: () -> T

    fun set(value: T) {
        ref = { value }
    }

    fun set(value: () -> T) {
        ref = value
    }

    fun update(modifier: (() -> T) -> (() -> T)) {
        ref = modifier(ref)
    }

    operator fun invoke(): T = ref()

    companion object {
        fun <T> from(value: () -> T): Property<T> = PropertyImpl(value)
        fun <T> from(value: T): Property<T> = from { value }
    }
}

internal data class PropertyImpl<T>(override var ref: () -> T) : Property<T> {

    override fun toString() = ref().toString()
}