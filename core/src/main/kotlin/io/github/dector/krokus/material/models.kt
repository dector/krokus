package io.github.dector.krokus.material


data class Material(val color: Color)

data class Color(val value: String) {

    companion object {
        val White = Color("#ffffff")
        val Black = Color("#000000")

        val Red = Color("#ff0000")
        val Green = Color("#00ff00")
        val Blue = Color("#0000ff")

        val Cardinal = Color("#c41e3a")
        val Azure = Color("#007fff")
    }
}