package baby.internal.cosmo.fr.babyscore

data class Result(val teamScoreA: TeamScore, val teamScoreB: TeamScore)

data class TeamScore(val team: Team, val score: Int)

data class Team(val players: List<Player>)

data class Player(val name: String)