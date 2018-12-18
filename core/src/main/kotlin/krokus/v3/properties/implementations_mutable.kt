package krokus.v3.properties

import java.util.*

class ImmutableColor(
    override val r: ColorComponent = 0.0,
    override val g: ColorComponent = 0.0,
    override val b: ColorComponent = 0.0
) : Color {

    override fun equals(other: Any?) = (this === other) || equals(other as? Color)
    override fun hashCode() = Objects.hash(r, g, b)

    fun equals(other: Color?) = other != null &&
            ((r == other.r) && (g == other.g) && (b == other.b))
}

fun Color.mutate() = MutableColor(r = r, g = g, b = b)