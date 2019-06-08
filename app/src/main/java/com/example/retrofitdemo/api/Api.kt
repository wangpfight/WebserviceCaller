package com.example.retrofitdemo.api

import com.example.webservicecaller.getRetroAdapter
import com.example.webservicecaller.getServiceCaller
import okhttp3.ResponseBody
import com.example.retrofitdemo.api.requestmodels.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

val baseUrl = " https://equine.fitser.com"

val userImageUrl = baseUrl + "uploads/"
val imageBaseUrl = userImageUrl
val videoUrl = baseUrl + "videos/"


val DEFAULT_TIME_OUT: Long = 3000L

fun getRestCaller(connectionTime: Long = DEFAULT_TIME_OUT): API {
    val adapter = getRetroAdapter(baseUrl, connectionTime)
    return getServiceCaller(adapter, API::class.java)
}

/*object EquineApi {
    val horseList: String = "apieq/horselist"
    val horseBreed: String = "com.example.retrofitdemo.api/horsebreed"
    val horseHeightHH: String = "com.example.retrofitdemo.api/horsehh"
    val horseColor: String = "com.example.retrofitdemo.api/horsecolor"
    val horseSex: String = "com.example.retrofitdemo.api/horsesex"


    val providerType : String = "com.example.retrofitdemo.api/providertypelist"
    val userAssistantList:String ="com.example.retrofitdemo.api/assistance_list"
    val providerTypeList:String ="com.example.retrofitdemo.api/providerjobptype"
    val providerList:String="com.example.retrofitdemo.api/providernameget"

    val horseDetails = "apieq/horsedetails "
    val horseHeightCM = "com.example.retrofitdemo.api/horsecm"
    val deleteHorse = "com.example.retrofitdemo.api/horsedelete"
    val contract_list_owner = "com.example.retrofitdemo.api/contract_list_owner"

    val trainerList = "com.example.retrofitdemo.api/trainernameget"
    val serviceTypeList: String = "com.example.retrofitdemo.api/getservicetype"

    val acceptContract: String = "com.example.retrofitdemo.api/trainercontractaccept"
    val rejectContract: String = "com.example.retrofitdemo.api/trainercontractreject"
    val trainerContractNegotiate = "com.example.retrofitdemo.api/trainercontractnegotiateupdate"

    val providerJobrequest = "com.example.retrofitdemo.api/provider_jobrequest"
    val acceptJobrequest = "com.example.retrofitdemo.api/requestedjob_accept"
    val rejectJobrequest = "com.example.retrofitdemo.api/requestedjob_reject"
    val negotiateJob = "com.example.retrofitdemo.api/updatetask_negotiation"
}*/


interface API {
    @POST("com/example/retrofitdemo/api/login")
    fun doLogin(@Body loginRequest: LoginRequest): Observable<ResponseBody>

