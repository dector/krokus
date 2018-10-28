package io.github.dector.krokus.javascad

import eu.printingin3d.javascad.models.Abstract3dModel
import eu.printingin3d.javascad.utils.SaveScadFiles
import java.io.File


class JavaScadExporter {

    fun exportToScad(model: Abstract3dModel, filename: String): Boolean {
        val file = File(filename)

        try {
            SaveScadFiles(file.absoluteFile.parentFile)
                .addModel(file.name, model)
                .saveScadFiles()
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }

        return true
    }
}