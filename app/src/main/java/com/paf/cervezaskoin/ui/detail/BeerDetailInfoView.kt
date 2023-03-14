package com.paf.cervezaskoin.ui.detail

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.paf.cervezaskoin.R
import com.paf.cervezaskoin.data.entities.Beer


class BeerDetailInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    fun setBeer(beer: Beer) = with(beer) {
        text = buildSpannedString {

            bold { append("${context.getString(R.string.text_abu)}: ") }
            appendLine(abu.toString())

            bold { append("${context.getString(R.string.text_ibu)}: ") }
            appendLine(ibu.toString())

            bold { append("${context.getString(R.string.text_food_pairing)}: ") }
            appendLine()

            for (food in foodPairing)
                appendLine(" * $food")
        }
    }
}