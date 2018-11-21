package io.github.dector.krokus.core.properties


interface Property<T> {
    fun get(): () -> T
    fun set(v: () -> T)

    fun resolve(): T
}

//abstract class BaseProperty<T> : Property<T> {
//
//    override var valueProvider: () -> T
//        get() = _valueProvider
//        set(value) { _valueProvider = value }
//}
//
//data class Scalar<T>(val value: T) : Property<T> {
//
//    private var _valueProvider: () -> T = { value }
//    override var valueProvider: () -> T
//        get() = _valueProvider
//        set(value) { _valueProvider = value }
//
//    override fun toString() = value.toString()
//}
//
//data class Computable<T>(private var provider: () -> T) : Property<T>() {
//
//
//    override var value
//        get() = provider()
//        set(value) =
//
//            override
//
//    fun toString() = value.toString()
//}

/*data class CubeNode(
    val size: Property<Int>
)

data class MapNode<T>(
    val str: Property<T>,
    val map: Property<T>
)

class BaseProperty<T>(private var provider: () -> T) : Property<T> {

    private var _provider: () ->

    override fun get(): Property<T> {
    }

    override fun set(another: Property<T>) {
    }

    override fun resolveValue() = value
}

class ScalarProperty<T>(value: T) : BaseProperty<T>({ value }) {


}*/

class PropertyImpl<T>(defaultValue: T) : Property<T> {

    private var _value: () -> T = { defaultValue }

    override fun get(): () -> T {
        return _value
    }

    override fun set(v: () -> T) {
        _value = v
    }

    override fun resolve() = _value()
}

data class Node(val name: String, val x: Property<Int> = PropertyImpl(0)) {

    override fun toString() = "$name.x -> ${x.resolve()}"
}

operator fun Property<Int>.times(v: Int) = { get()() * v }

fun main() {
    val cube1 = Node("cube1")
    val cube2 = Node("cube2").apply {
        x.set(cube1.x * 2)
    }

    println(cube1)
    println(cube2)

    cube1.x.set { 5 }

    println(cube1)
    println(cube2)
}