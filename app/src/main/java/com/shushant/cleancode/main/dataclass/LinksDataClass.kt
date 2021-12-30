package com.shushant.cleancode.main.dataclass

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LinksDataClass(
    @SerializedName("previous")
    @Expose
    var previous: String?,
    @SerializedName("current")
    @Expose
    var current: String,
    @SerializedName("next")
    @Expose
    var next: String?
)
