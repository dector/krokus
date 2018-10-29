import io.github.dector.krokus.api.geometry.*
import io.github.dector.krokus.api.transformation.Rotation
import io.github.dector.krokus.api.vector.xyz
import io.github.dector.krokus.samples.exportGeometry


fun main(args: Array<String>) {
    exportGeometry("playground") {
        val base = ShapeGeometry(Cube3(size = xyz(10, 10, 10)))

        val hole = ShapeGeometry(Cylinder3(height = 10.0 + 1, radius = Pair(3.0, 3.0)))
         base - hole.let { it + it.rotate(Rotation(angleX = 90)) + it.rotate(Rotation(angleY = 90)) }
    }
}