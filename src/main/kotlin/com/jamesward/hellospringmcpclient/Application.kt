package com.jamesward.hellospringmcpclient

import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.tool.ToolCallbackProvider
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {

    @Bean
    fun chatClient(toolCallbackProvider: ToolCallbackProvider, builder: ChatClient.Builder): ChatClient =
        builder.defaultToolCallbacks(toolCallbackProvider).build()

    val question = "What is the latest version of the org.webjars:webjars-locator-lite library?"

    @Bean
    fun runner(chatClient: ChatClient) = ApplicationRunner {
        println(chatClient.prompt().user(question).call().content())
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
