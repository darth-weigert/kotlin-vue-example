import attributes.attributeStringString
import dw.html.vue.buildTemplate
import dw.html.vue.component
import dw.html.vue.vueBind
import dw.html.vue.vueFor
import kotlinx.html.*
import kotlin.js.json

fun productDetails() {
    app.component("product-details") {
        props = json(
            "details" to json(
                "type" to Array::class.js,
                "required" to true
            )
        )
        template = buildTemplate {
            ul {
                li {
                    vueFor("detail in details")
                    vueBind["key"] = "detail"
                    +"{{ detail }}"
                }
            }
        }
    }
}

class ProductDetails(consumer: TagConsumer<*>) : HTMLTag("product-details", consumer, emptyMap(), inlineTag=true, emptyTag = false), HtmlInlineTag {
    var details: String
        get() = attributeStringString[this, "details"]
        set(newValue) {
            attributeStringString[this, "details"] = newValue
        }
}

fun HTMLTag.productDetails(block: ProductDetails.() -> Unit = {}) {
    return ProductDetails(consumer).visit(block)
}
