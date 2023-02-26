package cz.hammermantuts.kotlincoroutinebasis.builders.launch

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.FileReader

class CsvService {

    suspend fun readCustomersFromCsv(csvFile: String): List<Customer> = withContext(Dispatchers.IO) {
        val customers = mutableListOf<Customer>()
        FileReader(csvFile).readLines().forEach { line ->
                val fields = line.split(",")
                val customer = Customer(
                    firstName = fields[0], lastName = fields[1], email = fields[2], phone = fields[3]
                )
                customers.add(customer)
            }
        customers
    }
}