package ru.sportmaster.elasticsearchtest

import co.elastic.clients.elasticsearch.ElasticsearchClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.client.ClientConfiguration
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories
import org.springframework.stereotype.Service
import java.util.*
import kotlin.system.exitProcess


@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = ["ru.ezhov"])
@EnableAutoConfiguration
class LearnElasticsearchApplication

fun main(args: Array<String>) {
    runApplication<LearnElasticsearchApplication>(*args)
}

@Service
class Ex(
    private val testEsService: TestEsService,
    private val clientConfiguration: ClientConfiguration,
    private val elasticsearchClient: ElasticsearchClient,
    private val elasticsearchOperations: ElasticsearchOperations,
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        testEsService.create(UUID.randomUUID().toString())

        println(clientConfiguration.socketTimeout)
        println(elasticsearchClient.cluster().health().clusterName())
        println(elasticsearchOperations.cluster().health().clusterName)

        println(testEsService.all().map { "${it.id}:${it.text}" })

        exitProcess(0)
    }
}


@Configuration
class MyClientConfig(
    @Value("\${spring.elasticsearch.rest.uris}")
    private val urls: String,

    @Value("\${spring.elasticsearch.username}")
    private val username: String,

    @Value("\${spring.elasticsearch.password}")
    private val password: String,

    ) : ElasticsearchConfiguration() {
    override fun clientConfiguration(): ClientConfiguration {
        return ClientConfiguration
            .builder()
            .connectedTo(urls)
            .usingSsl()
            .withBasicAuth(username, password)
            .build()
    }
}