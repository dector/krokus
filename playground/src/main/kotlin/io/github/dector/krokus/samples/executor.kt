package io.github.dector.krokus.samples

import io.github.dector.krokus.geometry.Geometry
import io.github.dector.krokus.javascad.JavaScadExporter
import io.github.dector.krokus.javascad.JavaScadGeometryConverter


fun export(name: String, builder: () -> Geometry) {
    val converter = JavaScadGeometryConverter()
    val result = JavaScadExporter().exportToScad(
        converter.convert(builder()), "samples/$name.scad"
    )

    if (result) println("Done.")
    else System.err.println("Export failed")
}