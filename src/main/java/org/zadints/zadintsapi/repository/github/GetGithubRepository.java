package org.zadints.zadintsapi.repository.github;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;
import org.zadints.zadintsapi.model.dto.GithubRepositoryDto;

import java.util.Arrays;
import java.util.List;

@Repository
public class GetGithubRepository {
    private final RestClient client;

    public GetGithubRepository(@Qualifier("githubRestClient") RestClient client) {
        this.client = client;
    }

    public GithubRepositoryDto getRepository(String name) {
        return client.get()
                .uri("/repos/{owner}/{repo}", "AugustoDevZ", name)
                .retrieve()
                .body(GithubRepositoryDto.class);
    }

    public List<GithubRepositoryDto> getRepositories() {

        GithubRepositoryDto[] response = client.get()
                .uri("/users/{owner}/repos?per_page=100",
                        "AugustoDevZ")
                .retrieve()
                .body(GithubRepositoryDto[].class);

        return response == null
                ? List.of()
                : Arrays.asList(response);
    }
}
