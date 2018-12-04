package io.github.dector.krokus.core.properties


typealias Producer<T> = () -> T

interface Property<T> {
    var ref: Producer<T>

    fun set(value: T) {
        ref = { value }
    }

    fun set(value: Producer<T>) {
        ref = value
    }

    fun update(modifier: (Producer<T>) -> (Producer<T>)) {
        ref = modifier(ref)
    }

    operator fun invoke(): T = ref()

    companion object {
        fun <T> from(value: Producer<T>): Property<T> = PropertyImpl(value)
        fun <T> from(value: T): Property<T> = from(ScalarProducer(value))
    }
}

class ScalarProducer<T>(internal val value: T) : Producer<T> {

    constructor(producer: Producer<T>) : this(producer())

    override operator fun invoke() = value
}

internal data class PropertyImpl<T>(override var ref: Producer<T>) : Property<T> {

    override fun toString() = ref().toString()
}

internal fun <T> Property<T>.unpackOrSet(producer: () -> T): T {
    val storedRef = ref as? ScalarProducer<T>

    val ref = if (storedRef != null) {
        storedRef
    } else {
        val newRef = ScalarProducer(producer)
        this.ref = newRef
        newRef
    }

    return ref.value
}

internal fun <T> Property<T>.unpackOrThrow(): T {
    val ref = (ref as? ScalarProducer<T>)
        ?: throw IllegalStateException("Non-value reference can't be modified")

    return ref.value
}