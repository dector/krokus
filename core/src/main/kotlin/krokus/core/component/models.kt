package krokus.core.component

import krokus.core.geometry.Geometry
import krokus.core.material.Material


data class Component(
    val name: String,
    val geometry: Geometry,
    val material: Material? = null
)