package ru.sportmaster.elasticsearchtest

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

@Document(indexName = "test-for-delete", createIndex = false)
class TestEs(
    @Id
    val id: String? = null,

    @Field(type = FieldType.Text)
    val text: String
)
