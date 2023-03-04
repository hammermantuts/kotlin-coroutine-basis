package cz.hammermantuts.kotlincoroutinebasis.builders.async.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import cz.hammermantuts.kotlincoroutinebasis.builders.async.domain.Work
import java.io.File

class JsonService : FileService<Work> {
    override fun read(file: File): List<Work> {
        val mapper = jacksonObjectMapper()
        val json = file.readText()
        return mapper.readValue(json)
    }
}