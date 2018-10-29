package io.github.dector.krokus.core.assembly

import io.github.dector.krokus.core.component.Component
import io.github.dector.krokus.core.vector.Vector3


data class Assembly(val name: String, val entries: List<Entry>)

data class Entry(val component: Component, val position: Vector3)