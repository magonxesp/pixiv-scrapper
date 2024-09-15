package io.github.magonxesp.pixivscrapper

import it.skrape.core.htmlDocument
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.response
import it.skrape.fetcher.skrape
import kotlinx.serialization.Serializable

@Serializable
data class IllustrationUrls(
	val mini: String,
	val thumb: String,
	val small: String,
	val regular: String,
	val original: String
)

@Serializable
data class IllustrationTag(
	val tag: String,
	val romaji: String,
	val translation: Map<String, String>
)

@Serializable
data class IllustrationTags(
	val tags: List<IllustrationTag>
)

@Serializable
data class Illustration(
	val illustId: String,
	val illustTitle: String,
	val urls: IllustrationUrls,
	val tags: IllustrationTags
)

@Serializable
data class Illustrations(
	val illust: Map<String, Illustration>
) {
	companion object {
		fun readFromUrl(url: String): Illustrations {
			if (!isPixivUrl(url)) {
				throw InvalidUrlException.forUrl(url)
			}

			return skrape(HttpFetcher) {
				request {
					this.url = url
					headers = mapOf(
						"Accept-Language" to "en-US,en;q=0.5",
						"Accept" to "text/html"
					)
				}

				response {
					htmlDocument {
						findFirst("meta[name=\"preload-data\"]") {
							jsonEncoder.decodeFromString<Illustrations>(
								attributes["content"] ?: throw ContentNotFoundException.forUrl(url)
							)
						}
					}
				}
			}
		}
	}
}
