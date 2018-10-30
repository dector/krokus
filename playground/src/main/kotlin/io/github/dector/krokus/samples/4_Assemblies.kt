package io.github.dector.krokus.samples

import io.github.dector.krokus.api.mirrorVertically
import io.github.dector.krokus.api.moveToZ
import io.github.dector.krokus.api.rotateAtX
import io.github.dector.krokus.api.rotateAtY
import io.github.dector.krokus.core.assembly.assembly
import io.github.dector.krokus.core.assembly.toEntry
import io.github.dector.krokus.core.assembly.toEntryAt
import io.github.dector.krokus.core.component.component
import io.github.dector.krokus.core.geometry.*
import io.github.dector.krokus.core.material.Color
import io.github.dector.krokus.core.material.Material
import io.github.dector.krokus.samples.utils.exportAssembly
import kotlin.math.sqrt


fun main(args: Array<String>) {
    exportAssembly("4assembly") {

        val baseComponent = component(name = "base", material = Material(Color.Cardinal)) {
            val sphere = (5 * sqrt(2f)).let { r ->
                val mask = cube(10, 10, r)
                sphere(r) * mask.moveToZ(r / 2)
            }

            cube(10) -
                    (cylinder(10 + 1, 3).rotateAtX(90) +
                            cylinder(10 + 1, 3).rotateAtY(90)) +
                    sphere.moveToZ(5) +
                    sphere.moveToZ(-5).mirrorVertically()
        }

        val pinComponent = component(name = "pin", material = Material(Color.Azure)) {
            cylinder(20, 2).rotateAtX(90)
        }

        val capComponent = component(name = "cap", material = Material(Color.Erin)) {
            cube(20, 3, 25) -
                    cylinder(3 + 1, 5).rotateAtX(90)
        }

        val bearingComponent = component(name = "bearing", material = Material(Color.Amber)) {
            cylinder(3, 5).rotateAtX(90) -
                    cylinder(3 + 1, 3).rotateAtX(90)
        }

        assembly("example") {
            listOf(
                baseComponent.toEntry(),
                pinComponent.toEntry(),
                capComponent.toEntryAt(0, 15, 0),
                capComponent.toEntryAt(0, -15, 0),
                bearingComponent.toEntryAt(0, -22, 0),
                bearingComponent.toEntryAt(0, 22, 0)
            )
        }
    }
}