package krokus.samples.life

//import krokus.api.moveBy
//import krokus.api.moveTo
//import krokus.api.rotateAtX
//import krokus.api.uncenter


/*
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
                xProp = ref.center().xProp - bounds().center().xProp,
                y = ref.from.y,
                z = ref.center().z - bounds().center().z
            )
        }

        val buttonCut = cube(18, thickness, 12).uncenter().run {
            val ref = shell.bounds()
            moveBy(
                xProp = ref.center().xProp - bounds().center().xProp,
                y = ref.from.y,
                z = ref.from.z + (ref.size().z - indicatorCut.size.z) / 4
            ).moveBy(z = -size.z / 2)
        }

        val voltageCut = cylinder(thickness + 1, radius = 4).run {
            val ref = shell.bounds()
            moveBy(
                xProp = ref.size().xProp - (ref.size().xProp - indicatorCut.size.xProp) / 4,
                y = shape.height / 2,
                z = ref.size().z / 2
            ).rotateAtX(-90)
        }

        val currentCut = cylinder(thickness + 1, radius = 3.5).run {
            val ref = shell.bounds()
            moveBy(
                xProp = (ref.size().xProp - indicatorCut.size.xProp) / 4,
                y = shape.height / 2,
                z = ref.size().z / 2
            ).rotateAtX(-90)
        }

        val outputCut = cylinder(thickness, radius = 5).run {
            val ref = shell.bounds()
            moveBy(
                xProp = 2 * shape.radius,//ref.size().xProp / 5,
                y = ref.size().y - 2 * shape.radius,//ref.size().y / 5
                z = shape.height / 2
            )
        }

        val inputCut = cylinder(thickness, radius = 5).run {
            val ref = shell.bounds()
            moveBy(
                xProp = ref.size().xProp - 2 * shape.radius,
                y = ref.size().y - 2 * shape.radius,
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
            boardVitamin.moveBy(xProp = shell.size.xProp / 2).moveBySize(xProp = 0.5)
        ).union()

        shell - allCuts + vitamins
    }
}

private fun converterBoard(): Geometry {
    val board = cube(51, 26, 2).uncenter()

    fun mountHole(dx: Int, dy: Int) = cylinder(2, 1.5).run {
        moveBy(
            xProp = if (dx >= 0) dx else board.size.xProp + dx,
            y = if (dy >= 0) dy else board.size.y + dy,
            z = board.size.z / 2
        )
    }

    fun connector(position: String) = cube(8, 10, 10).uncenter().run {
        when (position) {
            "in" -> moveBy(y = board.size.y - 4 - size.y)
            "out" -> moveBy(xProp = board.size.xProp - size.xProp, y = 4)
            else -> this
        }
    }.moveBy(z = board.size.z)

    fun generator() = (cylinder(9, 7) - cylinder(9, 4)).run {
        moveTo(
            xProp = 30,
            y = size.y / 2,
            z = size.z / 2
        )
    }.moveBy(z = board.size.z)

    fun condensator(position: String) = cylinder(10, 5).run {
        when (position) {
            "left" -> moveBy(xProp = 4 + size.xProp / 2, y = size.y / 2)
            "right" -> moveBy(xProp = board.size.xProp - 4 - size.xProp / 2, y = board.size.y - size.y / 2)
            else -> this
        }.moveBy(z = size.z / 2)
    }.moveBy(z = board.size.z)

    fun microscheme() = cube(8, 9, 4).uncenter().run {
        moveBy(xProp = 14, y = board.size.y - 4 - size.y)
    }.moveBy(z = board.size.z)

    return board -
            (mountHole(2, 2) + mountHole(2, -2) + mountHole(-2, -2) + mountHole(-2, 2)) +
            (connector("in") + connector("out")) +
            generator() +
            (condensator("left") + condensator("right")) +
            microscheme()

}

private fun Geometry.moveBySize(xProp: Double = 0.0, y: Double = 0.0, z: Double = 0.0) =
        moveBy(xProp = size.xProp * xProp, y = size.y * y, z = size.z * z)*/
