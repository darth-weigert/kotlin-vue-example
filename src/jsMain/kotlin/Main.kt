import dw.html.vue.createApp
import kotlinx.browser.window
import kotlin.js.json

@OptIn(ExperimentalJsExport::class)
@JsExport
fun main() {
    window.asDynamic()["app"] = createApp {
        data = {
            json("product" to "Socks",
                "brand" to "Vue Mastery",
                "selectedVariant" to 0,
                "onSale" to true,
                "details" to arrayOf("50% cotton", "30% wool", "20% polyester"),
                "variants" to arrayOf(
                    json("id" to 2234, "color" to "green", "image" to "./assets/images/socks_green.jpg", "quantity" to 50),
                    json("id" to 2235, "color" to "blue", "image" to "./assets/images/socks_blue.jpg", "quantity" to 0)
                ),
                "cart" to 0,
                "onSale" to true
            )
        }
        methods = json(
            "addToCart" to {
                js("this").cart += 1
            },
            "updateVariant" to { index: Int ->
                js("this").selectedVariant = index
                console.log(index)
            }
        )
        computed = json(
            "title" to {
                val self = js("this")
                "${self.brand} ${self.product}"
            },
            "image" to {
                val self = js("this")
                self.variants[self.selectedVariant].image
            },
            "inStock" to {
                val self = js("this")
                self.variants[self.selectedVariant].quantity
            },
            "sale" to {
                val self = js("this")
                if (self.onSale) {
                    "${self.brand} ${self.product} is on sale."
                } else {
                    ""
                }
            }
        )
    }
}
