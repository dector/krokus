package io.github.dector.krokus.api.component

import io.github.dector.krokus.api.geometry.Geometry
import io.github.dector.krokus.api.material.Material


data class Component(
    val name: String,
    val geometry: Geometry,
    val material: Material? = null
)