package kr.co.mustit.producer

import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/producer")
class ProducerController(val streamBridge: StreamBridge) {

    var id = 0

    @PostMapping("/{message}")
    fun sendEvent(@PathVariable message: String): Boolean {
        return streamBridge.send("callMeEventSupplier-out-0", CallMeEvent(++id, message))
    }

    @PostMapping("/{message}/process/{process}")
    fun sendEventWithHeader(@PathVariable message: String, @PathVariable process: Boolean): Boolean {
        return streamBridge.send("callMeEventSupplier-out-0", MessageBuilder.createMessage(CallMeEvent(++id, message),
                MessageHeaders(mutableMapOf(Pair<String, Any>("to_process", process)))))
    }
}