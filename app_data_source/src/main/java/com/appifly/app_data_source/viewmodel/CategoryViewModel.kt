package com.appifly.app_data_source.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.appifly.app_data_source.datamapper.toDto
import com.appifly.app_data_source.dto.CategoryDto
import com.appifly.app_data_source.dto.ChannelDto
import com.appifly.cachemanager.dao.CategoryDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    categoryDao: CategoryDao,
) : ViewModel() {

    private val _channelCategoryName = MutableLiveData<String>()

    val channelCategoryName: LiveData<String>
        get() = _channelCategoryName


   private  val _videoUrl = MutableLiveData<String>("https://ndtvindiaelemarchana.akamaized.net/hls/live/2003679/ndtvindia/master.m3u8")


    /*         val exoPlayer = remember { ExoPlayer.Builder(context).build() }

            // Set up observer for video URI changes

            DisposableEffect(key1 = Unit) {
                // Clean up the ExoPlayer when the composable is disposed
                onDispose {
                    exoPlayer.release()
                }
            }

            AndroidView(
                factory = { context ->
                    PlayerView(context).apply {
                        player = exoPlayer
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                    }
                },
                modifier = Modifier.fillMaxSize().height(300.dp)
            )

            LaunchedEffect(viewModel.videoUrl.observeAsState().value) {
                if (viewModel.videoUrl.value != null) {
                    val mediaItem =MediaItem.fromUri(Uri.parse(viewModel.videoUrl.value))
                    exoPlayer.setMediaItem(mediaItem)
                    exoPlayer.prepare()
                    exoPlayer.play()
                }
            }*/
    val videoUrl: LiveData<String>
        get() = _videoUrl

    val categoryData = categoryDao.getAllCategory()?.map { it -> it.map { it.toDto() } }


    fun setUrl(url:String){
        _videoUrl.value=url
    }

    private val _favoriteCategoryList = MutableLiveData<List<CategoryDto>>()

    val favoriteCategoryList: LiveData<List<CategoryDto>>
        get() = _favoriteCategoryList

    fun setCategoryName(categoryName: String?) {
        _channelCategoryName.value = categoryName ?: "N/A"
    }

    fun setFavoriteCategory(favoriteChannel: List<ChannelDto>) {
        val catList = ArrayList<CategoryDto>()
        for (item in favoriteChannel) {
            for (data in categoryData?.value!!) {
                if (item.catId == data.id) {
                    catList.add(data)
                    break
                }
            }
        }

        val removeDuplicate = catList.toHashSet()
        _favoriteCategoryList.value = removeDuplicate.toList()
    }

}