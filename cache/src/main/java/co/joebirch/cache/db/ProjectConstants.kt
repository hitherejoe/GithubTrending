package co.joebirch.cache.db

object ProjectConstants {

    const val TABLE_NAME = "projects"

    const val QUERY_PROJECTS = "SELECT * FROM $TABLE_NAME"

    const val DELETE_PROJECTS = "DELETE * FROM $TABLE_NAME"

}