package krokus.v3

import java.util.*


typealias Coordinate = Double       // (-∞, +∞)
typealias Angle = Double            // (-∞, +∞)
typealias Distance = Double         // (-∞, +∞)
typealias ColorComponent = Double   // [0, 1]

// Bodies

interface Body : Positionable, Rotatable, Colorable

interface CompositeBody : Body {

    val children: List<Body>
}

interface UnionBody : CompositeBody

interface DifferenceBody : CompositeBody {

    val source: Body
}

interface IntersectionBody : CompositeBody

interface PrimitiveBody : Body

interface Cube : PrimitiveBody {

    val size: Dimensions

    val width: Distance
        get() = size.x
    /*set(value) {
        size.x = value
    }*/

    val depth: Distance
        get() = size.y
    /*set(value) {
        size.y = value
    }*/

    val height: Distance
        get() = size.z
    /*set(value) {
        size.z = value
    }*/

    val origin: Origin

    enum class Origin {
        Center, Corner
    }
}

interface Sphere : PrimitiveBody {

    val radius: Distance
}

interface Cylinder : PrimitiveBody {

    val radius: Distance
    val height: Distance

    val origin: Origin

    enum class Origin {
        Center, Bottom
    }
}

// Properties

interface Positionable {

    val position: Vec3
}

interface Rotatable {

    val rotation: Ang3
}

interface Colorable {

    val color: Color
}

// Math

interface Vec3 {
    val x: Coordinate
    val y: Coordinate
    val z: Coordinate
}

interface Ang3 {
    val x: Angle
    val y: Angle
    val z: Angle
}

interface Dimensions {
    val x: Distance
    val y: Distance
    val z: Distance
}

interface Color {
    val r: ColorComponent
    val g: ColorComponent
    val b: ColorComponent

    //val a: ColorComponent

    companion object {

        /* Base colors */

        val Undefined = object : Color {
            override val r: ColorComponent
                get() = Double.NaN
                /*set(value) {}*/
            override val g: ColorComponent
                get() = Double.NaN
                /*set(value) {}*/
            override val b: ColorComponent
                get() = Double.NaN
                /*set(value) {}*/
        }

        val White = ImmutableColor(r = 1.0, g = 1.0, b = 1.0)
        val Black = ImmutableColor(r = 0.0, g = 0.0, b = 0.0)

        val Red = ImmutableColor(r = 1.0)
        val Green = ImmutableColor(g = 1.0)
        val Blue = ImmutableColor(b = 1.0)
    }
}

class ImmutableColor(
    r: ColorComponent = 0.0,
    g: ColorComponent = 0.0,
    b: ColorComponent = 0.0
) : Color {

    override val r: ColorComponent = r
    /*set(value) {
        throw Error("This color instance is immutable")
    }*/

    override val g: ColorComponent = g
    /*set(value) {
        throw Error("This color instance is immutable")
    }*/

    override val b: ColorComponent = b
    /*set(value) {
        throw Error("This color instance is immutable")
    }*/

    fun asMutable() = MutableColor(r = r, g = g, b = b)

    override fun equals(other: Any?) = (this === other) || equals(other as? Color)
    override fun hashCode() = Objects.hash(r, g, b)

    fun equals(other: Color?) = other != null &&
            ((r == other.r) && (g == other.g) && (b == other.b))
}