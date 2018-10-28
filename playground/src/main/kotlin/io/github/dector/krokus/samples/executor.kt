package io.github.dector.krokus.samples

import io.github.dector.krokus.geometry.Geometry
import scad.GeometryConverter
import scad.JavaScadExporter


fun export(name: String, builder: () -> Geometry) {
    val result = JavaScadExporter().exportToScad(
        GeometryConverter().convert(builder()), "samples/$name.scad"
    )

    if (result) println("Done.")
    else System.err.println("Export failed")
}