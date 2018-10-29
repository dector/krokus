package io.github.dector.krokus.samples

import io.github.dector.krokus.assembly.assembly
import io.github.dector.krokus.assembly.toEntry
import io.github.dector.krokus.assembly.toEntryAt
import io.github.dector.krokus.component.component
import io.github.dector.krokus.component.mirrorVertically
import io.github.dector.krokus.geometry.*
import io.github.dector.krokus.material.Color
import io.github.dector.krokus.material.Material
import io.github.dector.krokus.vector.v
import kotlin.math.sqrt


fun main(args: Array<String>) {
    exportAssembly("4assembly") {

        val box = component(name = "box", material = Material(Color.Cardinal)) {
            cube(10).centered()
        }

        val sphere = component(name = "sphere", material = Material(Color.Azure)) {
            val r = 5 * sqrt(2f)
            fun mask() = cube(v(10, 10, r)).centered()
            sphere(r) * mask().atPosZ(r / 2)
        }

        assembly("example") {
            listOf(
                box.toEntry(),
                sphere.toEntryAt(v(0, 0, 5)),
                sphere.mirrorVertically().toEntryAt(v(0, 0, -5))
            )
        }.apply { this.entries.filterIndexed { i, _ -> i in 1..2 }.forEach { it.log() } }
    }
}