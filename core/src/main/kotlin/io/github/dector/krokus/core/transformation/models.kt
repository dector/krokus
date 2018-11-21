package io.github.dector.krokus.core.transformation

import io.github.dector.krokus.core.properties.Property
import io.github.dector.krokus.core.space.Angle3
import io.github.dector.krokus.core.space.Plane
import io.github.dector.krokus.core.space.Vector3


interface Transformation

data class Translation(
    val position: Property<Vector3> = Property.from(Vector3())
) : Transformation

data class Rotation(
    val angle: Property<Angle3> = Property.from(Angle3())
) : Transformation

data class Mirroring(
    val plane: Property<Plane> = Property.from(Plane.None)
) : Transformation

//val Property<Translation>.isNotZero: Boolean get() =
//    ref.position.ref.isNotZero

//data class Transformations(
//    val translation: Translation = Translation(),
//    val rotation: Rotation = Rotation(),
//    val mirroring: Mirroring = Mirroring()
//) {
//
//    val hasTranslation = translation.position.ref.isNotZero
//    val hasRotation = rotation.angle.ref.isNotZero
//    val hasMirroring = mirroring.plane.ref != Plane.None
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