package io.github.dector.krokus.samples

import io.github.dector.krokus.geometry.cube


fun main(args: Array<String>) {
    exportGeometry("1cube") {
        cube(10)
    }
}