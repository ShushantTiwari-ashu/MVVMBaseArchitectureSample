package com.assignment.userassignment.main.dataclass

import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PaginationDataClass(
    @SerializedName("total")
    @Expose
    var total: Int,
    @SerializedName("pages")
    @Expose
    var pages: Int,
    @PrimaryKey
    @SerializedName("page")
    @Expose
    var page: Int,
    @SerializedName("limit")
    @Expose
    var limit: Int,
    @SerializedName("links")
    @Expose
    var links: LinksDataClass)
