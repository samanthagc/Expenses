package br.com.sam.expenses.feature.detailedentries.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Month(
    val id: Int = 0,
    val name: String = "",
    val amout: Double = 0.0
) : Parcelable
