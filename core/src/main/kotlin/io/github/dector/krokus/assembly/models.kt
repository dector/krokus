package io.github.dector.krokus.assembly

import io.github.dector.krokus.component.Component
import io.github.dector.krokus.vector.Vector3


data class Assembly(val name: String, val entries: List<Entry>)

data class Entry(val component: Component, val position: Vector3)