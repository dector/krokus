package io.github.dector.krokus.samples

import io.github.dector.krokus.api.moveTo
import io.github.dector.krokus.core.geometry.cube
import io.github.dector.krokus.core.operation.difference
import io.github.dector.krokus.core.operation.union
import io.github.dector.krokus.core.properties.*
import io.github.dector.krokus.core.space.Vector3
import io.github.dector.krokus.openscad.OpenScadExporter
import java.io.File


fun main(args: Array<String>) {
    OpenScadExporter(dryRun = true).export(shelvesTriCorner(), File("/tmp/test.scad"))
}

fun shelvesTriCorner() = union {
    val depth = Property.from(50.0)
    val length = Property.from(30.0)

    val shelfThickness = Property.from(18.0)
    val thickness = Property.from(1.0)
    val innerGap = Property.from(0.3)

    val outerVertical = cube {
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
    }
}