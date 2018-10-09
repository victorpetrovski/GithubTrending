package com.victor.data.mapper

import com.victor.data.model.GithubUser
import com.victor.domain.model.GithubUserEntity
import javax.inject.Inject

/**
 * Created by victor on 10/8/18
 */
open class MapGithubUserEntity @Inject constructor() : ModelMapper<GithubUser, GithubUserEntity> {

    override fun mapFromModel(model: GithubUser) : GithubUserEntity {
        return GithubUserEntity(model.name,model.avatarUrl,model.contributions,model.profileUrl)
    }
}