package io.github.dector.krokus.samples

import io.github.dector.krokus.core.geometry.cube
import io.github.dector.krokus.samples.utils.exportGeometry


fun main(args: Array<String>) {
    exportGeometry("1cube") {
        cube(10)
    }
}