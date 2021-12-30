package com.shushant.cleancode.main.dataclass

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MetaDataClass(
    @SerializedName("pagination")
    @Expose
    var pagination:PaginationDataClass
)
