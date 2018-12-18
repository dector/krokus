package krokus.v3


data class MutableCube(
    override var size: Dimensions = MutableDimensions(),

    override var position: Vec3 = MutableVector(),
    override var rotation: Ang3 = MutableAngles(),
    override var color: Color = Color.Undefined,

    override var origin: Cube.Origin = Cube.Origin.Center
) : Cube

data class MutableSphere(
    override var radius: Distance = 1.0,

    override var position: Vec3 = MutableVector(),
    override var rotation: Ang3 = MutableAngles(),
    override var color: Color = Color.Undefined
) : Sphere

data class MutableCylinder(
    override var radius: Distance = 1.0,
    override var height: Distance = 1.0,

    override var position: Vec3 = MutableVector(),
    override var rotation: Ang3 = MutableAngles(),
    override var color: Color = Color.Undefined,

    override var origin: Cylinder.Origin = Cylinder.Origin.Center
) : Cylinder

data class MutableDimensions(
    override var x: Distance = 1.0,
    override var y: Distance = 1.0,
    override var z: Distance = 1.0
) : Dimensions

data class MutableVector(
    override var x: Coordinate = 0.0,
    override var y: Coordinate = 0.0,
    override var z: Coordinate = 0.0
) : Vec3

data class MutableAngles(
    override var x: Angle = 0.0,
    override var y: Angle = 0.0,
    override var z: Angle = 0.0
) : Ang3

data class MutableColor(
    override var r: ColorComponent = 0.0,
    override var g: ColorComponent = 0.0,
    override var b: ColorComponent = 0.0
) : Color