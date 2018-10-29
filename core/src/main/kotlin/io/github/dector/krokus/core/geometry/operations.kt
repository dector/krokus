package io.github.dector.krokus.core.geometry

import io.github.dector.krokus.core.operation.Difference
import io.github.dector.krokus.core.operation.Intersection
import io.github.dector.krokus.core.operation.Union
import io.github.dector.krokus.core.transformation.Rotation

operator fun Geometry.plus(other: Geometry) = union(other)
operator fun Geometry.minus(other: Geometry) = difference(other)
operator fun Geometry.times(other: Geometry) = intersection(other)

fun List<Geometry>.unite() = Union(children = this)

infix fun Geometry.union(other: Geometry) = Union(children = listOf(this, other))
infix fun Geometry.difference(other: Geometry) =
    Difference(source = this, children = listOf(other))
infix fun Geometry.intersection(other: Geometry) =
    Intersection(children = listOf(this, other))

fun Geometry.rotate(rotation: Rotation) = addTransformation(rotation)