package io.github.dector.krokus.component

import io.github.dector.krokus.geometry.Geometry
import io.github.dector.krokus.material.Material


fun component(name: String, geometryBuilder: () -> Geometry) =
        Component(name = name, geometry = geometryBuilder())

fun component(name: String, material: Material, geometryBuilder: () -> Geometry) =
        Component(name = name, material = material, geometry = geometryBuilder())