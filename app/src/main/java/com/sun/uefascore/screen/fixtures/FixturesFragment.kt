package com.sun.uefascore.screen.fixtures

import android.app.DatePickerDialog
import android.os.Bundle
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
import com.sun.uefascore.data.source.repository.FixtureRepository
import com.sun.uefascore.screen.fixtures.adapter.FixtureAdapter
import com.sun.uefascore.screen.fixtures.adapter.FixtureAllAdapter
import com.sun.uefascore.screen.searchteam.SearchFragment
import com.sun.uefascore.utils.Constant
import com.sun.uefascore.utils.addFragment
import com.sun.uefascore.utils.hideKeyboard
import kotlinx.android.synthetic.main.activity_main.*
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
        textViewDayOfMatches.text = dayDevice
    }

    private fun searchTeam() {
        searchViewTeam.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(name: String?): Boolean {
                addFragment(SearchFragment.newInstance(name?.trim()), R.id.containerLayout)
                hideKeyboard()
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
                    val datePicker =
                        SimpleDateFormat(Constant.DAY_FORMAT_FORWARD).parse("$year/${monthOfYear + 1}/$dayOfMonth")
                    val stringDate = SimpleDateFormat(Constant.DAY_FORMAT).format(datePicker)
                    dayByPicker = stringDate
                    textViewDayOfMatches.text = stringDate
                    fixturePresenter.apply {
                        setView(this@FixturesFragment)
                        getFixture(stringDate, season)
                    }
                }, year, month, day)
            }
        datePicker?.show()
    }

    private fun initSpinner(seasons: MutableList<String>) {
        val stateAdapter = view?.let {
            ArrayAdapter<String>(
                it.context,
                R.layout.support_simple_spinner_dropdown_item,
                seasons
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

    override fun getSeasonSuccess(season: MutableList<String>) {
        initSpinner(season)
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
