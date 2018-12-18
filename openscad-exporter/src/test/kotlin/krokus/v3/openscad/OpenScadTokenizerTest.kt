package krokus.v3.openscad

import assertk.assert
import assertk.assertions.isEqualTo
import krokus.v3.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class OpenScadTokenizerTest {

    lateinit var tokenizer: OpenScadTokenizer

    @BeforeEach
    fun setUp() {
        tokenizer = OpenScadTokenizer()
    }

    @Test
    fun defaultCube() {
        val cube = MutableCube()

        val result = tokenizer.shape(cube)

        assert(result).isEqualTo(
            """
            |cube(1.0, center = true);""".trimMargin()
        )
    }

    @Test
    fun cubeWithCornerOrigin() {
        val cube = MutableCube().apply {
            origin = Cube.Origin.Corner
        }

        val result = tokenizer.shape(cube)

        assert(result).isEqualTo(
            """
            |cube(1.0);""".trimMargin()
        )
    }

    @Test
    fun defaultSphere() {
        val sphere = MutableSphere()

        val result = tokenizer.shape(sphere)

        assert(result).isEqualTo(
            """
            |sphere(r = 1.0);""".trimMargin()
        )
    }

    @Test
    fun defaultCylinder() {
        val cylinder = MutableCylinder()

        val result = tokenizer.shape(cylinder)

        assert(result).isEqualTo(
            """
            |cylinder(r = 1.0, h = 1.0, center = true);""".trimMargin()
        )
    }

    @Test
    fun cylinderWithBottomOrigin() {
        val cylinder = MutableCylinder().apply {
            origin = Cylinder.Origin.Bottom
        }

        val result = tokenizer.shape(cylinder)

        assert(result).isEqualTo(
            """
            |cylinder(r = 1.0, h = 1.0);""".trimMargin()
        )
    }
}