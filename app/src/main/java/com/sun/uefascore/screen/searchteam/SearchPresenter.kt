package com.sun.uefascore.screen.searchteam

import com.sun.uefascore.data.model.TeamDetail
import com.sun.uefascore.data.source.remote.OnFetchDataJsonListener
import com.sun.uefascore.data.source.repository.TeamRepository

class SearchPresenter internal constructor(private val repository: TeamRepository?) :
    SearchContract.Presenter {

    private var view: SearchContract.View? = null

    override fun getTeamByName(name: String) {
        repository?.getTeamByName(
            name,
            object : OnFetchDataJsonListener<MutableList<TeamDetail>> {
                override fun onSuccess(data: MutableList<TeamDetail>) {
                    view?.getTeamByNameSuccess(data)
                }

                override fun onError(exception: Exception?) {
                    view?.onError(exception)
                }
            }
        )
    }

    override fun onStart() {}

    override fun onStop() {}

    override fun setView(view: SearchContract.View?) {
        this.view = view
    }

}