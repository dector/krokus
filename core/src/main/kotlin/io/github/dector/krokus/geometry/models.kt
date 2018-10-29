package io.github.dector.krokus.geometry

import io.github.dector.krokus.operation.Operation
import io.github.dector.krokus.transformation.Transformation
import io.github.dector.krokus.vector.Vector3

sealed class Geometry(val transformations: List<Transformation>) {

    abstract fun copyWithTransformation(transformation: Transformation): Geometry
}

data class Combined(
    val children: List<Geometry> = emptyList(),
    val operation: Operation = Operation.Union,
    private var _transformations: List<Transformation> = emptyList()
) : Geometry(_transformations) {

    override fun copyWithTransformation(transformation: Transformation): Combined =
        copy(_transformations = _transformations + transformation)
}

data class Contained(val geometry: Geometry, private var _transformations: List<Transformation> = emptyList()) :
    Geometry(_transformations) {

    override fun copyWithTransformation(transformation: Transformation): Contained =
        copy(_transformations = _transformations + transformation)
}

sealed class Primitive(transformations: List<Transformation>) : Geometry(transformations)

data class Cube(
    val size: Vector3,
    val centered: Boolean = false,
    private var _transformations: List<Transformation> = emptyList()
) : Primitive(_transformations) {

    override fun copyWithTransformation(transformation: Transformation): Cube =
        copy(_transformations = _transformations + transformation)
}

data class Sphere(val r: Float, private var _transformations: List<Transformation> = emptyList()) :
    Primitive(_transformations) {

    override fun copyWithTransformation(transformation: Transformation): Sphere =
        copy(_transformations = _transformations + transformation)
}

data class Cylinder(
    val h: Float,
    val bottomR: Float,
    val topR: Float,
    private var _transformations: List<Transformation> = emptyList()
) : Primitive(_transformations) {

    override fun copyWithTransformation(transformation: Transformation): Cylinder =
        copy(_transformations = _transformations + transformation)
}