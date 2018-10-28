package io.github.dector.krokus.samples

import io.github.dector.krokus.geometry.*
import io.github.dector.krokus.vector.*


fun main(args: Array<String>) {
    export("2parametric_box") {
        val cellsConfig = CellsConfig(rows = 2, columns = 3, size = Dimen3(30, 25, 15))
        val boxConfig = BoxConfig(outerWall = 1, innerWall = 1, bottom = 1)

        shell(cellsConfig, boxConfig) - cells(cellsConfig, boxConfig)
    }
}

private fun shell(cellsConfig: CellsConfig, boxConfig: BoxConfig): Geometry {
    val boxSize = ((cellsConfig.size.wh() + boxConfig.innerWall) scale
            v2(cellsConfig.columns, cellsConfig.rows)) - boxConfig.innerWall + 2 * boxConfig.outerWall

    return cube(boxSize.withZ(cellsConfig.size.depth + boxConfig.bottom))
}

private fun cells(cellsConfig: CellsConfig, boxConfig: BoxConfig): Geometry {
    val result = mutableListOf<Geometry>()
    (1..cellsConfig.columns).forEach { i ->
        (1..cellsConfig.rows).forEach { j ->
            val x = boxConfig.outerWall + (i-1) * (cellsConfig.size.width + boxConfig.innerWall)
            val y = boxConfig.outerWall + (j-1) * (cellsConfig.size.height + boxConfig.innerWall)
            val z = boxConfig.bottom
            result += cube(cellsConfig.size).atPos(v(x, y, z))
        }
    }

    return result.unite()
}

private data class CellsConfig(
    val rows: Int, val columns: Int,
    val size: Dimen3
)

private data class BoxConfig(
    val outerWall: Int, val innerWall: Int,
    val bottom: Int
)

data class Dimen3(val width: Int, val height: Int, val depth: Int)

fun Dimen3.wh() = v2(width, height)

fun v2(x: Int, y: Int) = Vector2(x.toFloat(), y.toFloat())

fun Vector2.withZ(z: Int) = Vector3(x = x, y = y, z = z.toFloat())

fun cube(size: Dimen3) = cube(size.asVector())

fun Dimen3.asVector() = Vector3(width.toFloat(), height.toFloat(), depth.toFloat())

fun <T> T.log() = also { println(it) }