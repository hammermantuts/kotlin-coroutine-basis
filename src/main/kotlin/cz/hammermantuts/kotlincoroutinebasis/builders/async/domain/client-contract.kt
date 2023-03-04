package cz.hammermantuts.kotlincoroutinebasis.builders.async.domain

data class Person(val username: String, val fullName: String, val email: String)

data class Address(val country: String, val city: String, val zip: String, val street: String, val username: String)

data class Work(val job: String, val salary: Double, val position: String, val username: String)

data class Employee(val username: String, val person: Person, val address: Address, val work: Work)