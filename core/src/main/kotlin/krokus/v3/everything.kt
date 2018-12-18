package krokus.v3


// Bodies

interface Body : Positionable, Rotatable, Colorable

interface CompositeBody : Body {

    var children: MutableList<Body>
}

interface UnionBody : CompositeBody

interface DifferenceBody : CompositeBody {

    var source: Body
}

interface IntersectionBody : CompositeBody

interface PrimitiveBody : Body

interface Cube : PrimitiveBody {

    var width: Double
    var depth: Double
    var height: Double
}

interface Sphere : PrimitiveBody {

    var radius: Double
}

interface Cylinder : PrimitiveBody {

    var radius: Double
    var height: Double
}

// Properties

interface Positionable {

    var position: Vec3
}

interface Rotatable {

    var rotation: Ang3
}

interface Colorable {

    var color: Color
}

// Math

interface Vec3 {
    var x: Double
    var y: Double
    var z: Double
}

interface Ang3 {
    var x: Double
    var y: Double
    var z: Double
}

interface Color {
    var r: Double
    var g: Double
    var b: Double

    //var a: Double
}