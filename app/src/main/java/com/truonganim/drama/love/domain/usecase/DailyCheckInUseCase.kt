package com.truonganim.drama.love.domain.usecase

import com.truonganim.drama.love.domain.model.CheckInReward
import com.truonganim.drama.love.domain.repository.UserRepository
import javax.inject.Inject

class DailyCheckInUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<CheckInReward> {
        return userRepository.dailyCheckIn()
    }
}
