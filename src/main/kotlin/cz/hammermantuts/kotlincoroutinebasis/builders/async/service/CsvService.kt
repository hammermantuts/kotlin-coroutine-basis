package cz.hammermantuts.kotlincoroutinebasis.builders.async.service

import cz.hammermantuts.kotlincoroutinebasis.builders.async.domain.Person
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.File
import java.io.FileReader

class CsvService : FileService<Person> {
    override fun read(file: File): List<Person> {
        val reader = FileReader(file)
        val parser = CSVParser(reader, CSVFormat.DEFAULT.withHeader())

        val persons = mutableListOf<Person>()
        for (record in parser.records) {
            val username = record["username"]
            val fullName = record["fullName"]
            val email = record["email"]

            val person = Person(username, fullName, email)
            persons.add(person)
        }

        parser.close()
        reader.close()

        return persons
    }
}