package com.loan.stl.module.mine.viewControl

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.amap.api.services.core.PoiItem
import com.amap.api.services.poisearch.PoiResult
import com.amap.api.services.poisearch.PoiSearch
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout
import com.loan.stl.BR
import com.loan.stl.R
import com.loan.stl.common.BundleKeys
import com.loan.stl.common.CommonControl
import com.loan.stl.common.SwipeListener
import com.loan.stl.common.binding.BaseRecyclerViewAdapter
import com.loan.stl.common.binding.PositionClick
import com.loan.stl.databinding.CommonViewPagerRecyclerBinding
import com.loan.stl.utils.Util
import java.util.ArrayList

/**
author: russell
time: 2019-07-16:16:49
describe：
 */
class GdSearchCtrl(val binding:CommonViewPagerRecyclerBinding, private val cityCode: String): CommonControl() {

    private var page = 0
    private var pageCount = 20
    private val pageSize = 15
    private var keyword = ""
    private var activity: Activity = Util.getActivity(binding.root)
    private val searchList = ArrayList<PoiItem>()

    init {

        listener.set(object : SwipeListener() {
            override fun swipeInit(swipeLayout: SwipeToLoadLayout) {
                setSwipeLayout(swipeLayout)
                swipeLayout.isRefreshEnabled = false
            }

            override fun refresh() {
                page = 0
                poiSearch(page, keyword)
            }

            override fun loadMore() {
                page++
                poiSearch(page, keyword)
            }
        })

        binding.swipeTarget.layoutManager= LinearLayoutManager(activity)
        val adapter= BaseRecyclerViewAdapter(searchList, BR.item, R.layout.item_poi_layout)
        binding.swipeTarget.adapter=adapter
        adapter.setPositionClick(object : PositionClick {
            override fun click(view: View, position: Int) {
                val  intent= Intent()
                intent.putExtra(BundleKeys.DATA, searchList[position])
                activity.setResult(Activity.RESULT_OK, intent)
                activity.finish()
            }

        })
    }



    fun poiSearch(pageNum: Int, key: String) {
        //POI搜索类型共分为以下20种：汽车服务|汽车销售|汽车维修|摩托车服务|餐饮服务|购物服务|生活服务|体育休闲服务|医疗保健服务|住宿服务|风景名胜|商务住宅|政府机构及社会团体|科教文化服务|交通设施服务|金融保险服务|公司企业|道路附属设施|地名地址信息|公共设施
        //餐饮服务|商务住宅|生活服务
        if (TextUtils.isEmpty(key)) {
            return
        }
        page = pageNum
        keyword = key
        val query = PoiSearch.Query(key, "商务住宅|餐饮服务|生活服务", cityCode)
        query.pageSize = pageSize
        query.pageNum = pageNum
        val poiSearch = PoiSearch(activity, query)
        poiSearch.setOnPoiSearchListener(object : PoiSearch.OnPoiSearchListener {
            override fun onPoiSearched(poiResult: PoiResult, i: Int) {
                getSwipeLayout()?.isLoadingMore = false
                pageCount = poiResult.pageCount
                convert(poiResult.pois)
                getSwipeLayout()?.isLoadMoreEnabled = page < pageCount - 1
            }

            override fun onPoiItemSearched(poiItem: PoiItem, i: Int) {}
        })
        poiSearch.searchPOIAsyn()
    }

    private fun convert(list: List<PoiItem>?) {
        if (list == null || list.isEmpty()) {
            return
        }
        if (page == 0) {
            searchList.clear()
        }
        searchList.addAll(list)
        binding.swipeTarget.adapter?.notifyDataSetChanged()

    }
}