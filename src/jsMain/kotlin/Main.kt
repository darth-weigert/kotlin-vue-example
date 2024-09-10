import dw.html.vue.createApp
import kotlinx.browser.window
import kotlin.js.json

@OptIn(ExperimentalJsExport::class)
@JsExport
fun main() {
    window.asDynamic()["app"] = createApp {
        data = {
            json("product" to "Socks",
                "description" to "A warm fuzzy pair of socks.")
        }
    }
}
