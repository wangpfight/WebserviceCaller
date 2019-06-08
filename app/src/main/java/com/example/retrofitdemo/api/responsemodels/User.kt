package com.example.retrofitdemo.api.responsemodels

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/*fun Context.saveUser(user: User) {
    _editablePreference {
        putObject(User::class.java.simpleName to user)
    }
}

fun Context.clearUserData() {
    _editablePreference {
        clear()
    }
}

fun Context.getSavedUser(): User? {
    var user: User? = null
    try {
        user = getSharedPreference().getObject<User>(User::class.java.simpleName) as User
    } catch (e: Exception) {
        Log.d("Error", Log.getStackTraceString(e))
    }
    return user
}*/

class User : Serializable {

    @field:SerializedName("about")
    var about: String = ""

    @field:SerializedName("user_role_id")
    var userRoleId: String = ""

    @field:SerializedName("password")
    var password: String = ""

    @field:SerializedName("pin")
    var pin: String = ""

    @field:SerializedName("created_ts")
    var createdTs: String = ""

    @field:SerializedName("tax_number")
    var taxNumber: String = ""

    @field:SerializedName("state_id")
    var stateId: String = ""

    @field:SerializedName("first_name")
    var firstName: String = ""

    @field:SerializedName("email")
    var email: String = ""

    @field:SerializedName("linked_provider")
    var linkedProvider: String = ""

    @field:SerializedName("updated_ts")
    var updatedTs: String = ""

    @field:SerializedName("business_name")
    var businessName: String = ""

    @field:SerializedName("address")
    var address: String = ""

    @field:SerializedName("is_active")
    var isActive: String = ""

    @field:SerializedName("town")
    var town: String = ""

    @field:SerializedName("mobile")
    var mobile: String = ""

    @field:SerializedName("middle_name")
    var middleName: String = ""

    @field:SerializedName("created_by")
    var createdBy: String = ""

    @field:SerializedName("user_id")
    var userId: String = "0"

    @field:SerializedName("dob")
    var dob: String = ""

    @field:SerializedName("image_path")
    var imagePath: String = ""

    @field:SerializedName("updated_by")
    var updatedBy: String = ""

    @field:SerializedName("family_name")
    var familyName: String = ""

    @field:SerializedName("user_profile_id")
    var userProfileId: String = ""

    @field:SerializedName("country_id")
    var countryId: String = ""


    override fun toString(): String {
        return "User(taxNumber=$taxNumber, firstName=$firstName, email=$email, businessName=$businessName, isActive=$isActive, mobile=$mobile)"
    }


}


