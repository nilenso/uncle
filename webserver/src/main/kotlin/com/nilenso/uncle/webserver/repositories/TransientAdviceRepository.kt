package com.nilenso.uncle.webserver.repositories

import com.nilenso.uncle.webserver.dto.AdviceDTO

class TransientAdviceRepository : com.nilenso.uncle.webserver.repositories.AdviceRepository {
    private val sageAdvices = mutableListOf(
        "Time is more valuable than money. Once spent, you can never get it back, so use it wisely.",
        "Respect your elders and listen to their experiences. Their wisdom can guide you through life's challenges.",
        "Always be honest and transparent. Integrity is the foundation of trust and respect.",
        "Hard work and perseverance pay off. Success comes to those who are dedicated and persistent.",
        "Save a portion of your earnings, no matter how small. Financial discipline today will ensure security tomorrow.",
        "Keep your word. Your promise is your bond, and breaking it can lead to loss of trust and relationships.",
        "Learn to control your anger. It can cloud your judgment and harm relationships. Patience is a virtue.",
        "Cultivate good habits early in life. They build character and set a strong foundation for the future.",
        "Family is everything. Cherish and support your loved ones, for they are your true strength and comfort.",
        "Never stop learning. Knowledge and skills are your greatest assets, and they grow with time and experience."
    )

    override suspend fun getAdvice(): AdviceDTO {
        return AdviceDTO(sageAdvices.random())
    }

    override suspend fun addAdvice(advice: AdviceDTO) {
        sageAdvices.add(advice.advice)
    }

}