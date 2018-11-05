package io.github.dector.krokus.samples.life

import io.github.dector.krokus.api.moveBy
import io.github.dector.krokus.api.moveTo
import io.github.dector.krokus.api.rotateAtX
import io.github.dector.krokus.api.uncenter
import io.github.dector.krokus.core.geometry.*
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
                z = ref.from.z + (ref.size().z - indicatorCut.size.z) / 4
            ).moveBy(z = -size.z / 2)
        }

        val voltageCut = cylinder(thickness + 1, radius = 4).run {
            val ref = shell.bounds()
            moveBy(
                x = ref.size().x - (ref.size().x - indicatorCut.size.x) / 4,
                y = shape.height / 2,
                z = ref.size().z / 2
            ).rotateAtX(-90)
        }

        val currentCut = cylinder(thickness + 1, radius = 3.5).run {
            val ref = shell.bounds()
            moveBy(
                x = (ref.size().x - indicatorCut.size.x) / 4,
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

        val boardVitamin = converterBoard()

        val vitamins = listOf(
            boardVitamin.moveBy(x = shell.size.x / 2).moveBySize(x = 0.5)
        ).union()

        shell - allCuts + vitamins
    }
}

private fun converterBoard(): Geometry {
    val board = cube(51, 26, 2).uncenter()

    fun mountHole(dx: Int, dy: Int) = cylinder(2, 1.5).run {
        moveBy(
            x = if (dx >= 0) dx else board.size.x + dx,
            y = if (dy >= 0) dy else board.size.y + dy,
            z = board.size.z / 2
        )
    }

    fun connector(position: String) = cube(8, 10, 10).uncenter().run {
        when (position) {
            "in" -> moveBy(y = board.size.y - 4 - size.y)
            "out" -> moveBy(x = board.size.x - size.x, y = 4)
            else -> this
        }
    }.moveBy(z = board.size.z)

    fun generator() = (cylinder(9, 7) - cylinder(9, 4)).run {
        moveTo(
            x = 30,
            y = size.y / 2,
            z = size.z / 2
        )
    }.moveBy(z = board.size.z)

    fun condensator(position: String) = cylinder(10, 5).run {
        when (position) {
            "left" -> moveBy(x = 4 + size.x / 2, y = size.y / 2)
            "right" -> moveBy(x = board.size.x - 4 - size.x / 2, y = board.size.y - size.y / 2)
            else -> this
        }.moveBy(z = size.z / 2)
    }.moveBy(z = board.size.z)

    fun microscheme() = cube(8, 9, 4).uncenter().run {
        moveBy(x = 14, y = board.size.y - 4 - size.y)
    }.moveBy(z = board.size.z)

    return board -
            (mountHole(2, 2) + mountHole(2, -2) + mountHole(-2, -2) + mountHole(-2, 2)) +
            (connector("in") + connector("out")) +
            generator() +
            (condensator("left") + condensator("right")) +
            microscheme()

}

private fun Geometry.moveBySize(x: Double = 0.0, y: Double = 0.0, z: Double = 0.0) =
        moveBy(x = size.x * x, y = size.y * y, z = size.z * z)