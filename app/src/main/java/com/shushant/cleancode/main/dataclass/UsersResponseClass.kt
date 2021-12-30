package com.shushant.cleancode.main.dataclass

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UsersResponseClass(
    @SerializedName("meta")
    @Expose
    var metaDataClass: MetaDataClass,
    @SerializedName("data")
    @Expose
    var userDataList: List<UserDataClass>
)
