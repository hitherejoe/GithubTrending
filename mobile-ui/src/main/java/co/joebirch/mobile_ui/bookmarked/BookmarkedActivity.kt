package co.joebirch.mobile_ui.bookmarked

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import co.joebirch.mobile_ui.R
import co.joebirch.mobile_ui.injection.ViewModelFactory
import co.joebirch.mobile_ui.mapper.ProjectViewMapper
import co.joebirch.mobile_ui.model.Project
import co.joebirch.presentation.BrowseBookmarkedProjectsViewModel
import co.joebirch.presentation.model.ProjectView
import co.joebirch.presentation.state.Resource
import co.joebirch.presentation.state.ResourceState
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_browse.*
import javax.inject.Inject

class BookmarkedActivity: AppCompatActivity() {

    @Inject lateinit var browseAdapter: BookmarkedAdapter
    @Inject lateinit var mapper: ProjectViewMapper
    @Inject lateinit var viewModelFactory: ViewModelFactory
    private lateinit var browseViewModel: BrowseBookmarkedProjectsViewModel

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, BookmarkedActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)
        AndroidInjection.inject(this)

        browseViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(BrowseBookmarkedProjectsViewModel::class.java)

        setupBrowseRecycler()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onStart() {
        super.onStart()
        browseViewModel.getProjects().observe(this,
                Observer<Resource<List<ProjectView>>> {
                    it?.let {
                        handleDataState(it)
                    }
                })
        browseViewModel.fetchProjects()
    }

    private fun setupBrowseRecycler() {
        recycler_projects.layoutManager = LinearLayoutManager(this)
        recycler_projects.adapter = browseAdapter
    }

    private fun handleDataState(resource: Resource<List<ProjectView>>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
                setupScreenForSuccess(resource.data?.map {
                    mapper.mapToView(it)
                })
            }
            ResourceState.LOADING -> {
                progress.visibility = View.VISIBLE
                recycler_projects.visibility = View.GONE
            }
        }
    }

    private fun setupScreenForSuccess(projects: List<Project>?) {
        progress.visibility = View.GONE
        projects?.let {
            browseAdapter.projects = it
            browseAdapter.notifyDataSetChanged()
            recycler_projects.visibility = View.VISIBLE
        } ?: run {

        }
    }
}