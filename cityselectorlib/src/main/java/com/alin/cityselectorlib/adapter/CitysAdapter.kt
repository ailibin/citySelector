package com.example.myinterviewdemo.cityselector.adapter

import android.content.Context
import android.widget.TextView
import com.aiitec.jpmj.entitylibary.model.CharCitys
import com.alin.cityselectorlib.R
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * @author ailibin
 */
class CitysAdapter(context: Context, datas: MutableList<CharCitys>) :
    BaseQuickAdapter<CharCitys, BaseViewHolder>(
        R.layout.item_logistics,
        datas
    ) {

    override fun convert(holder: BaseViewHolder, item: CharCitys) {
        val tvItemLogistics = holder?.getView<TextView>(R.id.tvItemLogistics)
        tvItemLogistics?.text = item?.name
    }

}