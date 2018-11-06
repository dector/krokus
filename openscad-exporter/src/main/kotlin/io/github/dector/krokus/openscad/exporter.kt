package io.github.dector.krokus.openscad

import io.github.dector.krokus.core.converter.GeometryConverter
import io.github.dector.krokus.core.geometry.*
import io.github.dector.krokus.core.operation.Difference
import io.github.dector.krokus.core.operation.Intersection
import io.github.dector.krokus.core.operation.Union
import io.github.dector.krokus.core.space.Vector3
import java.io.File


class OpenScadExporter {

    fun export(geometry: Geometry, file: File) {
        val source = OpenScadConverter().convert(geometry)

        file.bufferedWriter().apply {
            write(source)
            flush()
        }
    }
}

class OpenScadConverter : GeometryConverter<String> {

    override fun convert(geometry: Geometry): String = when (geometry) {
        is ShapeGeometry<*> -> {
            val shape = geometry.shape

            when (shape) {
                is Cube -> convertCube(shape)
                is Sphere -> convertSphere(shape)
                is Cylinder -> convertCylinder(shape)
                is Cone -> convertCone(shape)
                else -> throw notImplemented(shape)
            }
        }
        is Union -> convertUnion(geometry)
        is Difference -> convertDifference(geometry)
        is Intersection -> convertIntersection(geometry)
        else -> throw notImplemented(geometry)
    }

    private fun convertCube(cube: Cube) = buildString {
        append("cube(")
        append(cube.size.toString(canBeSimplified = true))

        when (cube.origin) {
            Cube.Origin.Corner -> {
                /* do nothing */
            }
            Cube.Origin.Center -> append(", center = true")
        }

        append(");")
    }

    private fun convertCylinder(cylinder: Cylinder) = buildString {
        append("cylinder(")
        append("h = ").append(cylinder.height)
        append(", r = ").append(cylinder.radius)

        when (cylinder.origin) {
            Cylinder.Origin.Bottom -> {
                /* do nothing */
            }
            Cylinder.Origin.Center -> append(", center = true")
        }

        append(");")
    }

    private fun convertCone(cone: Cone) = buildString {
        append("cylinder(")
        append("h = ").append(cone.height)
        append(", r1 = ").append(cone.radiusBottom)
        append(", r2 = ").append(cone.radiusTop)

        when (cone.origin) {
            Cone.Origin.Bottom -> {
                /* do nothing */
            }
            Cone.Origin.Center -> append(", center = true")
        }

        append(");")
    }

    private fun convertSphere(sphere: Sphere) = buildString {
        append("sphere(r = ")
        append(sphere.radius)
        append(");")
    }

    private fun convertUnion(union: Union) = buildString {
        append("union() {")
        union.children.joinAllTo(this)
        append("}")
    }

    private fun convertDifference(difference: Difference) = buildString {
        append("difference(){\n")
        append(convert(difference.source))

        difference.children.joinAllTo(this)

        append("}")
    }

    private fun convertIntersection(intersection: Intersection) = buildString {
        append("intersection() {")

        intersection.children.joinAllTo(this)

        append("}")
    }

    private fun List<Geometry>.joinAllTo(sb: StringBuilder) =
        this.joinTo(sb, separator = "\n", prefix = "\n", postfix = "\n", transform = ::convert)

    private fun Vector3.toString(canBeSimplified: Boolean = false) =
        if (canBeSimplified && allAreEqual) x.toString()
        else "[$x, $y, $z]"
}

fun main(args: Array<String>) {
    val export = object {

        private var counter = 0
        private val exporter = OpenScadExporter()

        operator fun invoke(vararg geometry: Geometry) {
            geometry.forEach(this::export)
        }

        fun export(geometry: Geometry) {
            val filename = "tmp/test${counter}.scad"
            val file = File(filename).also { it.parentFile.mkdirs() }

            exporter.export(
                geometry,
                file
            )

            // OpenScad export to image seems broken
            //render(file)

            counter++
        }

        fun render(src: File) {
            val out = File("tmp/out/${src.nameWithoutExtension}.png")
                .also { it.parentFile.mkdirs() }
                .absolutePath

            val p = ProcessBuilder(
                "openscad",
                "-m", "make",
                "-o", out,
                "--render", "--camera=100,100,100,0,0,0",
                "--colorscheme=Tomorrow Night",
                src.absolutePath
            ).run {
                start()
            }

            val result = p.waitFor()

            if (result != 0) {
                System.err.println(p.errorStream.reader().readText())
            }
        }
    }

    fun clip(vararg geometry: Geometry) {
        val scad = OpenScadConverter().convert(geometry.last())
        ProcessBuilder("sh", "-c", "echo '$scad' | xclip -selection clipboard")
            .start().waitFor()
    }

//    export(
    clip(
        cube(10),
        cube(10).cornerOrigin(),
        cylinder(10, 5),
        cylinder(10, 5).bottomOrigin(),
        sphere(10),
        cube(10) + sphere(7),
        cube(10).cornerOrigin() + sphere(7),
        cube(10) - sphere(7),
        cube(10).cornerOrigin() - sphere(7),
        cube(10) * sphere(7),
        cube(10).cornerOrigin() * sphere(7),
        cone(10, 10, 5),
        cone(10, 10, 5).bottomOriginCone(),
        cube(8) - (cube(10) + sphere(7))
    )
}