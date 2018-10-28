package io.github.dector.krokus.javascad

import eu.printingin3d.javascad.basic.Radius
import eu.printingin3d.javascad.coords.Coords3d
import eu.printingin3d.javascad.coords.Dims3d
import eu.printingin3d.javascad.models.Cube
import eu.printingin3d.javascad.models.Sphere
import eu.printingin3d.javascad.tranzitions.Difference
import eu.printingin3d.javascad.tranzitions.Translate
import eu.printingin3d.javascad.tranzitions.Union
import eu.printingin3d.javascad.utils.SaveScadFiles
import eu.printingin3d.javascad.vrl.export.StlTextFile
import io.github.dector.krokus.vector.*
import java.io.File

fun main(args: Array<String>) {
    val size = v(10, 20, 10)

    val cube = cube(size).uncenter()
    val cubes = size.corners.map { corner -> cube(2).atPos(corner) }.let {
        Union(it)
    }
    val body = Union(cube, cubes);

    val sphere = Sphere(Radius.fromRadius(size.minValue().toDouble())).let {
        val pos = size / 2
        Translate(it, Coords3d(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble()))
    }

    val geom = Difference(
        body,
        sphere
    )

    StlTextFile(File("test.stl")).writeToFile(geom.toCSG().toFacets())
    SaveScadFiles(File("")).addModel("test.scad", geom).saveScadFiles()
}

fun Cube.uncenter() = Translate(this, Coords3d(boundaries.size.x / 2, boundaries.size.y / 2, boundaries.size.z / 2))
fun Cube.atPos(pos: Vector3) = Translate(this, pos.toCoords3d())

fun cube(size: Vector3) = Cube(size.toDims3d())
fun cube(size: Int) = Cube(size.toDouble())

fun Vector3.toDims3d() = Dims3d(x.toDouble(), y.toDouble(), z.toDouble())
fun Vector3.toCoords3d() = Coords3d(x.toDouble(), y.toDouble(), z.toDouble())

fun Coords3d.asVector3() = Vector3(x.toFloat(), y.toFloat(), z.toFloat())