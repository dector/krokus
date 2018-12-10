package krokus.core.geometry

import krokus.core.geometry.shape.*
import krokus.core.properties.Property
import krokus.core.space.Vector3
import krokus.core.space.v

fun cube(size: Vector3) = ShapeGeometry(
    shape = Property.from(Cube(size = size))
)

fun cube(sx: Number = 0, sy: Number = 0, sz: Number = 0) = cube(v(sx, sy, sz))
fun cube(size: Number) = cube(size, size, size)

fun sphere(radius: Double) = ShapeGeometry(
    shape = Property.from(Sphere(radius = radius))
)

fun sphere(radius: Number) = sphere(radius.toDouble())

fun cylinder(height: Double, radius: Double) = ShapeGeometry(
    shape = Property.from(Cylinder(height, radius))
)

fun cylinder(height: Number, radius: Number) = cylinder(height.toDouble(), radius.toDouble())
fun cylinder(heightAndRadius: Number) = cylinder(heightAndRadius, heightAndRadius)

fun cone(height: Number, radiusBottom: Number, radiusTop: Number = radiusBottom) =
    ShapeGeometry(
        shape = Property.from(
            Cone(
                height = height.toDouble(),
                radiusBottom = radiusBottom.toDouble(),
                radiusTop = radiusTop.toDouble()
            )
        )
    )

fun prism(height: Number, radius: Number, vertices: Int = 3) =
    ShapeGeometry(
        shape = Property.from(
            Prism(
                height = height.toDouble(),
                radius = radius.toDouble(),
                vertices = vertices
            )
        )
    )

// Builders

fun cube(initializer: ShapeGeometry<Cube>.() -> Unit) =
    cube().apply(initializer)