import attributes.attributeStringString
import dw.html.vue.buildTemplate
import dw.html.vue.component
import dw.html.vue.vueModel
import dw.html.vue.vueOn
import kotlinx.browser.window
import kotlinx.html.HTMLTag
import kotlinx.html.HtmlInlineTag
import kotlinx.html.HtmlTagMarker
import kotlinx.html.InputType
import kotlinx.html.TagConsumer
import kotlinx.html.form
import kotlinx.html.h3
import kotlinx.html.id
import kotlinx.html.input
import kotlinx.html.label
import kotlinx.html.option
import kotlinx.html.select
import kotlinx.html.textArea
import kotlinx.html.visitAndFinalize
import kotlin.js.json

fun reviewForm() {
    app.component("review-form") {
        template = buildTemplate {
            form(classes = "review-form") {
                vueOn.submit.prevent("onSubmit")
                h3 { +"Leave a review" }
                label {
                    htmlFor = "name"
                    +"Name:"
                }
                input {
                    id = "name"
                    vueModel("name")
                }
                label {
                    htmlFor = "review"
                    +"Review:"
                }
                textArea {
                    id = "review"
                    vueModel("review")
                }
                label {
                    htmlFor = "rating"
                    +"Rating:"
                }
                select {
                    id = "rating"
                    vueModel.number("rating")
                    option { +"5" }
                    option { +"4" }
                    option { +"3" }
                    option { +"2" }
                    option { +"1" }
                }
                label {
                    htmlFor = "recommend"
                    +"Would you recommend this product?"
                }
                select {
                    id = "recommend"
                    vueModel("recommend")
                    option { +"Yes" }
                    option { +"No" }
                }
                input(classes="button", type = InputType.submit) {
                    value = "Submit"
                }
            }
        }
        data = {
            json(
                "name" to "",
                "review" to "",
                "rating" to null,
                "recommend" to null
            )
        }
        methods = json(
            "onSubmit" to {
                val self = js("this")
                if (self.name === "" || self.review === "" || self.rating === null || self.recommend === null) {
                    window.alert("Review is incomplete. Please fill out every field.")
                } else {
                    val productReview = json(
                        "name" to self.name,
                        "review" to self.review,
                        "rating" to self.rating,
                        "recommend" to self.recommend
                    )
                    self.`$emit`("review-submitted", productReview)

                    self.name = ""
                    self.review = ""
                    self.rating = null
                    self.recommend = null
                }
            }
        )
    }
}

class ReviewForm(consumer: TagConsumer<*>) : HTMLTag("review-form", consumer, emptyMap(), inlineTag=true, emptyTag = false), HtmlInlineTag {
    var reviewSubmitted: String
        get() = attributeStringString[this, "review-submitted"]
        set(newValue) {
            attributeStringString[this, "review-submitted"] = newValue
        }
}

@HtmlTagMarker
inline fun <T, C: TagConsumer<T>> C.reviewForm(crossinline block: ReviewForm.() -> Unit = {}): T {
    return ReviewForm(this).visitAndFinalize(this, block)
}
