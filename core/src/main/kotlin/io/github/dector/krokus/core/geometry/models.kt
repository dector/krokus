package io.github.dector.krokus.core.geometry

import io.github.dector.krokus.core.space.Vector3
import io.github.dector.krokus.core.transformation.Transformation
import io.github.dector.krokus.core.transformation.Transformations
import kotlin.math.max


//data class Bounds(val from: Vector3 = Vector3(), val to: Vector3 = Vector3())

interface Geometry {
    val transformations: Transformations

    fun addTransformation(transformation: Transformation): Geometry
}

data class ShapeGeometry<T : Shape>(
    val shape: T,
    override val transformations: Transformations = Transformations()   // FIXME actually there are values (position, rotation), not transformations in this semantic
) : Geometry {

    /*override val top: Vector3
        get() = when (shape) {
            is Cube -> origin + shape.size / 2
            is Sphere -> origin + vz(shape.radius)
            is Cylinder -> origin + vz(shape.height / 2)
            else -> throw NotImplementedError("$shape")
        }

    override val bottom: Vector3
        get() = when (shape) {
//            is Cube -> origin + shape.size / 2
//            is Sphere -> origin + vz(shape.radius)
            is Cylinder -> origin - vz(shape.height / 2)
            else -> throw NotImplementedError("$shape")
        }*/

    override fun addTransformation(transformation: Transformation) =
        copy(transformations = transformations merge transformation)
}

sealed class Shape
data class Cube(val size: Vector3) : Shape()
data class Sphere(val radius: Double) : Shape()
data class Cylinder(val height: Double, val radius: Radius) : Shape() { // FIXME make cone

    data class Radius(val bottom: Double, val top: Double) {

        fun max() = max(bottom, top)
    }
}
data class Prism(val height: Double, val radius: Double, val sides: Int = 3) : Shape()

/*fun countDefaultOrigin(shape: Shape) = when (shape) {
    is Cube -> shape.size / 2
    is Sphere -> v(shape.radius)
    is Cylinder -> v(shape.radius.max()).copy(z = shape.height / 2)
    else -> throw NotImplementedError("$shape")
}*/

/*
interface Bounds {

    val top: Vector3
}*/
