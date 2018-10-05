package com.victor.domain.usecase

import com.victor.domain.executor.Schedulers
import com.victor.domain.gateway.RepositoryGateway
import com.victor.domain.model.GithubRepository
import com.victor.domain.usecase.base.UseCase
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by victor on 10/5/18
 */
class DetailsRepositoryUseCase @Inject constructor(
        private val schedulers: Schedulers,
        private val repositoryGateway: RepositoryGateway
) : UseCase<GithubRepository,DetailsRepositoryUseCase.Params>(schedulers) {

    override fun buildUseCaseObservable(params: Params?): Observable<GithubRepository> {
        if (params == null) throw IllegalArgumentException("Repository Id can't be null")

        return repositoryGateway.getProjectById(params.repoId)
    }

    data class Params(val repoId : Long)
}