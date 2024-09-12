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
        "removeById" to { id: Int ->
            val self = js("this")
            val index = self.cart.indexOf(id)
            if (index > -1) {
                self.cart.splice(index, 1)
            }
        }
    )
}

fun main() {
    productDisplay()
    window.asDynamic()["app"] = app
}
