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
                "inventory" to 8,
                "onSale" to true,
                "details" to arrayOf("50% cotton", "30% wool", "20% polyester"),
                "variants" to arrayOf(
                    json("id" to 2234, "color" to "green"),
                    json("id" to 2235, "color" to "blue")
                ),
                "sizes" to arrayOf("S", "M", "L", "XL")
            )
        }
    }
}
