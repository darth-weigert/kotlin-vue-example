import attributes.attributeStringString
import dw.html.vue.buildTemplate
import dw.html.vue.component
import dw.html.vue.vueBind
import dw.html.vue.vueFor
import kotlinx.html.HTMLTag
import kotlinx.html.HtmlInlineTag
import kotlinx.html.TagConsumer
import kotlinx.html.br
import kotlinx.html.div
import kotlinx.html.h3
import kotlinx.html.li
import kotlinx.html.ul
import kotlinx.html.visit
import kotlin.js.json

fun reviewList() {
    app.component("review-list") {
        props = json(
            "reviews" to json(
                "type" to Array::class.js,
                "required" to true
            )
        )
        template = buildTemplate {
            div(classes="review-container") {
                h3 {
                    +"Reviews:"
                }
                ul {
                    li {
                        vueFor("(review, index) in reviews")
                        vueBind["key"] = "review.name + '/' + review.rating + '/' + review.review"
                        +"{{ review.name }} gave this {{ review.rating }} stars"
                        br()
                        +"{{ review.review }}"
                        br()
                        +"Recommended: {{ review.recommend }}"
                    }
                }
            }
        }
    }
}

class ReviewList(consumer: TagConsumer<*>) : HTMLTag("review-list", consumer, emptyMap(), inlineTag=true, emptyTag = false), HtmlInlineTag {
    var reviews: String
        get() = attributeStringString[this, "reviews"]
        set(newValue) {
            attributeStringString[this, "reviews"] = newValue
        }
}

fun HTMLTag.reviewList(block: ReviewList.() -> Unit = {}) {
    return ReviewList(consumer).visit(block)
}
