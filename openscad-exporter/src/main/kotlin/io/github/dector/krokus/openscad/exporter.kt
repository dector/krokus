package io.github.dector.krokus.openscad

import io.github.dector.krokus.core.converter.GeometryConverter
import io.github.dector.krokus.core.geometry.Cube
import io.github.dector.krokus.core.geometry.Geometry
import io.github.dector.krokus.core.geometry.ShapeGeometry
import io.github.dector.krokus.core.space.Vector3
import io.github.dector.krokus.core.space.v
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

    override fun convert(geometry: Geometry) = when (geometry) {
        is ShapeGeometry<*> -> {
            val shape = geometry.shape

            when (shape) {
                is Cube -> convert(shape)
                else -> throw notImplemented(shape)
            }
        }
        else -> throw notImplemented(geometry)
    }

    private fun convert(cube: Cube) = buildString {
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

    export(
        ShapeGeometry(Cube(size = v(10), origin = Cube.Origin.Center)),
        ShapeGeometry(Cube(size = v(10), origin = Cube.Origin.Corner)),
        ShapeGeometry(Cube(size = v(10)))
    )
}