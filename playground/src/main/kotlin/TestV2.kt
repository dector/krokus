import io.github.dector.krokus.api.rotateX
import io.github.dector.krokus.api.rotateY
import io.github.dector.krokus.core.geometry.*
import io.github.dector.krokus.core.space.v
import io.github.dector.krokus.samples.utils.exportGeometry


fun main(args: Array<String>) {
    exportGeometry("playground") {
        val base = ShapeGeometry(Cube(size = v(10, 10, 10)))

        val hole = ShapeGeometry(Cylinder(height = 10.0 + 1, radius = Cylinder.Radius(3.0, 3.0)))
        base - hole.let { it + it.rotateX(90) + it.rotateY(90) }
    }
}