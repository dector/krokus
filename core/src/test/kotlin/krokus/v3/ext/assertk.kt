package krokus.v3.ext

import assertk.Assert
import assertk.assertions.support.fail
import krokus.v3.*


infix fun Assert<Double>.isEqualToInt(expected: Int) {
    if (actual == expected.toDouble()) return
    fail(expected, actual)
}

infix fun Assert<Cube>.hasSize(expected: Number) = hasSize(expected.toDouble())
infix fun Assert<Cube>.hasSize(expected: Double) = hasSize(MutableDimensions(expected, expected, expected))
infix fun Assert<Cube>.hasSize(expected: Dimensions) =
    test(expected, actual.size, ::areEquals)

infix fun Assert<Cube>.hasWidth(expected: Number) = hasWidth(expected.toDouble())
infix fun Assert<Cube>.hasWidth(expected: Double) =
    test(expected, actual.width, ::areEquals)

infix fun Assert<Cube>.hasHeight(expected: Number) = hasHeight(expected.toDouble())
infix fun Assert<Cube>.hasHeight(expected: Double) =
    test(expected, actual.height, ::areEquals)

infix fun Assert<Cube>.hasDepth(expected: Number) = hasDepth(expected.toDouble())
infix fun Assert<Cube>.hasDepth(expected: Double) =
    test(expected, actual.depth, ::areEquals)

infix fun Assert<Cube>.hasPositionX(expected: Number) = hasPositionX(expected.toDouble())
infix fun Assert<Cube>.hasPositionX(expected: Double) =
    test(expected, actual.position.x, ::areEquals)

infix fun Assert<Cube>.hasPositionY(expected: Number) = hasPositionY(expected.toDouble())
infix fun Assert<Cube>.hasPositionY(expected: Double) =
    test(expected, actual.position.y, ::areEquals)

infix fun Assert<Cube>.hasPositionZ(expected: Number) = hasPositionZ(expected.toDouble())
infix fun Assert<Cube>.hasPositionZ(expected: Double) =
    test(expected, actual.position.z, ::areEquals)

infix fun Assert<Cube>.hasRotationX(expected: Number) = hasRotationX(expected.toDouble())
infix fun Assert<Cube>.hasRotationX(expected: Double) =
    test(expected, actual.rotation.x, ::areEquals)

infix fun Assert<Cube>.hasRotationY(expected: Number) = hasRotationY(expected.toDouble())
infix fun Assert<Cube>.hasRotationY(expected: Double) =
    test(expected, actual.rotation.y, ::areEquals)

infix fun Assert<Cube>.hasRotationZ(expected: Number) = hasRotationZ(expected.toDouble())
infix fun Assert<Cube>.hasRotationZ(expected: Double) =
    test(expected, actual.rotation.z, ::areEquals)

infix fun Assert<Cube>.hasColor(expected: Color) =
    test(expected, actual.color, ::areEquals)

infix fun Assert<Color>.hasRedComponent(expected: ColorComponent) = test(expected, actual.r, ::areEquals)
infix fun Assert<Color>.hasGreenComponent(expected: ColorComponent) = test(expected, actual.g, ::areEquals)
infix fun Assert<Color>.hasBlueComponent(expected: ColorComponent) = test(expected, actual.b, ::areEquals)

infix fun Assert<Cube>.hasOrigin(expected: Cube.Origin) =
    test(expected, actual.origin, ::areEquals)

// Support

private fun <T> Assert<T>.test(expected: T, actual: T, predicate: (T, T) -> Boolean) {
    if (!predicate(expected, actual)) {
        fail(expected, actual)
    }
}

private inline fun <reified T> areEquals(expected: T, actual: T) = expected == actual