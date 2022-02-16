package com.digimaster.projectdia.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.digimaster.projectdia.MovieDetailActivity
import com.digimaster.projectdia.R
import com.digimaster.projectdia.activity.HomeActivity
import com.digimaster.projectdia.adapter.MovieAdapter
import com.digimaster.projectdia.adapter.MovieClickListener
import com.digimaster.projectdia.databinding.FragmentMovieBinding
import com.digimaster.projectdia.model.MovieModel
import com.digimaster.projectdia.model.MovieResponseModel
import com.digimaster.projectdia.utils.MovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieFragment : Fragment(), MovieClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    private var listMovie: List<MovieModel>? = null
    private var adapter : MovieAdapter = MovieAdapter()

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
        // Inflate the layout for this fragment
        _binding = FragmentMovieBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvMovie.layoutManager = layoutManager
        adapter.movieClickListener = this
        binding.rvMovie.adapter = adapter

        val services = MovieRepository.create()
        services.getPopularMovie(page = 1).enqueue(object: Callback<MovieResponseModel>{
            override fun onResponse(
                call: Call<MovieResponseModel>,
                response: Response<MovieResponseModel>
            ) {
                if (response.isSuccessful){
                    val data =response.body()
                    listMovie = data?.movieModels
                    adapter?.updateData(listMovie)

                }
            }

            override fun onFailure(call: Call<MovieResponseModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MovieFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MovieFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClickItem(movieId: Int) {
        Toast.makeText(requireContext(), "Clicked $movieId", Toast.LENGTH_SHORT).show()
//        val intent = Intent(this, MovieDetailActivity::class.java)
//        startActivity(intent)
    }



}