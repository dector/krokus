//package io.github.dector.krokus.samples
//
//import io.github.dector.krokus.api.component.component
//import io.github.dector.krokus.api.geometry.centered
//import io.github.dector.krokus.api.geometry.cube
//import io.github.dector.krokus.api.geometry.sphere
//import io.github.dector.krokus.api.material.Color
//import io.github.dector.krokus.api.material.Material
//
//
//private const val FileName = "3material"
//
//fun main(args: Array<String>) {
//    exportComponent(FileName) {
//        component(name = "box", material = Material(Color.Cardinal)) {
//            cube(10).centered()
//        }
//    }
//
//    exportComponent(FileName) {
//        component(name = "sphere", material = Material(Color.Azure)) {
//            sphere(5)
//        }
//    }
//}