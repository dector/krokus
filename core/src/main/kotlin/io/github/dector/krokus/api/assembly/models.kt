package io.github.dector.krokus.api.assembly

import io.github.dector.krokus.api.component.Component
import io.github.dector.krokus.api.vector.Vector3


data class Assembly(val name: String, val entries: List<Entry>)

data class Entry(val component: Component, val position: Vector3)