package krokus.samples

import krokus.core.geometry.cube
import krokus.samples.utils.exportGeometry


fun main() {
    exportGeometry("1cube") {
        cube(10)
    }
}