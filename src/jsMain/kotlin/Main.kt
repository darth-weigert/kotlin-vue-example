import dw.html.vue.createApp
import kotlinx.browser.window
import kotlin.js.json

val app = createApp {
    data = {
        json(
            "cart" to emptyArray<Int>(),
            "premium" to true
        )
    }
    methods = json(
        "updateCart" to { id: Int ->
            js("this").cart.push(id)
        },
    )
}

fun main() {
    productDisplay()
    reviewForm()
    reviewList()
    window.asDynamic()["app"] = app
}
