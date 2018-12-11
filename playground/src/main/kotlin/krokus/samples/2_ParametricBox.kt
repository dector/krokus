package krokus.samples

//import krokus.api.moveTo
//import krokus.core.geometry.minus
//import krokus.core.geometry.union


/*
private data class CellsConfig(
    val rows: Int, val columns: Int,
    val size: Dimen3
)

private data class BoxConfig(
    val outerWall: Int, val innerWall: Int,
    val bottom: Int
)

fun main(args: Array<String>) {
    exportComponent("2parametric_box") {
        val cellsConfig = CellsConfig(rows = 2, columns = 3, size = Dimen3(30, 25, 15))
        val boxConfig = BoxConfig(outerWall = 1, innerWall = 1, bottom = 1)

        boxComponent(cellsConfig, boxConfig)
    }
}

private fun boxComponent(cellsConfig: CellsConfig, boxConfig: BoxConfig) = component("StorageBox") {
    fun cells(cellsConfig: CellsConfig, boxConfig: BoxConfig): Geometry {
        val result = mutableListOf<Geometry>()
        (1..cellsConfig.columns).forEach { i ->
            (1..cellsConfig.rows).forEach { j ->
                val xProp = boxConfig.outerWall + (i - 1) * (cellsConfig.size.width + boxConfig.innerWall)
                val y = boxConfig.outerWall + (j - 1) * (cellsConfig.size.height + boxConfig.innerWall)
                val z = boxConfig.bottom
                result += cube(cellsConfig.size).moveTo(v(xProp, y, z) + cellsConfig.size.asVector() / 2)
            }
        }

        return result.union()
    }

    val boxSize = (((cellsConfig.size.wh() + boxConfig.innerWall) scaleEachBy
            v2(cellsConfig.columns, cellsConfig.rows)) - boxConfig.innerWall + 2 * boxConfig.outerWall)
        .withZ(cellsConfig.size.depth + boxConfig.bottom)
    (cube(boxSize).moveTo(boxSize / 2) - cells(cellsConfig, boxConfig))
}

private data class Dimen3(val width: Int, val height: Int, val depth: Int)

private fun Dimen3.wh() = v2(width, height)

fun v2(xProp: Int, y: Int) = Vector2(xProp.toFloat(), y.toFloat())

fun Vector2.withZ(z: Int) = Vector3(xProp = xProp.toDouble(), y = y.toDouble(), z = z.toDouble())

private fun cube(size: Dimen3) = cube(size.asVector())

private fun Dimen3.asVector() = Vector3(width.toDouble(), height.toDouble(), depth.toDouble())

fun <T> T.log() = also { println(it) }*/