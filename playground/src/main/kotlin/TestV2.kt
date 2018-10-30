import io.github.dector.krokus.api.rotateAtX
import io.github.dector.krokus.api.rotateAtY
import io.github.dector.krokus.core.geometry.cube
import io.github.dector.krokus.core.geometry.cylinder
import io.github.dector.krokus.core.geometry.minus
import io.github.dector.krokus.core.geometry.plus
import io.github.dector.krokus.samples.utils.exportGeometry


fun main(args: Array<String>) {
    exportGeometry("playground") {
        val base = cube(10)

        val hole = cylinder(height = 10 + 1, radius = 3)
        base - hole.let { it + it.rotateAtX(90) + it.rotateAtY(90) }
    }
}