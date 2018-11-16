package co.joebirch.presentation.mapper

import co.joebirch.presentation.test.factory.ProjectFactory
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProjectViewMapperTest {

    private val mapper = ProjectViewMapper()

    @Test
    fun mapToViewMapsData() {
        val project = ProjectFactory.makeProject()
        val projectView = mapper.mapToView(project)

        assertEquals(project.id, projectView.id)
        assertEquals(project.name, projectView.name)
        assertEquals(project.fullName, projectView.fullName)
        assertEquals(project.starCount, projectView.starCount)
        assertEquals(project.dateCreated, projectView.dateCreated)
        assertEquals(project.ownerName, projectView.ownerName)
        assertEquals(project.ownerAvatar, projectView.ownerAvatar)
        assertEquals(project.isBookmarked, projectView.isBookmarked)
    }

}