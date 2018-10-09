package com.victor.data.mapper

import com.victor.data.model.GithubProjectModel
import com.victor.data.model.GithubUser
import com.victor.domain.model.GithubRepositoryEntity
import javax.inject.Inject

/**
 * Created by victor on 10/5/18
 */
open class MapGithubEntity @Inject constructor() : ModelMapper<GithubProjectModel,GithubRepositoryEntity>{

    override fun mapFromModel(model: GithubProjectModel): GithubRepositoryEntity {
        return GithubRepositoryEntity(model.name,model.link,model.id,model.owner.avatarUrl,model.owner.name, model.stars ,model.description,model.createdAt,model.forkCount,model.watchCount)
    }

}