package com.riyaldi.storyapp.utils

import com.riyaldi.storyapp.data.remote.response.login.LoginResponse
import com.riyaldi.storyapp.data.remote.response.login.LoginResult
import com.riyaldi.storyapp.data.remote.response.signup.SignUpResponse
import com.riyaldi.storyapp.data.remote.response.stories.PostStoryResponse
import com.riyaldi.storyapp.data.remote.response.stories.StoriesResponse
import com.riyaldi.storyapp.data.remote.response.stories.Story

object DataDummy {
    fun generateDummyStories(): StoriesResponse {
        val listStory = ArrayList<Story>()
        for (i in 1..20) {
            val story = Story(
                createdAt = "2022-02-22T22:22:22Z",
                description = "Description $i",
                id = "id_$i",
                lat = i.toDouble() * 10,
                lon = i.toDouble() * 10,
                name = "Name $i",
                photoUrl = "https://akcdn.detik.net.id/visual/2020/02/14/066810fd-b6a9-451d-a7ff-11876abf22e2_169.jpeg?w=650"
            )
            listStory.add(story)
        }

        return StoriesResponse(
            error = false,
            message = "Stories fetched successfully",
            listStory = listStory
        )
    }

    fun generateDummyCreateStory(): PostStoryResponse {
        return PostStoryResponse(
            error = false,
            message = "success"
        )
    }

    fun generateDummyRegister(): SignUpResponse {
        return SignUpResponse(
            error = false,
            message = "User Created"
        )
    }

    fun generateDummyLogin(): LoginResponse {
        return LoginResponse(
            error = false,
            message = "success",
            loginResult = LoginResult(
                userId = "user-yj5pc_LARC_AgK61",
                name = "Arif Faizin",
                token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJ1c2VyLXlqNXBjX0xBUkNfQWdLNjEiLCJpYXQiOjE2NDE3OTk5NDl9.flEMaQ7zsdYkxuyGbiXjEDXO8kuDTcI__3UjCwt6R_I"
            )
        )
    }
}