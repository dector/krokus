package io.github.dector.krokus.api.assembly

import io.github.dector.krokus.api.component.Component
import io.github.dector.krokus.api.vector.Vector3
import io.github.dector.krokus.api.vector.xyz


fun assembly(name: String, entriesBuilder: () -> List<Entry>) =
    Assembly(name = name, entries = entriesBuilder())

fun entry(component: Component, position: Vector3 = Vector3()) =
        Entry(component = component, position = position)

fun Component.toEntry() = entry(this)
fun Component.toEntryAt(position: Vector3) = entry(this, position)
fun Component.toEntryAt(x: Number, y: Number, z: Number) = entry(this, xyz(x, y, z))