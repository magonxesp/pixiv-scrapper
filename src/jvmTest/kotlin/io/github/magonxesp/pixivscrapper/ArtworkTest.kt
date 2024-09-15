package io.github.magonxesp.pixivscrapper

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class ArtworkTest : DescribeSpec() {
	init {
	    describe("ArtworkPicture.fromFile") {
			it("should read a valid pixiv image file") {
				val expected = ArtworkPicture(
					artworkId = "63519881",
					fileName = "63519881_p0.png",
					number = 0
				)

				val picture = ArtworkPicture.fromFile("examples/63519881_p0.png")

				picture shouldBe expected
			}
			it("should not read a invalid pixiv image file") {
				shouldThrow<InvalidFileNameException> {
					ArtworkPicture.fromFile("examples/invalid_picture.png")
				}
			}
		}
	}
}
