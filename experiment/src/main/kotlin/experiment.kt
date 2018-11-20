import io.github.dector.krokus.openscad.OpenScadBuilder
import io.github.dector.krokus.openscad.OpenScadGeometryConverter

sealed class Property<T> {
    data class Scalar<T>(override var value: T) : Property<T>() {

        override fun toString() = value.toString()
    }

    data class Computable<T>(var provider: () -> T) : Property<T>() {

        override val value: T
            get() = provider()

        override fun toString() = value.toString()
    }

    abstract val value: T
}

//fun <T> Property<T>.div(v: Int): Property.Computable<T> = Property.Computable<T> { value / v }
operator fun Property<Int>.div(v: Int) = Property.Computable { value / v }

data class Vector3(
    var x: Property<Double> = Property.Scalar(0.0),
    var y: Property<Double> = Property.Scalar(0.0),
    var z: Property<Double> = Property.Scalar(0.0)
) {

    companion object {

        val Origin = Vector3()
    }
}

/** */
fun main() {
    val postament = cube(xy = 20, z = 10)
    val statue = sphere(10)
    val g = postament + statue

    println(convert(g))

    postament.z = statue.r / 2

    println(convert(g))
}

// -----

interface Entity
data class Cube(var x: Property<Int>, var y: Property<Int>, var z: Property<Int>) : Entity
data class Sphere(var r: Property<Int>) : Entity
data class Union(var entries: MutableList<Entity>) : Entity

fun cube(xy: Int, z: Int) = Cube(x = Property.Scalar(xy), y = Property.Scalar(xy), z = Property.Scalar(z))
fun sphere(r: Int) = Sphere(Property.Scalar(r))

operator fun Entity.plus(other: Entity) = Union(entries = mutableListOf(this, other))

fun convert(e: Entity, level: Int = 0): String = buildString {
    when (e) {
        is Cube -> {
            append(indent(level))
            appendln("cube([${e.x}, ${e.y}, ${e.z}], center = true);")
        }
        is Sphere -> {
            append(indent(level))
            appendln("sphere(r = ${e.r});")
        }
        is Union -> {
            append(indent(level))
            appendln("union() {")

            e.entries.map { convert(it, level + 1) }
                .forEach { append(it) }

            append(indent(level))
            appendln("}")
        }
    }
}

fun indent(level: Int) = buildString {
    (1..level).forEach { append("  ") }
}