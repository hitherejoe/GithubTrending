package co.joebirch.domain.test

import co.joebirch.domain.model.Project
import java.util.*

object ProjectDataFactory {

    fun randomUuid(): String {
        return UUID.randomUUID().toString()
    }

    fun randomBoolean(): Boolean {
        return Math.random() < 0.5
    }

    fun makeProject(): Project {
        return Project(randomUuid(), randomUuid(), randomUuid(),
                randomUuid(), randomUuid(), randomUuid(),
                randomUuid(), randomBoolean())
    }

    fun makeProjectList(count: Int): List<Project> {
        val projects = mutableListOf<Project>()
        repeat(count) {
            projects.add(makeProject())
        }
        return projects
    }

}