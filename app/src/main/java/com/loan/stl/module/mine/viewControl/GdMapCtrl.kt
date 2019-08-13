package com.loan.stl.module.mine.viewControl

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager

import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.AMapOptions
import com.amap.api.maps2d.CameraUpdateFactory
import com.amap.api.maps2d.LocationSource
import com.amap.api.maps2d.model.BitmapDescriptorFactory
import com.amap.api.maps2d.model.CameraPosition
import com.amap.api.maps2d.model.LatLng
import com.amap.api.maps2d.model.MyLocationStyle
import com.amap.api.services.core.LatLonPoint
import com.amap.api.services.core.PoiItem
import com.amap.api.services.poisearch.PoiResult
import com.amap.api.services.poisearch.PoiSearch
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout
import com.loan.stl.BR
import com.loan.stl.LoanApplication
import com.loan.stl.R
import com.loan.stl.common.BundleKeys
import com.loan.stl.common.CommonControl
import com.loan.stl.common.SwipeListener
import com.loan.stl.common.binding.BaseRecyclerViewAdapter
import com.loan.stl.common.binding.PositionClick
import com.loan.stl.databinding.ActivityGdMapBinding
import com.loan.stl.module.mine.ui.activity.GdMapActivity
import com.loan.stl.module.mine.ui.fragment.GdSearchFrag
import com.loan.stl.utils.LogUtils

import java.util.ArrayList

/**
 * Author: Chenming
 * E-mail: cm1@erongdu.com
 * Date: 2017/2/23 下午2:26
 *
 *
 * Description: 高德地图（
 */
