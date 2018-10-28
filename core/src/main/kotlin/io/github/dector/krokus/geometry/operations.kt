package io.github.dector.krokus.geometry

import io.github.dector.krokus.operation.Operation

operator fun Geometry.plus(other: Geometry) = Combined(Operation.Union, listOf(this, other))
operator fun Geometry.minus(other: Geometry) = Combined(Operation.Difference, listOf(this, other))
operator fun Geometry.times(other: Geometry) = Combined(Operation.Intersection, listOf(this, other))

fun List<Geometry>.unite() = Combined(Operation.Union, this)