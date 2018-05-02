package co.joebirch.domain.test

import co.joebirch.domain.model.Project

object ProjectDataFactory {

    fun randomUuid(): String {
        return java.util.UUID.randomUUID().toString()
    }

    fun makeProject(): Project {
        return Project(randomUuid(), randomUuid(), randomUuid(), randomUuid(),
                randomUuid(), randomUuid(), randomUuid(), false)
    }

    fun makeProjectList(count: Int) : List<Project> {
        val projects = mutableListOf<Project>()
        repeat(count) {
            projects.add(makeProject())
        }
        return projects
    }

}