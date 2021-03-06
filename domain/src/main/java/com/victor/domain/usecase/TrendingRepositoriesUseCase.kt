package com.victor.domain.usecase

import com.victor.domain.executor.Schedulers
import com.victor.domain.gateway.GithubRepositoryGateway
import com.victor.domain.model.GithubRepositoryEntity
import com.victor.domain.usecase.base.UseCase
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by victor on 10/5/18
 */
open class TrendingRepositoriesUseCase @Inject constructor(
        private val schedulers: Schedulers,
        private val repositoryGateway: GithubRepositoryGateway
) : UseCase<List<GithubRepositoryEntity>, Unit>(schedulers) {

    override fun buildUseCaseObservable(params: Unit?): Observable<List<GithubRepositoryEntity>> = repositoryGateway.getProjects()

}