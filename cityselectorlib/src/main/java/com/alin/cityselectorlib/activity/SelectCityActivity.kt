package com.alin.cityselectorlib.activity

import android.app.Activity
import android.content.Intent
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import com.aiitec.jpmj.entitylibary.model.CharCitys
import com.aiitec.jpmj.entitylibary.model.Citys
import com.alibaba.android.arouter.facade.annotation.Route
import com.alin.cityselectorlib.R
import com.alin.cityselectorlib.base.BaseKtActivity
import com.alin.cityselectorlib.db.CityDBUtils
import com.alin.cityselectorlib.entity.Key
import com.alin.cityselectorlib.entity.Region
import com.alin.cityselectorlib.router.RouterPath
import com.alin.cityselectorlib.util.LogUtil
import com.alin.cityselectorlib.widget.SideBar
import com.alin.cityselectorlib.widget.TitleItemDecoration
import com.example.myinterviewdemo.cityselector.adapter.CitysAdapter
import com.github.promeg.pinyinhelper.Pinyin
import kotlinx.android.synthetic.main.activity_select_citys.*
import java.util.*

/**
 * @author ailibin
 * 选择城市列表
 *
 */
@Route(path = RouterPath.TEST_SELECT_CITY_ACTIVITY, name = "selectCityActivity")
class SelectCityActivity : BaseKtActivity() {

    private var datas: ArrayList<Citys> = ArrayList()
    private var adapter: CitysAdapter? = null
    private var keyword: String? = ""
    private var cityLists: ArrayList<Region>? = null
    private var charCitys = ArrayList<CharCitys>()
    private var cityDbUtils: CityDBUtils? = null
    private var TAG = "ailibin"
    private var pinyinComparator: PinyinComparator? = null
    private var type = 0
    private var currentCityName: String? = null
    private var currentCharCity: CharCitys? = null


    private fun filledDataByChar(data: List<CharCitys>) {

        if (data.isEmpty()) {
            return
        }
        SideBar.b.clear()
        for (i in data.indices) {
            val citys = data[i]
            if (!SideBar.b.contains(citys.char)) {
                //不包含才添加进去
                SideBar.b.add(citys.char)
            }
        }
        Collections.sort(SideBar.b, PinyinComparator2())
        LogUtil.e(TAG, "after: " + SideBar.b)
        side_bar.postInvalidate()

    }


    private fun initDB() {

        //比较器初始化
        pinyinComparator = PinyinComparator()
        cityDbUtils = CityDBUtils(this)
        cityDbUtils!!.initAllCities()
        cityDbUtils!!.setOnCityInitListener { parentRegion, regions, areaLevel ->
            //获取中国所有的城市
            cityLists = regions
            //子线程
            var citys: Citys
            charCitys.clear()
            for (i in 0 until cityLists!!.size) {
                citys = Citys()
                citys.name = cityLists!![i].name
                citys.id = cityLists!![i].id
                datas.add(citys)
                LogUtil.e(TAG, "id: ${citys.id}")
            }
            filledData(datas)
        }

    }


    internal inner class PinyinComparator : Comparator<CharCitys> {

        override fun compare(o1: CharCitys, o2: CharCitys): Int {
            //这里主要是用来对ListView里面的数据根据ABCDEFG...来排序
            return when {
                "#" == o2.char -> -1
                "#" == o1.char -> 1
                else -> o1.char!!.compareTo(o2.char!!)
            }
        }
    }

