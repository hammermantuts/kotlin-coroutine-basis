package cz.hammermantuts.kotlincoroutinebasis.builders.launch

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class CustomersService {
    suspend fun processCustomers(csvFile: String, outputCsvFile: String) {
        val csvService = CsvService()
        val customers = csvService.readCustomersFromCsv(csvFile)
        val processedCustomers = mutableListOf<Customer>()
        coroutineScope {
            customers.map { customer ->
                launch {
                    val processedCustomer = processCustomer(customer)
                    processedCustomers.add(processedCustomer)
                }
            }
        }

        coroutineScope {
            customers.map {
                launch {
                    sendToConsole(it)
                }
            }
        }

        File(outputCsvFile).bufferedWriter().use { writer ->
            writer.write("First Name,Last Name,Email,Phone Number\n")
            processedCustomers.forEach { customer ->
                writer.write("${customer.firstName},${customer.lastName},${customer.email},${customer.phone}\n")
            }
        }
    }

    private suspend fun sendToConsole(it: Customer) = withContext(Dispatchers.Default) {
        if (it.firstName == "Mason") {
            println(it)
        }
    }

    private suspend fun processCustomer(customer: Customer): Customer = withContext(Dispatchers.Default) {
        // perform some expensive processing on the customer object
        customer.copy(email = customer.email.lowercase(), lastName = customer.lastName.uppercase())
    }
}