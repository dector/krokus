package krokus.v3.bodies

import krokus.v3.properties.*


data class MutableUnion(
    override var children: List<Body> = emptyList(),
    override var position: Vec3 = MutableVec3(),
    override var rotation: Ang3 = MutableAng3(),
    override var color: Color = Color.Undefined
) : UnionBody

data class MutableDifference(
    override var source: Body,
    override var children: List<Body> = emptyList(),
    override var position: Vec3 = MutableVec3(),
    override var rotation: Ang3 = MutableAng3(),
    override var color: Color = Color.Undefined
) : DifferenceBody

data class MutableIntersection(
    override var children: List<Body> = emptyList(),
    override var position: Vec3 = MutableVec3(),
    override var rotation: Ang3 = MutableAng3(),
    override var color: Color = Color.Undefined
) : IntersectionBody