  /*  @POST("com.example.retrofitdemo.api/register")
    fun doOwnerRegistration(@Body registrationRequest: OwnerRegistrationRequest): Observable<ResponseBody>


    @POST("com.example.retrofitdemo.api/registerpro")
    fun doProviderRegistration(@Body registrationRequest: ProviderRegistrationRequest): Observable<ResponseBody>

    @POST("com.example.retrofitdemo.api/forget_pass")
    fun doForgotPassword(@Body forgotPasswordRequest: ForgotPasswordRequest): Observable<ResponseBody>

    @POST("com.example.retrofitdemo.api/userdetails")
    fun getUserDetails(@Body userIdRequest: UserIdRequest): Observable<ResponseBody>

    @POST("com.example.retrofitdemo.api/provider_list")
    fun getProviderList(@Body userIdRequest: UserIdRequest): Observable<ResponseBody>

    @POST("com.example.retrofitdemo.api/invitelist")
    fun getInvitesList(@Body userIdRequest: UserIdRequest): Observable<ResponseBody>

    @POST("com.example.retrofitdemo.api/my_provider_list")
    fun getMyProviderList(@Body userIdRequest: UserIdRequest): Observable<ResponseBody>

    @POST("com.example.retrofitdemo.api/add_provider")
    fun addProvider(@Body addProviderRequest: AddProviderRequest): Observable<ResponseBody>

    @POST("com.example.retrofitdemo.api/delete_provider")
    fun deleteProvider(@Body providerIdRequest: ProviderIdRequest): Observable<ResponseBody>

    @POST("com.example.retrofitdemo.api/assistance_list")
    fun assistantList(@Body userIdRequest: UserIdRequest): Observable<ResponseBody>

    @POST("com.example.retrofitdemo.api/assistance_update")
    fun assistantUpdate(@Body addAssistantRequest: AddAssistantRequest): Observable<ResponseBody>

    @POST("com.example.retrofitdemo.api/search_provider")
    fun searchProviderLocation(@Body searchProviderLocationRequest: SearchProviderLocationRequest): Observable<ResponseBody>

    @POST("com.example.retrofitdemo.api/search_provider_sr_location")
    fun searchProviderServiceLocation(@Body searchProviderLocationRequest: SearchProviderLocationRequest): Observable<ResponseBody>

    @POST("com.example.retrofitdemo.api/add_provider")
    fun addProvider(@Body searchProviderLocationRequest: SearchProviderLocationRequest): Observable<ResponseBody>


    @POST("com.example.retrofitdemo.api/add_invite_email")
    fun addProviderInviteMail(@Body addInviteProviderMailRequest: AddInviteProviderMailRequest): Observable<ResponseBody>

    @POST("com.example.retrofitdemo.api/add_invite_phone")
    fun addProviderInvitePhone(@Body addInviteProviderPhoneRequest: AddInviteProviderPhoneRequest): Observable<ResponseBody>

    @POST("com.example.retrofitdemo.api/ow_sow_joblist")
    fun getProviderJobList(@Body getProviderJobListRequest: GetProviderJobListRequest): Observable<ResponseBody>

    @POST("com.example.retrofitdemo.api/ow_sow_assis_joblist")
    fun getAssistantJobList(@Body getAssistantJobListRequest: GetAssistantJobListRequest): Observable<ResponseBody>

    @POST("com.example.retrofitdemo.api/taskslistbyjob")
    fun getJobtaskList(@Body jobIdRequest: JobIdRequest): Observable<ResponseBody>

    @POST("com.example.retrofitdemo.api/taskdel")
    fun deletetask(@Body deleteTaskIdRequest: DeleteTaskIdRequest): Observable<ResponseBody>

    @POST("com.example.retrofitdemo.api/jobdetailsfull")
    fun jobDetails(@Body jobDetailsRequest: JobDetailsRequest): Observable<ResponseBody>



    @Multipart
    @POST("com.example.retrofitdemo.api/addsingltsk")
    fun addjobTask(@Part("job_id") job_id: RequestBody,
                   @Part("sl_horse") sl_horse: RequestBody,
                   @Part("srv_type") srv_type: RequestBody,
                   @Part("start_task_date") start_task_date: RequestBody,
                   @Part("task_start_time") task_start_time: RequestBody,
                   @Part("task_end_date") task_end_date: RequestBody,
                   @Part("task_end_time") task_end_time: RequestBody,
                   @Part("task_job_type") task_job_type: RequestBody,
                   @Part("task_cost") task_cost: RequestBody,
                   @Part("task_description") task_description: RequestBody,
                   @Part("files") files: RequestBody,
                   @Part image: MultipartBody.Part): Observable<ResponseBody>


    @Multipart
    @POST("com.example.retrofitdemo.api/update_owner_pdetails")
    fun updateOwnerProfileStepOne(@Part("user_id") user_id: RequestBody,
                                  @Part("cgname") cgname: RequestBody,
                                  @Part("fname") fname: RequestBody,
                                  @Part("mname") mname: RequestBody,
                                  @Part("famname") famname: RequestBody,
                                  @Part("dob") dob: RequestBody,
                                  @Part("phone") phone: RequestBody,
                                  @Part("email") email: RequestBody,
                                  @Part("image_path") image_path: RequestBody,
                                  @Part("uabout") uabout: RequestBody,
                                  @Part image: MultipartBody.Part): Observable<ResponseBody>

    @Multipart
    @POST("com.example.retrofitdemo.api/update_user")
    fun updateOwnerProfileStepTwo(@Part("user_id") user_id: RequestBody,
                                  @Part("address") address: RequestBody,
                                  @Part("town") town: RequestBody,
                                  @Part("zip") zip: RequestBody,
                                  @Part("country") country: RequestBody,
                                  @Part("state") state: RequestBody,
                                  @Part("long") long: RequestBody,
                                  @Part("lat") lat: RequestBody): Observable<ResponseBody>


    @Multipart
    @POST("com.example.retrofitdemo.api/initializetrainerprovidjob")
    fun addNewJob(@Part("user_id") user_id: RequestBody,
                  @Part("mapLat") mapLat: RequestBody,
                  @Part("mapLng") mapLng: RequestBody,
                  @Part("mapLat1") mapLat1: RequestBody,
                  @Part("mapLng1") mapLng1: RequestBody,
                  @Part("map_address") map_address: RequestBody,
                  @Part("map_address1") map_address1: RequestBody,
                  @Part("job_horse") job_horse: RequestBody,
                  @Part("job_provider_type") job_provider_type: RequestBody,
                  @Part("job_provider_name") job_provider_name: RequestBody,
                  @Part("job_user_assistant") job_user_assistant: RequestBody): Observable<ResponseBody>


    @Multipart
    @POST("com.example.retrofitdemo.api/update_user")
    fun updateUser(@Part("user_id") user_id: RequestBody,
                   @Part("first_name") first_name: RequestBody,
                   @Part("middle_name") middle_name: RequestBody,
                   @Part("family_name") family_name: RequestBody,
                   @Part("dob") dob: RequestBody,
                   @Part("mobile") mobile: RequestBody,
                   @Part("address") address: RequestBody,
                   @Part("town") town: RequestBody,
                   @Part("pin") pin: RequestBody,
                   @Part("business_name") business_name: RequestBody,
                   @Part("tax_number") tax_number: RequestBody,
                   @Part("image_path") image_path: RequestBody,
                   @Part("email") email: RequestBody,
                   @Part image: MultipartBody.Part): Observable<ResponseBody>

    @Multipart
    @POST("com.example.retrofitdemo.api/assistance_insert")
    fun addAssistant(@Part("user_id") user_id: RequestBody,
                     @Part("user_role_name") user_role_name: RequestBody,
                     @Part("first_name") first_name: RequestBody,
                     @Part("family_name") family_name: RequestBody,
                     @Part("mobile") mobile: RequestBody,
                     @Part("email") email: RequestBody,
                     @Part("password") password: RequestBody,
                     @Part("image_path") image_path: RequestBody,
                     @Part image: MultipartBody.Part): Observable<ResponseBody>

    @Multipart
    @POST("com.example.retrofitdemo.api/assistance_update")
    fun updateAssistant(@Part("user_id") user_id: RequestBody,
                        @Part("first_name") first_name: RequestBody,
                        @Part("family_name") family_name: RequestBody,
                        @Part("mobile") mobile: RequestBody,
                        @Part("email") email: RequestBody,
                        @Part("password") password: RequestBody,
                        @Part("image_path") image_path: RequestBody?,
                        @Part image: MultipartBody.Part?): Observable<ResponseBody>

    @Multipart
    @POST("com.example.retrofitdemo.api/update_user")
    fun updateUserWithoutImage(@Part("user_id") user_id: RequestBody,
                               @Part("first_name") first_name: RequestBody,
                               @Part("middle_name") middle_name: RequestBody,
                               @Part("family_name") family_name: RequestBody,
                               @Part("dob") dob: RequestBody,
                               @Part("mobile") mobile: RequestBody,
                               @Part("address") address: RequestBody,
                               @Part("town") town: RequestBody,
                               @Part("pin") pin: RequestBody,
                               @Part("business_name") business_name: RequestBody,
                               @Part("tax_number") tax_number: RequestBody,
                               @Part("email") email: RequestBody): Observable<ResponseBody>


    @POST
    fun callService(@Url method: String, @Body objectOfT: Any): Observable<ResponseBody>


    @Multipart
    @POST("com.example.retrofitdemo.api/addhorseprofile")
    fun addHorseProfile(@Part("user_id") user_id: RequestBody,
                        @Part("mapLat") mapLat: RequestBody,
                        @Part("mapLng") mapLng: RequestBody,
                        @Part("ageyr") ageyr: RequestBody,
                        @Part("map_address") map_address: RequestBody,
                        @Part("horse_name") horse_name: RequestBody,
                        @Part("horse_pet_name") horse_pet_name: RequestBody,
                        @Part("horse_desc") horse_desc: RequestBody,
                        @Part("horse_michip") horse_michip: RequestBody,
                        @Part("horse_dob") horse_dob: RequestBody,
                        @Part("horse_sex") horse_sex: RequestBody,
                        @Part("horse_color") horse_color: RequestBody,
                        @Part("horse_breed") horse_breed: RequestBody,
                        @Part("horse_hh") horse_hh: RequestBody,
                        @Part("horse_cm") horse_cm: RequestBody,
                        @Part("horse_weight") horse_weight: RequestBody,
                        @Part("horse_stadd") horse_stadd: RequestBody,
                        @Part("horse_town") horse_town: RequestBody,
                        @Part("horse_state") horse_state: RequestBody,
                        @Part("horse_country") horse_country: RequestBody,
                        @Part("horse_paddock") horse_paddock: RequestBody,
                        @Part("horse_stblnum") horse_stblnum: RequestBody,
                        @Part("horse_rshoe") horse_rshoe: RequestBody,
                        @Part("horse_decepline") horse_decepline: RequestBody,
                        @Part("horse_ftype") horse_ftype: RequestBody,
                        @Part("horse_father") horse_father: RequestBody,
                        @Part("horse_mother") horse_mother: RequestBody,
                        @Part("f_grand_father") f_grand_father: RequestBody,
                        @Part("f_grand_mother") f_grand_mother: RequestBody,
                        @Part("m_grand_father") m_grand_father: RequestBody,
                        @Part("m_grand_mother") m_grand_mother: RequestBody,
                        @Part("ff_great_grand_father") ff_great_grand_father: RequestBody,
                        @Part("ff_great_grand_mother") ff_great_grand_mother: RequestBody,
                        @Part("fm_great_grand_father") fm_great_grand_father: RequestBody,
                        @Part("fm_great_grand_mother") fm_great_grand_mother: RequestBody,
                        @Part("mf_great_grand_father") mf_great_grand_father: RequestBody,
                        @Part("mm_great_grand_father") mm_great_grand_father: RequestBody,
                        @Part("mm_great_grand_mother") mm_great_grand_mother: RequestBody,
                        @Part userFile: List<MultipartBody.Part>?,
                        @Part uservfile: List<MultipartBody.Part>?): Observable<ResponseBody>

    @Multipart
    @POST("com.example.retrofitdemo.api/addhorseprofile")
    fun createHorseStepOne(
        @Part("user_id") user_id: RequestBody,
        @Part("horse_name") horse_name: RequestBody,
        @Part("horse_pet_name") horse_pet_name: RequestBody,
        @Part("horse_desc") horse_desc: RequestBody): Observable<ResponseBody>


    @Multipart
    @POST("com.example.retrofitdemo.api/updatehorseprofile")
    fun updateHorseMedia(
        @Part("user_id") user_id: RequestBody,
        @Part("horse_id") horse_id: RequestBody,
        @Part userFile: List<MultipartBody.Part>?,
        @Part uservfile: List<MultipartBody.Part>?): Observable<ResponseBody>



    @Multipart
    @POST("com.example.retrofitdemo.api/addcontractsubmit")
    fun addContractSubmit(
        @Part("user_id") user_id: RequestBody,
        @Part("contract_horse") contract_horse: RequestBody,
        @Part("contract_trainer") contract_trainer: RequestBody,
        @Part("contract_assistant") contract_assistant: RequestBody,
        @Part("contract_servicetype") contract_servicetype: RequestBody,
        @Part("mapLat") mapLat: RequestBody,
        @Part("mapLng") mapLng: RequestBody,
        @Part("map_address") map_address: RequestBody,
        @Part("contract_location") contract_location: RequestBody,
        @Part("contract_type") contract_type: RequestBody,
        @Part("contract_weeks") contract_weeks: RequestBody,
        @Part("contract_start_date") contract_start_date: RequestBody,
        @Part("contract_end_date") contract_end_date: RequestBody,
        @Part("contract_costweek") contract_costweek: RequestBody,
        @Part("contract_totalcost") contract_totalcost: RequestBody,
        @Part files: List<MultipartBody.Part>?): Observable<ResponseBody>*/
}
