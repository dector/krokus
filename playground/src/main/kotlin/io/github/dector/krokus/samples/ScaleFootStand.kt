package io.github.dector.krokus.samples

import io.github.dector.krokus.api.*
import io.github.dector.krokus.core.geometry.*
import io.github.dector.krokus.core.math.asRadius
import io.github.dector.krokus.core.space.Plane
import io.github.dector.krokus.samples.utils.exportGeometry


fun main(args: Array<String>) {
    exportGeometry("foot_stand") {
        val stand = cylinder(height = 3, radius = asRadius(45))

        val leg = cylinder(height = 8.5, radius = 10)
            .moveTo(stand, Side.Bottom to Side.Top) as ShapeGeometry<Cylinder>

        val legCut = cylinder(height = leg.height - 2, radius = 8)
            .moveTo(leg, Side.Top to Side.Top) as ShapeGeometry<Cylinder>

        val legSectionCut = cube(leg.shape.radius.bottom * 2 + 1, 3, legCut.height)
            .rotateZ(60)
            .moveTo(legCut, Side.Bottom to Side.Bottom).let { it + it.rotateZ(-60) }    // FIXME apply relative rotation

        val grabber = cylinder(height = 3, radiuses = leg.shape.radius.bottom + 3 to leg.shape.radius.bottom).moveTo(
            leg,
            Side.Top to Side.Top
        ) * (
                ShapeGeometry(Prism(3.0, leg.shape.radius.bottom))
                    .rotateZ(-90)
                    .moveByY(leg.shape.radius.bottom)
                    .moveTo(leg, Side.Top to Side.Top).let {
                        it + it.mirror(Plane.XZ).moveToY(-2 * leg.shape.radius.bottom)
                    }
                ) - legSectionCut.copy(children = legSectionCut.children.map {
            (it as ShapeGeometry<Cube>).resizeXBy(
                10
            )
        })

        stand + (leg + grabber - legCut - legSectionCut)
    }
}

