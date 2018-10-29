package io.github.dector.krokus.samples

import io.github.dector.krokus.assembly.assembly
import io.github.dector.krokus.assembly.toEntry
import io.github.dector.krokus.assembly.toEntryAt
import io.github.dector.krokus.component.component
import io.github.dector.krokus.geometry.*
import io.github.dector.krokus.material.Color
import io.github.dector.krokus.material.Material
import kotlin.math.sqrt


fun main(args: Array<String>) {
    exportAssembly("4assembly") {

        val baseComponent = component(name = "base", material = Material(Color.Cardinal)) {
            val sphere = (5 * sqrt(2f)).let { r ->
                fun mask() = cube(10, 10, r).centered()
                sphere(r) * mask().atPosZ(r / 2)
            }

            cube(10).centered() -
                    (cylinder(10 + 1, 3).rotateX(90) +
                            cylinder(10 + 1, 3).rotateY(90)) +
                    sphere.atPos(0, 0, 5) +
                    sphere.mirrorVertically().atPos(0, 0, -5)
        }

        val pinComponent = component(name = "pin", material = Material(Color.Azure)) {
            cylinder(20, 2).rotateX(90)
        }

        val capComponent = component(name = "cap", material = Material(Color.Erin)) {
            cube(20, 3, 25).centered() -
                    cylinder(3 + 1, 5).rotateX(90)
        }

        val bearingComponent = component(name = "bearing", material = Material(Color.Amber)) {
            cylinder(3, 5).rotateX(90) -
                    cylinder(3 + 1, 3).rotateX(90)
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