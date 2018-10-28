package io.github.dector.krokus.component

import io.github.dector.krokus.geometry.Geometry
import io.github.dector.krokus.material.Material


data class Component(
    val name: String,
    val geometry: Geometry,
    val material: Material? = null
)