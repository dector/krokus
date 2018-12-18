package krokus.v3.properties


fun Dimensions.allAreEquals(): Boolean = x == y && y == z

fun Vec3.allAreEquals(): Boolean = x == y && y == z // TODO: use mathematical name
fun Vec3.isNotZero(): Boolean = x != 0.0 && y != 0.0 && z != 0.0

fun Ang3.isNotZero(): Boolean = x != 0.0 && y != 0.0 && z != 0.0