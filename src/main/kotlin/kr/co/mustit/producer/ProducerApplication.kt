package kr.co.mustit.producer

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import java.util.function.Function
import java.util.function.Supplier

@SpringBootApplication
class ProducerApplication {
    val log = LoggerFactory.getLogger(this::class.java)
    var id = 0

    @Value("\${callme.supplier.enabled}")
    val supplierEnabled: Boolean = false

//    @Bean
//    fun callMeEventSupplier(): Supplier<Message<CallMeEvent>?> = Supplier { createEvent() }
//
//    private fun createEvent(): Message<CallMeEvent>? {
//        return if (supplierEnabled)
//            MessageBuilder.withPayload(CallMeEvent(++id, "I'm callme event!"))
//                    .setHeader("to_process", true)
//                    .build()
//        else
//            null
//    }

    @Bean
    fun upperCase(): Function<String, String> = Function {
        log.info("upperCase Function 실행!! ${it.toUpperCase()}")
        it.toUpperCase()
    }

    @Bean
    fun lowerCase(): Function<String, String> = Function {
        log.info("upperCase Function 실행!! ${it.toLowerCase()}")
        it.toLowerCase()
    }
}

fun main(args: Array<String>) {
    runApplication<ProducerApplication>(*args)
}
