package org.icpclive.clics.objects

import org.icpclive.ksp.clics.*

@SinceClics(FeedVersion.`2020_03`)
@UpdateContestEvent
@EventSerialName("judgement-types")
public interface JudgementType {
    @Required public val id: String
    @Required public val name: String
    @Required public val solved: Boolean
    @Required public val penalty: Boolean
}