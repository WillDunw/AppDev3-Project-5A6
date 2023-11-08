package com.example.emptyactivity.DataModels

import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun savePost(post: Post) : Boolean
    suspend fun getAllPosts() : Flow<List<Post>>
    fun getAllPostsLiked(userId: String) : List<Post>
    suspend fun likePost(post: Post)
    suspend fun delete(id: String)
}