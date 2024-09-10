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
                "url" to "https://www.vuemastery.com/")
        }
    }
}
