package br.com.sam.expenses.feature.detailedentries.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    @SerializedName("id") val id: Int?,
    @SerializedName("nome") val name: String?
) : Parcelable
