package com.digimaster.projectdia.utils

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.digimaster.projectdia.model.JobPosition

class SQLiteHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "job.db"
        private const val TBL_JOB = "tbl_job"
        private const val ID = "id"
        private const val JOB_NAME = "job_name"
        private const val IMAGE = "image"
        private const val COMPANY = "company"

    }
    override fun onCreate(p0: SQLiteDatabase?) {
        val createTblJob = "CREATE TABLE $TBL_JOB ($ID INTEGER PRIMARY KEY, " +
        "$JOB_NAME TEXT, "+
        "$IMAGE TEXT, "+
        "$COMPANY TEXT)"
        p0?.execSQL(createTblJob)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS $TBL_JOB")
        onCreate(p0)
    }

    fun insertJob(job: JobPosition): Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, job.id)
        contentValues.put(JOB_NAME, job.jobName)
        contentValues.put(IMAGE, job.image)
        contentValues.put(COMPANY, job.company)
        val success = db.insert(TBL_JOB, null, contentValues)
        db.close()
        return success
    }

    //fungsi select
    @SuppressLint("Range")
    fun getAllJobs(): ArrayList<JobPosition>{
        val jobList: ArrayList<JobPosition> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_JOB"
        val db = this.writableDatabase

        var cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)

        }catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var jobName: String
        var image: String
        var company: String

        if (cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex("id"))
                jobName = cursor.getString(cursor.getColumnIndex("job_name"))
                image = cursor.getString(cursor.getColumnIndex("image"))
                company = cursor.getString(cursor.getColumnIndex("company"))
                val job = JobPosition(id= id, jobName = jobName, image = image, company = company)
                jobList.add(job)
            }while (cursor.moveToNext())
        }

        return jobList

    }
}