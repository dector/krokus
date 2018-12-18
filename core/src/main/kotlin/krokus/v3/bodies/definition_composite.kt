package krokus.v3.bodies

interface CompositeBody : Body {

    val children: List<Body>
}

interface UnionBody : CompositeBody
interface DifferenceBody : CompositeBody {

    val source: Body
}

interface IntersectionBody : CompositeBody