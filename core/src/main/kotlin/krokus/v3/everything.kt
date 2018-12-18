package krokus.v3

import java.util.*


typealias Coordinate = Double       // (-∞, +∞)
typealias Angle = Double            // (-∞, +∞)
typealias Distance = Double         // (-∞, +∞)
typealias ColorComponent = Double   // [0, 1]

// Bodies

interface Body : Positionable, Rotatable, Colorable

interface CompositeBody : Body {

    var children: MutableList<Body>
}

interface UnionBody : CompositeBody

interface DifferenceBody : CompositeBody {

    var source: Body
}

interface IntersectionBody : CompositeBody

interface PrimitiveBody : Body

interface Cube : PrimitiveBody {

    var width: Distance
    var depth: Distance
    var height: Distance
}

interface Sphere : PrimitiveBody {

    var radius: Distance
}

interface Cylinder : PrimitiveBody {

    var radius: Distance
    var height: Distance
}

// Properties

interface Positionable {

    var position: Vec3
}

interface Rotatable {

    var rotation: Ang3
}

interface Colorable {

    var color: Color
}

// Math

interface Vec3 {
    var x: Coordinate
    var y: Coordinate
    var z: Coordinate
}

interface Ang3 {
    var x: Angle
    var y: Angle
    var z: Angle
}

interface Color {
    var r: ColorComponent
    var g: ColorComponent
    var b: ColorComponent

    //var a: ColorComponent

    companion object {

        /* Base colors */

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

    override var r: ColorComponent = r
        set(value) {
            throw Error("This color instance is immutable")
        }

    override var g: ColorComponent = g
        set(value) {
            throw Error("This color instance is immutable")
        }

    override var b: ColorComponent = b
        set(value) {
            throw Error("This color instance is immutable")
        }

    fun asMutable() = ColorImpl(r = r, g = g, b = b)

    override fun equals(other: Any?) = (this === other) || equals(other as? Color)
    override fun hashCode() = Objects.hash(r, g, b)

    fun equals(other: Color?) = other != null &&
            ((r == other.r) && (g == other.g) && (b == other.b))
}