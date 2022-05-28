package br.com.sam.expenses.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Entry(
    @SerializedName("id") val id: Int?,
    @SerializedName("valor") val value: Double?,
    @SerializedName("origem") val origin: String?,
    @SerializedName("categoria") val category: Int?,
    @SerializedName("mes_lancamento") val month: Int?,
) : Parcelable
