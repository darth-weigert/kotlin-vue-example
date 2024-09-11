import dw.html.vue.createApp
import kotlinx.browser.window
import kotlin.js.json

@OptIn(ExperimentalJsExport::class)
@JsExport
fun main() {
    window.asDynamic()["app"] = createApp {
        data = {
            json("product" to "Socks",
                "image" to "./assets/images/socks_green.jpg",
                "inStock" to false,
                "onSale" to true,
                "details" to arrayOf("50% cotton", "30% wool", "20% polyester"),
                "variants" to arrayOf(
                    json("id" to 2234, "color" to "green", "image" to "./assets/images/socks_green.jpg"),
                    json("id" to 2235, "color" to "blue", "image" to "./assets/images/socks_blue.jpg")
                ),
                "cart" to 0
            )
        }
        methods = json(
            "addToCart" to {
                js("this").cart += 1
            },
            "updateImage" to { variantImage: String ->
                js("this").image = variantImage
            }
        )
    }
}
