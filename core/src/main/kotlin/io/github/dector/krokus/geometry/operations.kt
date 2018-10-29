package io.github.dector.krokus.geometry

import io.github.dector.krokus.operation.Operation

operator fun Geometry.plus(other: Geometry) = Combined(children = listOf(this, other), operation = Operation.Union)
operator fun Geometry.minus(other: Geometry) = Combined(children = listOf(this, other), operation = Operation.Difference)
operator fun Geometry.times(other: Geometry) = Combined(children = listOf(this, other), operation = Operation.Intersection)

fun List<Geometry>.unite() = Combined(children = this, operation = Operation.Union)