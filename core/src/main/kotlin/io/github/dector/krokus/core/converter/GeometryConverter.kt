package io.github.dector.krokus.core.converter

import io.github.dector.krokus.core.geometry.Geometry


interface GeometryConverter<T> {

    fun convert(geometry: Geometry): T
}