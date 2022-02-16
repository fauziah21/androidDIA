package com.digimaster.projectdia.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.digimaster.projectdia.R
import com.digimaster.projectdia.adapter.ListAdapter
import com.digimaster.projectdia.databinding.FragmentHomeBinding
import com.digimaster.projectdia.model.JobPosition
import com.digimaster.projectdia.utils.SQLiteHelper

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding:FragmentHomeBinding
    var adapter: ListAdapter? = null
    var layoutManager : RecyclerView.LayoutManager? = null

    private lateinit var sqlitehelper : SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sqlitehelper = SQLiteHelper(requireContext())
//        val listJob = listOf(
//            JobPosition("Chef","https://www.it-jurnal.com/wp-content/uploads/2014/04/programmer-terbaik-di-dunia.jpg", "Kopiku"),
//            JobPosition("Programmer","https://www.it-jurnal.com/wp-content/uploads/2014/04/programmer-terbaik-di-dunia.jpg", "Garena")
//        )
        layoutManager = LinearLayoutManager(context)
        adapter = ListAdapter(sqlitehelper.getAllJobs(), requireContext())
        binding.rvList.adapter = adapter
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapter
        }

        binding.btnAddData.setOnClickListener{
            addJob()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addJob(){
        val jobName = binding.etJobName.text.toString()
        val image = binding.etImage.text.toString()
        val company = binding.etCompany.text.toString()

        val job = JobPosition(jobName = jobName, image = image, company = company)
        val status = sqlitehelper.insertJob(job)

        if (status > -1){
            Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
            adapter?.arrayList = sqlitehelper.getAllJobs()
            adapter?.notifyDataSetChanged()
        }else{
            Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}