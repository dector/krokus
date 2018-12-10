package krokus.core.assembly

import krokus.core.component.Component
import krokus.core.space.Vector3
import krokus.core.space.v


fun assembly(name: String, entriesBuilder: () -> List<Entry>) =
    Assembly(name = name, entries = entriesBuilder())

fun entry(component: Component, position: Vector3 = Vector3.Origin) =
        Entry(component = component, position = position)

fun Component.toEntry() = entry(this)
fun Component.toEntryAt(position: Vector3) = entry(this, position)
fun Component.toEntryAt(x: Number = 0, y: Number = 0, z: Number = 0) = entry(this, v(x, y, z))
