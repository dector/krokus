package io.github.dector.krokus.core.geometry

import io.github.dector.krokus.core.space.*
import io.github.dector.krokus.core.transformation.Transformation
import io.github.dector.krokus.core.transformation.Transformations
import kotlin.math.absoluteValue
import kotlin.math.max


data class Bounds(val from: Vector3 = Vector3(), val to: Vector3 = Vector3()) {

    fun side(side: Side) = when (side) {
        Side.Front -> ((from - to) / 2).copy(y = from.y)
        else -> throw NotImplementedError()
    }

    fun center() = (to - from) / 2

    fun size() = (to - from).run { v(x.absoluteValue, y.absoluteValue, z.absoluteValue) }
}

interface Geometry {
    val transformations: Transformations

    fun setTransformation(transformation: Transformation): Geometry
//    fun applyTransformation(transformation: Transformation): Geometry

    fun bounds(absolute: Boolean = true): Bounds = throw NotImplementedError()

    val size: Vector3
        get() = bounds().size()
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

    override fun setTransformation(transformation: Transformation) =
        copy(transformations = transformations update transformation)

//    override fun applyTransformation(transformation: Transformation) =
//        copy(transformations = transformations merge transformation)

    override fun bounds(absolute: Boolean): Bounds {
        fun count(absolute: Boolean, halfSize: Vector3) =
            (if (absolute) position else Vector3.Origin).let { origin ->
                Bounds(
                    from = origin - halfSize,
                    to = origin + halfSize
                )
            }

        return when (shape) {
            is Cube -> {
                val halfSize = v(shape.size.x / 2, shape.size.y / 2, shape.size.z / 2)
                count(absolute, halfSize)
            }
            is Cylinder -> {
                val maxRadius = shape.radius.max()
                val halfSize = v(maxRadius, maxRadius, shape.height / 2)
                count(absolute, halfSize)
            }
            else -> throw NotImplementedError()
        }
    }

    private val position = transformations.translation.position
}

sealed class Shape
data class Cube(val size: Vector3) : Shape()
data class Sphere(val radius: Double) : Shape()
data class Cylinder(val height: Double, val radius: Radius) : Shape() { // FIXME make cone

    data class Radius(val bottom: Double, val top: Double) {

        fun max() = max(bottom, top)
    }
}

data class Prism(val height: Double, val radius: Double, val vertices: Int) : Shape()

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
