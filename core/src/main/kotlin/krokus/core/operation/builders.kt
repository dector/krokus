package krokus.core.operation

import krokus.core.geometry.Geometry


fun union(initializer: UnionBuilder.() -> Unit): Union = UnionBuilder().run {
    initializer()
    invoke()
}

fun difference(source: Geometry, initializer: DifferenceBuilder.() -> Unit): Difference = DifferenceBuilder().run {
    this.source = source
    initializer()
    invoke()
}

fun difference(initializer: DifferenceBuilder.() -> Unit): Difference = DifferenceBuilder().run {
    initializer()
    invoke()
}

class UnionBuilder {

    private val children = mutableListOf<Geometry>()

    operator fun invoke() = Union { children }

    fun <T : Geometry> T.appendHere(): T = also {
        children += this
    }

    operator fun Geometry.unaryPlus() = appendHere()
}

class DifferenceBuilder {

    lateinit var source: Geometry

    private val children = mutableListOf<Geometry>()

    operator fun invoke() = Difference({ source }) { children }

    fun <T : Geometry> T.appendHere(): T = also {
        children += this
    }

    operator fun Geometry.unaryPlus() = appendHere()
}