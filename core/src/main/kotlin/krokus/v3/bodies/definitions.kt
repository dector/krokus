package krokus.v3.bodies

import krokus.v3.properties.*


interface Body : Positionable, Rotatable, Colorable

interface CompositeBody : Body {

    val children: List<Body>
}

interface UnionBody : CompositeBody
interface DifferenceBody : CompositeBody {

    val source: Body
}

interface IntersectionBody : CompositeBody

interface PrimitiveBody : Body
interface Cube : PrimitiveBody {

    val size: Dimensions

    val width: Distance get() = size.x
    val depth: Distance get() = size.y
    val height: Distance get() = size.z

    val origin: Origin

    enum class Origin {
        Center, Corner
    }
}

interface Sphere : PrimitiveBody {

    val radius: Distance
}

interface Cylinder : PrimitiveBody {

    val radius: Distance
    val height: Distance

    val origin: Origin

    enum class Origin {
        Center, Bottom
    }
}