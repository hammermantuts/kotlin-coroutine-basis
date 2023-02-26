package cz.hammermantuts.kotlincoroutinebasis.builders.launch

import kotlinx.coroutines.runBlocking

fun main() {

    val customerService = CustomersService()
    runBlocking {
        customerService.processCustomers("src/main/resources/csv/customer.csv", "src/main/resources/csv/output.csv")
    }
}