package io.github.magonxesp

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.equals.shouldBeEqual

class ExampleTest : DescribeSpec() {
	init {
		describe("greeting") {
			it("should return the greeting") {
				val greet = greeting("Kotlin library")

				greet shouldBeEqual "Hello Kotlin library!"
			}
		}
	}
}
