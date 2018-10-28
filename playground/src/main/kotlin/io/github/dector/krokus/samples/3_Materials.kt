package io.github.dector.krokus.samples

import io.github.dector.krokus.component.component
import io.github.dector.krokus.geometry.centered
import io.github.dector.krokus.geometry.cube
import io.github.dector.krokus.geometry.sphere
import io.github.dector.krokus.material.Color
import io.github.dector.krokus.material.Material


private const val FileName = "3material"

fun main(args: Array<String>) {
    exportComponent(FileName) {
        component(name = "box", material = Material(Color.Cardinal)) {
            cube(10).centered()
        }
    }

    exportComponent(FileName) {
        component(name = "sphere", material = Material(Color.Azure)) {
            sphere(5)
        }
    }
}