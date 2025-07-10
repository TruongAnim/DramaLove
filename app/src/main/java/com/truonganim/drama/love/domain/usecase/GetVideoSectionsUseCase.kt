package com.truonganim.drama.love.domain.usecase

import com.truonganim.drama.love.domain.model.VideoSection
import com.truonganim.drama.love.domain.repository.VideoRepository
import javax.inject.Inject

class GetVideoSectionsUseCase @Inject constructor(
    private val videoRepository: VideoRepository
) {
    suspend operator fun invoke(): Result<List<VideoSection>> {
        return videoRepository.getVideoSections()
    }
}
