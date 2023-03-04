package cz.hammermantuts.kotlincoroutinebasis.builders.async.service

import java.io.File

interface FileService<T> {
    fun read(file: File): List<T>
}