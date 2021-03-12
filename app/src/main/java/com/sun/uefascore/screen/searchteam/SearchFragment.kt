package com.sun.uefascore.screen.searchteam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.sun.uefascore.R
import com.sun.uefascore.data.model.TeamDetail
import com.sun.uefascore.data.source.repository.TeamRepository

class SearchFragment : Fragment(), SearchContract.View {

    private var name: String? = null

    private val searchTeamPresenter by lazy {
        SearchPresenter(
            TeamRepository.instance
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(NAME)
        }
        Toast.makeText(context, name.toString(), Toast.LENGTH_LONG).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    override fun getTeamByNameSuccess(team: MutableList<TeamDetail>) {}

    override fun onError(exception: Exception?) {}

    private fun initData() {
        searchTeamPresenter.apply {
            name?.let { getTeamByName(it) }
        }
    }

    companion object {
        private const val NAME = "NAME"
        fun newInstance(name: String?) = SearchFragment().apply {
            arguments = bundleOf(NAME to name)
        }
    }

}
