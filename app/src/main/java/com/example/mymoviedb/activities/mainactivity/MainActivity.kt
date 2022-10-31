package com.example.mymoviedb.activities.mainactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import com.bumptech.glide.load.engine.Resource
import com.example.mymoviedb.R
import com.example.mymoviedb.activities.SearchResultActivity
import com.example.mymoviedb.databinding.ActivityMainBinding
import com.example.mymoviedb.utils.Constants

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        supportFragmentManager.beginTransaction().replace(R.id.frag_main,MainMovieFragment()).commit()
        val searchView = findViewById<SearchView>(R.id.seacrh_main)
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val intent = Intent(this@MainActivity, SearchResultActivity::class.java)
                intent.putExtra(Constants.SEARCH_QUERY,query)
                startActivity(intent)
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        binding?.cvMainmoviebtn?.setOnClickListener {
            val fragment = supportFragmentManager.beginTransaction().replace(R.id.frag_main,MainMovieFragment())
            binding?.cvMainmoviebtn?.setCardBackgroundColor(ContextCompat.getColor(this,R.color.primary1))
            binding?.tvMainmovieBtntext?.setTextColor(ContextCompat.getColor(this,R.color.primary2))
            binding?.cvMainseriesbtn?.setCardBackgroundColor(ContextCompat.getColor(this,R.color.primary2))
            binding?.tvMainseriesBtntext?.setTextColor(ContextCompat.getColor(this,R.color.primary1))
            fragment.commit()
        }
        binding?.cvMainseriesbtn?.setOnClickListener {
            val fragment = supportFragmentManager.beginTransaction().replace(R.id.frag_main,MainSeriesFragment())
            binding?.cvMainmoviebtn?.setCardBackgroundColor(ContextCompat.getColor(this,R.color.primary2))
            binding?.tvMainmovieBtntext?.setTextColor(ContextCompat.getColor(this,R.color.primary1))
            binding?.cvMainseriesbtn?.setCardBackgroundColor(ContextCompat.getColor(this,R.color.primary1))
            binding?.tvMainseriesBtntext?.setTextColor(ContextCompat.getColor(this,R.color.primary2))
            fragment.commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}
