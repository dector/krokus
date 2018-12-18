package krokus.v3.openscad

import assertk.assert
import assertk.assertions.isEqualTo
import krokus.v3.bodies.MutableCube
import krokus.v3.bodies.MutableCylinder
import krokus.v3.bodies.MutableDifference
import krokus.v3.bodies.MutableSphere
import krokus.v3.properties.MutableAng3
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class OpenScadBodyConverterTest {

    lateinit var converter: OpenScadBodyConverter

    @BeforeEach
    fun setUp() {
        converter = OpenScadBodyConverter(OpenScadTokenizer())
    }

    @Test
    fun rotation() {
        val cube = MutableCube().apply {
            rotation = MutableAng3(x = 90.0)
        }

        val result = converter.convert(cube)

        assert(result).isEqualTo("rotate([90.0, 0.0, 0.0]) cube(1.0, center = true);")
    }

    @Test
    fun highlight() {
        val cube = MutableCube().apply {
            highlighted = true
        }

        val result = converter.convert(cube)

        assert(result).isEqualTo("#cube(1.0, center = true);")
    }

    @Test
    fun openScadLogo() {
        val body = run {
            val size = 50.0
            val hole = size / 2
            val cylinderHeight = size * 1.25

            MutableDifference(
                source = MutableSphere(radius = size / 2),
                children = listOf(
                    MutableCylinder(radius = hole / 2, height = cylinderHeight),
                    MutableCylinder(radius = hole / 2, height = cylinderHeight).apply {
                        rotation = MutableAng3(x = 90.0)
                        highlighted = true
                    },
                    MutableCylinder(radius = hole / 2, height = cylinderHeight).apply {
                        rotation = MutableAng3(y = 90.0)
                    }
                )
            )
        }
        val result = converter.convert(body)

        val expected = """
        difference() {
        sphere(r = 25.0);
        cylinder(r = 12.5, h = 62.5, center = true);
        #rotate([90.0, 0.0, 0.0]) cylinder(r = 12.5, h = 62.5, center = true);
        rotate([0.0, 90.0, 0.0]) cylinder(r = 12.5, h = 62.5, center = true);
        }
        """.trimIndent()

        assert(result).isEqualTo(expected)
    }
}