package io.github.magonxesp.pixivscrapper

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class UrlUtilsTest : DescribeSpec() {
	init {
	    describe("isPixivUrl") {
			it("should return false if it is not valid") {
				val result = isPixivUrl("https://example.com")

				result shouldBe false
			}
			it("should return true if it is valid") {
				val result = isPixivUrl("https://www.pixiv.net/en/artworks/110177583")

				result shouldBe true
			}
		}
	}
}
