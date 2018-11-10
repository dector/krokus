package io.github.dector.krokus.samples.life

import io.github.dector.krokus.api.*
import io.github.dector.krokus.core.component.component
import io.github.dector.krokus.core.geometry.*
import io.github.dector.krokus.core.material.Color
import io.github.dector.krokus.core.material.Material
import io.github.dector.krokus.core.space.Plane
import io.github.dector.krokus.samples.utils.exportComponent


fun main(args: Array<String>) {
    val config = Config()

    exportComponent("FootStand") { buildComponent(config) }
}

data class Config(
    val platformThickness: Double = 3.0,
    val platformRadius: Double = 23.0,
    val legHeight: Double = 8.5,
    val legRadius: Double = 10.0,
    val legCutThickness: Double = 2.0
)

fun buildComponent(config: Config) = component("Foot Stand", Material(Color.Amber)) {
    val platform = cylinder(height = config.platformThickness, radius = config.platformRadius)

    val leg = cylinder(height = config.legHeight, radius = config.legRadius)
        .moveTo(platform, Side.Bottom to Side.Top)

    val legCut = cylinder(height = config.legHeight, radius = config.legRadius - config.legCutThickness)
        .moveTo(leg, Side.Top to Side.Top)

    val legSectionCut = cube((config.legRadius + 3) * 2 + 1, 3, config.legHeight /*- 3*/)
        .rotateAtZ(60)
        .moveTo(legCut, Side.Top to Side.Top)
        .let { it + it.rotateAtZ(-60) }

    val hook = cylinder(height = 3, radiuses = config.legRadius + 3 to config.legRadius)
        .moveTo(leg, Side.Top to Side.Top) *
            prism(3.0, config.legRadius)
                .rotateAtZ(-90)
                .moveTo(leg, Side.Top to Side.Top)
                .moveByY(leg.shape.radius)
                .withModified {
                    it.mirror(Plane.XZ).moveByY(-2 * config.legRadius)
                }
                .union() - legSectionCut

    platform + (leg + hook - legCut - legSectionCut)
}

