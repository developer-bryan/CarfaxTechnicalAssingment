package com.bryanmills.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import com.bryanmills.details.R

class KeyValueView(ctx: Context, set: AttributeSet?, defStyle: Int) : ConstraintLayout(ctx, set, defStyle) {

    constructor(ctx: Context, set: AttributeSet?) : this(ctx, set, 0)

    constructor(ctx: Context) : this(ctx, null)

    init {
        inflate(ctx, R.layout.layout_vehicle_breakdown_item, this)
        with(ctx.obtainStyledAttributes(set, R.styleable.KeyValueView)) {
            if (getIndex(0) == R.styleable.KeyValueView_labelValue) {
                findViewById<TextView>(R.id.label).text = getString(R.styleable.KeyValueView_labelValue)
            }
            recycle()
        }
    }

    fun setValue(text: String) {
        findViewById<TextView>(R.id.value).text = text
    }

}