package krokus.v3.properties

import java.util.*

data class ImmutableColor(
    override val r: ColorComponent = 0.0,
    override val g: ColorComponent = 0.0,
    override val b: ColorComponent = 0.0
) : Color {

    override fun equals(other: Any?) = (this === other) || equalTo(other as? Color)
    override fun hashCode() = countHashCode()
    override fun toString() = hexValue()
}

fun Color.mutate() = MutableColor(r = r, g = g, b = b)

fun Color.equalTo(other: Color?) = (other != null) &&
        ((r == other.r) && (g == other.g) && (b == other.b))

fun Color.countHashCode() = Objects.hash(r, g, b)

fun Color.hexValue() = run {
    fun componentHex(v: Double) = (v * 255).toInt().toString(16)

    "#${componentHex(r)}${componentHex(g)}${componentHex(b)}"
}