class GdMapCtrl(var binding: ActivityGdMapBinding, val activity: GdMapActivity) : CommonControl(),
    LocationSource, AMapLocationListener, AMap.OnCameraChangeListener {
    private var aMap: AMap? = null
    private var mListener: LocationSource.OnLocationChangedListener? = null
    private var mlocationClient: AMapLocationClient? = null
    private var mLocationOption: AMapLocationClientOption? = null
    private var searchBound: PoiSearch.SearchBound? = null
    private var page = 0
    private var pageCount = 20
    private val pageSize = 10
    private var cityCode = ""
    private var gdSearchFrag: GdSearchFrag?=null
    private var transaction : FragmentTransaction?=null
    private val searchList = ArrayList<PoiItem>()

    init {
        aMap=binding.map.map
        setSwipeLayout(binding.layouts)
        if (aMap != null) {
            aMap = binding.map.map
            setUpMap()
            aMap!!.setOnCameraChangeListener(this)
            binding.swipeTarget.layoutManager=LinearLayoutManager(activity)
            val adapter=BaseRecyclerViewAdapter(searchList, BR.item,R.layout.item_poi_layout)
            binding.swipeTarget.adapter=adapter
            adapter.setPositionClick(object :PositionClick{
                override fun click(view: View, position: Int) {
                        val  intent=Intent()
                       intent.putExtra(BundleKeys.DATA, searchList[position])
                       activity.setResult(RESULT_OK, intent)
                       activity.finish()
                }

            })

        }

        listener.set(object : SwipeListener() {
          override  fun swipeInit(swipeLayout: SwipeToLoadLayout) {
              setSwipeLayout(swipeLayout)
              getSwipeLayout()?.isRefreshing = false
              getSwipeLayout()?.isRefreshEnabled = false
            }

            override  fun refresh() {

            }

            override fun loadMore() {
                poiSearch(page++)
            }
        })
    }

    private fun setTitleVisibility() {
        transaction = activity.supportFragmentManager.beginTransaction()

        if (binding.tvSearch.visibility == View.VISIBLE) {
            binding.tvSearch.visibility = View.GONE
            binding.tvSearchBtn.visibility = View.VISIBLE
            binding.etSearch.visibility = View.VISIBLE
            binding.container.visibility = View.VISIBLE
            binding.etSearch.requestFocus()
            gdSearchFrag = GdSearchFrag.getInstance(cityCode)
            transaction?.add(binding.container.id, gdSearchFrag!!)
            transaction?.addToBackStack(null)

        } else {
            binding.tvSearch.visibility = View.VISIBLE
            binding.tvSearchBtn.visibility = View.GONE
            binding.etSearch.visibility = View.GONE
            binding.container.visibility = View.GONE
            transaction?.remove(gdSearchFrag!!)

        }
        transaction?.commitAllowingStateLoss()
    }

    /** 返回事件  */
    @Suppress("UNUSED_PARAMETER")
    fun onBackPressed(view: View) {
        if (binding.tvSearch.visibility == View.VISIBLE) {
            activity.finish()
        } else {
            binding.tvSearch.visibility = View.VISIBLE
            binding.tvSearchBtn.visibility = View.GONE
            binding.etSearch.visibility = View.GONE
            binding.container.visibility = View.GONE
            transaction = activity.supportFragmentManager.beginTransaction()
            transaction?.remove(gdSearchFrag!!)
            transaction?.commitAllowingStateLoss()
        }
    }

    /** 搜索按钮  */
    @Suppress("UNUSED_PARAMETER")
    fun toSearch(view: View) {
        setTitleVisibility()
    }

    /** 搜索  */
    @Suppress("unused", "UNUSED_PARAMETER")
    fun search(view: View) {
        page = 1
        poiSearch(page)
    }

    /**
     * 设置一些amap的属性
     */
    private fun setUpMap() {
        // 自定义系统定位小蓝点
        val myLocationStyle = MyLocationStyle()
        myLocationStyle.myLocationIcon(
            BitmapDescriptorFactory
                .fromResource(R.drawable.icon_location_marker)
        )// 设置小蓝点的图标
        myLocationStyle.radiusFillColor(activity.resources.getColor(R.color.amap_transparent_theme_color))// 设置圆形的填充颜色
        myLocationStyle.strokeColor(activity.resources.getColor(R.color.amap_transparent_theme_color))
        myLocationStyle.strokeWidth(1.0f)// 设置圆形的边框粗细
        aMap!!.moveCamera(CameraUpdateFactory.zoomTo(19f))
        aMap!!.setMyLocationStyle(myLocationStyle)
        aMap!!.setLocationSource(this)// 设置定位监听
        aMap!!.uiSettings.isZoomControlsEnabled = false
        aMap!!.uiSettings.logoPosition = AMapOptions.LOGO_POSITION_BOTTOM_RIGHT
        aMap!!.uiSettings.isMyLocationButtonEnabled = false// 设置默认定位按钮是否显示
        aMap!!.isMyLocationEnabled = true// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false

        binding.ivLocation.setOnClickListener {
            if (mlocationClient != null) {
                val aMapLocation = mlocationClient!!.lastKnownLocation
                aMap!!.animateCamera(
                    CameraUpdateFactory.changeLatLng(
                        LatLng(
                            aMapLocation.latitude,
                            aMapLocation.longitude
                        )
                    )
                )
            }
        }
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun afterTextChanged(editable: Editable) {

                gdSearchFrag?.viewCtrl?.poiSearch(0, editable.toString())
            }
        })
    }

    override fun onLocationChanged(aMapLocation: AMapLocation?) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null && aMapLocation.errorCode == 0) {
                mListener!!.onLocationChanged(aMapLocation)// 显示系统小蓝点
                binding.ivLocation.visibility = View.VISIBLE
                cityCode = aMapLocation.cityCode
                /*if(firstList){
                    firstList = false;
                    searchBound = new PoiSearch.SearchBound(new LatLonPoint(aMapLocation.getLatitude(), aMapLocation.getLongitude()), 1000, true);
                    page = 0;
                    pageCount = 20;
                    poiSearch(page);
                }*/
            } else {
                val errText = "定位失败," + aMapLocation.errorCode + ": " + aMapLocation.errorInfo
                Log.e("AmapErr", errText)
            }
        }
    }

    override fun onCameraChange(cameraPosition: CameraPosition) {}

    override fun onCameraChangeFinish(cameraPosition: CameraPosition) {
        LogUtils.d("lat="+LoanApplication.lat)
        LogUtils.d("lon="+LoanApplication.lon)
        searchBound = PoiSearch.SearchBound(LatLonPoint(LoanApplication.lat, LoanApplication.lon), 1000, true);
        page = 0
        pageCount = 20
        poiSearch(page)
    }

    override fun activate(onLocationChangedListener: LocationSource.OnLocationChangedListener) {
        mListener = onLocationChangedListener
        if (mlocationClient == null) {
            mlocationClient = AMapLocationClient(activity)
            mLocationOption = AMapLocationClientOption()
            //设置定位监听
            mlocationClient!!.setLocationListener(this)
            //设置为高精度定位模式
            mLocationOption!!.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
            mLocationOption!!.isOnceLocation = true
            //设置定位参数
            mlocationClient!!.setLocationOption(mLocationOption)
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除

            mlocationClient!!.startLocation()
        }
    }

    override fun deactivate() {
        mListener = null
        if (mlocationClient != null) {
            mlocationClient!!.stopLocation()
            mlocationClient!!.onDestroy()
        }
        mlocationClient = null
    }

    private fun poiSearch(pageNum: Int) {
        //		POI搜索类型共分为以下20种：汽车服务|汽车销售|汽车维修|摩托车服务|餐饮服务|购物服务|生活服务|体育休闲服务|医疗保健服务|住宿服务|风景名胜|商务住宅|政府机构及社会团体|科教文化服务|交通设施服务|金融保险服务|公司企业|道路附属设施|地名地址信息|公共设施
        //		餐饮服务|商务住宅|生活服务
        val query = PoiSearch.Query("", "商务住宅|餐饮服务|生活服务")
        query.pageSize = pageSize
        query.pageNum = pageNum
        val poiSearch = PoiSearch(activity, query)
        poiSearch.bound = searchBound
        poiSearch.setOnPoiSearchListener(object : PoiSearch.OnPoiSearchListener {
            override fun onPoiSearched(poiResult: PoiResult, i: Int) {
                getSwipeLayout()?.isRefreshing = false
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
