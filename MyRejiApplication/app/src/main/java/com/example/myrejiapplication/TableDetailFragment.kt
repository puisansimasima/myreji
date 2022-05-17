package com.example.myrejiapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.myrejiapplication.databinding.FragmentShopManagerBinding
import com.example.myrejiapplication.databinding.FragmentTableDetailBinding
import com.google.firebase.FirebaseError
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TableDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private lateinit var fireDatabase: DatabaseReference

class TableDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    private var _binding: FragmentTableDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTableDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addNewTable.setOnClickListener {




        writeNewItem(binding.tableName.text.toString() )







          //  val database = Firebase.database
          //  val myRef = database.getReference("table")

          //  val tableName=binding.tableName.text.toString()
          //  myRef.push().setValue(tableName)
           // myRef.setValue(tableName)
        }
    }

    private fun writeNewItem(tableName: String) {
      //  val firebaseItem = FirebaseItem(itemName)
        fireDatabase=Firebase.database.reference
        fireDatabase.child("table").push().setValue(tableName)

        val list:List<String>
            =listOf(tableName,"id")

        fireDatabase.child("list").push().setValue(list)



        //myRef1.setValue(list)


    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TableDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TableDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}