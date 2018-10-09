package com.victor.xapogithubtrending.model

import com.victor.domain.model.GithubUserEntity
import javax.inject.Inject

/**
 * Created by victor on 10/8/18
 */
class EntityUserMapper @Inject constructor() : EntityMapper<GithubUserEntity,GithubUserView>{

    override fun mapFromEntity(model: GithubUserEntity): GithubUserView {
        return GithubUserView(model.name,model.avatarUrl,model.contributions,model.profileUrl)
    }

}