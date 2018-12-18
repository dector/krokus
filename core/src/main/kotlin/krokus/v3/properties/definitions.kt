package krokus.v3.properties

typealias Coordinate = Double       // (-∞, +∞)
typealias Angle = Double            // (-∞, +∞)
typealias Distance = Double         // (-∞, +∞)
typealias ColorComponent = Double   // [0, 1]

interface Positionable {

    val position: Vec3
}

interface Rotatable {

    val rotation: Ang3
}

interface Colorable {

    val color: Color
}

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