package io.github.dector.krokus.api.geometry

import io.github.dector.krokus.api.transformation.Rotation
import io.github.dector.krokus.api.transformation.Transformation
import io.github.dector.krokus.api.transformation.Transformations
import io.github.dector.krokus.api.transformation.Translation
import io.github.dector.krokus.api.vector.Vector3


//data class Bounds(val from: Vector3 = Vector3(), val to: Vector3 = Vector3())

sealed class Shape
data class Cube3(val size: Vector3) : Shape()
data class Sphere3(val radius: Double) : Shape()
data class Cylinder3(val height: Double, val radius: Pair<Double, Double>) : Shape()

interface Geometry {
    val transformations: Transformations

    fun addTransformation(transformation: Transformation): Geometry
}

data class ShapeGeometry(
    val shape: Shape,
    override val transformations: Transformations = Transformations()
) : Geometry {

    override fun addTransformation(transformation: Transformation) =
        copy(transformations = transformations merge transformation)
}

infix fun Transformations.merge(transformation: Transformation) = when (transformation) {
    is Translation -> copy(translation = transformation)
    is Rotation -> copy(rotation = transformation)
    else -> throw NotImplementedError("Unknown transformation: $transformation")
}