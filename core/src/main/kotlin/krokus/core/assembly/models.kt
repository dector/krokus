package krokus.core.assembly

import krokus.core.component.Component
import krokus.core.space.Vector3


data class Assembly(val name: String, val entries: List<Entry>)

data class Entry(val component: Component, val position: Vector3)