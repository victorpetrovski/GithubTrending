package com.victor.xapogithubtrending.model

import com.victor.domain.model.GithubRepositoryEntity
import javax.inject.Inject

/**
 * Created by victor on 10/6/18
 */
class EntityViewMapper @Inject constructor(
        private val entityUserMapper: EntityUserMapper
): EntityMapper<GithubRepositoryEntity,RepositoryView>{

    override fun mapFromEntity(model: GithubRepositoryEntity): RepositoryView {
       return RepositoryView(model.id.toString(),model.repositoryName,model.description,model.stars,
               model.createdAt,model.ownerName,model.ownerAvatar,model.forkCount,model.watchCount,
               model.repoContributors?.map { entityUserMapper.mapFromEntity(it) })
    }

}