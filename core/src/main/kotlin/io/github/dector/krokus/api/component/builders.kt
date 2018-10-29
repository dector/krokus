package io.github.dector.krokus.api.component

import io.github.dector.krokus.api.geometry.Geometry
import io.github.dector.krokus.api.material.Material


fun component(name: String, geometryBuilder: () -> Geometry) =
        Component(name = name, geometry = geometryBuilder())

fun component(name: String, material: Material, geometryBuilder: () -> Geometry) =
        Component(name = name, material = material, geometry = geometryBuilder())