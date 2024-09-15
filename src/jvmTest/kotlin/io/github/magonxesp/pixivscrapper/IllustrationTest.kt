package io.github.magonxesp.pixivscrapper

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.maps.beEmpty
import io.kotest.matchers.shouldNot

class IllustrationTest : DescribeSpec() {
	init {
	    describe("Illustrations.readFromUrl") {
			it("should throw an exception if url is invalid") {
				shouldThrow<InvalidUrlException> {
					Illustrations.readFromUrl("https://example.com")
				}
			}
			it("should return illustrations collection") {
				val illustrations = Illustrations.readFromUrl("https://www.pixiv.net/en/artworks/110177583")

				illustrations.illust shouldNot beEmpty()
			}
		}
	}
}
