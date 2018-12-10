package krokus.core.converter

import krokus.core.geometry.Geometry


interface GeometryConverter<T> {

    fun convert(geometry: Geometry): T
}