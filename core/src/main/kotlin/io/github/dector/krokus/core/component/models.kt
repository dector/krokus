package io.github.dector.krokus.core.component

import io.github.dector.krokus.core.geometry.Geometry
import io.github.dector.krokus.core.material.Material


data class Component(
    val name: String,
    val geometry: Geometry,
    val material: Material? = null
)