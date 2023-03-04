package cz.hammermantuts.kotlincoroutinebasis.builders.async

import cz.hammermantuts.kotlincoroutinebasis.builders.async.domain.Address
import cz.hammermantuts.kotlincoroutinebasis.builders.async.domain.Employee
import cz.hammermantuts.kotlincoroutinebasis.builders.async.domain.Person
import cz.hammermantuts.kotlincoroutinebasis.builders.async.domain.Work
import cz.hammermantuts.kotlincoroutinebasis.builders.async.service.CsvService
import cz.hammermantuts.kotlincoroutinebasis.builders.async.service.ExcelService
import cz.hammermantuts.kotlincoroutinebasis.builders.async.service.JsonService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File

fun main(): Unit = runBlocking {
    val coroutineScope = CoroutineScope(Dispatchers.IO)

    val csvService = CsvService()
    val excelService = ExcelService()
    val jsonService = JsonService()
    coroutineScope.launch {
        val csvDeferred = async { csvService.read(File("src/main/resources/csv/person.csv")) }
        val excelDeferred = async { excelService.read(File("src/main/resources/excel/addresses.xlsx")) }
        val jsonDeferred = async { jsonService.read(File("src/main/resources/json/work.json")) }

        processFiles(csvDeferred.await(), excelDeferred.await(), jsonDeferred.await()).forEach { println(it) }
    }
    delay(10000)
}

fun processFiles(persons: List<Person>, addresses: List<Address>, works: List<Work>): List<Employee> {
    val addressMap = addresses.associateBy { it.username }
    val workMap = works.associateBy { it.username }
    return persons.map {
        Employee(
            username = it.username,
            person = it,
            address = addressMap[it.username]!!,
            work = workMap[it.username]!!
        )
    }
}
