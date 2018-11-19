package io.github.dector.krokus.core.material


data class Material(val color: Color)

data class Color(val value: String) {

    companion object {
        val White = Color("#ffffff")
        val Black = Color("#000000")

        val Red = Color("#ff0000")
        val Green = Color("#00ff00")
        val Blue = Color("#0000ff")

        val Cardinal = Color("#c41e3a")
        val Erin = Color("#00ff40")
        val Azure = Color("#007fff")
        val Amber = Color("#ffbf00")
    }

    // TODO check validity

    /** In range [0..255] */
    val red: Int
        get() = value.substring(1..2).toInt(16)

    /** In range [0..255] */
    val green: Int
        get() = value.substring(3..4).toInt(16)

    /** In range [0..255] */
    val blue: Int
        get() = value.substring(5..6).toInt(16)

    /**
     * In range [0.0...1.0]
     */
    val redComponent: Double
        get() = colorToDouble(red)

    /**
     * In range [0.0...1.0]
     */
    val greenComponent: Double
        get() = colorToDouble(green)

    /**
     * In range [0.0...1.0]
     */
    val blueComponent: Double
        get() = colorToDouble(blue)
}

private fun colorToDouble(value: Int, precision: Int = 2) =
    (value / 255.0 * pow(10, precision)).toInt() / pow(10, precision).toDouble()

private fun pow(x: Int, y: Int) = Math.pow(x.toDouble(), y.toDouble()).toInt()