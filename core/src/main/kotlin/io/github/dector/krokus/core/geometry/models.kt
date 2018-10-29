package io.github.dector.krokus.core.geometry

import io.github.dector.krokus.core.space.Vector3
import io.github.dector.krokus.core.transformation.Transformation
import io.github.dector.krokus.core.transformation.Transformations


//data class Bounds(val from: Vector3 = Vector3(), val to: Vector3 = Vector3())

sealed class Shape
data class Cube(val size: Vector3) : Shape()
data class Sphere(val radius: Double) : Shape()
data class Cylinder(val height: Double, val radius: Radius) : Shape() {

    data class Radius(val bottom: Double, val top: Double)
}

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