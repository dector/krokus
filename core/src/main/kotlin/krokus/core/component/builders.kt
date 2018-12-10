package krokus.core.component

import krokus.core.geometry.Geometry
import krokus.core.material.Material


fun component(name: String, geometryBuilder: () -> Geometry) =
        Component(name = name, geometry = geometryBuilder())

fun component(name: String, material: Material, geometryBuilder: () -> Geometry) =
        Component(name = name, material = material, geometry = geometryBuilder())