    internal inner class PinyinComparator2 : Comparator<String> {

        override fun compare(o1: String, o2: String): Int {
            //这里主要是用来对ListView里面的数据根据ABCDEFG...来排序
            return when {
                "#" == o2 -> -1
                "#" == o1 -> 1
                else -> o1.compareTo(o2)
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_select_citys
    }


    override fun initData() {
        super.initData()
        currentCityName = intent.getStringExtra(Key.PARAM_CITY_NAME)
        initDB()
    }

    override fun initView() {

        side_bar.setTextView(tv_contact_select)
        adapter = CitysAdapter(this, searchCharCitys)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val titleItemDecoration = TitleItemDecoration(this, searchCharCitys)
        recyclerView.addItemDecoration(titleItemDecoration)
        recyclerView.adapter = adapter
        recyclerView.isNestedScrollingEnabled = false
        //当前定位的城市名称
        tvCurrentLocation.text = currentCityName
        setListener()
    }

    private fun setListener() {

        //设置右侧触摸监听
        side_bar.setOnTouchingLetterChangedListener { s ->
            for (i in 1..searchCharCitys.size) {
                if (searchCharCitys[i].char!! == s) {
                    recyclerView.scrollToPosition(i)
                    return@setOnTouchingLetterChangedListener
                }
            }
        }

        tvSearch.setOnClickListener {
            //搜索
            keyword = edtChooseCitys.text.toString().trim()
            if (TextUtils.isEmpty(keyword)) {
                return@setOnClickListener
            }
            toSearchCityList(keyword!!)
        }

        adapter?.setOnItemClickListener { adapter, view, position ->
            toast("当前选择城市： ${searchCharCitys[position].name}")
            val intent = Intent()
            intent.putExtra(Key.PARAM_CITY, searchCharCitys[position])
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        llLocationContainer?.setOnClickListener {
            //点击当前定位的城市
//            val intent = Intent()
//            intent.putExtra(Key.PARAM_CITY, currentCharCity)
//            setResult(Activity.RESULT_OK, intent)
//            finish()
        }

    }

    private var searchCharCitys = ArrayList<CharCitys>()

    private fun toSearchCityList(keyword: String) {

        searchCharCitys.clear()
        for (i in charCitys.indices) {
            val city = charCitys[i]
            val cityPinyin = Pinyin.toPinyin(city.name, "")
            val keyPinyin = Pinyin.toPinyin(keyword, "")
            if (cityPinyin?.indexOf(keyPinyin)!! >= 0) {
                searchCharCitys.add(city)
            }
        }
        filledDataByChar(searchCharCitys)
        // 根据a-z进行排序源数据
        Collections.sort(searchCharCitys, pinyinComparator)
        adapter?.notifyDataSetChanged()

    }

    /**
     * 为ListView填充数据
     *
     * @param data
     * @return
     */
    private fun filledData(data: List<Citys>) {

        if (data.isEmpty()) {
            return
        }

        var charcitys: CharCitys
        charCitys.clear()
        SideBar.b.clear()

        for (i in data.indices) {

            val citys = data[i]
            charcitys = CharCitys()
            charcitys.name = citys.name
            charcitys.id = citys.id.toLong()
            //汉字转换成拼音
            var pinyin = if (!TextUtils.isEmpty(citys.name)) {
                Pinyin.toPinyin(citys.name!!.toCharArray()[0])
            } else {
                ""
            }
            if (TextUtils.isEmpty(pinyin)) {
                citys.sortLetters = "#"
                citys.char = "#"
                charcitys.char = "#"
            } else {
                val sortString = pinyin.substring(0, 1).toUpperCase()
                LogUtil.e(TAG, "sortString: $sortString")
                // 正则表达式，判断首字母是否是英文字母
                if (sortString.matches("[A-Z]".toRegex())) {
                    citys.sortLetters = sortString.toUpperCase()
                    citys.char = sortString.toUpperCase()
                    charcitys.char = sortString.toUpperCase()
                } else {
                    citys.sortLetters = "#"
                    citys.char = "#"
                    charcitys.char = "#"
                }
            }

            if (!SideBar.b.contains(citys.sortLetters)) {
                SideBar.b.add(citys.sortLetters)
            }
            searchCharCitys.add(charcitys)
            charCitys.add(charcitys)

        }

        Collections.sort(SideBar.b, PinyinComparator2())
        side_bar.postInvalidate()
        filledDataByChar(searchCharCitys)
        // 根据a-z进行排序源数据
        Collections.sort(searchCharCitys, pinyinComparator)
        adapter?.notifyDataSetChanged()

        LogUtil.e(TAG, "after: " + SideBar.b)
        LogUtil.e(TAG, "searchCharCitys: $searchCharCitys")

    }


}