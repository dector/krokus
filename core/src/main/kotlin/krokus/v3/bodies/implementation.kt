package krokus.v3.bodies

import krokus.v3.properties.*

data class MutableCube(
    override var size: Dimensions = MutableDimensions(),

    override var position: Vec3 = MutableVec3(),
    override var rotation: Ang3 = MutableAng3(),
    override var color: Color = Color.Undefined,

    override var origin: Cube.Origin = Cube.Origin.Center,

    override var highlighted: Boolean = false
) : Cube

data class MutableSphere(
    override var radius: Distance = 1.0,

    override var position: Vec3 = MutableVec3(),
    override var rotation: Ang3 = MutableAng3(),
    override var color: Color = Color.Undefined,

    override var highlighted: Boolean = false
) : Sphere

data class MutableCylinder(
    override var radius: Distance = 1.0,
    override var height: Distance = 1.0,

    override var position: Vec3 = MutableVec3(),
    override var rotation: Ang3 = MutableAng3(),
    override var color: Color = Color.Undefined,

    override var origin: Cylinder.Origin = Cylinder.Origin.Center,

    override var highlighted: Boolean = false
) : Cylinder