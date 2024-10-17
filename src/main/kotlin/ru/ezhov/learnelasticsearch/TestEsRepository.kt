package ru.sportmaster.elasticsearchtest

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository


@Repository
interface TestEsRepository : ElasticsearchRepository<TestEs, String>
