package org.icpclive.config

import java.io.FileInputStream
import java.io.FileNotFoundException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

object Config {
    var configDirectory = "config"
    fun loadProperties(name: String) =
        loadPropertiesIfExists(name) ?: throw FileNotFoundException("$name.properties not found in $configDirectory")

    fun loadPropertiesIfExists(name: String): Properties? {
        val path = Paths.get(configDirectory, "$name.properties")
        if (!Files.exists(path)) return null
        val properties = Properties()
        properties.load(FileInputStream(path.toString()))
        return properties
    }

    fun loadFile(name: String) =
        String(Files.readAllBytes(Paths.get(configDirectory, name)), StandardCharsets.UTF_8)

    fun loadFileIfExists(name: String) =
        if (Files.exists(Paths.get(configDirectory, name)))
            loadFile(name)
        else
            null
}