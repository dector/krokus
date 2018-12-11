package krokus.samples

import krokus.api.moveTo
import krokus.core.geometry.cube
import krokus.core.operation.difference
import krokus.core.operation.union
import krokus.core.properties.Property
import krokus.core.space.Vector3
import krokus.openscad.OpenScadExporter
import java.io.File


fun main(args: Array<String>) {
    OpenScadExporter(dryRun = true).export(shelvesTriCorner(), File("/tmp/test.scad"))
}

fun shelvesTriCorner() = union {
    val depth = Property.from(5.0)
    val length = Property.from(5.0)

    val shelfThickness = Property.from(18.0)
    val thickness = Property.from(0.5)
    val innerGap = Property.from(0.0)

    val pattern = ConnectorPattern()

    // Central
    +difference {
        val cut = cube {
            shape().size.apply {
                x.set(shelfThickness)
                y.set(shelfThickness)
                z.set(depth)
            }
        }

        source = cube {
            shape().size.apply {
                x.set { cut.shape().size.x() + 2 * (thickness() + innerGap()) }
                y.set { cut.shape().size.y() + 2 * (thickness() + innerGap()) }
                z.set { cut.shape().size.z() + (thickness() + innerGap()) }
            }
        }

        +cut.moveTo(Property.from {
            Vector3().apply {
                z.set { thickness() + innerGap() }
            }
        })
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

data class ConnectorPattern(val s: String = "")