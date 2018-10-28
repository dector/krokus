package io.github.dector.krokus.samples

import io.github.dector.krokus.assembly.assembly
import io.github.dector.krokus.assembly.toEntry
import io.github.dector.krokus.assembly.toEntryAt
import io.github.dector.krokus.component.component
import io.github.dector.krokus.geometry.centered
import io.github.dector.krokus.geometry.cube
import io.github.dector.krokus.geometry.sphere
import io.github.dector.krokus.material.Color
import io.github.dector.krokus.material.Material
import io.github.dector.krokus.vector.v


fun main(args: Array<String>) {
    exportAssembly("4assembly") {

        assembly("example") {
            listOf(
                component(name = "box", material = Material(Color.Cardinal)) {
                    cube(10).centered()
                }.toEntry(),

                component(name = "sphere", material = Material(Color.Azure)) {
                    sphere(5)
                }.toEntryAt(v(0, 0, 10))
            )
        }
    }
}