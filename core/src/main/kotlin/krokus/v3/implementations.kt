package krokus.v3


data class CubeImpl(
    override var width: Distance = 1.0,
    override var depth: Distance = 1.0,
    override var height: Distance = 1.0,

    override var position: Vec3 = Vector(),
    override var rotation: Ang3 = Angles(),
    override var color: Color = ColorImpl()
) : Cube

data class SphereImpl(
    override var radius: Distance = 1.0,

    override var position: Vec3 = Vector(),
    override var rotation: Ang3 = Angles(),
    override var color: Color = ColorImpl()
) : Sphere

data class CylinderImpl(
    override var radius: Distance = 1.0,
    override var height: Distance = 1.0,

    override var position: Vec3 = Vector(),
    override var rotation: Ang3 = Angles(),
    override var color: Color = ColorImpl()
) : Cylinder

data class Vector(
    override var x: Coordinate = 0.0,
    override var y: Coordinate = 0.0,
    override var z: Coordinate = 0.0
) : Vec3

data class Angles(
    override var x: Angle = 0.0,
    override var y: Angle = 0.0,
    override var z: Angle = 0.0
) : Ang3

data class ColorImpl(
    override var r: ColorComponent = 0.0,
    override var g: ColorComponent = 0.0,
    override var b: ColorComponent = 0.0
) : Color