package io.github.dector.krokus.core.operation

import io.github.dector.krokus.core.geometry.Geometry


fun union(initializer: UnionBuilder.() -> Unit): Union = UnionBuilder().run {
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

