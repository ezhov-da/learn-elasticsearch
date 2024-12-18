package ru.ezhov.learnelasticsearch

import org.springframework.stereotype.Service


@Service
class TestEsService(
    private val testEsRepository: TestEsRepository
) {
    fun create(text: String) {
        testEsRepository.save(
            TestEs(
                text = text
            )
        )
    }


    fun all(): List<TestEs> =
        testEsRepository.findAll().toList()
}
