package io.github.dector.krokus.core.properties

interface Property<T> {
    var ref: () -> T

    fun set(value: T) {
        ref = { value }
    }

    fun set(value: () -> T) {
        ref = value
    }

    companion object {
        fun <T> from(value: () -> T): Property<T> = PropertyImpl(value)
        fun <T> from(value: T): Property<T> = from { value }
    }
}

internal class PropertyImpl<T>(override var ref: () -> T) : Property<T>

data class Node(val name: String) {
    var x: Property<Int> = Property.from(0)

    override fun toString() = "$name.x -> ${x.ref()}"
}

operator fun Property<Int>.times(v: Int) = { ref() * v }

fun main() {
    val cube1 = Node("cube1").apply {
        x.set(1)
    }
    val cube2 = Node("cube2").apply {
        x.ref = cube1.x * 2
    }

    println(cube1)
    println(cube2)

    cube1.x.set(5)

    println(cube1)
    println(cube2)
}