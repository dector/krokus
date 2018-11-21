package io.github.dector.krokus.core.transformation

import io.github.dector.krokus.core.properties.Property
import io.github.dector.krokus.core.properties.asScalar
import io.github.dector.krokus.core.space.Angle3
import io.github.dector.krokus.core.space.Plane
import io.github.dector.krokus.core.space.Vector3
import io.github.dector.krokus.core.space.isNotZero


interface Transformation

data class Translation(var position: Property<Vector3> = Vector3().asScalar()) : Transformation
data class Rotation(var angle: Property<Angle3> = Angle3().asScalar()) : Transformation
data class Mirroring(var plane: Property<Plane> = Plane.None.asScalar()) : Transformation

val Property<Translation>.isNotZero: Boolean get() =
    value.position.value.isNotZero

//data class Transformations(
//    val translation: Translation = Translation(),
//    val rotation: Rotation = Rotation(),
//    val mirroring: Mirroring = Mirroring()
//) {
//
//    val hasTranslation = translation.position.value.isNotZero
//    val hasRotation = rotation.angle.value.isNotZero
//    val hasMirroring = mirroring.plane.value != Plane.None
//
////    infix fun merge(transformation: Transformation) = when (transformation) {
////        is Translation -> copy(translation = applyTranslation(translation))
////        is Rotation -> copy(rotation = applyRotation(transformation))
////        is Mirroring -> copy(mirroring = applyMirroring(transformation))
////        else -> throw NotImplementedError("Unknown transformation: $transformation")
////    }
//
//    infix fun update(transformation: Transformation) = when (transformation) {
//        is Translation -> copy(translation = transformation)
//        is Rotation -> copy(rotation = transformation)
//        is Mirroring -> copy(mirroring = transformation)
//        else -> throw NotImplementedError("Unknown transformation: $transformation")
//    }
//
//    /*private fun applyTranslation(another: Translation) =
//        if (hasTranslation) translation.copy(position = translation.position + another.position)
//        else another
//
//    private fun applyRotation(another: Rotation) =
//        if (hasRotation) rotation.copy(angle = rotation.angle + another.angle)
//        else another
//
//    private fun applyMirroring(another: Mirroring) = another*/
//}