package com.jamesward.hellospringmcpclient

import io.modelcontextprotocol.client.McpSyncClient
import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {

    @Bean
    fun chatClient(mcpSyncClients: List<McpSyncClient>, builder: ChatClient.Builder): ChatClient =
        builder
            .defaultToolCallbacks(SyncMcpToolCallbackProvider(mcpSyncClients))
            .defaultSystem(mcpSyncClients.joinToString("\n") { it.serverInstructions })
            .build()

    val question = "for the tool server, get the number of open connections and active connections"

    @Bean
    fun runner(chatClient: ChatClient) = ApplicationRunner { args ->
        println(chatClient.prompt().user(question).call().content())
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
