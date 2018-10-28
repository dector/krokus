/*
fun main(args: Array<String>) {
    val size = v(10, 20, 10)
    val geometry = (cube(size) - sphere(size.minValue()).atPos(size / 2)) +
            size.corners.map { corner -> cube(2).centered().atPos(corner) }.unite()

    export(geometry, "test.scad")

    storageBox()
}

fun storageBox() {
    val count = Pair(3, 2)
    val cellSize = v(25, 50, 30)
    val innerThickness = 1
    val outerThickness = 1
    val bottomThickness = 1

    fun boxXSize(count: Int, size: Float, innerThickness: Int, outerThickness: Int)
            = count * (size + innerThickness) - innerThickness + 2 * outerThickness;

    fun boxYSize(count: Int, size: Float, innerThickness: Int, outerThickness: Int)
            = count * (size + innerThickness) - innerThickness + 2 * outerThickness;

    fun boxZSize(size: Float, bottomThickness: Int)
            = bottomThickness + size;

    val boxSize = v(
        boxXSize(count.first, cellSize.x, innerThickness, outerThickness),
        boxYSize(count.second, cellSize.y, innerThickness, outerThickness),
        boxZSize(cellSize.z, bottomThickness)
    )

    fun cells(): Geometry {
        val result = mutableListOf<Geometry>()
        (1..count.first).forEach { i ->
            (1..count.second).forEach { j ->
                val x = outerThickness + (i-1) * (cellSize.x + innerThickness)
                val y = outerThickness + (j-1) * (cellSize.y + innerThickness)
                val z = bottomThickness
                result += cube(cellSize).atPos(v(x, y, z))
            }
        }

        return result.unite()
    }

    val geom = cube(boxSize) - cells()

    export(geom, "box.scad")
}

fun export(g: Geometry, filename: String) {
    JavaScadExporter().exportToScad(
        JavaScadGeometryConverter().convert(g), filename
    ).let { exported ->
        if (exported) println("Exported model to $filename")
    }
}
*/
