package io.github.magonxesp.pixivscrapper

import kotlinx.serialization.Serializable
import java.io.File

@Serializable
data class Artwork(
	val id: String,
	val url: String,
	val tags: List<IllustrationTag>,
	val imageUrls: IllustrationUrls
) {
	companion object {
		fun fromFile(path: String) {
			val picture = ArtworkPicture.fromFile(path)
			val url = "https://www.pixiv.net/en/artworks/${picture.artworkId}"


		}
	}
}

@Serializable
data class ArtworkPicture(
	val artworkId: String,
	val number: Int,
	val fileName: String
) {
	companion object {
		fun fromFile(path: String): ArtworkPicture {
			val regex = Regex("^([0-9]+)_p([0-9]+)\\.[a-z]+\$")
			val file = File(path)

			if (!regex.matches(file.name)) {
				throw InvalidFileNameException.forFile(file)
			}

			val groups = regex.findAll(file.name).first().groupValues

			return ArtworkPicture(
				artworkId = groups[1],
				number = groups[2].toInt(),
				fileName = file.name
			)
		}
	}
}
