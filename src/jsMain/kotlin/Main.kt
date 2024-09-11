import dw.html.vue.createApp
import kotlinx.browser.window
import kotlin.js.json

val app = createApp {
    data = {
        json(
            "cart" to 0,
            "premium" to false
        )
    }
    methods = json()
}

fun main() {
    productDisplay()
    productDetails()
    window.asDynamic()["app"] = app
}
