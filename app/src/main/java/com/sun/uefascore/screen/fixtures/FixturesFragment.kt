package com.sun.uefascore.screen.fixtures

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sun.uefascore.R
import com.sun.uefascore.data.model.Fixture
import com.sun.uefascore.data.model.Season
import com.sun.uefascore.data.source.repository.FixtureRepository
import com.sun.uefascore.screen.fixtures.adapter.FixtureAdapter
import com.sun.uefascore.screen.fixtures.adapter.FixtureAllAdapter
import com.sun.uefascore.utils.Constant
import kotlinx.android.synthetic.main.fragment_fixtures.*
import java.text.SimpleDateFormat
import java.util.*

class FixturesFragment : Fragment(), ContractFixture.View, AdapterView.OnItemSelectedListener {

    private var season = ""
    private var dayByPicker = ""
    private val fixtureAdapter by lazy { FixtureAdapter() }
    private val fixtureAllAdapter by lazy { FixtureAllAdapter() }
    private val fixturePresenter: ContractFixture.Presenter by lazy {
        FixturePresenter(
            FixtureRepository.instance
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fixtures, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPresenter()
        initDate()
        initView()
        searchTeam()
        onClickItem()
        onClickDatePicker()
        initSpinner()
    }

    private fun initPresenter() {
        val date = Date()
        val formatter = SimpleDateFormat(Constant.DAY_FORMAT)
        val dayDevice = formatter.format(date)
        fixturePresenter.apply {
            setView(this@FixturesFragment)
            getFixture(dayDevice, season)
            getSeason()
        }
    }

    private fun initView() {
        recyclerViewFixtureOfDay.apply {
            adapter = this@FixturesFragment.fixtureAdapter
        }
        recyclerViewFixtureHistory.apply {
            adapter = this@FixturesFragment.fixtureAllAdapter
        }
    }

    private fun initDate() {
        val date = Date()
        val formatter = SimpleDateFormat(Constant.DAY_FORMAT)
        val dayDevice = formatter.format(date)
        dayByPicker = dayDevice
        textViewDayOfMatches.text = dayDevice
    }

    private fun searchTeam() {
        searchViewTeam.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })
    }

    private fun onClickItem() {
        fixtureAdapter.apply {
            registerHomeClickListener { }
            registerAwayClickListener { }
        }
        fixtureAllAdapter.apply {
            registerHomeClickListener { }
            registerAwayClickListener { }
        }
    }

    private fun onClickDatePicker() {
        imageViewCalendar.setOnClickListener {
            onGetDataByDatePicker()
        }
    }

    private fun onGetDataByDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker =
            context?.let {
                DatePickerDialog(it, { _, year, monthOfYear, dayOfMonth ->
                    val dayFormat = if (dayOfMonth < 10) {
                        "0$dayOfMonth"
                    } else {
                        "$dayOfMonth"
                    }
                    val monthFormat = if (monthOfYear < 9) {
                        "0${monthOfYear + 1}"
                    } else {
                        "${monthOfYear + 1}"
                    }
                    val dayPicker = "$year-$monthFormat-$dayFormat"
                    dayByPicker = dayPicker
                    textViewDayOfMatches.text = dayPicker
                    fixturePresenter.apply {
                        setView(this@FixturesFragment)
                        getFixture(dayPicker, season)
                    }
                }, year, month, day)
            }
        datePicker?.show()
    }

    private fun initSpinner() {
        val listState = mutableListOf<String>(
            "2016",
            "2017",
            "2018",
            "2019",
            "2020",
            "2021",
            "2022"
        )
        val stateAdapter = view?.let {
            ArrayAdapter<String>(
                it.context,
                R.layout.support_simple_spinner_dropdown_item,
                listState
            )
        }
        spinnerSeason.adapter = stateAdapter
        spinnerSeason.onItemSelectedListener = this

    }

    override fun onGetFixtureSuccess(fixtures: MutableList<Fixture>) {
        fixtureAdapter.updateData(fixtures)
    }

    override fun onGetAllFixtureSuccess(fixtures: MutableList<Fixture>) {
        fixtureAllAdapter.updateData(fixtures)
    }

    override fun onGetSeasonSuccess(season: MutableList<Season>) {
        Log.e("xxx" , season.toString())
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(context, exception?.message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = FixturesFragment()
    }

    override fun onItemSelected(p: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val seasonSpinner = p?.selectedItem.toString()
        textViewSeason.text = seasonSpinner
        season = seasonSpinner
        fixturePresenter.apply {
            setView(this@FixturesFragment)
            getAllFixture(season)
            getFixture(dayByPicker, season)
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}
