package Models

data class UserRequest(val type:Int,
                       val username:String,
                       val password:String,
                       val name:String,
                       val surname:String,
                       val phone_number:String?,
                       val office:String?,
                       val email:String,
                       val is_associated:Boolean?,
                       val is_deputy:Boolean?){}