package io.github.dector.krokus.samples

import io.github.dector.krokus.api.moveBy
import io.github.dector.krokus.api.rotateAtX
import io.github.dector.krokus.core.geometry.*
import io.github.dector.krokus.core.space.div
import io.github.dector.krokus.samples.utils.exportGeometry


fun main(args: Array<String>) {
    exportGeometry("PowerSupply") {
        val innerWidth = 110
        val innerHeight = 65
        val innerDeep = 40
        val thickness = 2

        val shell = cube(innerWidth, innerDeep, innerHeight).run {
            resizeBy(2 * thickness).uncenter() - resizeBy(y = thickness).uncenter().moveBy(thickness)
        }

        val indicatorCut = cube(45, thickness, 26).uncenter().run {
            val ref = shell.bounds()
            moveBy(
                x = ref.center().x - bounds().center().x,
                y = ref.from.y,
                z = ref.center().z - bounds().center().z
            )
        }

        val buttonCut = cube(18, thickness, 12).uncenter().run {
            val ref = shell.bounds()
            moveBy(
                x = ref.center().x - bounds().center().x,
                y = ref.from.y,
                z = ref.from.z + (ref.size().z - indicatorCut.bounds().size().z) / 4
            ).moveBy(z = -bounds().size().z / 2)
        }

        val voltageCut = cylinder(thickness + 1, radius = 4).run {
            val ref = shell.bounds()
            moveBy(
                x = ref.size().x - (ref.size().x - indicatorCut.bounds().size().x) / 4,
                y = shape.height / 2,
                z = ref.size().z / 2
            ).rotateAtX(-90)
        }

        val currentCut = cylinder(thickness + 1, radius = 3.5).run {
            val ref = shell.bounds()
            moveBy(
                x = (ref.size().x - indicatorCut.bounds().size().x) / 4,
                y = shape.height / 2,
                z = ref.size().z / 2
            ).rotateAtX(-90)
        }

        val outputCut = cylinder(thickness, radius = 5).run {
            val ref = shell.bounds()
            moveBy(
                x = 2 * shape.radius.bottom,//ref.size().x / 5,
                y = ref.size().y - 2 * shape.radius.bottom,//ref.size().y / 5
                z = shape.height / 2
            )
        }

        val inputCut = cylinder(thickness, radius = 5).run {
            val ref = shell.bounds()
            moveBy(
                x = ref.size().x - 2 * shape.radius.bottom,
                y = ref.size().y - 2 * shape.radius.bottom,
                z = ref.size().z - shape.height / 2
            )
        }

        val allCuts = listOf(
            indicatorCut,
            buttonCut,
            voltageCut,
            currentCut,
            outputCut,
            inputCut
        ).union()

        val boardVitamin = cube(52, 27, 13)

        val vitamins = listOf(boardVitamin).union()

        shell - allCuts //+ vitamins
    }
}

private fun ShapeGeometry<Cube>.uncenter() = moveBy(shape.size / 2)