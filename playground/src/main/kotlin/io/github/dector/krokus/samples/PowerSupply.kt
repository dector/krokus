package io.github.dector.krokus.samples

import io.github.dector.krokus.api.moveBy
import io.github.dector.krokus.core.geometry.*
import io.github.dector.krokus.core.space.div
import io.github.dector.krokus.samples.utils.exportGeometry


fun main(args: Array<String>) {
    exportGeometry("PowerSupply") {
        val innerWidth = 110
        val innerHeight = 65
        val innerDeep = 40
        val thickness = 2

        val shell = cube(innerWidth, innerDeep, innerHeight).let {
            it.resizeBy(2 * thickness).uncenter() - it.resizeBy(y = thickness).uncenter().moveBy(thickness)
        }

        val indicatorCut = cube(45, thickness, 26).uncenter().let {
            val ref = shell.bounds()
            it.moveBy(
                x = ref.center().x - it.bounds().center().x,
                y = ref.from.y,
                z = ref.center().z - it.bounds().center().z
            )
        }

        val buttonCut = cube(18, thickness, 12).uncenter().let {
            val ref = shell.bounds()
            it.moveBy(
                x = ref.center().x - it.bounds().center().x,
                y = ref.from.y,
                z = ref.from.z + (ref.size().z - indicatorCut.bounds().size().z) / 4
            ).moveBy(z = -it.bounds().size().z / 2)
        }

        shell - indicatorCut - buttonCut
    }
}

private fun ShapeGeometry<Cube>.uncenter() = moveBy(shape.size / 2)