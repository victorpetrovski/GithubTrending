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
class TrendingRepositoriesUseCase @Inject constructor(
        private val schedulers: Schedulers,
        private val repositoryGateway: RepositoryGateway
) : UseCase<List<GithubRepository>, Nothing>(schedulers) {

    override fun buildUseCaseObservable(params: Nothing?): Observable<List<GithubRepository>> = repositoryGateway.getProjects()

}