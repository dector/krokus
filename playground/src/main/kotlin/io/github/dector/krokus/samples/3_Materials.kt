package io.github.dector.krokus.samples

import io.github.dector.krokus.core.component.component
import io.github.dector.krokus.core.geometry.cube
import io.github.dector.krokus.core.geometry.sphere
import io.github.dector.krokus.core.material.Color
import io.github.dector.krokus.core.material.Material
import io.github.dector.krokus.samples.utils.exportComponent


private const val FileName = "3material"

fun main(args: Array<String>) {
    exportComponent(FileName) {
        component(name = "box", material = Material(Color.Cardinal)) {
            cube(10)
        }
    }

    exportComponent(FileName) {
        component(name = "sphere", material = Material(Color.Azure)) {
            sphere(5)
        }
    }
}