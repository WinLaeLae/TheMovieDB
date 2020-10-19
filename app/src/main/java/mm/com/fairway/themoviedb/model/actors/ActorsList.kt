package mm.com.fairway.themoviedb.model.actors

data class ActorsList(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)