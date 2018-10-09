package com.victor.xapogithubtrending.model

import com.victor.domain.model.GithubRepositoryEntity
import javax.inject.Inject

/**
 * Created by victor on 10/6/18
 */
open class EntityViewMapper @Inject constructor(
        private val entityUserMapper: EntityUserMapper
): EntityMapper<GithubRepositoryEntity,RepositoryView>{

    override fun mapFromEntity(entity: GithubRepositoryEntity): RepositoryView {
       return RepositoryView(entity.id.toString(),entity.repositoryName,entity.description,entity.stars,
               entity.createdAt,entity.ownerName,entity.ownerAvatar,entity.forkCount,entity.watchCount,
               entity.repoContributors?.map { entityUserMapper.mapFromEntity(it) })
    }

}