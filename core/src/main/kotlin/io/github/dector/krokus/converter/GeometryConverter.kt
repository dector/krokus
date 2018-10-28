package io.github.dector.krokus.converter

import io.github.dector.krokus.geometry.Geometry


interface GeometryConverter<T> {

    fun convert(g: Geometry): T
}