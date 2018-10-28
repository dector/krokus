package io.github.dector.krokus.component

import io.github.dector.krokus.geometry.Geometry


fun component(name: String, geometryBuilder: () -> Geometry) =
        Component(geometryBuilder(), name = name)