package krokus.samples

import krokus.api.moveTo
import krokus.core.geometry.*
import krokus.core.geometry.shape.Cube
import krokus.core.operation.difference
import krokus.core.operation.union
import krokus.core.properties.Property
import krokus.core.properties.p
import krokus.openscad.OpenScadExporter
import java.io.File


fun main(args: Array<String>) {
    OpenScadExporter(dryRun = true).export(shelvesTriCorner(), File("/tmp/test.scad"))
}

fun shelvesTriCorner() = union {
    val innerDepth = Property.from(5.0)
    val length = Property.from(5.0)

    val shelfThickness = Property.from(18.0)
    val thickness = Property.from(0.5)
    val innerGap = Property.from(0.0)

    val pattern = ConnectorPattern(
        up = true, down = true,
        right = true, left = true
    )

    // Central
    val centralCut = cube {
        shape().size.apply {
            x.set { shelfThickness() + 2 * innerGap() }
            y.set { shelfThickness() + 2 * innerGap() }
            z.set { innerDepth() + innerGap() }
        }
    }
    val centralShell = cube {
        shape().size.apply {
            x.set { centralCut.shape().size.x() + 2 * thickness() }
            y.set { centralCut.shape().size.y() + 2 * thickness() }
            z.set { centralCut.shape().size.z() + thickness() }
        }
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

    // ---------

    /*val outerVertical = cube {
        shape().size.apply {
            x = shelfThickness + 2 * (thickness + innerGap)
            y = depth //+ thickness + innerGap
            z = (shelfThickness + 2 * (thickness + innerGap)) + 2 * (length)
        }
    }

    val outerHorizontal = cube {
        shape().size.apply {
            x = length
            y = depth //+ thickness + innerGap
            z = shelfThickness + 2 * (thickness + innerGap)
        }
    }

    +difference {
        // Shell
        source = union {
            +outerVertical

            +outerHorizontal
                .moveTo(Property.from {
                    Vector3(
                        x = (outerVertical.shape().size.x + outerHorizontal.shape().size.x) / 2
                    )
                })
        }

        // Cut
        +union {
            +cube {
                val refSize = outerVertical.shape().size

                shape().size.apply {
                    x = shelfThickness + 2 * innerGap
                    y = refSize.y - (thickness + innerGap)
                    z = refSize.z
                }
                moveTo(Property.from {
                    Vector3(
                        y = -(thickness + innerGap) / 2
                    )
                })
            }

            +cube {
                val refSize = outerHorizontal.shape().size

                shape().size.apply {
                    x = refSize.x + (thickness + innerGap)
                    y = refSize.y - (thickness + innerGap)
                    z = refSize.z - 2 * (thickness + innerGap)
                }
                moveTo(Property.from {
                    val refPosition = outerHorizontal.translation().position()

                    Vector3(
                        x = refPosition.x - thickness / 2,
                        y = refPosition.y - (thickness + innerGap) / 2,
                        z = refPosition.z
                    )
                })
            }
        }
    }*/
}

data class ConnectorPattern(
    val up: Boolean = false,
    val down: Boolean = false,
    val left: Boolean = false,
    val right: Boolean = false
)