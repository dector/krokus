package io.github.dector.krokus.api.converter

import io.github.dector.krokus.api.geometry.Geometry


interface GeometryConverter<T> {

    fun convert(geometry: Geometry): T
}