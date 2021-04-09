package com.fuze.ttapp.ui.tweetsearch




import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.fuze.ttapp.R
import com.fuze.ttapp.api.models.Twit
import com.fuze.ttapp.api.models.TwitterProfile
import com.fuze.ttapp.ui.MainActivity
import kotlinx.android.synthetic.main.activity_user_profile.*
import javax.inject.Inject

class UserProfileActivity : MainActivity<UserProfileViewModel>()  {
    @Inject   lateinit var userprofilerViewModel:UserProfileViewModel
    override fun getViewModel(): UserProfileViewModel  = userprofilerViewModel


    private var mAdapter: UserTweetAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        val user = intent.getStringExtra(USER)
        val username = findViewById<TextView>(R.id.username)
        val backButton= findViewById<ImageView>(R.id.backbutton)
        backbutton.setOnClickListener {
            this.onBackPressed();
        }
        username.text = user
        userprofilerViewModel.searchUser(user.substring(1))
        setupUI()

    }

    private fun setupUI(){

        userprofilerViewModel.searchUserObservable().observe(this, Observer {

                setData(it)

        })
        userprofilerViewModel.searchTweetObservable().observe(this, Observer {

            setTweets(it)
        })
        setUpRecyclerView()

    }
    private fun setUpRecyclerView(){
        var linearLayoutManager= LinearLayoutManager(this)
        usertweets.layoutManager = linearLayoutManager
        mAdapter = UserTweetAdapter(this)
        usertweets.adapter = mAdapter
        usertweets.setNestedScrollingEnabled(false);
    }
    private fun setData(it: TwitterProfile){

        val name :TextView = findViewById(R.id.name)
        val profileImg :ImageView = findViewById(R.id.userImage)
        val banner :ImageView = findViewById(R.id.banner)
        val description :TextView = findViewById(R.id.description)
        val location :TextView = findViewById(R.id.location)
        val friends :TextView = findViewById(R.id.friends)
        val followers :TextView = findViewById(R.id.followers)
        name.text = it.userName
        description.text = it.description
        location.text = it.location
        friends.text = it.following.toString()
        followers.text = it.followers.toString()
        //get better quality image
        val biggerImgUrl = it.imageUrl?.replace("_normal","",true)
        val biggerBannerUrl = it.banner?.replace("_normal","",true)
        Glide.with(this)
            .load(biggerImgUrl)
            .centerCrop()
            .into(profileImg)
        Glide.with(this)
            .load(biggerBannerUrl)
            .centerCrop()
            .into(banner)
    }
    fun setTweets(it: List<Twit>?){
        it?.let { it1 ->  mAdapter?.tweets = it1 }
    }
    override fun onBackPressed() {
        super.onBackPressed()

        unsubscribeUser()
    }

    fun unsubscribeUser(){
        userprofilerViewModel.unSubscribeUser()
    }
}