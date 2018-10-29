package io.github.dector.krokus.geometry

import io.github.dector.krokus.operation.Operation
import io.github.dector.krokus.transformation.Transformation
import io.github.dector.krokus.vector.Vector3
import kotlin.reflect.KClass

sealed class Geometry() {

    private val _transformations =
        mutableMapOf<KClass<out Transformation>, Transformation>()   // TODO make it immutable

    val transformations: List<Transformation>
        get() = _transformations.values.toList()

    fun setTransformation(transformation: Transformation): Transformation? {
        return _transformations.put(transformation::class, transformation)
    }
}

data class Combined(val operation: Operation, val children: List<Geometry>) : Geometry()

data class Contained(val geometry: Geometry) : Geometry()

sealed class Primitive : Geometry()
data class Cube(val size: Vector3, val centered: Boolean = false) : Primitive()
data class Sphere(val r: Float) : Primitive()
data class Cylinder(val h: Float, val bottomR: Float, val topR: Float) : Primitive()