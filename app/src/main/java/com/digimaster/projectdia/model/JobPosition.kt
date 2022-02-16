package com.digimaster.projectdia.model

import java.util.*

data class JobPosition(
    var id: Int = getAutoId(),
    var jobName: String, var image: String, var company: String
){
    companion object{
        fun getAutoId(): Int{
            val random = Random()
            return random.nextInt(100)
        }
    }
}
