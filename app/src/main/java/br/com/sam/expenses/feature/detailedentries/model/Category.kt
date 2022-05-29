package br.com.sam.expenses.feature.detailedentries.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    var id: Int = 0,
    var name: String = "-",
    var amout: Double = 0.0
) : Parcelable
