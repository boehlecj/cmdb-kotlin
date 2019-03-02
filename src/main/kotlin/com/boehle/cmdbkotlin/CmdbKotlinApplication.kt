package com.boehle.cmdbkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CmdbKotlinApplication

fun main(args: Array<String>) {
	runApplication<CmdbKotlinApplication>(*args)
}
