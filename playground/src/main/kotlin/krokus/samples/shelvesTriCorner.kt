package krokus.samples

import krokus.api.moveTo
import krokus.core.geometry.*
import krokus.core.geometry.shape.Cube
import krokus.core.operation.difference
import krokus.core.operation.union
import krokus.core.properties.Property
import krokus.core.properties.div
import krokus.core.properties.minus
import krokus.core.properties.p
import krokus.openscad.OpenScadExporter
import java.io.File


fun main(args: Array<String>) {
    OpenScadExporter(dryRun = true).export(shelvesTriCorner(), File("/tmp/test.scad"))
}

val outerDepth = Property.from(27.0)
val innerDepth = Property.from(10.0)
val length = Property.from(27.0)

val shelfThickness = Property.from(19.3)
val thickness = Property.from(0.85)
val innerGap = Property.from(0.0)

val pattern = connector(2)

fun shelvesTriCorner() = union {
    // Central
    val centralCut = cube {
        width = p { shelfThickness() + 2 * innerGap() }
        depth = p { shelfThickness() + 2 * innerGap() }
        height = p { outerDepth() + innerGap() }
    }
    val centralShell = cube {
        width = p { centralCut.shape().size.x() + 2 * thickness() }
        depth = p { centralCut.shape().size.y() + 2 * thickness() }
        height = p { centralCut.shape().size.z() + thickness() }
    }
    +difference {
        source = centralShell

        +centralCut.moveTo {
            z.set { thickness() / 2 }
        }

        if (pattern.right || pattern.left) {
            fun cut(xShiftMultiplier: Int) = cube {
                width = thickness
                depth = p { shelfThickness() + 2 * innerGap() }
                height = centralCut.shape().size.z
            }.run {
                moveTo {
                    x.set { xShiftMultiplier * (centralCut.width() / 2 + width() / 2) }
                    z.set(centralCut.z)
                }
            }

            if (pattern.right) +cut(+1)
            if (pattern.left) +cut(-1)
        }
        if (pattern.up || pattern.down) {
            fun cut(yShiftMultiplier: Int) = cube {
                width = p { shelfThickness() + 2 * innerGap() }
                depth = thickness
                height = centralCut.shape().size.z
            }.run {
                moveTo {
                    y.set { yShiftMultiplier * (centralCut.depth() / 2 + depth() / 2) }
                    z.set(centralCut.z)
                }
            }

            if (pattern.up) +cut(+1)
            if (pattern.down) +cut(-1)
        }
    }

    if (pattern.right || pattern.left) {
        fun cut() = cube {
            width = length
            depth = p { shelfThickness() + 2 * innerGap() }
            height = centralCut.height
        }

        fun shell(cut: ShapeGeometry<Cube>) = cube {
            width = cut.width
            depth = p { cut.depth() + 2 * thickness() }
            height = centralShell.height
        }

        val cut = cut()
        val shell = shell(cut)

        fun sleeve(xShiftMultiplier: Int) = difference(shell) {
            +cut.moveTo {
                z.set { thickness() / 2 }
            }
        }.moveTo {
            x.set { xShiftMultiplier * (centralShell.width() / 2 + shell.width() / 2) }
        }

        if (pattern.right) +sleeve(+1)
        if (pattern.left) +sleeve(-1)
    }
    if (pattern.up || pattern.down) {
        fun cut() = cube {
            width = p { shelfThickness() + 2 * innerGap() }
            depth = length
            height = centralCut.height
        }

        fun shell(cut: ShapeGeometry<Cube>) = cube {
            width = p { cut.width() + 2 * thickness() }
            depth = cut.depth
            height = centralShell.height
        }

        val cut = cut()
        val shell = shell(cut)

        fun sleeve(yShiftMultiplier: Int) = difference(shell) {
            +cut.moveTo {
                z.set { thickness() / 2 }
            }
        }.moveTo {
            y.set { yShiftMultiplier * (centralShell.depth() / 2 + shell.depth() / 2) }
        }

        if (pattern.up) +sleeve(+1)
        if (pattern.down) +sleeve(-1)
    }
}.let {
    difference {
        source = it

        // Hack for shorter innerDepth
        +cube {
            width = p { length() * 2 }
            depth = p { length() * 1.5 }
            height = outerDepth - innerDepth - thickness

            x = width / 2
            z = height / 2 - thickness
            y = depth / 2
        }
    }
}

data class ConnectorPattern(
    val up: Boolean = false,
    val down: Boolean = false,
    val left: Boolean = false,
    val right: Boolean = false
)

data class Connector(val cutsPattern: ConnectorPattern, val sleevesPattern: ConnectorPattern)

fun connector(sides: Int, runaway: Boolean = false) = when (sides) {
    0 -> ConnectorPattern()
    1 -> ConnectorPattern(up = true)
    2 -> if (runaway) ConnectorPattern(left = true, right = true) else ConnectorPattern(up = true, right = true)
    3 -> ConnectorPattern(up = true, left = true, right = true)
    4 -> ConnectorPattern(up = true, down = true, left = true, right = true)
    else -> throw IllegalArgumentException("Number of sides should be in range 0..4")
}