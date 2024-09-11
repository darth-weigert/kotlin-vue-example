import dw.html.vue.buildTemplate
import dw.html.vue.component
import dw.html.vue.vueBind
import dw.html.vue.vueElse
import dw.html.vue.vueFor
import dw.html.vue.vueIf
import dw.html.vue.vueOn
import kotlinx.html.button
import kotlinx.html.div
import kotlinx.html.h1
import kotlinx.html.img
import kotlinx.html.p
import kotlin.js.json

fun productDisplay() {
    app.component("product-display") {
        props = json(
            "premium" to json(
                "type" to Boolean::class.js,
                "required" to true
            )
        )
        template = buildTemplate {
            div(classes = "product-display") {
                div(classes = "product-container") {
                    div(classes = "product-image") {
                        img {
                            vueBind["src"] = "image"
                        }
                    }
                    div(classes = "product-info") {
                        h1 { +"{{ title }}" }
                        p {
                            vueIf("inStock")
                            +"In Stock"
                        }
                        p {
                            vueElse()
                            +"Out of Stock"
                        }
                        p {
                            +"Shipping: {{ shipping }}"
                        }
                        productDetails {
                            vueBind["details"] = "details"
                        }
                        div(classes = "color-circle") {
                            vueFor("(variant, index) in variants")
                            vueBind["key"] = "variant.id"
                            vueOn.mouse.over("updateVariant(index)")
                            vueBind["style"] = "{ 'background-color': variant.color }"
                        }
                        button(classes = "button") {
                            vueBind["class"] = "{ 'disabled-button': !inStock }"
                            vueBind["disabled"] = "!inStock"
                            vueOn.click("addToCart")
                            +"Add to Cart"
                        }
                    }
                }
            }
        }
        data = {
            json(
                "product" to "Socks",
                "brand" to "Vue Mastery",
                "selectedVariant" to 0,
                "onSale" to true,
                "details" to arrayOf("50% cotton", "30% wool", "20% polyester"),
                "variants" to arrayOf(
                    json(
                        "id" to 2234,
                        "color" to "green",
                        "image" to "./assets/images/socks_green.jpg",
                        "quantity" to 50
                    ),
                    json("id" to 2235, "color" to "blue", "image" to "./assets/images/socks_blue.jpg", "quantity" to 0)
                ),
            )
        }
        methods = json(
            "addToCart" to {
                js("this").cart += 1
            },
            "updateVariant" to { index: Int ->
                js("this").selectedVariant = index
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
            "shipping" to {
                val self = js("this")
                if (self.premium) {
                    "Free"
                } else {
                    2.99
                }
            }
        )
    }
}
