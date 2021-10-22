package io.github.gravetii.theme

import javafx.event.Event
import javafx.event.EventType
import javafx.scene.image.Image

class Theme(private val type: ThemeType) {

    companion object {
        val THEME_CHANGE_EVENT_TYPE: EventType<ChangeEvent> = EventType(Event.ANY, "theme-change")
        class ChangeEvent : Event(THEME_CHANGE_EVENT_TYPE)
    }

    val image: Image by lazy {
        Image(javaClass.getResource(type.getImgPath())!!.toExternalForm())
    }

    val stylesheet: String by lazy {
        javaClass.getResource(type.getStyleSheetPath())!!.toExternalForm()
    }

}